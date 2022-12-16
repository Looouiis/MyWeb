package me.looouiiis.controller;

import com.alibaba.fastjson2.JSON;
import jakarta.servlet.http.HttpServletRequest;
import me.looouiiis.pojo.*;
import me.looouiiis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class Message {
    private MessageService service;

    @Autowired
    public void setMessageService(MessageService service) {
        this.service = service;
    }
    @GetMapping(value = "/myUnread", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkMyUnread(HttpServletRequest request) {
        boolean isMe = (boolean) request.getAttribute("isMe");
        JsonContentReturn ret = new JsonContentReturn();
        if(isMe){
            List<MyUnread> res = service.checkMyUnread();
            if(res.size() == 0) {
                ret.setContext(res);
                ret.setDescription("Find nothing");
                ret.setStatus(false);
            }
            else {
                ret.setContext(res);
                ret.setDescription("success");
                ret.setStatus(true);
            }
        }
        else{
            ret.setContext(null);
            ret.setStatus(false);
            ret.setDescription("Permission denied");
        }
        return JSON.toJSONString(ret);
    }

    @GetMapping(value = "/anonymous/communication", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAnoCommunication(String mac, Integer page, Integer num, HttpServletRequest request) {
        Integer start = null;
        if (page != null && num != null && page > 0) {
            start = num * (page - 1);
        } else if (page == null && num != null) {
            start = 0;
        }
        boolean me = (boolean) request.getAttribute("isMe");
        List<AnonymousMessage> res = service.getAnoCommunicationByMac(mac, start, num, me);
        JsonContentReturn ret = new JsonContentReturn();
        if(res.size() == 0) {
            ret.setStatus(false);
            ret.setDescription("Find nothing");
            ret.setContext(res);
        }
        else {
            ret.setContext(res);
            ret.setDescription("success");
            ret.setStatus(true);
        }
        return JSON.toJSONString(ret);
    }

    @PostMapping(value = "/anonymous/communication", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String postAnoMessage(String mac, String content) {
        JsonContentReturn res = service.commitAnoMessage(mac, content);
        return JSON.toJSONString(res);
    }

    @PostMapping(value = "/anonymous/reply", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String postAnoReply(Integer id, String content) {
        JsonContentReturn res = service.commitAnoReply(id, content);
        return JSON.toJSONString(res);
    }

    @GetMapping(value = "/anonymous/reply", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkAnoUnread(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("anoId");
        JsonContentReturn ret = new JsonContentReturn();
        if(id != null){
            AnoUnread res = service.checkAnoUnread(id);
            ret.setContext(res);
            ret.setDescription("success");
            ret.setStatus(true);
        }
        else{
            ret.setContext(null);
            ret.setStatus(false);
            ret.setDescription("Give me your mac first");
        }
        return JSON.toJSONString(ret);
    }

    @GetMapping(value = "/users/communication", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getUsrCommunication(Integer page, Integer num, HttpServletRequest request) {
        Integer start = null;
        Integer id = (int) request.getAttribute("usrId");
        JsonContentReturn ret = new JsonContentReturn();
        if(id != null) {
            boolean me = (boolean) request.getAttribute("isMe");
            if (page != null && num != null && page > 0) {
                start = num * (page - 1);
            } else if (page == null && num != null) {
                start = 0;
            }
            List<me.looouiiis.pojo.Message> res = service.getCommunicationByUserId(id, start, num, me);
            ret.setContext(JSON.toJSON(res));
            ret.setStatus(true);
            ret.setDescription("success");
        }
        else{
            ret.setContext(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't login yet.");
        }
        return JSON.toJSONString(ret);
    }

    @PostMapping(value = "/users/communication", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String postUsrMessage(String content, HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("usrId");
        if(id != null) {
            JsonContentReturn res = service.commitMessage(id, content);
            return JSON.toJSONString(res);
        }
        else{
            JsonContentReturn ret = new JsonContentReturn();
            ret.setContext(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't login yet.");
            return JSON.toJSONString(ret);
        }
    }

    @PostMapping(value = "/users/reply", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String postUsrReply(Integer id, String content) {
        if(id != null) {
            JsonContentReturn res = service.commitReply(id, content);
            return JSON.toJSONString(res);
        }
        else{
            JsonContentReturn ret = new JsonContentReturn();
            ret.setContext(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't determined sent to who");
            return JSON.toJSONString(ret);
        }
    }
    @GetMapping(value = "/users/reply", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String checkUsrUnread(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("usrId");
        JsonContentReturn ret = new JsonContentReturn();
        if(id != null){
            UsrUnread res = service.checkUsrUnread(id);
            ret.setContext(res);
            ret.setDescription("success");
            ret.setStatus(true);
        }
        else{
            ret.setContext(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't login yet.");
        }
        return JSON.toJSONString(ret);
    }
}
