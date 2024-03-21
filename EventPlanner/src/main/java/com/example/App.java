package com.example;

import com.example.data.RoomData;
import com.example.data.UserData;
import com.example.entites.Room;
import com.example.entites.User;
import com.example.services.*;

import java.util.List;
//bmw
public class App {

    public List<User> users;
    public List<Room> rooms;
    public Login loginService;
    public User loggedInUser;
    public String currentPage;
    public SignUp signUpService;
    public AddRoom addRoomService;
    public SPAcc SPAccount;
    public AddEvent addEventService;
    public DeleteUpdateEvent deleteUpdateEventService;
    public SearchServiceProvider SearchSP;
    public ReserveRoom reserveRoomService;
    public ReserveSP reserveSPService;
    public BookEvent bookEventService;

    public UpgradeClient upgradeClient;
    public UserData ud;





    public App()
    {
        ud=new UserData();
        users=ud.getUsers();
        loginService = new Login(users);
        loggedInUser = null;
        signUpService = new SignUp(users);

        RoomData rd = new RoomData();
        rooms = rd.getRooms();
        addRoomService = new AddRoom(rooms);
        SPAccount = new SPAcc();
        loginService.setSPAccObj(SPAccount);
        addEventService=new AddEvent();
        SearchSP = new SearchServiceProvider();
        deleteUpdateEventService = new DeleteUpdateEvent(addEventService);
      
        reserveRoomService = new ReserveRoom(loggedInUser);
        reserveSPService = new ReserveSP(loggedInUser);
        bookEventService = new BookEvent();


        upgradeClient =new UpgradeClient(loggedInUser);

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
