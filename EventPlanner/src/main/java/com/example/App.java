package com.example;

import com.example.data.RoomData;
import com.example.data.UserData;
import com.example.entites.Room;
import com.example.entites.User;
import com.example.services.*;
import java.util.List;
public class App {
    private List<User> users;
    private List<Room> rooms;
    private Login loginService;
    private User loggedInUser;
    private String currentPage;
    private SignUp signUpService;
    private AddRoom addRoomService;
    private SPAcc SPAccount;
    private AddEvent addEventService;
    private DeleteUpdateEvent deleteUpdateEventService;
    private SearchServiceProvider SearchSP;
    private ReserveRoom reserveRoomService;
    private ReserveSP reserveSPService;
    private BookEvent bookEventService;
    private UpgradeClient upgradeClient;
    private UserData ud;
   private RoomData rd ;




    public App()
    {
        ud=new UserData();
        users=ud.getUsers();
        loginService = new Login(users);
        loggedInUser = null;
        signUpService = new SignUp(users);

         rd = new RoomData();
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
    public List<User> getUsers() {
        return users;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Login getLoginService() {
        return loginService;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public SignUp getSignUpService() {
        return signUpService;
    }

    public AddRoom getAddRoomService() {
        return addRoomService;
    }

    public SPAcc getSPAccount() {
        return SPAccount;
    }

    public AddEvent getAddEventService() {
        return addEventService;
    }

    public DeleteUpdateEvent getDeleteUpdateEventService() {
        return deleteUpdateEventService;
    }

    public SearchServiceProvider getSearchSP() {
        return SearchSP;
    }

    public ReserveRoom getReserveRoomService() {
        return reserveRoomService;
    }

    public ReserveSP getReserveSPService() {
        return reserveSPService;
    }

    public BookEvent getBookEventService() {
        return bookEventService;
    }

    public UpgradeClient getUpgradeClient() {
        return upgradeClient;
    }

    public UserData getUserData() {
        return ud;
    }

}
