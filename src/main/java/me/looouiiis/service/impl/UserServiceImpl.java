package me.looouiiis.service.impl;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import me.looouiiis.dao.AccountDao;
import me.looouiiis.pojo.JsonAccountStatus;
import me.looouiiis.pojo.User;
import me.looouiiis.service.UserService;
import me.looouiiis.utils.TokenOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    private AccountDao accountDao;

    @Override
    public String login(String username, String password) {
        User user = accountDao.selectByPassword(username, password);
        JsonAccountStatus status = new JsonAccountStatus();
        status.setMethod("login");
        if (user != null) {
            status.setToken(TokenOperator.generate(user.getId()));
            status.setStatus(true);
        } else {
            status.setToken("");
            status.setStatus(false);
        }
        return JSON.toJSONString(status);
    }

    @Override
    public String register(String username, String password, boolean isMe, boolean gender) {
        List<User> users = accountDao.selectByUsername(username);
        JsonAccountStatus status = new JsonAccountStatus();
        status.setMethod("register");
        if (users.size() == 0) {
            User user = new User(0,username,password,isMe,gender);
            int res = accountDao.insert(user);
            if (res != 0) {
                status.setToken(TokenOperator.generate(user.getId()));
                status.setStatus(true);
            } else {
                status.setToken("");
                status.setStatus(false);
            }
        } else {
            status.setToken("");
            status.setStatus(false);
        }
        return JSON.toJSONString(status);
    }

    @Override
    public String close(String username, String password) {
        int res = accountDao.delete(username, password);
        JsonAccountStatus status = new JsonAccountStatus();
        status.setMethod("close");
        status.setToken("");
        status.setStatus(res != 0);
        return JSON.toJSONString(status);
    }

    @Override
    public String update(int id, String username, String password, boolean isMe, boolean gender) {
        User user = new User(id, username, password, isMe, gender);
        int res = accountDao.update(user);
        JsonAccountStatus status = new JsonAccountStatus();
        status.setMethod("update");
        status.setToken("");
        status.setStatus(res != 0);
        return JSON.toJSONString(status);
    }

    @Override
    public String selectAll(String token) {
        HashMap<String, Object> verify = TokenOperator.verify(token);
        Claims jws = (Claims) verify.get("jws");
        int id = (int) jws.get("id");
        User user = accountDao.selectById(id);
        if(user.getIsMe())
            return JSON.toJSONString(accountDao.selectByUsername(null));
        else
            return "Denied";
    }


}
