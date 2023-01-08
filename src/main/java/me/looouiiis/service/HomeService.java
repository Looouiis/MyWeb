package me.looouiiis.service;

import me.looouiiis.pojo.Home;
import me.looouiiis.pojo.JsonContentReturn;

import java.util.List;

public interface HomeService {
    List<Home> getHomeList();
    boolean updateSingle(Home home);
    boolean deleteById(int id);
    boolean insert(Home home);
}
