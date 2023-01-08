package me.looouiiis.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import me.looouiiis.pojo.Header;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HeaderDao extends BaseMapper<Header> {
}
