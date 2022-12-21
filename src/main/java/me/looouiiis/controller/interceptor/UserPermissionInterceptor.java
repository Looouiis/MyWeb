package me.looouiiis.controller.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import me.looouiiis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class UserPermissionInterceptor implements HandlerInterceptor {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String token = request.getParameter("token");
//        JsonContentReturn select = new JsonContentReturn();
//        select.setStatus(false);
//        select.setContext(null);
//        if (token != null && !"".equals(token)) {
//            int id = userService.checkToken(token);
//            if (id != -1) {
//                if (userService.checkPermission(id)) {
//                    return true;
//                } else {
//                    select.setDescription("Permission Denied");
//                }
//            } else {
//                select.setDescription("Token cannot be trusted");
//            }
//        } else {
//            select.setDescription("Token cannot be null");
//        }
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(response.getOutputStream()));
//        writer.write(JSON.toJSONString(select));
//        writer.close();
        Integer id = (Integer) request.getAttribute("usrId");
        if (userService.checkPermission(id)) {
            request.setAttribute("isMe", true);
        } else {
            request.setAttribute("isMe", false);
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
