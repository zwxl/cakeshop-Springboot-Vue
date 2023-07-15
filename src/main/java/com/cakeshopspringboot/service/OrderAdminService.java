package com.cakeshopspringboot.service;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.entity.OrderAdminVo;
import com.cakeshopspringboot.vo.OrderItemVo;

/**
 * @Author 张伟雄
 * @Date 2022/12/17
 **/

public interface OrderAdminService {
    Result deleteOrderAdmin(int id);
    Result updateOrderAdmin(OrderItemVo orderItemVo);
}
