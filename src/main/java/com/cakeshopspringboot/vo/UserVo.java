package com.cakeshopspringboot.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author 张伟雄
 * @Date 2022/12/3
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {
        private int id;
        private String username;
        private String password;
        private String name;
        private String email;
        private String phone;
        private String address;
}
