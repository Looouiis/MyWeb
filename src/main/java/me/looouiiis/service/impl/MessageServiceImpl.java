package me.looouiiis.service.impl;

import com.alibaba.fastjson2.JSON;
import me.looouiiis.dao.MessageDao;
import me.looouiiis.pojo.AnonymousMessage;
import me.looouiiis.pojo.Message;
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
    public String getAnoCommunicationByMac(String mac, Integer start, Integer num) {
        Integer id = messageDao.getAnoIdByMac(mac);
        List<AnonymousMessage> messages = messageDao.selectAnoMessageById(id, start, num);
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
                    e.printStackTrace();
                }
            }
        }
        return JSON.toJSONString(messages);
    }

    @Override
    public String getCommunicationByUserId(Integer id, Integer start, Integer num) {
        List<Message> messages = messageDao.selectMessageById(id, start, num);
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
                    e.printStackTrace();
                }
            }
        }
        return JSON.toJSONString(messages);
    }

    @Override
    public boolean commitAnoMessage(String mac, String content) {
        if(messageDao.getAnoIdByMac(mac) == null){
            messageDao.createAnoAccount(mac);
        }
        int id = messageDao.getAnoIdByMac(mac);
        AnonymousMessage message = new AnonymousMessage();
        message.setDate(new Date());
        if (content.length() > 100) {
            String path = "./Ano/Msg/" + id + "/";
            String filePath = contentHandle(path, content);
            if ("".equals(filePath))
                return false;
            message.setContent(filePath);
        } else
            message.setContent(content);
        message.setLocal(content.length() > 100);
        message.setAnoId(id);
        return messageDao.commitAnoMessage(message) != 0;
    }

    @Override
    public boolean commitMessage(int id, String content) {
        Message message = new Message();
        message.setDate(new Date());
        if (content.length() > 100) {
            String path = "./Usr/Msg/" + id + "/";
            String filePath = contentHandle(path, content);
            if ("".equals(filePath))
                return false;
            message.setContent(filePath);
        } else
            message.setContent(content);
        message.setLocal(content.length() > 100);
        message.setUserId(id);
        return messageDao.commitMessage(message) != 0;
    }

    @Override
    public boolean commitAnoReply(int id, String content) {
        AnonymousMessage message = new AnonymousMessage();
        message.setDate(new Date());
        if (content.length() > 100) {
            String path = "./Ano/Rep/" + id + "/";
            String filePath = contentHandle(path, content);
            if ("".equals(filePath))
                return false;
            message.setContent(filePath);
        } else
            message.setContent(content);
        message.setLocal(content.length() > 100);
        message.setAnoId(id);
        return messageDao.commitAnoReply(message) != 0;
    }

    @Override
    public boolean commitReply(int id, String content) {
        Message message = new Message();
        message.setDate(new Date());
        if (content.length() > 100) {
            String path = "./Usr/Rep/" + id + "/";
            String filePath = contentHandle(path, content);
            if ("".equals(filePath))
                return false;
            message.setContent(filePath);
        } else
            message.setContent(content);
        message.setLocal(content.length() > 100);
        message.setUserId(id);
        return messageDao.commitMessage(message) != 0;
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