package me.looouiiis.dao;

import me.looouiiis.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountDao {
    @Select("select is_me as isMe from users where id = #{id}")
    User selectById(@Param("id")int id);
    List<User> selectByUsername(@Param("username")String username);
    @Select("select id from users where username = #{username} and password = #{password}")
    User selectByPassword(@Param("username")String username, @Param("password")String password);
    @Insert("insert into users(username, password, is_me, female) values(#{username},#{password},#{isMe},#{gender})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(User user);
    @Delete("delete from users where username = #{username} and password = #{password}")
    int delete(@Param("username")String username, @Param("password")String password);
    int update(User user);
}
