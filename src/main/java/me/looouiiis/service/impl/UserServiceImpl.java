package me.looouiiis.service.impl;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import me.looouiiis.dao.AccountDao;
import me.looouiiis.pojo.JsonContentReturn;
import me.looouiiis.pojo.JsonAccountStatus;
import me.looouiiis.pojo.User;
import me.looouiiis.service.UserService;
import me.looouiiis.utils.TokenOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

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
    public String register(String username, String password, boolean gender) {
        User users = accountDao.selectByUsername(username);
        JsonAccountStatus status = new JsonAccountStatus();
        status.setMethod("register");
        if (users == null) {
            User user = new User(0, username, password, false, gender);
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
        User user = accountDao.selectByUsername(username);
        int id = user.getId();
        accountDao.deleteFromMyUnread(id);
        accountDao.deleteFromUsrUnread(id);
        accountDao.deleteFromMessage(id);
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

    //    @Override
//    public String selectAll(String token) {
//        JsonAccountSelect select = new JsonAccountSelect();
//        if (token != null && !token.equals("")) {
//            HashMap<String, Object> verify = TokenOperator.verify(token);
//            if ((boolean) verify.get("trusted")) {
//                Claims jws = (Claims) verify.get("jws");
//                int id = (int) jws.get("id");
//                User user = accountDao.selectById(id);
//                if (user.getIsMe()) {
//                    select.setContext(JSON.toJSON(accountDao.selectByUsername(null)));
//                    select.setStatus(true);
//                    select.setDescription("Success");
//                } else {
//                    select.setContext(null);
//                    select.setStatus(false);
//                    select.setDescription("Denied");
//                }
//            } else {
//                select.setContext(null);
//                select.setStatus(false);
//                select.setDescription("Token cannot be trusted");
//            }
//        } else {
//            select.setContext(null);
//            select.setStatus(false);
//            select.setDescription("Token cannot be null");
//        }
//        return JSON.toJSONString(select);
//    }
    @Override
    public String selectAll() {
        JsonContentReturn select = new JsonContentReturn();
        select.setContext(JSON.toJSON(accountDao.selectAll()));
        select.setStatus(true);
        select.setDescription("Success");
        return JSON.toJSONString(select);
    }

    @Override
    public boolean checkPermission(Integer id) {
        if(id == null){
            return false;
        }
        User user = accountDao.selectById(id);
        return user.getIsMe();
    }

    @Override
    public int checkToken(String token) {
        HashMap<String, Object> verify = TokenOperator.verify(token);
        if ((boolean) verify.get("trusted")) {
            Claims jws = (Claims) verify.get("jws");
            return (int) jws.get("id");
        } else {
            return -1;
        }
    }

    @Override
    public boolean checkIsOutdated(String token) {
        HashMap<String, Object> verify = TokenOperator.verify(token);
        if((boolean) verify.get("trusted")){
            Claims jws = (Claims) verify.get("jws");
            return new Date().after(jws.getExpiration());
        }
        return true;
    }
}