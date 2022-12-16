package me.looouiiis.pojo;

public class AnoUnread {
    private int anoId;
    private int num;

    public int getAnoId() {
        return anoId;
    }

    public void setAnoId(int anoId) {
        this.anoId = anoId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "AnoUnread{" +
                "anoId=" + anoId +
                ", num=" + num +
                '}';
    }
}
