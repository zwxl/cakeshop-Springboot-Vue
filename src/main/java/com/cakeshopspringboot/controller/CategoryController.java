package com.cakeshopspringboot.controller;

import com.cakeshopspringboot.dao.GoodsDao;
import com.cakeshopspringboot.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/10
 **/
@RestController("/category")
public class CategoryController {
    @Autowired
    private GoodsDao goodsDao;
//    @GetMapping("/{id}")
    @GetMapping
    public List<GoodsVo> findAllGoods(){
        return goodsDao.findGoods();
    }
}
