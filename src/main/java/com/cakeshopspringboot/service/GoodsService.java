package com.cakeshopspringboot.service;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.entity.Goods;

import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
public interface GoodsService {
    List<Goods> findgoods(Integer pageNum,Integer pageSize);
    Integer selectTotal();

    Result updateGoods(Goods goods);

    Result deleteGoods(Integer id);

    List<Goods> findGoodsbyId(int id);

    List<Goods> findAllGoods();
}
