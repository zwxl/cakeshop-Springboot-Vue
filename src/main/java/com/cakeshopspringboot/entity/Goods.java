package com.cakeshopspringboot.entity;

import lombok.Data;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@Data
public class Goods {
    private int id;
    private String name;
    private int amount;
    private float price;
    private String intro;
    private String images1;
    private String stock;
    private String typeid;
}
