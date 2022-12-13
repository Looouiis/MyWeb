package me.looouiiis.service;

public interface UserService {
    String login(String username, String password);
    String  register(String username, String password, boolean isMe, boolean gender);
    boolean close(String username, String password);
}
