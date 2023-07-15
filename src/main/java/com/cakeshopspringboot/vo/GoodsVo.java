package com.cakeshopspringboot.vo;

import lombok.Data;

/**
 * @Author 张伟雄
 * @Date 2022/12/10
 **/
@Data
public class GoodsVo {
    private int id;
    private String goodsname;
    private String images1;
    private float price;
    private String intro;
}
