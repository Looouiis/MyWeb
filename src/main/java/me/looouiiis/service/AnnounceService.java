package me.looouiiis.service;

import me.looouiiis.pojo.Announce;

import java.util.HashMap;
import java.util.List;

public interface AnnounceService {
    Integer insert(String message, Integer id);
    HashMap<String, Object> select(Integer start, Integer num);
    Integer delete(int[] ids);
}
