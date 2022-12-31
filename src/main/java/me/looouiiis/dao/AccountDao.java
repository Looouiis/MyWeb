package me.looouiiis.dao;

import me.looouiiis.pojo.AnonymousUser;
import me.looouiiis.pojo.User;
import me.looouiiis.pojo.UserForUpdate;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AccountDao {
    @Select("select id,username,is_me as isMe, female as gender from users where id = #{id}")
    User selectById(@Param("id")int id);
    @Select("select id,username,password,is_me as isMe, female as gender from users where username = #{username}")
    User selectByUsername(@Param("username")String username);
    @Select("select id,username,password,is_me as isMe, female as gender from users")
    List<User> selectAll();
    @Select("select id,username, female as gender from users")
    List<User> selectSimple();
    @Select("select id, is_me as isMe from users where username = #{username} and password = #{password}")
    User selectByPassword(@Param("username")String username, @Param("password")String password);
    @Insert("insert into users(username, password, is_me, female) values(#{username},#{password},#{isMe},#{gender})")
    @Options(useGeneratedKeys = true,keyProperty = "id")
    int insert(User user);
    @Delete("delete from users where username = #{username} and password = #{password}")
    int delete(User user);
    @Select("select id from users where id = #{id} and password = #{oriPassword}")
    User preUpdate(UserForUpdate user);
    @Select("select id from users where username = #{username}")
    User checkUsername(@Param("username")String username);
    int update(UserForUpdate user);
    @Delete("delete from message where user_id = #{userId}")
    int deleteFromMessage(@Param("userId") int userId);
    @Delete("delete from unread_for_me where usr_id = #{userId}")
    int deleteFromMyUnread(@Param("userId") int userId);
    @Delete("delete from unread_for_usr where usr_id = #{userId}")
    int deleteFromUsrUnread(@Param("userId") int userId);
    @Select("select id from anonymous_users")
    List<AnonymousUser> selectAllAno();
}
