package com.example.services;

import com.example.data.UserData;
import com.example.entites.User;

import java.util.List;

public class SignUp {

    private final List<User> users;

    private String msg = "";

    public SignUp(List<User> users) {
        this.users = users;
    }

    private static final String SOME_MISSING_FIELDS = "some fields are missing";

    public boolean register(String username, String password, String email, String role) {
        if (email.isEmpty()) {
            setMsg(SOME_MISSING_FIELDS);
            return false;
        }
        if (emailExisted(email)) {
            setMsg("the email you registred is not available");
            return false;
        }
        if (!emailFormatCheck(email)) {
            setMsg("the email you registred is invalid format");
            return false;
        }
        if (username.isEmpty()) {
            setMsg(SOME_MISSING_FIELDS);
            return false;
        }
        if (usernameExisted(username)) {
            setMsg("the username you entered is already registred");
            return false;
        }
        if (password.isEmpty()) {
            setMsg(SOME_MISSING_FIELDS);
            return false;
        }
        if (password.length() < 8) {
            setMsg("your password is weak");
            return false;
        }
        char r = role.toLowerCase().toCharArray()[0];
        if (r != 's' && r != 'c') {
            setMsg("you entered an invalid role");
            return false;
        }
        UserData.addUser(username, password, email, role.toLowerCase().toCharArray()[0]);
        return true;
    }

    public boolean usernameExisted(String username) {
        boolean flag = false;
        for (User u : users) {
            flag = (username.equals(u.getUsername()));
            if (flag) return true;
        }
        return flag;
    }

    public boolean emailExisted(String email) {
        boolean flag = false;
        for (User u : users) {
            flag = (email.equalsIgnoreCase(u.getContactEmail()));
            if (flag) return true;
        }
        return flag;
    }

    private boolean emailFormatCheck(String email) {

        if (email == null || email.isEmpty()) {
            return false;
        }

        int atSymbolCount = 0;
        for (char c : email.toCharArray()) {
            if (c == '@') {
                atSymbolCount++;
            }
        }
        if (atSymbolCount != 1) {
            return false;

        }

        int atIndex = email.indexOf('@');
        int lastDotIndex = email.lastIndexOf('.');
        return lastDotIndex > atIndex;





    }

    private void setMsg(String msg) {
        this.msg = msg;
    }


    public String getMsg() {
        return msg;
    }}