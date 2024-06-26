package com.example;
import com.example.data.EventData;
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
    private SignUp signUpService;
    private AddRoom addRoomService;
    private SPAcc spAccount;
    private AddEvent addEventService;
    private DeleteUpdateEvent deleteUpdateEventService;
    private SearchServiceProvider searchSP;
    private ReserveRoom reserveRoomService;
    private ReserveSP reserveSPService;
    private BookEvent bookEventService;
    private UpgradeClient upgradeClient;
    private UserData ud;

    private DeleteRoom deleteRoomService;

    private Calender calenderService;

    public App()
    {
        ud=new UserData();
        users=ud.getUsers();
        loginService = new Login(users);
        loggedInUser = null;
        signUpService = new SignUp(users);
        new RoomData().initializeData ();
        rooms = RoomData.getRooms();
        addRoomService = new AddRoom(rooms);
        spAccount = new SPAcc();
        loginService.setSpAccObj(spAccount);
        addEventService=new AddEvent();
        searchSP = new SearchServiceProvider();
        deleteUpdateEventService = new DeleteUpdateEvent(addEventService);
      
        reserveRoomService = new ReserveRoom(loggedInUser);
        reserveSPService = new ReserveSP(loggedInUser);
        bookEventService = new BookEvent();

        new EventData().initializeData ();
        upgradeClient = new UpgradeClient(loggedInUser);

        calenderService = new Calender(loggedInUser);

        deleteRoomService=new DeleteRoom();

    }

    public DeleteRoom getDeleteRoomService() {
        return deleteRoomService;
    }

    public Calender getCalenderService() {
        return calenderService;
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



    public SignUp getSignUpService() {
        return signUpService;
    }

    public AddRoom getAddRoomService() {
        return addRoomService;
    }

    public SPAcc getSPAccount() {
        return spAccount;
    }

    public AddEvent getAddEventService() {
        return addEventService;
    }

    public DeleteUpdateEvent getDeleteUpdateEventService() {
        return deleteUpdateEventService;
    }

    public SearchServiceProvider getSearchSP() {
        return searchSP;
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


}
