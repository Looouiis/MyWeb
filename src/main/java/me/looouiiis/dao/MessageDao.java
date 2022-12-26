package me.looouiiis.dao;

import me.looouiiis.pojo.*;
import org.apache.ibatis.annotations.*;

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
    @Select("select count(content) from anonymous_message where ano_id = #{anoId}")
    Integer getAnoMessageTotalNum(@Param("anoId") int anoId);

    //    @Select("select user_id as userId, comment, local, date from message where user_id = #{id} order by date DESC limit #{start},#{num}")
    List<Message> selectMessageById(@Param("id") int id, @Param("start") Integer start, @Param("num") Integer num);
    @Select("select count(content) from message where user_id = #{usrId}")
    Integer getUsrMessageTotalNum(@Param("usrId") int usrId);
    @Select("select id from anonymous_users where mac = #{mac}")
    Integer getAnoIdByMac(@Param("mac") String mac);

    //    @Insert("insert into message(user_id, content, local, date, message) values(#{userId},#{content}, #{local}, #{date}, #{message})")
    Integer insertFromAno(@Param("anoList") List<AnonymousMessage> messages, @Param("userId") Integer id);

    @Delete("delete from anonymous_message where ano_id = #{id}")
    Integer deleteAnoMsgById(@Param("id") int id);

    @Delete("delete from message where user_id = #{id}")
    Integer deleteMsgById(@Param("id") int id);
    @Insert("insert into anonymous_users(mac) values(#{mac})")
    Integer createAnoAccount(@Param("mac") String mac);


    @Select("select ano_id as anoId, usr_id as usrId, num from unread_for_me")
    List<MyUnread> checkMyUnread();
    @Select("select ano_id as anoId, num from unread_for_me where ano_id = #{id}")
    MyUnread checkAnoExist(@Param("id") int id);
    @Select("select usr_id as usrId, num from unread_for_me where usr_id = #{id}")
    MyUnread checkUsrExist(@Param("id") int id);
    @Select("select ano_id as anoId, num from unread_for_ano where ano_id = #{id}")
    AnoUnread checkAnoUnread(@Param("id") int id);
    @Select("select usr_id as userId, num from unread_for_usr where usr_id = #{id}")
    UsrUnread checkUsrUnread(@Param("id") int id);

    @Insert("insert into unread_for_me(ano_id, num) values(#{anoId},#{num})")
    Integer insertMyAnoUnread(@Param("anoId") int anoId, @Param("num") int num);
    @Insert("insert into unread_for_me(usr_id, num) values(#{usrId},#{num})")
    Integer insertMyUsrUnread(@Param("usrId") int usrId, @Param("num") int num);
    @Insert("insert into unread_for_ano(ano_id, num) values(#{anoId},#{num})")
    Integer insertAnoUnread(@Param("anoId") int anoId, @Param("num") int num);
    @Insert("insert into unread_for_usr(usr_id, num) values(#{usrId},#{num})")
    Integer insertUsrUnread(@Param("usrId") int usrId, @Param("num") int num);

    @Update("update unread_for_me set num = #{num} where ano_id = #{anoId}")
    Integer updateMyAnoUnread(@Param("anoId") int anoId, @Param("num") int num);
    @Update("update unread_for_me set num = #{num} where usr_id = #{usrId}")
    Integer updateMyUsrUnread(@Param("usrId") int usrId, @Param("num") int num);
    @Update("update unread_for_ano set num = #{num} where ano_id = #{anoId}")
    Integer updateAnoUnread(@Param("anoId") int anoId, @Param("num") int num);
    @Update("update unread_for_usr set num = #{num} where usr_id = #{usrId}")
    Integer updateUsrUnread(@Param("usrId") int usrId, @Param("num") int num);


    @Delete("delete from unread_for_me where ano_id = #{anoId}")
    Integer deleteMyAnoUnread(@Param("anoId") int anoId);
    @Delete("delete from unread_for_me where usr_id = #{usrId}")
    Integer deleteMyUsrUnread(@Param("usrId") int usrId);
    @Delete("delete from unread_for_ano where ano_id = #{anoId}")
    Integer deleteAnoUnread(@Param("anoId") int anoId);
    @Delete("delete from unread_for_usr where usr_id = #{usrId}")
    Integer deleteUsrUnread(@Param("usrId") int usrId);

    @Delete("delete from anonymous_users where id = #{id}")
    Integer deleteAnoUsr(@Param("id") int id);

    @Insert("insert into ano_comment(msg_id, content, date, local, message) values(#{msgId}, #{content}, #{date}, #{local}, 1)")
    Integer addAnoComment(AnoComment comment);
    @Delete("delete from ano_comment where msg_id = #{msgId}")
    Integer delAnoComment(@Param("msgId") int msgId);
    @Select("select msg_id as msgId, content, date, local, message from ano_comment where msg_id = #{msgId}")
    List<AnoComment> selectAnoCommentByMsgId(@Param("msgId") int msgId);
    @Insert("insert into ano_comment(msg_id, content, date, local, message) values(#{msgId}, #{content}, #{date}, #{local}, 0)")
    Integer addAnoCommentReply(AnoComment comment);

    @Insert("insert into usr_comment(msg_id, content, date, local, message) values(#{msgId}, #{content}, #{date}, #{local}, 1)")
    Integer addUsrComment(UsrComment comment);
    @Delete("delete from usr_comment where msg_id = #{msgId}")
    Integer delUsrComment(@Param("msgId") int msgId);
    @Select("select msg_id as msgId, content, date, local, message from usr_comment where msg_id = #{msgId}")
    List<UsrComment> selectUsrCommentByMsgId(@Param("msgId") int msgId);
    @Insert("insert into usr_comment(msg_id, content, date, local, message) values(#{msgId}, #{content}, #{date}, #{local}, 0)")
    Integer addUsrCommentReply(UsrComment comment);

    @Select("select * from anonymous_message where id = #{msgId}")
    AnonymousMessage selectAnoMSgById(@Param("msgId") int msgId);
    @Select("select * from message where id = #{msgId}")
    Message selectUsrMSgById(@Param("msgId") int msgId);

    @Select("select ano_id from anonymous_message where id = #{msgId}")
    int getAnoIdByMsgId(@Param("msgId") int msgId);
    @Select("select user_id from message where id = #{msgId}")
    int getUsrIdByMsgId(@Param("msgId") int msgId);
}
