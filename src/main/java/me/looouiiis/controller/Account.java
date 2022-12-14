package me.looouiiis.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.looouiiis.pojo.*;
import me.looouiiis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RequestMapping("/users")
@RestController//SpringMVC专用声明bean注解
public class Account {
    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @PostMapping
    public JsonAccountStatus login(@RequestBody User user) {
//        System.out.println(user.getUsername());
//        System.out.println(user.getPassword());
        return service.login(user.getUsername(), user.getPassword());
    }

    @PutMapping
    public JsonAccountStatus register(HttpServletRequest request, @RequestBody User user) {
        Integer anoId = (Integer) request.getAttribute("anoId");
        return service.register(user, anoId);
    }

    @DeleteMapping
    public JsonAccountStatus closeByPassword(@RequestBody User user) {
        return service.closeByPassword(user);
    }

    @PatchMapping
    public JsonAccountStatus update(@RequestBody UserForUpdate user) {
        return service.update(user);
    }

    @GetMapping("/{simple}")
    public JsonContentReturn manageWithPer(HttpServletRequest request, @PathVariable boolean simple) {
        return service.selectAll(simple);
    }

    @PutMapping("/token")
    public JsonAccountStatus refresh(@RequestBody RefreshRequest refresh){
        return service.refresh(refresh);
    }

    @GetMapping("/token")
    public JsonContentReturn loginWithToken(@RequestHeader String token) {
        return service.loginWithToken(token);
    }
}
