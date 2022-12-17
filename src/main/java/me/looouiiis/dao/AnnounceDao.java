package me.looouiiis.dao;

import me.looouiiis.pojo.Announce;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface AnnounceDao {
    @Insert("insert into announce(content, local, date) values(#{content}, #{local}, #{date})")
    Integer insert(Announce announce);
    @Update("update announce set content = #{msg} where id = #{id}")
    Integer update(@Param("msg") String message, int id);
    @Select("select content, local, date from announce where id = #{id}")
    Announce selectById(@Param("id") int id);
    List<Announce> select(@Param("start") Integer start, @Param("num") Integer num);
    @Select("select count(content) from announce")
    Integer getTotalCount();
    Integer delete(@Param("ids")int[] ids);
}
