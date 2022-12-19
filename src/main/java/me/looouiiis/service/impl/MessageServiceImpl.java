package me.looouiiis.service.impl;

import me.looouiiis.controller.Code;
import me.looouiiis.dao.MessageDao;
import me.looouiiis.exception.SystemException;
import me.looouiiis.pojo.*;
import me.looouiiis.service.MessageService;
import me.looouiiis.utils.ContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    private MessageDao messageDao;

    @Override
    public Integer getAnoIdByMac(String mac) {
        return messageDao.getAnoIdByMac(mac);
    }

    @Override
    public HashMap<String, Object> getAnoCommunicationByMac(String mac, Integer start, Integer num, boolean me) {
        Integer id = getAnoIdByMac(mac);
        if(id == null){
            id = 0;
        }
        int totalCount = messageDao.getAnoMessageTotalNum(id);
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
                    throw new SystemException(Code.SYSTEM_IO_ERR, "后台文件读写时发生异常，已开启事务所以您的数据是安全的不用担心，您可重试或想办法联系我", e);
                }
            }
        }
        HashMap<String, Object> res = new HashMap<>();
        res.put("totalCount",totalCount);
        res.put("messages",messages);
        return res;
    }

    @Override
    public HashMap<String, Object> getCommunicationByUserId(Integer id, Integer start, Integer num, boolean me) {
        int totalCount = messageDao.getUsrMessageTotalNum(id);
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
                    throw new SystemException(Code.SYSTEM_IO_ERR, "后台文件读写时发生异常，已开启事务所以您的数据是安全的不用担心，您可重试或想办法联系我", e);
                }
            }
        }
        HashMap<String, Object> res = new HashMap<>();
        res.put("totalCount",totalCount);
        res.put("messages",messages);
        return res;
    }

    @Override
    public JsonContentReturn commitAnoMessage(String mac, String content) {
        if(messageDao.getAnoIdByMac(mac) == null){
            messageDao.createAnoAccount(mac);
        }
        int id = messageDao.getAnoIdByMac(mac);
        AnonymousMessage message = new AnonymousMessage();
        message.setDate(new Date());
        JsonContentReturn ret = new JsonContentReturn();
        ret.setContent(null);
        if (content.length() > 100) {
            String path = "../AllMsg/Ano/Msg/" + id + "/";
            String filePath = ContentHandler.handle(path, content);
            if ("".equals(filePath)) {
                ret.setStatus(false);
                ret.setDescription("New file failed");
                return ret;
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
        return ret;
    }

    @Override
    public JsonContentReturn commitMessage(int id, String content) {
        Message message = new Message();
        message.setDate(new Date());
        JsonContentReturn ret = new JsonContentReturn();
        ret.setContent(null);
        if (content.length() > 100) {
            String path = "../AllMsg/Usr/Msg/" + id + "/";
            String filePath = ContentHandler.handle(path, content);
            if ("".equals(filePath)) {
                ret.setStatus(false);
                ret.setDescription("New file failed");
                return ret;
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
        return ret;
    }

    @Override
    public JsonContentReturn commitAnoReply(int id, String content) {
        AnonymousMessage message = new AnonymousMessage();
        message.setDate(new Date());
        JsonContentReturn ret = new JsonContentReturn();
        ret.setContent(null);
        if (content.length() > 100) {
            String path = "../AllMsg/Ano/Rep/" + id + "/";
            String filePath = ContentHandler.handle(path, content);
            if ("".equals(filePath)){
                ret.setStatus(false);
                ret.setDescription("New file failed");
                return ret;
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
        return ret;
    }

    @Override
    public JsonContentReturn commitReply(int id, String content) {
        Message message = new Message();
        message.setDate(new Date());
        JsonContentReturn ret = new JsonContentReturn();
        ret.setContent(null);
        if (content.length() > 100) {
            String path = "../AllMsg/Usr/Rep/" + id + "/";
            String filePath = ContentHandler.handle(path, content);
            if ("".equals(filePath)){
                ret.setStatus(false);
                ret.setDescription("New file failed");
                return ret;
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
        return ret;
    }

    @Override
    public void transformToUser(Integer anoId, Integer usrId) {
        List<AnonymousMessage> messages = messageDao.selectAnoMessageById(anoId,null,null);
        for (AnonymousMessage message : messages) {
            if(message.isLocal()){
                String filePath = message.getContent();
                String targetPath = filePath.replace("Ano","Usr").replace("/"+anoId+"/","/"+usrId+"/");
                try {
                    File path = new File(targetPath.substring(0,targetPath.lastIndexOf("/")));
                    path.mkdirs();
                    BufferedReader br = new BufferedReader(new FileReader(filePath));
                    BufferedWriter bw = new BufferedWriter(new FileWriter(targetPath));
                    StringBuilder sb = new StringBuilder();
                    String tmp = br.readLine();
                    sb.append(tmp);
                    while((tmp = br.readLine()) != null){
                        sb.append("\n").append(tmp);
                    }
                    bw.write(sb.toString());
                    bw.close();
                    br.close();
                    File old = new File(filePath);
                    old.delete();
                    message.setContent(targetPath);
                } catch (IOException e) {
                    throw new SystemException(Code.SYSTEM_IO_ERR, "后台文件读写时发生异常，已开启事务所以您的数据是安全的不用担心，您可重试或想办法联系我", e);
                }
            }
        }
        new File("../AllMsg/Ano/Msg/"+anoId).delete();
        new File("../AllMsg/Ano/Rep/"+anoId).delete();
        AnoUnread anoUnread = messageDao.checkAnoUnread(anoId);
        int anoNum = anoUnread.getNum();
        addUsrUnread(usrId,anoNum);
        MyUnread myUnread = messageDao.checkAnoExist(anoId);
        int myNum = myUnread.getNum();
        addMyUsrUnread(usrId,myNum);
        messageDao.insertFromAno(messages,usrId);
        messageDao.deleteAnoUnread(anoId);
        messageDao.deleteMyAnoUnread(anoId);
        messageDao.deleteAnoMsgById(anoId);
        messageDao.deleteAnoUsr(anoId);
    }
//    String contentHandle(String path, String content){
//        File dir = new File(path);
//        dir.mkdirs();
//        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
//        String filePath = path + df.format(new Date()) + ".txt";
//        try {
//            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
//            bw.write(content);
//            bw.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            return "";
//        }
//        return filePath;
//    }
    @Override
    public JsonContentReturn addMyAnoUnread(int anoId) {
        return addMyAnoUnread(anoId, null);
    }

    @Override
    public JsonContentReturn addMyAnoUnread(int anoId, Integer num) {
        MyUnread check = messageDao.checkAnoExist(anoId);
        boolean flag;
        if(check == null){
            flag = (messageDao.insertMyAnoUnread(anoId, 1) != 0);
        }
        else{
            if (num == null){
                num = check.getNum();
            }
            flag = (messageDao.updateMyAnoUnread(anoId, num+1) != 0);
        }
        JsonContentReturn ret = new JsonContentReturn();
        ret.setDescription("addMyAnoUnread");
        ret.setStatus(flag);
        return ret;
    }
    @Override
    public JsonContentReturn addMyUsrUnread(int usrId) {
        return addMyUsrUnread(usrId, null);
    }

    @Override
    public JsonContentReturn addMyUsrUnread(int usrId, Integer num) {
        MyUnread check = messageDao.checkAnoExist(usrId);
        boolean flag;
        if(check == null){
            flag = (messageDao.insertMyUsrUnread(usrId, 1) != 0);
        }
        else{
            if (num == null){
                num = check.getNum();
            }
            flag = (messageDao.updateMyUsrUnread(usrId, num+1) != 0);
        }
        JsonContentReturn ret = new JsonContentReturn();
        ret.setDescription("addMyUnread");
        ret.setStatus(flag);
        return ret;
    }

    @Override
    public List<MyUnread> checkMyUnread() {
        List<MyUnread> myUnread = messageDao.checkMyUnread();
        return myUnread;
    }
    @Override
    public JsonContentReturn addAnoUnread(int anoId) {
        return addAnoUnread(anoId, null);
    }

    @Override
    public JsonContentReturn addAnoUnread(int anoId, Integer num) {
        AnoUnread check = messageDao.checkAnoUnread(anoId);
        boolean flag;
        if(check == null){
            flag = (messageDao.insertAnoUnread(anoId, 1) != 0);
        }
        else{
            if (num == null){
                num = check.getNum();
            }
            flag = (messageDao.updateAnoUnread(anoId, num+1) != 0);
        }
        JsonContentReturn ret = new JsonContentReturn();
        ret.setDescription("addAnoUnread");
        ret.setStatus(flag);
        return ret;
    }

    @Override
    public AnoUnread checkAnoUnread(int anoId) {
        AnoUnread anoUnread = messageDao.checkAnoUnread(anoId);
        return anoUnread;
    }
    @Override
    public JsonContentReturn addUsrUnread(int usrId) {
        return addUsrUnread(usrId, null);
    }

    @Override
    public JsonContentReturn addUsrUnread(int usrId, Integer num) {
        UsrUnread check = messageDao.checkUsrUnread(usrId);
        boolean flag;
        if(check == null){
            flag = (messageDao.insertUsrUnread(usrId, 1) != 0);
        }
        else{
            if (num == null){
                num = check.getNum();
            }
            flag = (messageDao.updateUsrUnread(usrId, num+1) != 0);
        }
        JsonContentReturn ret = new JsonContentReturn();
        ret.setDescription("addUsrUnread");
        ret.setStatus(flag);
        return ret;
    }

    @Override
    public UsrUnread checkUsrUnread(int usrId) {
        UsrUnread usrUnread = messageDao.checkUsrUnread(usrId);
        return usrUnread;
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