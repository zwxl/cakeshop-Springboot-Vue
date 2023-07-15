package com.cakeshopspringboot.service;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.entity.User;
import com.cakeshopspringboot.vo.RegisterVo;
import com.cakeshopspringboot.vo.UserLoginVo;
import com.cakeshopspringboot.vo.UserVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/2
 **/
public interface UserService {
     int save(User user);
     Boolean removeByIds(List<Integer> list);
     List<UserVo> findAllUser();
     void exportExcel(HttpServletResponse response) throws IOException;
     Boolean importExcel(MultipartFile file) throws IOException;
     Result UserLogin(String username,String password);
     Result TokenLogin(String token);
     Result UserRegister(User user);
     String FindUserName(int userid);
}
