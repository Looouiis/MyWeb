package me.looouiiis.controller;

import me.looouiiis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller//SpringMVC专用声明bean注解
public class Account {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/login", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String login(String username, String password) {
        String res = service.login(username, password);
        return res;
    }

    @RequestMapping(value = "/register", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String register(String username, String password, boolean isMe, boolean gender){
        String res = service.register(username, password, isMe, gender);
        return res;
    }
    @RequestMapping(value = "/close", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String close(String username, String password){
        String res = service.close(username, password);
        return res;
    }
    @RequestMapping(value = "/update", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String update(int id, String username, String password, boolean isMe, boolean gender){
        String res = service.update(id, username, password, isMe, gender);
        return res;
    }
    @RequestMapping(value = "/manage", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String manage(String token){
        String res = service.selectAll(token);
        return res;
    }

}
