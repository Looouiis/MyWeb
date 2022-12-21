package me.looouiiis.service;

import me.looouiiis.pojo.JsonAccountStatus;
import me.looouiiis.pojo.JsonContentReturn;
import me.looouiiis.pojo.User;
import me.looouiiis.pojo.UserForUpdate;

public interface UserService {
    JsonAccountStatus login(String username, String password);

    JsonAccountStatus register(User user);

    JsonContentReturn selectAll();

    JsonAccountStatus closeByPassword(User user);

    JsonAccountStatus update(UserForUpdate user);

    boolean checkPermission(Integer id);

    int checkToken(String token);

    boolean checkIsOutdated(String token);
}
