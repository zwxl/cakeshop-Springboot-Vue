package com.cakeshopspringboot.service;

import com.cakeshopspringboot.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;

import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@Mapper
public interface OrderService {
    List<OrderVo> selectOrder(int userId, Integer pageNum, Integer pageSize);
    Integer selectTotal(int userId);
}
