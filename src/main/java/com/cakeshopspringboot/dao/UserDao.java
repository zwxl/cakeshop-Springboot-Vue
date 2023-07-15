package com.cakeshopspringboot.dao;

import com.cakeshopspringboot.entity.User;
import com.cakeshopspringboot.vo.UserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 *@Author 张伟雄
 *@Date 2022/12/1
**/
@Mapper
public interface UserDao {
    @Select("SELECT id, username, password, name, email, phone, address from user")
    List<UserVo> findAll();

    @Select("select * from user where username like #{username} or email like #{email} or address like #{address} limit #{pageNum}, #{pageSize}")
    List<User> selectPage(@Param("pageNum")Integer pageNum,@Param("pageSize") Integer pageSize, @Param("username")String username,@Param("email") String email,@Param("address") String address);

    @Select("select count(*) from user where username like concat('%', #{username}, '%') or email like concat('%', #{email}, '%') or address like concat('%', #{address}, '%')")
    Integer selectTotal(@Param("username")String username,@Param("email")String email,@Param("address")String address);

    @Insert("insert into user(username,password,name,email,phone,address) values(#{username},#{password}," +
            "#{name},#{email},#{phone},#{address})")
    int addUser(User user);

    @Delete("delete from user where id = #{id}")
    Integer deleteById(@Param("id") Integer id);

    int updateUser(User user);

    Boolean Batchdelete(List<Integer> list);
    Boolean BatchAddUser(@Param("list") List<UserVo> list);
    //用户登录
    /*
    * 通过用户的密码和
    * */
    Boolean findIsAdmin(@Param("username")String username,@Param("password") String password);
    @Select("select * from user where username = #{username} and password = #{password}")
    User findUserId(@Param("username")String username,@Param("password") String password);

    @Select("select username from user where id = #{id}")
    String findUserName(int id);

    @Select("select * from user where id = #{id}")
    User findOneUser(int id);
}
