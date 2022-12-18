package me.looouiiis.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.looouiiis.pojo.JsonAccountStatus;
import me.looouiiis.pojo.JsonContentReturn;
import me.looouiiis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller//SpringMVC专用声明bean注解
public class Account {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/login")
    @ResponseBody
    public JsonAccountStatus login(String username, String password) {
        System.out.println(username);
        System.out.println(password);
        return service.login(username, password);
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public JsonAccountStatus register(String username, String password, boolean gender) {
        return service.register(username, password, gender);
    }

    @RequestMapping(value = "/close")
    @ResponseBody
    public JsonAccountStatus close(String username, String password) {
        return service.close(username, password);
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public JsonAccountStatus update(int id, String username, String password, boolean isMe, boolean gender) {
        return service.update(id, username, password, isMe, gender);
    }

    @RequestMapping(value = "/manage")
    @ResponseBody
    public JsonContentReturn manageWithPer(HttpServletRequest request) {
        return service.selectAll();
    }

    @RequestMapping(value = "/test")
    @ResponseBody
    public String test() {
        return "张三";
    }
}
