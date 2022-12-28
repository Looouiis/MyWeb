package me.looouiiis.controller;

import jakarta.servlet.http.HttpServletRequest;
import me.looouiiis.exception.SystemException;
import me.looouiiis.pojo.*;
import me.looouiiis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
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

    @GetMapping(value = "/anonymous/communication/{num}/{page}/{anoId}")
    @ResponseBody
    public JsonContentReturn getAnoCommunication(@PathVariable Integer anoId, @PathVariable Integer page, @PathVariable Integer num, HttpServletRequest request) {
        Integer start = null;
        if (page == -1) {
            start = null;
            num = null;
        } else if (page != null && num != null && page > 0) {
            start = num * (page - 1);
        } else if (num != null) {
            start = 0;
        }
        boolean me = (boolean) request.getAttribute("isMe");
        Integer id = (Integer) request.getAttribute("anoId");
        if (anoId != null && anoId > 0) {
            id = anoId;
        }
        HashMap<String, Object> res = service.getAnoCommunication(id, start, num, me);
        JsonContentReturn ret = new JsonContentReturn();
        List<AnonymousMessage> messages = (List<AnonymousMessage>) res.get("messages");
        Integer totalCount = (Integer) res.get("totalCount");
        res.remove("totalCount");
        if (messages.size() == 0) {
            ret.setStatus(false);
            ret.setDescription("Find nothing");
            ret.setContent(null);
            ret.setTotalCount(totalCount);
        } else {
            ret.setContent(res);
            ret.setDescription("success");
            ret.setStatus(true);
            ret.setTotalCount(totalCount);
        }
        return ret;
    }

    @PostMapping(value = "/anonymous/communication")
    @ResponseBody
    public JsonContentReturn postAnoMessage(String content, HttpServletRequest request) {
        Integer id = (Integer) request.getAttribute("anoId");
        return service.commitAnoMessage(id, content);
    }

    @PostMapping(value = "/anonymous/reply/{anoId}")
    @ResponseBody
    public JsonContentReturn postAnoReplyWithPer(HttpServletRequest request, @PathVariable Integer anoId, String content) {
//        Integer id = (Integer) request.getAttribute("anoId");
        if (anoId == null) {
            throw new SystemException(Code.SYSTEM_ERR, "在预期可获得anoId的位置出错");
        }
        return service.commitAnoReply(anoId, content);
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
            ret.setDescription("您还没留言过");
        }
        return ret;
    }

    @GetMapping(value = "/users/communication/{num}/{page}/{usrId}")
    @ResponseBody
    public JsonContentReturn getUsrCommunication(@PathVariable Integer usrId, @PathVariable Integer page, @PathVariable Integer num, HttpServletRequest request) {
        Integer start = null;
        Integer id = (Integer) request.getAttribute("usrId");
        if (usrId != null && usrId > 0) {
            id = usrId;
        }
        JsonContentReturn ret = new JsonContentReturn();
        if (id != null) {
            boolean me = (boolean) request.getAttribute("isMe");
            if (page == -1) {
                start = null;
                num = null;
            } else if (page != null && num != null && page > 0) {
                start = num * (page - 1);
            } else if (num != null) {
                start = 0;
            }
            HashMap<String, Object> res = service.getCommunicationByUserId(id, start, num, me);
            List<me.looouiiis.pojo.Message> messages = (List<me.looouiiis.pojo.Message>) res.get("messages");
            Integer totalCount = (Integer) res.get("totalCount");
            res.remove("totalCount");
            if (messages.size() != 0) {
                ret.setContent(res);
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

    @PostMapping(value = "/users/reply/{usrId}")
    @ResponseBody
    public JsonContentReturn postUsrReplyWithPer(HttpServletRequest request, @PathVariable Integer usrId, String content) {
        System.out.println("收到了" + usrId);
        if (usrId != null) {
            JsonContentReturn res = service.commitReply(usrId, content);
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
//        JsonContentReturn ret = checkMyUnreadWithPer(request);
//        if (ret.isStatus()) {
//            return ret;
//        }
        JsonContentReturn ret = new JsonContentReturn();
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

    @GetMapping("/anonymous/comment/{msgId}")
    @ResponseBody
    public JsonContentReturn getAnoComment(@PathVariable int msgId) {
        HashMap<String, Object> res = service.getAnoCommentByMsgId(msgId);
        JsonContentReturn ret = new JsonContentReturn();
        if (res.size() != 0) {
            ret.setStatus(true);
            ret.setContent(res);
            ret.setDescription("成功");
        } else {
            ret.setStatus(false);
            ret.setDescription("无回复");
        }
        return ret;
    }

    @PostMapping(value = "/anonymous/comment/{msgId}")
    @ResponseBody
    public JsonContentReturn postAnoComment(@PathVariable Integer msgId, String content, HttpServletRequest request) {
        boolean isMe = (boolean) request.getAttribute("isMe");
        if (msgId != null) {
            JsonContentReturn res;
            if (isMe) {
                res = service.commitAnoCommentReply(msgId, content);
            }
            else {
                res = service.commitAnoComment(msgId, content);
            }
            return res;
        } else {
            JsonContentReturn ret = new JsonContentReturn();
            ret.setContent(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't login yet.");
            return ret;
        }
    }

    @GetMapping("/users/comment/{msgId}")
    @ResponseBody
    public JsonContentReturn getUsrComment(@PathVariable int msgId) {
        HashMap<String, Object> res = service.getUsrCommentByMsgId(msgId);
        JsonContentReturn ret = new JsonContentReturn();
        if (res.size() != 0) {
            ret.setStatus(true);
            ret.setContent(res);
            ret.setDescription("成功");
        } else {
            ret.setStatus(false);
            ret.setDescription("无回复");
        }
        return ret;
    }

    @PostMapping(value = "/users/comment/{msgId}")
    @ResponseBody
    public JsonContentReturn postUsrComment(HttpServletRequest request, @PathVariable Integer msgId, String content) {
        boolean isMe = (boolean) request.getAttribute("isMe");
        if (msgId != null) {
            JsonContentReturn res;
            if(isMe){
                res = service.commitUsrCommentReply(msgId, content);
            } else {
                res = service.commitUsrComment(msgId, content);
            }
            return res;
        } else {
            JsonContentReturn ret = new JsonContentReturn();
            ret.setContent(null);
            ret.setStatus(false);
            ret.setDescription("It seems that you haven't login yet.");
            return ret;
        }
    }
}
