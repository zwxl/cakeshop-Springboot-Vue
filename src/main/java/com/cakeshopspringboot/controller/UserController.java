package com.cakeshopspringboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.dao.UserDao;
import com.cakeshopspringboot.entity.User;
import com.cakeshopspringboot.service.UserService;
import com.cakeshopspringboot.utils.EmailUtils;
import com.cakeshopspringboot.utils.JWTUtils;
import com.cakeshopspringboot.utils.cosClient;
import com.cakeshopspringboot.utils.uploadUtils;
import com.cakeshopspringboot.vo.EmailVo;
import com.cakeshopspringboot.vo.UserLoginVo;
import com.cakeshopspringboot.vo.UserVo;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 张伟雄
 * @Date 2022/12/1
 **/
@RestController
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @PostMapping("upload")
    public Result uploadAvatar(MultipartFile file){
        // 指定要上传的文件
        String filename = file.getName();
        File localFile = uploadUtils.MultipartFileToFile(file);
//        File localFile = new File("");
        // 指定文件将要存放的存储桶
        String bucketName = "123-1310140370";
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为 folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下
        String key = "12.jpg";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        COSClient cos = cosClient.cos();
        PutObjectResult putObjectResult = cos.putObject(putObjectRequest);
        return Result.success();
    }
    /////////
//@PostMapping("upload")
//public String upload(MultipartFile file){
//    try {
//        if (file.isEmpty()){
//            return "文件为空";
//        }
//        //获取文件名
//        String fileName = file.getOriginalFilename();
//        log.info("上传的文件名："+fileName);
//        //获取文件后缀名
//        String suffixName = fileName.substring(fileName.lastIndexOf("."));
//        log.info("文件后缀名："+suffixName);
//        //设置文件存储路径
//        String filePath = "f:/upload/";
//        String path = filePath+fileName;
//        File dest = new File(path);
//        //检测是否存在该目录
//        if (!dest.getParentFile().exists()){
//            dest.getParentFile().mkdirs();
//        }
//        //写入文件
//        file.transferTo(dest);
//        return "上传成功！";
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//    return "上传失败";
//}
/////////////////
    @GetMapping
    public List<UserVo> index() {
        return userService.findAllUser();
    }
    @PostMapping
    public Integer save(@RequestBody User user) {
        // 新增或者更新
        return userService.save(user);
    }
    // 分页查询
    //  接口路径：/user/page?pageNum=1&pageSize=10
    // @RequestParam接受
    //    limit第一个参数 = (pageNum - 1) * pageSize
    // pageSize
    @GetMapping("/page")
    public Map<String, Object> findPage(@RequestParam Integer pageNum,
                                        @RequestParam Integer pageSize,
                                        @RequestParam String username,
                                        @RequestParam String email,
                                        @RequestParam String address) {
        pageNum = (pageNum - 1) * pageSize;
        username = "%" + username + "%";
        email = "%" + email + "%";
        address = "%" + address + "%";
        List<User> data = userDao.selectPage(pageNum, pageSize, username, email, address);
        Integer total = userDao.selectTotal(username,email,address);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
    @DeleteMapping("{id}")
    public Integer deleteUser(@PathVariable Integer id){
        return userDao.deleteById(id);
    }
    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> ids) {
        return userService.removeByIds(ids);
    }

    //导出为excel表
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception {
        userService.exportExcel(response);
    }
    @PostMapping("/import")
    public Boolean imp(MultipartFile file) throws Exception {
        return userService.importExcel(file);
    }
    @PostMapping("/login")
    public Result dologin(@RequestBody UserLoginVo userLoginVo,@RequestHeader(value="Authorization",required = false) String Authorization){
        String token1 = redisTemplate.opsForValue().get("userid");
        System.out.println("token1"+token1);
        if(StringUtils.isEmpty(token1))
            return userService.UserLogin(userLoginVo.getUsername(),userLoginVo.getPassword());
        if(Authorization.equals("null")){
            return userService.UserLogin(userLoginVo.getUsername(),userLoginVo.getPassword());
        }
        else{
            Result result = userService.TokenLogin(Authorization);
            if(result.getCode().equals("1001")){
                return userService.UserLogin(userLoginVo.getUsername(),userLoginVo.getPassword());
            }else{
                return result;
            }
        }
    }
    @PostMapping("/register")
    public Result doRegister(@RequestBody User user){
        return userService.UserRegister(user);
    }
    @GetMapping("/findUserId")
    public String findUserId(@RequestHeader("Authorization") String token){
        Map<String, Object> map = JWTUtils.checkToken(token);
        if(StringUtils.isEmpty(map)){
            return null;
        }
        int userId = Integer.parseInt((String)map.get("userId"));
        return userDao.findUserName(userId);
    }
    //邮箱验证登录
    @PostMapping("/emaillogin")
    public Result EmailLogin(@RequestBody EmailVo emailVo) throws Exception {
        if(!StringUtils.isEmpty(emailVo)){
            return Result.error("1005","不能出现控制，账号和验证码不能为空");
        }
        String code = EmailUtils.codeFen();
        EmailUtils.testMail("QQ邮箱验证密码",code,emailVo.getEmail());

        return null;
    }
    @GetMapping("/findOneUser")
    public User findOneUser(@RequestHeader("Authorization") String token){
        Map<String, Object> map = JWTUtils.checkToken(token);
        if(StringUtils.isEmpty(map)){
            return null;
        }
        int userid = Integer.parseInt((String) map.get("userId"));
        return userDao.findOneUser(userid);
    }
}
