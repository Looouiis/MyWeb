package me.looouiiis.pojo;

public class User {
    Integer id;
    String username;
    String password;
    Boolean isMe;
    Boolean gender;
    public User(){}

    public User(Integer id, String username, String password, Boolean isMe, Boolean gender) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.isMe = isMe;
        this.gender = gender;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isMe=" + isMe +
                ", gender=" + gender +
                '}';
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsMe() {
        return isMe;
    }

    public void setIsMe(Boolean isMe) {
        this.isMe = isMe;
    }
}
