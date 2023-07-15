package com.cakeshopspringboot.service.impl;

import com.cakeshopspringboot.dao.OrderDao;
import com.cakeshopspringboot.service.OrderService;
import com.cakeshopspringboot.vo.OrderVo;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    @Override
    public List<OrderVo> selectOrder(int userId, Integer pageNum, Integer pageSize) {
        return orderDao.findOrderListByUserId(userId,pageNum,pageSize);
    }



    @Override
    public Integer selectTotal(int userId) {
        return orderDao.selectTotal(userId);
    }
}
