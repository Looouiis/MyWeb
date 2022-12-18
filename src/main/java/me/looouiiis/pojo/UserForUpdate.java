package me.looouiiis.pojo;

public class UserForUpdate {
    Integer id;
    String username;
    String oriPassword;
    String newPassword;
    Boolean isMe;
    Boolean gender;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOriPassword() {
        return oriPassword;
    }

    public void setOriPassword(String oriPassword) {
        this.oriPassword = oriPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public Boolean getMe() {
        return isMe;
    }

    public void setMe(Boolean me) {
        isMe = me;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }
}
