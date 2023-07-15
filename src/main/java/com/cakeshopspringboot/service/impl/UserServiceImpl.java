package com.cakeshopspringboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSON;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.cakeshopspringboot.common.Constants;
import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.dao.UserDao;
import com.cakeshopspringboot.entity.User;
import com.cakeshopspringboot.service.UserService;
import com.cakeshopspringboot.utils.JWTUtils;
import com.cakeshopspringboot.vo.RegisterVo;
import com.cakeshopspringboot.vo.UserLoginVo;
import com.cakeshopspringboot.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author 张伟雄
 * @Date 2022/12/2
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Override
    //增加用户和修改用户
    public int save(User user) {
        if(user.getId()==0){
           return userDao.addUser(user);
        }else{
            return userDao.updateUser(user);
        }
    }

    @Override
    public Boolean removeByIds(List<Integer> list) {

        return userDao.Batchdelete(list);
    }

    @Override
    public List<UserVo> findAllUser() {
        List<UserVo> all = userDao.findAll();
        return all;
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {
        // 从数据库查询出所有的数据
        List<UserVo> list = userDao.findAll();
        // 通过工具类创建writer 写出到磁盘路径
//        ExcelWriter writer = ExcelUtil.getWriter(filesUploadPath + "/用户信息.xlsx");
        // 在内存操作，写出到浏览器
        ExcelWriter writer = ExcelUtil.getWriter(true);
        //自定义标题别名
        writer.addHeaderAlias("id", "用户编号");
        writer.addHeaderAlias("username", "用户名");
        writer.addHeaderAlias("password", "密码");
        writer.addHeaderAlias("name", "真实姓名");
        writer.addHeaderAlias("email", "邮箱");
        writer.addHeaderAlias("phone", "电话");
        writer.addHeaderAlias("address", "地址");

        // 一次性写出list内的对象到excel，使用默认样式，强制输出标题
        writer.write(list, true);

        // 设置浏览器响应的格式
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("用户信息", "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        out.close();
        writer.close();
    }

    @Override
    public Boolean importExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        // 方式1：(推荐) 通过 javabean的方式读取Excel内的对象，但是要求表头必须是英文，跟javabean的属性要对应起来
//        List<User> list = reader.readAll(User.class);

        // 方式2：忽略表头的中文，直接读取表的内容
        List<List<Object>> list = reader.read(1);
        List<UserVo> users = CollUtil.newArrayList();
        for (List<Object> row : list) {
            UserVo user = new UserVo();
            user.setUsername(row.get(0).toString());
            user.setPassword(row.get(1).toString());
            user.setName(row.get(2).toString());
            user.setEmail(row.get(3).toString());
            user.setPhone(row.get(4).toString());
            user.setAddress(row.get(5).toString());
            users.add(user);
        }
        userDao.BatchAddUser(users);
        return true;
    }

    @Override
    public Result UserLogin(String username ,String password) {
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return Result.error("102","用户名和密码不能为空！");
        }
        if(username==null||password==null){
            return Result.error("102","用户名和密码不能为空！");
        }
        User user = userDao.findUserId(username,password);

        if(user==null){
             return Result.error("101","你还没有注册，请先注册");
        }
        if(!user.getIsvalidate()){
            return Result.error("103","你的账户已经失效！");
        }
        String token = JWTUtils.createToken(String.valueOf(user.getId()));
        redisTemplate.opsForValue().set("userid", String.valueOf(user.getId()),1,TimeUnit.DAYS);
        if(user.getIsadmin()){
            return Result.success(token);
        }else{
            return Result.success(token);
        }

    }

    @Override
    public Result TokenLogin(String token) {
        Map<String, Object> map = JWTUtils.checkToken(token);
        if(StringUtils.isEmpty(map)){
            return Result.error("1001","校验失败");
        }
        String userId = (String)map.get("userId");
        String token1 = redisTemplate.opsForValue().get("userid");
        if(StringUtils.isEmpty(token1)){
            return Result.error("1001","校验失败");
        }
        if(userId.equals(token1)){
            System.out.println("success");
            return Result.success();
        }
        System.out.println("errorend");
        return Result.error("1001","校验失败");
    }

    @Override
    public Result UserRegister(User user) {
        User userId = userDao.findUserId(user.getUsername(), user.getPassword());
        if(StringUtils.isEmpty(userId)){
            int i = userDao.addUser(user);
            return Result.success();
        }else{
            return Result.error("100","用户已经注册");
        }
    }

    @Override
    public String FindUserName(int userid) {
        return userDao.findUserName(userid);
    }

}
