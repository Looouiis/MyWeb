package me.looouiiis.aop;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import me.looouiiis.pojo.JsonContentReturn;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PermissionAdvice {
    @Pointcut("execution(* me.looouiiis.controller.*.*WithPer(..))")
    private void permission(){}
    @Around("permission()")
    public Object method(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        HttpServletRequest request = (HttpServletRequest) args[0];
        boolean isMe = (boolean) request.getAttribute("isMe");
        if(isMe) {
            return pjp.proceed();
        }
        else {
            JsonContentReturn ret = new JsonContentReturn();
            ret.setStatus(false);
            ret.setDescription("Permission denied");
            ret.setContent(null);
            return JSON.toJSONString(ret);
        }
    }
}
