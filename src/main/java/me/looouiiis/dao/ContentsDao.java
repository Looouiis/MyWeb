package me.looouiiis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.looouiiis.pojo.Contents;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ContentsDao extends BaseMapper<Contents> {
}
