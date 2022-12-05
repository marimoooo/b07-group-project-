package com.example.b07;

public class Model {
    String username;
    String password;

    public Model(String username, String password) {
        this.password = password;
        this.username = username;

    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
