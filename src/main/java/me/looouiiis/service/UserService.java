package me.looouiiis.service;

import me.looouiiis.pojo.*;

import java.util.HashMap;

public interface UserService {
    JsonAccountStatus login(String username, String password);

    JsonAccountStatus register(User user, Integer anoId);

    JsonContentReturn selectAll(boolean simple);

    JsonAccountStatus closeByPassword(User user);

    JsonAccountStatus update(UserForUpdate user);

    boolean checkPermission(Integer id);

    int checkTokenId(HashMap<String, Object> verify);

    boolean checkIsTrusted(HashMap<String, Object> verify);

    boolean checkIsOutdated(HashMap<String, Object> verify);

    HashMap<String, Object> verify(String token);

    JsonAccountStatus refresh(RefreshRequest refresh);
    JsonContentReturn loginWithToken(String token);
}
