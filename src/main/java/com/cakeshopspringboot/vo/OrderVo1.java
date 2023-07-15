package com.cakeshopspringboot.vo;

import lombok.Data;

/**
 * @Author 张伟雄
 * @Date 2022/12/19
 **/
@Data
public class OrderVo1 {
    private int id;
    private float total;
    private int amount;
    private int status;
    private int paytype;
    private String datetime;
    private int userid;
    private int goodsid;
}
