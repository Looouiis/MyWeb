package me.looouiiis.service;

import me.looouiiis.pojo.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
@Transactional(rollbackFor = {Exception.class})
public interface MessageService {
//    Integer getAnoIdByMac(String mac);
    @Transactional(rollbackFor = {Exception.class})
    HashMap<String, Object> getAnoCommunication(Integer id, Integer start, Integer num, boolean me);
    @Transactional(rollbackFor = {Exception.class})
    HashMap<String, Object> getCommunicationByUserId(Integer id, Integer start, Integer num, boolean me);
    JsonContentReturn commitAnoMessage(Integer id, String content);
    JsonContentReturn commitMessage(int id, String content);
    JsonContentReturn commitAnoReply(int id, String content);
    JsonContentReturn commitReply(int id, String content);
    void transformToUser(Integer anoId, Integer usrId);
    JsonContentReturn addMyAnoUnread(int anoId);
    JsonContentReturn addMyAnoUnread(int anoId, Integer num);
    JsonContentReturn addMyUsrUnread(int usrId);
    JsonContentReturn addMyUsrUnread(int usrId,Integer num);
    List<MyUnread> checkMyUnread();
    JsonContentReturn addAnoUnread(int anoId);
    JsonContentReturn addAnoUnread(int anoId, Integer num);
    AnoUnread checkAnoUnread(int anoId);
    JsonContentReturn addUsrUnread(int usrId);
    JsonContentReturn addUsrUnread(int usrId, Integer num);
    UsrUnread checkUsrUnread(int usrId);
    void deleteMyAnoUnread(int anoId);
    void deleteMyUsrUnread(int usrId);
    void deleteAnoUnread(int anoId);
    void deleteUsrUnread(int usrId);
    HashMap<String, Object> getAnoCommentByMsgId(int msgId);
    JsonContentReturn commitAnoComment(int msgId, String content);
    JsonContentReturn commitAnoCommentReply(int msgId, String content);
    HashMap<String, Object> getUsrCommentByMsgId(int msgId);
    JsonContentReturn commitUsrComment(int msgId, String content);
    JsonContentReturn commitUsrCommentReply(int msgId, String content);
}
