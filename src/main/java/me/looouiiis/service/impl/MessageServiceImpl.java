package me.looouiiis.service.impl;

import me.looouiiis.dao.AccountDao;
import me.looouiiis.dao.MessageDao;
import me.looouiiis.pojo.AnonymousMessage;
import me.looouiiis.service.MessageService;
import me.looouiiis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    public void setMessageDao(MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    private MessageDao messageDao;

    @Override
    public String getAnoCommunicationByMac(String mac) {
        return null;
    }

    @Override
    public String getCommunicationByUserId(Integer id) {
        return null;
    }

    @Override
    public String commitAnoMessage() {
        return null;
    }

    @Override
    public String commitMessage() {
        return null;
    }

    @Override
    public String commitAnoReply() {
        return null;
    }

    @Override
    public String commitReply() {
        return null;
    }

    @Override
    public void transformToUser() {

    }
}
