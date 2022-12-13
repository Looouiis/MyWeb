package me.looouiiis.service.impl;

import com.alibaba.fastjson2.JSON;
import me.looouiiis.dao.AccountDao;
import me.looouiiis.pojo.JsonAccountStatus;
import me.looouiiis.pojo.User;
import me.looouiiis.service.UserService;
import me.looouiiis.utils.TokenOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            status.setToken(TokenOperator.generate(username));
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
            int res = accountDao.insert(username, password, isMe, gender);
            if (res != 0) {
                status.setToken(TokenOperator.generate(username));
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
    public boolean close(String username, String password) {
        int res = accountDao.delete(username, password);
        return res != 0;
    }
}
