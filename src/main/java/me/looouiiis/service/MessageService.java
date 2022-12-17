package me.looouiiis.service;

import me.looouiiis.pojo.*;

import java.util.HashMap;
import java.util.List;

public interface MessageService {
    Integer getAnoIdByMac(String mac);
    HashMap<String, Object> getAnoCommunicationByMac(String mac, Integer start, Integer num, boolean me);
    HashMap<String, Object> getCommunicationByUserId(Integer id, Integer start, Integer num, boolean me);
    JsonContentReturn commitAnoMessage(String mac, String content);
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
}
