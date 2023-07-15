package com.cakeshopspringboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 张伟雄
 * @Date 2022/12/1
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Boolean isadmin;
    private Boolean isvalidate;
}
