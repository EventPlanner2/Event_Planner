package com.example.data;

import com.example.entites.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private static List<User> users = new ArrayList<>();

    public UserData(){

        initializeData();

    }
//hello



    private static void initializeData(){
        // initial objects (could be replaced by database)

        User u1 = new User("Admin","123456","Admin@Gmail.com",'a'); // Admin
        User u2 = new User("FactoryX","FactoryX123","Factoryx@Gmail.com",'s'); // Service-provider
        User u3 = new User("Ahmad","Ahmad12345","Ahmad123@Gmail.com",'c'); // Client

        users.add(u1);users.add(u2);users.add(u3);
    }

    public List<User> getUsers(){
        return users;
    }

    public static void addUser(String username , String password , String email , Character role){
        User u1 = new User(username,password,email,role);
        users.add(u1);
    }



}
