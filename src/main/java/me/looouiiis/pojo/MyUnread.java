package me.looouiiis.pojo;

public class MyUnread {
    private Integer anoId;
    private Integer usrId;
    private int num;

    public Integer getAnoId() {
        return anoId;
    }

    public void setAnoId(Integer anoId) {
        this.anoId = anoId;
    }

    public Integer getUsrId() {
        return usrId;
    }

    public void setUsrId(Integer usrId) {
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
        return "MyUnread{" +
                "anoId=" + anoId +
                ", usrId=" + usrId +
                ", num=" + num +
                '}';
    }
}
