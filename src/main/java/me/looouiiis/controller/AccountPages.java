package me.looouiiis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountPages {
    @GetMapping("/Login")
    public String login(){
        return "/pages/Login.html";
    }
    @GetMapping("/Register")
    public String register(){
        return "/pages/Register.html";
    }
    @GetMapping("/Update")
    public String update(){
        return "/pages/Update.html";
    }
}
