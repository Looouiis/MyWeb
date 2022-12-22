package me.looouiiis.controller.interceptor;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.looouiiis.pojo.JsonContentReturn;
import me.looouiiis.service.MessageService;
import me.looouiiis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;

@Component
public class ParseInterceptor implements HandlerInterceptor {
    private MessageService messageService;
    private UserService userService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        JsonContentReturn select = new JsonContentReturn();
        select.setStatus(false);
        select.setContent(null);
        if (token != null && !"".equals(token)) {
            HashMap<String, Object> verify = userService.verify(token);
            if (userService.checkIsTrusted(verify)) {
                if (userService.checkIsOutdated(verify)) {
                    response.setStatus(401);
                    return false;
                }
                int id = userService.checkTokenId(verify);
                request.setAttribute("usrId", id);
                return true;
            } else {
//                select.setDescription("Token cannot be trusted");
//                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
//                writer.write(JSON.toJSONString(select));
//                writer.close();
                response.setStatus(401);
                return false;
            }
        } else {
            request.setAttribute("usrId", null);
        }

        String mac = request.getParameter("mac");
        if (mac != null && !"".

                equals(mac)) {
            Integer id = messageService.getAnoIdByMac(mac);
            request.setAttribute("anoId", id);
        } else {
            request.setAttribute("anoId", null);
        }
        return true;
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
