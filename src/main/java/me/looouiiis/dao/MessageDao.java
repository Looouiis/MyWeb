package me.looouiiis.dao;

import me.looouiiis.pojo.AnonymousMessage;
import me.looouiiis.pojo.Message;
import me.looouiiis.pojo.Reply_deprecated;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MessageDao {
    @Insert("insert into anonymous_message(ano_id, content, local, date, message) values (#{anoId}, #{content}, #{local}, #{date}, 1)")
    Integer commitAnoMessage(AnonymousMessage message);

    @Insert("insert into message(user_id, content, local, date, message) values (#{userId}, #{content}, #{local}, #{date}, 1)")
    Integer commitMessage(Message message);

    @Insert("insert into anonymous_message(ano_id, content, local, date, message) values (#{anoId}, #{content}, #{local}, #{date}, 0)")
    Integer commitAnoReply(AnonymousMessage reply);

    @Insert("insert into message(user_id, content, local, date, message) values (#{userId}, #{content}, #{local}, #{date}, 0)")
    Integer commitReply(Message reply);

    //    @Select("select ano_id as anoId, comment, local, date from anonymous_message where ano_id = (select id from anonymous_users where mac = #{mac}) order by date DESC limit #{start},#{num}")
    List<AnonymousMessage> selectAnoMessageById(@Param("anoId") int anoId, @Param("start") Integer start, @Param("num") Integer num);

    //    @Select("select user_id as userId, comment, local, date from message where user_id = #{id} order by date DESC limit #{start},#{num}")
    List<Message> selectMessageById(@Param("id") int id, @Param("start") Integer start, @Param("num") Integer num);

    @Select("select id from anonymous_users where mac = #{mac}")
    Integer getAnoIdByMac(@Param("mac") String mac);

    //    @Insert("insert into message(user_id, content, local, date, message) values(#{userId},#{content}, #{local}, #{date}, #{message})")
    Integer insertFromAno(@Param("anoList") List<AnonymousMessage> messages, @Param("userId") Integer id);

    @Delete("delete from anonymous_message where ano_id = #{id}")
    Integer deleteAnoMsgById(@Param("id") int id);

    @Delete("delete from message where user_id = #{id}")
    Integer deleteMsgById(@Param("id") int id);
    @Insert("insert into anonymous_users(mac) values #{mac}")
    Integer createAnoAccount(@Param("mac") String mac);
}
