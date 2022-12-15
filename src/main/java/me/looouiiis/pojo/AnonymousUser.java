package me.looouiiis.pojo;

public class AnonymousUser {
    int id;
    String mac;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    @Override
    public String toString() {
        return "AnonymousUser{" +
                "id=" + id +
                ", mac='" + mac + '\'' +
                '}';
    }
}
