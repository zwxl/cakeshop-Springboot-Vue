package com.cakeshopspringboot.controller;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.dao.OrderDao;
import com.cakeshopspringboot.entity.Order;
import com.cakeshopspringboot.service.OrderService;
import com.cakeshopspringboot.utils.JWTUtils;
import com.cakeshopspringboot.vo.OrderListVo;
import com.cakeshopspringboot.vo.OrderVo;
import com.cakeshopspringboot.vo.PageVo;
import com.cakeshopspringboot.vo.gouwucheVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDao orderDao;

    @PostMapping
    public Map<String,Object> pageFindOrder(@RequestBody PageVo pageVo, @RequestHeader("Authorization") String token){
        Map<String, Object> map = JWTUtils.checkToken(token);
        if(StringUtils.isEmpty(map)){
            return null;
        }
        int userId = Integer.parseInt((String) map.get("userId"));
        Integer pageNum = (pageVo.getPageNum() - 1) * pageVo.getPageSize();
//        List<OrderVo> data = orderService.selectOrder(userId,pageNum, pageVo.getPageSize());
//        Integer total = orderService.selectTotal(userId);
        List<OrderVo> data = orderDao.findOrderListByUserId(userId,pageNum,pageVo.getPageSize());
        Integer total = orderDao.selectTotal(userId);
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
    //付款
    @PostMapping("/list")
    public Result orderList(@RequestBody List<Order> order,@RequestHeader("Authorization") String token){
        Map<String, Object> map = JWTUtils.checkToken(token);
        if(StringUtils.isEmpty(map)){
            return null;
        }
        int userid = Integer.parseInt((String) map.get("userId"));
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(date);
        for(Order attribute : order) {
            System.out.println(attribute);
            attribute.setTotal(attribute.getTotal()*attribute.getAmount());
            attribute.setDatetime(format);
            attribute.setUserid(userid);
        }
        if(orderDao.InsertOrder(order)){
            return Result.success();
        }
        return Result.error("1001","fail");
    }
    @GetMapping("/orderlist")
    public List<OrderListVo> findList(@RequestHeader("Authorization") String token){
        Map<String, Object> map = JWTUtils.checkToken(token);
        if(StringUtils.isEmpty(map)){
            return null;
        }
        int userid = Integer.parseInt((String) map.get("userId"));
        return orderDao.find(userid);
    }
    @GetMapping("/updateStatus/{id}")
    public Result updateStatus(@PathVariable int id){
        if(orderDao.updateStatusOrder(id)){
            return Result.success();
        }
        return  Result.error("10010","订单状态修改失败");
    }
    @GetMapping("/updateStatus1/{id}")
    public Result updateStatus1(@PathVariable int id){
        if(orderDao.updateStatusOrder1(id)){
            return Result.success();
        }
        return  Result.error("10010","订单状态修改失败");
    }
    @DeleteMapping("{id}")
    public Result deleteOwnOrder(@PathVariable int id){
        if(orderDao.deleteById(id)){
            return Result.success();
        }
        return  Result.error("10011","订单状态删除失败");
    }
    @PostMapping("/OrderItem")
    public Result OrderItem(@RequestBody List<Order> order){
        int flag = 0;
        for(Order attribute : order) {
            if(orderDao.OrderItem(attribute)){
                flag++;
            }
        }
        if(flag == order.toArray().length){
            return Result.success();
        }
        return Result.error();
    }
}
