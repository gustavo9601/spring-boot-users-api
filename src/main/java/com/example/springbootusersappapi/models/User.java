package com.example.springbootusersappapi.models;

public class User {
    private String nickName;
    private String username;
    private String password;

    public User() {
    }

    public User(String nickName, String username, String password) {
        this.nickName = nickName;
        this.username = username;
        this.password = password;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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
}
