package com.example.services;

import com.example.App;
import com.example.data.UserData;
import com.example.entites.User;

import java.util.List;

public class SignUp {

    private final List<User> users;

    public String msg = "";
    public SignUp(List<User> users) {
        this.users = users;
    }

    public boolean register(String username,String password,String email,String role){
        if(email.isEmpty()){setMsg("some fields are missing");return false;}
        if(email_existed(email)) {setMsg("the email you registred is not available");return false;}
        if(!email_format_check(email)){setMsg("the email you registred is invalid format");return false;}
        if(username.isEmpty()){setMsg("some fields are missing");return false;}
        if(username_existed(username)){setMsg("the username you entered is already registred");return false;}
        if(password.isEmpty()){setMsg("some fields are missing");return false;}
        if(password.length() < 8){setMsg("your password is weak");return false;}
        char r = role.toLowerCase().toCharArray()[0];
        if(r != 's' && r != 'c'){setMsg("you entered an invalid role");return false;}
        UserData.addUser(username,password,email,role.toLowerCase().toCharArray()[0]);
        return true;
    }

    public boolean username_existed(String username){
        boolean flag = false;
        for(User u : users){
            flag = (username.equals(u.getUsername()));
        }
        return flag;
    }
    public boolean email_existed(String email){
        boolean flag = false;
        for(User u : users){
            flag = (email.equalsIgnoreCase(u.getContactEmail()));
        }
        return flag;
    }
    private boolean email_format_check(String email){

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
        if (lastDotIndex <= atIndex) {
            return false;
        }

        return true;
    }
    private void setMsg(String msg) {
        this.msg = msg;
    }



}
