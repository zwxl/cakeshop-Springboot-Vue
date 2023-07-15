package com.cakeshopspringboot.controller;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.entity.Goods;
import com.cakeshopspringboot.entity.Type;
import com.cakeshopspringboot.entity.User;
import com.cakeshopspringboot.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author 张伟雄
 * @Date 2022/12/11
 **/
@RestController
@RequestMapping("/type")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping
    public List<Type> getTypeName(){
        return typeService.findAllTypeName();
    }

    @GetMapping("typefind/{id}")
    public List<Goods> getTypeGoods(@PathVariable int id){
        return typeService.findGoodsById(id);
    }

    @GetMapping("/page")
    public Map<String, Object> pageType(@RequestParam Integer pageNum, @RequestParam Integer pageSize){
        pageNum = (pageNum - 1) * pageSize;
        List<Type> data = typeService.pageFindType(pageNum, pageSize);
        Integer total = typeService.selectTotal();
        Map<String, Object> res = new HashMap<>();
        res.put("data", data);
        res.put("total", total);
        return res;
    }
    @PostMapping
    public Result addType(@RequestBody Type type){
        if(type.getId()==0){
            return typeService.addType(type);
        }
        else{
            return typeService.updateType(type);
        }
    }

    @DeleteMapping("{id}")
    public Integer deleteUser(@PathVariable Integer id){

        return typeService.deleteType(id);
    }

}
