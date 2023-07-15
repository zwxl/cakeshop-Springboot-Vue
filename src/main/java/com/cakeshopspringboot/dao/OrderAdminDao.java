package com.cakeshopspringboot.dao;
import com.cakeshopspringboot.entity.OrderAdminVo;
import com.cakeshopspringboot.vo.OrderItemVo;
import org.apache.ibatis.annotations.*;
import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/17
 **/
@Mapper
public interface OrderAdminDao {
    @Select("select orderlist.id,total,status,datetime,user.name,phone,address,goods.name as name1 from orderlist,user,goods WHERE goodsid = goods.id AND userid = user.id and user.name like #{name} limit #{pageNum}, #{pageSize}")
    List<OrderAdminVo> selectPage(@Param("pageNum")Integer pageNum, @Param("pageSize") Integer pageSize, @Param("name")String name);

    @Select("select count(*) from orderlist,user,goods WHERE goodsid = goods.id AND userid = user.id and user.name like #{name}")
    Integer selectTotal(@Param("name")String name);

    @Delete("delete from orderlist where id = #{id}")
    Boolean deleteOrder(@Param("id")int id);
    @Update("update orderlist,user,goods set user.name = #{name},phone = #{phone},address = #{address} WHERE goodsid = goods.id AND userid = user.id and orderlist.id = #{id}")
    Boolean updateOrderList(OrderItemVo orderItemVo);

    @Update("update orderlist set status = 5 WHERE id = #{id}")
    Boolean updateOrderStatus(@Param("id") int id);

    @Update("update orderlist set status = 3 WHERE id = #{id}")
    Boolean updateOrderStatus1(@Param("id") int id);

}
