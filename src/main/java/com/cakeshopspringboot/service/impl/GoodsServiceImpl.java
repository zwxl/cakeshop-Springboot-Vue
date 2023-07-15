package com.cakeshopspringboot.service.impl;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.dao.GoodsDao;
import com.cakeshopspringboot.entity.Goods;
import com.cakeshopspringboot.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Override
    public List<Goods> findgoods(Integer pageNum, Integer pageSize) {
        return goodsDao.findPageGoods(pageNum,pageSize);
    }

    @Override
    public Integer selectTotal() {
        return goodsDao.selectTotal();
    }

    @Override
    public Result updateGoods(Goods goods) {
        Boolean isgoods = goodsDao.updateGoods(goods);
        if(isgoods){
            return Result.success();
        }
        return Result.error("110","修改商品操作失败");
    }

    @Override
    public Result deleteGoods(Integer id) {
        Boolean flag = goodsDao.deleteGoods(id);
        if(flag){
            return Result.success();
        }
        return Result.error("112","删除操作失败");
    }

    @Override
    public List<Goods> findGoodsbyId(int id) {
        return goodsDao.findGoodsById(id);
    }

    @Override
    public List<Goods> findAllGoods() {
        return goodsDao.findAllGoods();
    }
}
