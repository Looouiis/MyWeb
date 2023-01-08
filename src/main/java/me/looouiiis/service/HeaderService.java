package me.looouiiis.service;

import me.looouiiis.pojo.Header;

public interface HeaderService {
    Header select();
    boolean update(Header header);
}
