package me.looouiiis.service;

public interface MessageService {
    String getAnoCommunicationByMac(String mac, Integer start, Integer num, boolean me);
    String getCommunicationByUserId(Integer id, Integer start, Integer num, boolean me);
    String commitAnoMessage(String mac, String content);
    String commitMessage(int id, String content);
    String commitAnoReply(int id, String content);
    String commitReply(int id, String content);
    void transformToUser(Integer anoId, Integer usrId);
    String addMyAnoUnread(int anoId);
    String addMyAnoUnread(int anoId, Integer num);
    String addMyUsrUnread(int usrId);
    String addMyUsrUnread(int usrId,Integer num);
    String checkMyUnread();
    String addAnoUnread(int anoId);
    String addAnoUnread(int anoId, Integer num);
    String checkAnoUnread(int anoId);
    String addUsrUnread(int usrId);
    String addUsrUnread(int usrId, Integer num);
    String checkUsrUnread(int usrId);
    void deleteMyAnoUnread(int anoId);
    void deleteMyUsrUnread(int usrId);
    void deleteAnoUnread(int anoId);
    void deleteUsrUnread(int usrId);
}
