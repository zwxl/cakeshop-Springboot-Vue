package com.cakeshopspringboot.vo;

import lombok.Data;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@Data
public class RegisterVo {
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
}
