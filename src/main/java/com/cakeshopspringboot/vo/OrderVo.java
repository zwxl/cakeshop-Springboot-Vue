package com.cakeshopspringboot.vo;

import lombok.Data;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@Data
public class OrderVo {
    private int id;
    private String name;
    private float total;
    private int amount;
    private int status;
}
