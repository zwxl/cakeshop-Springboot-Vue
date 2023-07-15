package com.cakeshopspringboot.service.impl;
import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.dao.OrderAdminDao;
import com.cakeshopspringboot.service.OrderAdminService;
import com.cakeshopspringboot.vo.OrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author 张伟雄
 * @Date 2022/12/17
 **/
@Service
public class OrderAdminServiceImpl implements OrderAdminService {
    @Autowired
    private OrderAdminDao orderAdminDao;
    @Override
    public Result deleteOrderAdmin(int id) {
        if(orderAdminDao.deleteOrder(id)){
            return Result.success();
        }
        return Result.error("10009","删除失败");
    }
    @Override
    public Result updateOrderAdmin(OrderItemVo orderItemVo) {
        if(orderAdminDao.updateOrderList(orderItemVo)){
            return Result.success();
        }
        return Result.error("10009","修改失败");
    }
}
