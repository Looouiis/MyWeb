package me.looouiiis.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AnoComment {
    private int msgId;
    private String content;
    private String date;
    private boolean local;
    private boolean message;

    public int getMsgId() {
        return msgId;
    }

    public void setMsgId(int msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.date = df.format(date);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
    }

    public boolean isMessage() {
        return message;
    }

    public void setMessage(boolean message) {
        this.message = message;
    }
}
