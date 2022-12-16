package me.looouiiis.service.impl;

import com.alibaba.fastjson2.JSON;
import me.looouiiis.dao.MessageDao;
import me.looouiiis.pojo.*;
import me.looouiiis.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    private MessageDao messageDao;

    @Override
    public String getAnoCommunicationByMac(String mac, Integer start, Integer num, boolean me) {
        Integer id = messageDao.getAnoIdByMac(mac);
        List<AnonymousMessage> messages = messageDao.selectAnoMessageById(id, start, num);
        if(me)
            deleteMyAnoUnread(id);
        else
            deleteAnoUnread(id);
        for (AnonymousMessage message : messages) {
            if(message.isLocal()){
                try {
                    BufferedReader br = new BufferedReader(new FileReader(message.getContent()));
                    StringBuilder sb = new StringBuilder();
                    String tmp = br.readLine();
                    sb.append(tmp);
                    while((tmp = br.readLine()) != null){
                        sb.append("\n").append(tmp);
                    }
                    message.setContent(sb.toString());
                    br.close();
                } catch (IOException e) {
                    message.setContent("系統文件出問題了，抱歉");
                    e.printStackTrace();
                }
            }
        }
        return JSON.toJSONString(messages);
    }

    @Override
    public String getCommunicationByUserId(Integer id, Integer start, Integer num, boolean me) {
        List<Message> messages = messageDao.selectMessageById(id, start, num);
        if(me)
            deleteMyUsrUnread(id);
        else
            deleteUsrUnread(id);
        for (Message message : messages) {
            if(message.isLocal()){
                try {
                    BufferedReader br = new BufferedReader(new FileReader(message.getContent()));
                    StringBuilder sb = new StringBuilder();
                    String tmp = br.readLine();
                    sb.append(tmp);
                    while((tmp = br.readLine()) != null){
                        sb.append("\n").append(tmp);
                    }
                    message.setContent(sb.toString());
                    br.close();
                } catch (IOException e) {
                    message.setContent("系統文件出問題了，抱歉");
                    e.printStackTrace();
                }
            }
        }
        return JSON.toJSONString(messages);
    }

    @Override
    public String commitAnoMessage(String mac, String content) {
        if(messageDao.getAnoIdByMac(mac) == null){
            messageDao.createAnoAccount(mac);
        }
        int id = messageDao.getAnoIdByMac(mac);
        AnonymousMessage message = new AnonymousMessage();
        message.setDate(new Date());
        JsonContentReturn ret = new JsonContentReturn();
        ret.setContext(null);
        if (content.length() > 100) {
            String path = "../AllMsg/Ano/Msg/" + id + "/";
            String filePath = contentHandle(path, content);
            if ("".equals(filePath)) {
                ret.setStatus(false);
                ret.setDescription("New file failed");
                return JSON.toJSONString(ret);
            }
            message.setContent(filePath);
        } else {
            message.setContent(content);
        }
        message.setLocal(content.length() > 100);
        message.setAnoId(id);
        if(messageDao.commitAnoMessage(message) != 0){
            ret.setStatus(true);
            ret.setDescription("Success");
            addMyAnoUnread(id);
        }
        else{
            ret.setStatus(false);
            ret.setDescription("Insert affect 0 rows");
        }
        return JSON.toJSONString(ret);
    }

    @Override
    public String commitMessage(int id, String content) {
        Message message = new Message();
        message.setDate(new Date());
        JsonContentReturn ret = new JsonContentReturn();
        ret.setContext(null);
        if (content.length() > 100) {
            String path = "../AllMsg/Usr/Msg/" + id + "/";
            String filePath = contentHandle(path, content);
            if ("".equals(filePath)) {
                ret.setStatus(false);
                ret.setDescription("New file failed");
                return JSON.toJSONString(ret);
            }
            message.setContent(filePath);
        } else {
            message.setContent(content);
        }
        message.setLocal(content.length() > 100);
        message.setUserId(id);
        if(messageDao.commitMessage(message) != 0){
            ret.setStatus(true);
            ret.setDescription("Success");
            addMyUsrUnread(id);
        }
        else{
            ret.setStatus(false);
            ret.setDescription("Insert affect 0 rows");
        }
        return JSON.toJSONString(ret);
    }

    @Override
    public String commitAnoReply(int id, String content) {
        AnonymousMessage message = new AnonymousMessage();
        message.setDate(new Date());
        JsonContentReturn ret = new JsonContentReturn();
        ret.setContext(null);
        if (content.length() > 100) {
            String path = "../AllMsg/Ano/Rep/" + id + "/";
            String filePath = contentHandle(path, content);
            if ("".equals(filePath)){
                ret.setStatus(false);
                ret.setDescription("New file failed");
                return JSON.toJSONString(ret);
            }
            message.setContent(filePath);
        } else
            message.setContent(content);
        message.setLocal(content.length() > 100);
        message.setAnoId(id);
        if(messageDao.commitAnoReply(message) != 0){
            ret.setStatus(true);
            ret.setDescription("Success");
            addAnoUnread(id);
        }
        else{
            ret.setStatus(false);
            ret.setDescription("Insert affect 0 rows");
        }
        return JSON.toJSONString(ret);
    }

    @Override
    public String commitReply(int id, String content) {
        Message message = new Message();
        message.setDate(new Date());
        JsonContentReturn ret = new JsonContentReturn();
        ret.setContext(null);
        if (content.length() > 100) {
            String path = "../AllMsg/Usr/Rep/" + id + "/";
            String filePath = contentHandle(path, content);
            if ("".equals(filePath)){
                ret.setStatus(false);
                ret.setDescription("New file failed");
                return JSON.toJSONString(ret);
            }
            message.setContent(filePath);
        } else
            message.setContent(content);
        message.setLocal(content.length() > 100);
        message.setUserId(id);
        if(messageDao.commitReply(message) != 0){
            ret.setStatus(true);
            ret.setDescription("Success");
            addUsrUnread(id);
        }
        else{
            ret.setStatus(false);
            ret.setDescription("Insert affect 0 rows");
        }
        return JSON.toJSONString(ret);
    }

    @Override
    public void transformToUser(int anoId, int usrId) {
        List<AnonymousMessage> messages = messageDao.selectAnoMessageById(anoId,null,null);
        messageDao.insertFromAno(messages,usrId);
        messageDao.deleteAnoMsgById(anoId);
    }
    String contentHandle(String path, String content){
        File dir = new File(path);
        dir.mkdirs();
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String filePath = path + df.format(new Date()) + ".txt";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return filePath;
    }

    @Override
    public String addMyAnoUnread(int anoId) {
        MyUnread check = messageDao.checkAnoExist(anoId);
        boolean flag;
        if(check == null){
            flag = (messageDao.insertMyAnoUnread(anoId, 1) != 0);
        }
        else{
            int num = check.getNum();
            flag = (messageDao.updateMyAnoUnread(anoId, num+1) != 0);
        }
        JsonContentReturn ret = new JsonContentReturn();
        ret.setDescription("addMyAnoUnread");
        ret.setStatus(flag);
        return JSON.toJSONString(ret);
    }

    @Override
    public String addMyUsrUnread(int usrId) {
        MyUnread check = messageDao.checkAnoExist(usrId);
        boolean flag;
        if(check == null){
            flag = (messageDao.insertMyUsrUnread(usrId, 1) != 0);
        }
        else{
            int num = check.getNum();
            flag = (messageDao.updateMyUsrUnread(usrId, num+1) != 0);
        }
        JsonContentReturn ret = new JsonContentReturn();
        ret.setDescription("addMyUnread");
        ret.setStatus(flag);
        return JSON.toJSONString(ret);
    }

    @Override
    public String checkMyUnread() {
        List<MyUnread> myUnread = messageDao.checkMyUnread();
        return JSON.toJSONString(myUnread);
    }

    @Override
    public String addAnoUnread(int anoId) {
        AnoUnread check = messageDao.checkAnoUnread(anoId);
        boolean flag;
        if(check == null){
            flag = (messageDao.insertAnoUnread(anoId, 1) != 0);
        }
        else{
            int num = check.getNum();
            flag = (messageDao.updateAnoUnread(anoId, num+1) != 0);
        }
        JsonContentReturn ret = new JsonContentReturn();
        ret.setDescription("addAnoUnread");
        ret.setStatus(flag);
        return JSON.toJSONString(ret);
    }

    @Override
    public String checkAnoUnread(int anoId) {
        AnoUnread anoUnread = messageDao.checkAnoUnread(anoId);
        return JSON.toJSONString(anoUnread);
    }

    @Override
    public String addUsrUnread(int usrId) {
        UsrUnread check = messageDao.checkUsrUnread(usrId);
        boolean flag;
        if(check == null){
            flag = (messageDao.insertUsrUnread(usrId, 1) != 0);
        }
        else{
            int num = check.getNum();
            flag = (messageDao.updateUsrUnread(usrId, num+1) != 0);
        }
        JsonContentReturn ret = new JsonContentReturn();
        ret.setDescription("addUsrUnread");
        ret.setStatus(flag);
        return JSON.toJSONString(ret);
    }

    @Override
    public String checkUsrUnread(int usrId) {
        UsrUnread usrUnread = messageDao.checkUsrUnread(usrId);
        return JSON.toJSONString(usrUnread);
    }

    @Override
    public void deleteMyAnoUnread(int anoId) {
        messageDao.deleteMyAnoUnread(anoId);
    }

    @Override
    public void deleteMyUsrUnread(int usrId) {
        messageDao.deleteMyUsrUnread(usrId);
    }

    @Override
    public void deleteAnoUnread(int anoId) {
        messageDao.deleteAnoUnread(anoId);
    }

    @Override
    public void deleteUsrUnread(int usrId) {
        messageDao.deleteUsrUnread(usrId);
    }
}

//            File dir = new File(path);
//            dir.mkdir();
//            path += new Date() + ".txt";
//            try {
//                BufferedWriter bw = new BufferedWriter(new FileWriter(path));
//                bw.write(content);
//                bw.close();
//                content = path;
//                local = true;
//            } catch (IOException e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//        message.setContent(content);
//        message.setLocal(local);