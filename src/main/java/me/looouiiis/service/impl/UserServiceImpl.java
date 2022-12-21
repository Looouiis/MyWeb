package me.looouiiis.service.impl;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import me.looouiiis.controller.Code;
import me.looouiiis.dao.AccountDao;
import me.looouiiis.exception.BusinessException;
import me.looouiiis.exception.SystemException;
import me.looouiiis.pojo.JsonAccountStatus;
import me.looouiiis.pojo.JsonContentReturn;
import me.looouiiis.pojo.User;
import me.looouiiis.pojo.UserForUpdate;
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
    public JsonAccountStatus login(String username, String password) {
        User select = accountDao.selectByPassword(username, password);
        JsonAccountStatus status = new JsonAccountStatus();
        status.setMethod("login");
        if (select != null) {
            status.setToken(TokenOperator.generate(select.getId(),3));
            status.setRefreshToken(TokenOperator.generate(select.getId(), 7));
            status.setDescription("恭喜我让你登录成功");
            status.setStatus(true);
        } else {
            status.setDescription("登录失败，用户名或密码不正确");
            status.setStatus(false);
        }
        return status;
    }

    @Override
    public JsonAccountStatus register(User user) {
        User users = accountDao.selectByUsername(user.getUsername());
        JsonAccountStatus status = new JsonAccountStatus();
        status.setMethod("register");
        if (users == null) {
            user.setId(0);
            user.setIsMe(false);
            int res = accountDao.insert(user);
            if (res != 0) {
                status.setToken(TokenOperator.generate(user.getId(),3));
                status.setRefreshToken(TokenOperator.generate(user.getId(),7));
                status.setStatus(true);
            } else {
                throw new SystemException(Code.SYSTEM_ERR,"疑似数据库出现问题，请及时向我反馈");
            }
        } else {
            throw new BusinessException(Code.BUSINESS_ERR,"您的账户已存在，请勿重复注册");
        }
        return status;
    }

    @Override
    public JsonAccountStatus closeByPassword(User user) {
        User select = accountDao.selectByUsername(user.getUsername());
        int id = select.getId();
        accountDao.deleteFromMyUnread(id);
        accountDao.deleteFromUsrUnread(id);
        accountDao.deleteFromMessage(id);
        int res = accountDao.delete(user);
        JsonAccountStatus status = new JsonAccountStatus();
        status.setMethod("close");
        status.setToken("");
        status.setStatus(res != 0);
        return status;
    }

    @Override
    public JsonAccountStatus update(UserForUpdate user) {
        JsonAccountStatus status = new JsonAccountStatus();
        status.setMethod("update");
        status.setToken("");
        User select = accountDao.preUpdate(user);
        if(select != null) {
            int res = accountDao.update(user);
            System.out.println(res);
            status.setStatus(res != 0);
        }
        else{
            System.out.println(select);
            status.setStatus(false);
        }
        return status;
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
    public JsonContentReturn selectAll() {
        JsonContentReturn select = new JsonContentReturn();
        select.setContent(JSON.toJSON(accountDao.selectAll()));
        select.setStatus(true);
        select.setDescription("Success");
        return select;
    }

    @Override
    public boolean checkPermission(Integer id) {
        if (id == null) {
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
        if ((boolean) verify.get("trusted")) {
            Claims jws = (Claims) verify.get("jws");
            return new Date().after(jws.getExpiration());
        }
        return true;
    }
}