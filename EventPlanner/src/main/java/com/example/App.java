package com.example;

import com.example.data.RoomData;
import com.example.data.UserData;
import com.example.entites.Room;
import com.example.entites.User;
import com.example.services.*;


import java.util.ArrayList;
import java.util.List;

public class App {

    public List<User> users;
    public List<Room> rooms;
    public Login loginService;
    public User loggedInUser;
    public String currentPage;
    public SignUp signUpService;
    public AddRoom addRoomService;


    public App()
    {

        UserData ud = new UserData();
        users=ud.getUsers();
        loginService = new Login(users);
        loggedInUser = null;
        signUpService = new SignUp(users);

        RoomData rd = new RoomData();
        rooms = rd.getRooms();
        addRoomService = new AddRoom(rooms);
    }


    public String logoutPerform(){
        if(loggedInUser != null) {
            loggedInUser = null;
            return "Successful Logout";
        }
        else {
            return "Failed Logout";
        }
    }




}
