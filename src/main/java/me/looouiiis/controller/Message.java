package me.looouiiis.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.looouiiis.pojo.*;
import me.looouiiis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

@Controller
public class Message {
    private MessageService service;

    @Autowired
    public void setMessageService(MessageService service) {
        this.service = service;
    }

    @GetMapping(value = "/myUnread")
    @ResponseBody
    public JsonContentReturn checkMyUnreadWithPer(HttpServletRequest request) {
        JsonContentReturn ret = new JsonContentReturn();
        List<MyUnread> res = service.checkMyUnread();
        if (res.size() == 0) {
            ret.setContent(res);
            ret.setDescription("Find nothing");
            ret.setStatus(true);
        } else {
            ret.setContent(res);
            ret.setDescription("success");
            ret.setStatus(true);
        }
        return ret;
    }

    @GetMapping(value = "/anonymous/communication")
    @ResponseBody
    public JsonContentReturn getAnoCommunication(String mac, Integer page, Integer num, HttpServletRequest request) {
        Integer start = null;
        if (page != null && num != null && page > 0) {
            start = num * (page - 1);
        } else if (page == null && num != null) {
            start = 0;
        }
        boolean me = (boolean) request.getAttribute("isMe");
        HashMap<String, Object> res = service.getAnoCommunicationByMac(mac, start, num, me);
        JsonContentReturn ret = new JsonContentReturn();
        List<AnonymousMessage> messages = (List<AnonymousMessage>) res.get("messages");
        if (messages.size() == 0) {
            ret.setStatus(false);
            ret.setDescription("Find nothing");
            ret.setContent(null);
            ret.setTotalCount((Integer) res.get("totalCount"));
        } else {
            ret.setContent(messages);
            ret.setDescription("success");
            ret.setStatus(true);
            ret.setTotalCount((Integer) res.get("totalCount"));
        }
        return ret;
    }

    @PostMapping(value = "/anonymous/communication")
    @ResponseBody
    public JsonContentReturn postAnoMessage(String mac, String content) {
        return service.commitAnoMessage(mac, content);
    }

    @PostMapping(value = "/anonymous/reply")
    @ResponseBody
    public JsonContentReturn postAnoReply(Integer id, String content) {
        return service.commitAnoReply(id, content);
    }

    @GetMapping(value = "/anonymous/reply")
    @ResponseBody
    public JsonContentReturn checkAnoUnread(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("anoId");
        JsonContentReturn ret = new JsonContentReturn();
        if (id != null) {
            AnoUnread res = service.checkAnoUnread(id);
            ret.setContent(res);
            ret.setDescription("success");
            ret.setStatus(true);
        } else {
            ret.setContent(null);
            ret.setStatus(false);
            ret.setDescription("Give me your mac first");
        }
        return ret;
    }

    @GetMapping(value = "/users/communication")
    @ResponseBody
    public JsonContentReturn getUsrCommunication(Integer page, Integer num, HttpServletRequest request) {
        Integer start = null;
        Integer id = (Integer) request.getAttribute("usrId");
        JsonContentReturn ret = new JsonContentReturn();
        if (id != null) {
            boolean me = (boolean) request.getAttribute("isMe");
            if (page != null && num != null && page > 0) {
                start = num * (page - 1);
            } else if (page == null && num != null) {
                start = 0;
            }
            HashMap<String, Object> res = service.getCommunicationByUserId(id, start, num, me);
            List<me.looouiiis.pojo.Message> messages = (List<me.looouiiis.pojo.Message>) res.get("messages");
            Integer totalCount = (Integer) res.get("totalCount");
            if (messages.size() != 0) {
                ret.setContent(messages);
                ret.setStatus(true);
                ret.setDescription("success");
                ret.setTotalCount(totalCount);
            } else {
                ret.setContent(null);
                ret.setStatus(false);
                ret.setDescription("failed");
                ret.setTotalCount(totalCount);
            }
        } else {
            ret.setContent(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't login yet.");
        }
        return ret;
    }

    @PostMapping(value = "/users/communication")
    @ResponseBody
    public JsonContentReturn postUsrMessage(String content, HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("usrId");
        if (id != null) {
            JsonContentReturn res = service.commitMessage(id, content);
            return res;
        } else {
            JsonContentReturn ret = new JsonContentReturn();
            ret.setContent(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't login yet.");
            return ret;
        }
    }

    @PostMapping(value = "/users/reply")
    @ResponseBody
    public JsonContentReturn postUsrReply(Integer id, String content) {
        if (id != null) {
            JsonContentReturn res = service.commitReply(id, content);
            return res;
        } else {
            JsonContentReturn ret = new JsonContentReturn();
            ret.setContent(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't determined who to send");
            return ret;
        }
    }

    @GetMapping(value = "/users/reply")
    @ResponseBody
    public JsonContentReturn checkUsrUnread(HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("usrId");
        JsonContentReturn ret = checkMyUnreadWithPer(request);
        if(ret.isStatus()){
            return ret;
        }
        ret = new JsonContentReturn();
        if (id != null) {
            UsrUnread res = service.checkUsrUnread(id);
            ret.setContent(res);
            ret.setDescription("success");
            ret.setStatus(true);
        } else {
            ret.setContent(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't login yet.");
        }
        return ret;
    }
}
