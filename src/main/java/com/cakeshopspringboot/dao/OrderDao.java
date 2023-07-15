package com.cakeshopspringboot.dao;

import com.cakeshopspringboot.entity.Order;
import com.cakeshopspringboot.vo.OrderListVo;
import com.cakeshopspringboot.vo.OrderVo;
import com.cakeshopspringboot.vo.OrderVo1;
import com.cakeshopspringboot.vo.gouwucheVo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/

@Mapper
public interface OrderDao {

    @Update("update orderlist set amount =  amount+1 where id = #{id}")
    Boolean addOrderNum(int num,int id);

    @Update("update orderlist set amount =  amount-1 where id = #{id}")
    Boolean subOrderNum(int num,int id);

    @Select("select amount from orderlist where id = #{id}")
    int findamount(int id);

    @Insert("insert into orderlist(total,amount,status,paytype,datetime,userid,goodsid) values(#{total},#{amount},#{status},#{paytype},#{datetime},#{userid},#{goodsid})")
    Bool Insertorder(Order order);

//    @Select("select id,total,amount,status from orderlist where userid = #{userid} limit #{pageNum}, #{pageSize}")
    @Select("select orderlist.id,total,amount,status,name from orderlist,goods WHERE orderlist.goodsid = goods.id and userid = #{userid} limit #{pageNum}, #{pageSize}")
    List<OrderVo> findOrderListByUserId(@Param("userid") int userid,@Param("pageNum") Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from orderlist where userid = #{userid}")
    Integer selectTotal(@Param("userid") int userid);

    Boolean InsertOrder(List<Order> order);

    @Select("SELECT orderlist.id,total,amount,images1,name,goods.id as goodsid from orderlist,goods WHERE orderlist.goodsid = goods.id AND status = 0 and userid=#{userid} ")
    List<OrderListVo> find(int userid);

    @Update("update orderlist set status = 1 WHERE id = #{id}")
    Boolean updateStatusOrder(@Param("id") int id);

    @Update("update orderlist set status = 4 WHERE id = #{id}")
    Boolean updateStatusOrder1(@Param("id") int id);

    @Delete("delete from orderlist where id = #{id}")
    Boolean deleteById(@Param("id") Integer id);

    @Insert("insert into orderlist(total,amount,status,paytype,datetime,userid,goodsid) values (#{total},#{amount},#{status},#{paytype},#{datetime},#{userid},#{goodsid})")
    Boolean addOrder(OrderVo1 order);

    @Update("update goods set stock = #{stock} where id = #{id}")
    Boolean modifyGoods(@Param("stock") int stock,@Param("id") int id);


    Boolean OrderItem(Order order);
}
