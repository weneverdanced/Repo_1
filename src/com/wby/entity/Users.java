package com.wby.entity;

public class Users {
    private String id;
    private String username;
    private String password;
    private String type;
    private String email;

    public Users(String id, String username, String password, String type, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.type = type;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
