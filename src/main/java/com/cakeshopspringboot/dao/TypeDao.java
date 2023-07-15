package com.cakeshopspringboot.dao;

import com.cakeshopspringboot.common.Result;
import com.cakeshopspringboot.entity.Goods;
import com.cakeshopspringboot.entity.Type;
import com.cakeshopspringboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/13
 **/
@Mapper
public interface TypeDao {
    @Select("select * from type")
    List<Type> findTypeName();
    @Select("select * from goods where typeid = #{typeid}")
    List<Goods> findTypeGoods(int typeid);
    @Select("select * from type limit #{pageNum}, #{pageSize}")
    List<Type> pageFind(@Param("pageNum")Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from user")
    Integer selectTotal();

    @Insert("insert into type(typename) values(#{typename})")
    Boolean addType(Type type);

    @Update("update type set typename = #{typename} where id = #{id}")
    Boolean updateType(Type type);

    @Delete("delete from type where id = #{id}")
    Integer deleteType(int id);
}
