package me.looouiiis.controller.interceptor;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.looouiiis.pojo.JsonContentReturn;
import me.looouiiis.service.UserService;
import me.looouiiis.utils.TokenOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;
@Component
public class IdParseInterceptor implements HandlerInterceptor {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getParameter("token");
        JsonContentReturn select = new JsonContentReturn();
        select.setStatus(false);
        select.setContext(null);
        if (token != null && !"".equals(token)) {
            int id = userService.checkToken(token);
            if (id != -1) {
                request.setAttribute("usrId",id);
                return true;
            } else {
                select.setDescription("Token cannot be trusted");
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
                writer.write(JSON.toJSONString(select));
                writer.close();
                return false;
            }
        }
        String mac = request.getParameter("mac");
        if(mac != null && !"".equals(mac)){

        }
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
