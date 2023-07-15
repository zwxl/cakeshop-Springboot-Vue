package com.cakeshopspringboot.controller;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.dao.OrderAdminDao;
import com.cakeshopspringboot.entity.Order;
import com.cakeshopspringboot.entity.OrderAdminVo;
import com.cakeshopspringboot.entity.User;
import com.cakeshopspringboot.service.OrderAdminService;
import com.cakeshopspringboot.vo.OrderItemVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 张伟雄
 * @Date 2022/12/17
 **/
@RestController
@RequestMapping("/orderadmin")
public class OrderAdminController {
    @Autowired
    private OrderAdminDao orderAdminDao;
    @Autowired
    private OrderAdminService orderAdminService;

    @GetMapping("/pagefind")
    public Map<String, Object> findPage(@RequestParam Integer pageNum, @RequestParam Integer pageSize, @RequestParam String name) {
        name = "%" + name + "%";
        pageNum = (pageNum - 1) * pageSize;
        List<OrderAdminVo> data = orderAdminDao.selectPage(pageNum, pageSize,name);
        Integer total = orderAdminDao.selectTotal(name);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @PostMapping
    public Result editOrderAdmin(@RequestBody OrderItemVo orderItemVo){
        if(StringUtils.isEmpty(orderItemVo)){
            return Result.error("10008","不能为空");
        }
        return orderAdminService.updateOrderAdmin(orderItemVo);
    }

    @GetMapping("/checkOrder/{id}")
    public Result checkOrder(@PathVariable int id){
        if(orderAdminDao.updateOrderStatus(id)){
            return Result.success();
        }
        return  Result.error("10011","订单审核失败");
    }

    @GetMapping("/shipments/{id}")
    public Result shipments(@PathVariable int id){
        if(orderAdminDao.updateOrderStatus1(id)){
            return Result.success();
        }
        return  Result.error("10012","发货失败");
    }

}
