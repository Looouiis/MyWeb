package me.looouiiis.service;

import me.looouiiis.pojo.JsonAccountStatus;
import me.looouiiis.pojo.JsonContentReturn;
import me.looouiiis.pojo.User;

import java.util.List;

public interface UserService {
    JsonAccountStatus login(String username, String password);

    JsonAccountStatus register(String username, String password, boolean gender);

    JsonContentReturn selectAll();

    JsonAccountStatus close(String username, String password);

    JsonAccountStatus update(int id, String username, String password, boolean isMe, boolean gender);

    boolean checkPermission(Integer id);

    int checkToken(String token);

    boolean checkIsOutdated(String token);
}
