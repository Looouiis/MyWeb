package me.looouiiis.pojo;

public class UsrUnread {
    private int usrId;
    private int num;

    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
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
                "usrId=" + usrId +
                ", num=" + num +
                '}';
    }
}
