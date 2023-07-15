package com.cakeshopspringboot.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@Data
public class Order implements Serializable {
    private int id;
    private float total;
    private float price;
    private int stock;
    private int amount;
    private int status;
    private int paytype;
    private String datetime;
    private int userid;
    private int goodsid;
}
