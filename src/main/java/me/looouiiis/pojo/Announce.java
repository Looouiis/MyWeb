package me.looouiiis.pojo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Announce {
    private int id;
    private String content;
    private boolean local;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isLocal() {
        return local;
    }

    public void setLocal(boolean local) {
        this.local = local;
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

    @Override
    public String toString() {
        return "Announce{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", local=" + local +
                ", date='" + date + '\'' +
                '}';
    }
}
