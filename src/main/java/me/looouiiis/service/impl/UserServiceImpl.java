package me.looouiiis.service.impl;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import me.looouiiis.controller.Code;
import me.looouiiis.dao.AccountDao;
import me.looouiiis.exception.BusinessException;
import me.looouiiis.exception.SystemException;
import me.looouiiis.pojo.*;
import me.looouiiis.service.UserService;
import me.looouiiis.utils.TokenOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

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
            status.setToken(TokenOperator.generate(select.getId(), 3, select.getIsMe()));
            status.setRefreshToken(TokenOperator.generate(select.getId(), 7, select.getIsMe()));
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
                status.setToken(TokenOperator.generate(user.getId(), 3, false));
                status.setRefreshToken(TokenOperator.generate(user.getId(), 7, false));
                status.setStatus(true);
            } else {
                throw new SystemException(Code.SYSTEM_ERR, "疑似数据库出现问题，请及时向我反馈");
            }
        } else {
            throw new BusinessException(Code.BUSINESS_ERR, "您的账户已存在，请勿重复注册");
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
        if(user.getGender() == null && "".equals(user.getUsername()) && "".equals(user.getNewPassword())){
            status.setStatus(false);
            status.setDescription("您好像没提交任何修改");
        }
        else {
            User select = accountDao.preUpdate(user);
            if (select != null) {
                int res = accountDao.update(user);
                status.setStatus(res != 0);
                status.setDescription("成功");
            } else {
                status.setDescription("输入的原始密码错误");
                status.setStatus(false);
            }
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
    public int checkTokenId(HashMap<String, Object> verify) {
        Claims jws = (Claims) verify.get("jws");
        return (int) jws.get("id");
    }

    public boolean checkIsTrusted(HashMap<String, Object> verify) {
        return (boolean) verify.get("trusted");
    }

    @Override
    public boolean checkIsOutdated(HashMap<String, Object> verify) {
//        HashMap<String, Object> verify = TokenOperator.verify(token);
//        if ((boolean) verify.get("trusted")) {
        return (boolean) verify.get("outdated");
//        }
//        return true;
    }

    @Override
    public HashMap<String, Object> verify(String token) {
        return TokenOperator.verify(token);
    }

    @Override
    public JsonAccountStatus refresh(RefreshRequest refresh) {
        HashMap<String, Object> first = verify(refresh.getToken());
        HashMap<String, Object> second = verify(refresh.getRefreshToken());
        System.out.println(first);
        System.out.println(second);
        JsonAccountStatus status = new JsonAccountStatus();
        if(checkIsTrusted(first) && checkIsTrusted(second)) {
            int id = checkTokenId(first);
            User user = accountDao.selectById(id);
            if (id == checkTokenId(second)) {
                if (checkIsOutdated(second)) {
                    status.setDescription("Refresh token也过期了，请重新登录吧");
                    status.setStatus(false);
                } else {
                    System.out.println("已发放");
                    status.setDescription("成功(当你看到这条信息时，你给我小心点)");
                    status.setToken(TokenOperator.generate(id, 3, user.getIsMe()));
                    status.setRefreshToken(TokenOperator.generate(id, 7, user.getIsMe()));
                    status.setStatus(true);
                }
            } else {
                status.setDescription("token身份信息错乱，疑似盗用他人token");
                status.setStatus(false);
            }
        } else {
            status.setDescription("token不可信，请勿擅自修改token");
            status.setStatus(false);
        }
        return status;
    }

    @Override
    public JsonContentReturn loginWithToken(String token) {
        HashMap<String, Object> verify = TokenOperator.verify(token);
        JsonContentReturn ret = new JsonContentReturn();
        if(checkIsTrusted(verify)){
            if(!checkIsOutdated(verify)) {
                int id = checkTokenId(verify);
                ret.setContent(accountDao.selectById(id));
                ret.setDescription("成功");
                ret.setStatus(true);
            }else {
                ret.setStatus(false);
                ret.setDescription("Token已过期");
            }
        }
        else{
            ret.setDescription("Token不可信");
            ret.setStatus(false);
        }
        return ret;
    }
}