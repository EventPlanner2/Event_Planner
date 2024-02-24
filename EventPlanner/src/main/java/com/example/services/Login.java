package com.example.services;

import com.example.entites.User;

import java.util.ArrayList;
import java.util.List;

public class Login {

    private final List<User> users;

    public String errorMessage;
    public Login(List<User> users)
    {
        this.users = users;
        errorMessage = "";
    }

    public User LoginPerformed(String username , String password)
    {


        if(username.isEmpty() || password.isEmpty()) {
            setErrorMessage("Login Failed");
            return null;
        }

        for(User user : users) {
            if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }

        setErrorMessage("Login Failed");
        return null;

    }



    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
