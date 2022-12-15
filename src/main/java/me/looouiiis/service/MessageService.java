package me.looouiiis.service;

public interface MessageService {
    String getAnoCommunicationByMac(String mac, Integer start, Integer num);
    String getCommunicationByUserId(Integer id, Integer start, Integer num);
    String  commitAnoMessage(String mac, String content);
    boolean commitMessage(int id, String content);
    String commitAnoReply(int id, String content);
    boolean commitReply(int id, String content);
    void transformToUser(int anoId, int usrId);
}
