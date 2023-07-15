package com.cakeshopspringboot.controller;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.dao.OrderDao;
import com.cakeshopspringboot.entity.Goods;
import com.cakeshopspringboot.entity.Order;
import com.cakeshopspringboot.service.GoodsService;
import com.cakeshopspringboot.utils.JWTUtils;
import com.cakeshopspringboot.vo.OrderVo1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private OrderDao orderDao;

    @GetMapping
    public Map<String,Object> pageFindGoods(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        pageNum = (pageNum - 1) * pageSize;
        List<Goods> data = goodsService.findgoods(pageNum, pageSize);
        Integer total = goodsService.selectTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }

    @PutMapping
    public Result updateGoods(@RequestBody Goods goods){
        return goodsService.updateGoods(goods);
    }

    @DeleteMapping("/{id}")
    public Result deleteGoods(@PathVariable Integer id){
        return goodsService.deleteGoods(id);
    }

    @GetMapping("/goodsfind/{id}")
    public List<Goods> findGood(@PathVariable int id){
        System.out.println(id);
        return goodsService.findGoodsbyId(id);
    }
    @GetMapping("/goodsfind")
    public List<Goods> findGoods(){
        return goodsService.findAllGoods();
    }

    @PostMapping("/addOrder")
    public Result addOrder(@RequestBody Order order, @RequestHeader("Authorization") String token){
        Map<String, Object> map = JWTUtils.checkToken(token);
        if(StringUtils.isEmpty(map)){
            return null;
        }
        Date date = new Date(); // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(date);
        System.out.println(order);
        int userid = Integer.parseInt((String) map.get("userId"));
        OrderVo1 orderVo1 = new OrderVo1();
        orderVo1.setTotal(order.getPrice());
        orderVo1.setAmount(order.getAmount());
        orderVo1.setStatus(order.getStatus());
        orderVo1.setPaytype(order.getPaytype());
        orderVo1.setDatetime(format);
        orderVo1.setUserid(userid);
        orderVo1.setGoodsid(order.getId());
        if(orderDao.addOrder(orderVo1)&&orderDao.modifyGoods(order.getStock(),order.getId())){
            return Result.success();
        }
        return Result.error();
    }
}
