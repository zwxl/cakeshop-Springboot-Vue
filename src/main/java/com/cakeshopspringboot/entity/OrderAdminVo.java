package com.cakeshopspringboot.entity;

import lombok.Data;

/**
 * @Author 张伟雄
 * @Date 2022/12/17
 **/
@Data
public class OrderAdminVo {
    private int id;
    private float total;
    private String datetime;
    private String name;
    private String phone;
    private int status;
    private String address;
    private String name1;
}
