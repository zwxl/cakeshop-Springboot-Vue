package com.cakeshopspringboot.service.impl;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.dao.GoodsDao;
import com.cakeshopspringboot.dao.TypeDao;
import com.cakeshopspringboot.entity.Goods;
import com.cakeshopspringboot.entity.Type;
import com.cakeshopspringboot.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/13
 **/
@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao typeDao;
    @Autowired
    private GoodsDao goodsDao;
    @Override
    public List<Type> findAllTypeName() {
        return typeDao.findTypeName();
    }

    @Override
    public List<Goods> findGoodsById(int id) {
        return typeDao.findTypeGoods(id);
    }

    @Override
    public List<Type> pageFindType(Integer pageNum, Integer pageSize) {
        return typeDao.pageFind(pageNum,pageSize);
    }

    @Override
    public Integer selectTotal() {
        return typeDao.selectTotal();
    }

    @Override
    public Result addType(Type type) {
        Boolean ty = typeDao.addType(type);
        if(ty){
            return Result.success();
        }else{
            return Result.error("1006","增加失败");
        }
    }

    @Override
    public Result updateType(Type type) {
        Boolean ty = typeDao.updateType(type);
        if(ty){
            return Result.success();
        }else{
            return Result.error("1006","增加失败");
        }
    }

    @Override
    public Integer deleteType(int id) {
        return typeDao.deleteType(id);
    }
}
