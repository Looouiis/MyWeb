package me.looouiiis.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Message {
    int userId;
    String content;
    String date;
    boolean local;
    boolean message;
    int id;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setDate(Date  date) {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "userId=" + userId +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", local=" + local +
                ", message=" + message +
                ", id=" + id +
                '}';
    }
}
