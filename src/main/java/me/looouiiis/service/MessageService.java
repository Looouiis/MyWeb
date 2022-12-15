package me.looouiiis.service;

public interface MessageService {
    String getAnoCommunicationByMac(String mac);
    String getCommunicationByUserId(Integer id);
    String commitAnoMessage();
    String commitMessage();
    String commitAnoReply();
    String commitReply();
    void transformToUser();
}
