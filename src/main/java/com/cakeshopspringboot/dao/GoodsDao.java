package com.cakeshopspringboot.dao;

import com.cakeshopspringboot.entity.Goods;
import com.cakeshopspringboot.vo.GoodsVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author 张伟雄
 * @Date 2022/12/6
 **/
@Mapper
public interface GoodsDao {
    @Select("select id,name,images1,price,intro,stock,typeid from goods limit #{pageNum}, #{pageSize}")
    List<Goods> findPageGoods(@Param("pageNum")Integer pageNum, @Param("pageSize") Integer pageSize);

    @Select("select count(*) from goods")
    Integer selectTotal();

    Boolean updateGoods(Goods goods);

    @Delete("delete from goods where id = #{id}")
    Boolean deleteGoods(Integer id);

    @Select("select name,images1,price from goods")
    List<GoodsVo> findGoods();

    @Select("select goods.id,name,price,images1,intro,stock,typeid from goods where id = #{id}")
    List<Goods> findGoodsById(int id);

    @Select("select goods.id,name,price,images1,intro,stock,typeid from goods")
    List<Goods> findAllGoods();
}
