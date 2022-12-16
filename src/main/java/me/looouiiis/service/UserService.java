package me.looouiiis.service;

import me.looouiiis.pojo.User;

import java.util.List;

public interface UserService {
    String login(String username, String password);
    String  register(String username, String password, boolean gender);
    String selectAll();
    String close(String username, String password);
    String update(int id, String username, String password, boolean isMe, boolean gender);
    boolean checkPermission(Integer id);
    int checkToken(String token);
    boolean checkIsOutdated(String token);
}
