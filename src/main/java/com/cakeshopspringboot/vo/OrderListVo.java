package com.cakeshopspringboot.vo;

import lombok.Data;

/**
 * @Author 张伟雄
 * @Date 2022/12/10
 **/
@Data
public class OrderListVo {
    private int id;
    private float total;
    private int amount;
    private String images1;
    private String name;
    private int goodsid;
}
