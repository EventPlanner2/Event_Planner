package com.example.services;

import com.example.entites.ServiceProvider;
import com.example.entites.User;

import java.util.List;

public class Login {

    private final List<User> users;

    private String errorMessage;
    private SPAcc spAccObj;

    public void setSpAccObj(SPAcc spAccObj) {
        this.spAccObj = spAccObj;
    }

    public Login(List<User> users) {
        this.users = users;
        errorMessage = "";
    }

    public User loginPerformed(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            setErrorMessage("Login Failed");
            return null;
        }

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                if (user.getRole() == 's') {
                    ServiceProvider obj = ServiceProvider.getSPFromData(username);
                    spAccObj.setCompleteAccount ( obj.isFirstLogin () );
                    spAccObj.setSP(obj);
                }
                return user;
            }
        }

        setErrorMessage("Login Failed");
        return null;

    }


    private void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public String getErrorMessage() {
        return errorMessage;
    }
}