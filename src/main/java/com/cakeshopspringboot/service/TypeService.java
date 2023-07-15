package com.cakeshopspringboot.service;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.entity.Goods;
import com.cakeshopspringboot.entity.Type;

import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/13
 **/
public interface TypeService {
     List<Type> findAllTypeName();

     List<Goods> findGoodsById(int id);

    List<Type> pageFindType(Integer pageNum, Integer pageSize);

    Integer selectTotal();

    Result addType(Type type);
    Result updateType(Type type);
    Integer deleteType(int id);
}
