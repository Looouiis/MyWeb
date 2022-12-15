package me.looouiiis.controller;

import me.looouiiis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Message {
    private MessageService service;

    @Autowired
    public void setMessageService(MessageService service) {
        this.service = service;
    }

    @GetMapping(value = "/anonymous/communication", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String getAnoCommunication(String mac, Integer page, Integer num) {
        Integer start = null;
        if (page != null && num != null && page > 0) {
            start = num * (page - 1);
        } else if (page == null && num != null) {
            start = 0;
        }
        String res = service.getAnoCommunicationByMac(mac, start, num);
        return res;
    }

    @PostMapping(value = "/anonymous/communication", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String postAnoMessage(String mac, String content) {
        String res = service.commitAnoMessage(mac, content);
        return res;
    }

    @PostMapping(value = "/anonymous/reply", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String postAnoReply(Integer id, String content) {
        String res = service.commitAnoReply(id, content);
        return res;
    }
}
