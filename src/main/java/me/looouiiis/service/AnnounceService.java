package me.looouiiis.service;

import java.util.HashMap;

public interface AnnounceService {
    Integer insert(String message, Integer id);
    Integer insert(String message);
    HashMap<String, Object> select(Integer start, Integer num);
    Integer delete(int[] ids);
    String getMyName();
}
