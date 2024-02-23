package com.example;

import com.example.data.UserData;
import com.example.entites.User;
import com.example.services.Login;

import java.util.ArrayList;
import java.util.List;

public class App {


    public List<User> users;
    public Login loginService;
    public User loggedInUser;
    public App()
    {

        UserData ud = new UserData();
        users=ud.getUsers();
        loginService = new Login(users);
        loggedInUser = null;
    }




}
