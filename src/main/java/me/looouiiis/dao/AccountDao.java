package me.looouiiis.dao;

import me.looouiiis.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AccountDao {
    List<User> selectByUsername(@Param("username")String username);
    @Select("select id from users where username = #{username} and password = #{password}")
    User selectByPassword(@Param("username")String username, @Param("password")String password);
    @Insert("insert into users(username, password, is_me, female) values(#{username},#{password},#{isMe},#{gender})")
    int insert(@Param("username")String username, @Param("password")String password, @Param("isMe")boolean isMe, @Param("gender")boolean female);
    @Delete("delete from users where username = #{username} and password = #{password}")
    int delete(@Param("username")String username, @Param("password")String password);
}
