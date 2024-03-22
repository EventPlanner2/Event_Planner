package com.example;

import com.example.data.RoomData;
import com.example.entites.Event;
import com.example.entites.Room;
import com.example.entites.ServiceProvider;
import com.example.entites.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainMenu {
    public static final String LOCATION_CRITERIA = "Location";
    public static final String TYPE_CRITERIA = "Type";
    public static final String PRICE_CRITERIA = "Price";
    private static final Logger logger = Logger.getLogger(MainMenu.class.getName());
    private static final String WELCOME_STRING = """
                  
             __          __    _                                _______        _____  _               _          _____   _                                  
             \\ \\        / /   | |                              |__   __|      / ____|| |             | |        |  __ \\ | |                                 
              \\ \\  /\\  / /___ | |  ___  ___   _ __ ___    ___     | |  ___   | (___  | |_  _   _   __| | _   _  | |__) || |  __ _  _ __   _ __    ___  _ __ 
               \\ \\/  \\/ // _ \\| | / __|/ _ \\ | '_ ` _ \\  / _ \\    | | / _ \\   \\___ \\ | __|| | | | / _` || | | | |  ___/ | | / _` || '_ \\ | '_ \\  / _ \\| '__|
                \\  /\\  /|  __/| || (__| (_) || | | | | ||  __/    | || (_) |  ____) || |_ | |_| || (_| || |_| | | |     | || (_| || | | || | | ||  __/| |   
                 \\/  \\/  \\___||_| \\___|\\___/ |_| |_| |_| \\___|    |_| \\___/  |_____/  \\__| \\__,_| \\__,_| \\__, | |_|     |_| \\__,_||_| |_||_| |_| \\___||_|   
                                                                                                       __/ |                                             
                                                                                                      |___/                                              
            """;
    App app = new App();
    private String username;
    private String password;
    private User user;


    Scanner input = new Scanner(System.in);

    public void menu() {
        boolean isRunning = true;
        while (isRunning) {
            logger.info(WELCOME_STRING);
            logger.info("""
                    +--------------------+
                    | 1. log in          |
                    | 2. Sign up         |
                    | X. Exit            |
                    +--------------------+
                    """);
            logger.info("Enter your choice please : ");
            String choose = input.next();
            switch (choose) {
                case "1":
                    logIn();
                    break;
                case "2":
                    signUp();
                    break;
                case "X":
                    isRunning = false; // Terminate the loop
                    break;
                default:
                    logger.info("Invalid choice. Please try again.");
                    break;
            }
        }


    }

    public void logIn() {
        char role = 'a';
        while (true) {
            if (Back()) return;
            logger.info("Please enter the username and the password :");
            logger.info("Username :");
            username = input.next();
            logger.info("Password :");
            password = input.next();
            user = app.getLoginService().LoginPerformed(username, password);
            if (user == null) {
                logger.info(app.getLoginService().getErrorMessage());
            } else {
                app.setLoggedInUser(user);
                role = user.getRole();
                break;
            }
        }

        app.setLoggedInUser(user);
        switch (role) {
            case 'a':
                logger.info("""
                        ╔════════════════════════╗
                              ║ You logged in as Admin ║
                              ╚════════════════════════╝
                        """);
                Page("2.Add Room", user.getRole());
                break;

            case 's':
                logger.info("""
                        ╔═══════════════════════════════════╗
                              ║ You logged in as Service Provider ║
                              ╚═══════════════════════════════════╝
                        """);
                ServiceProvider.getSPFromData(username).setFirstLogin(true);
                Page("2.Complete", user.getRole());
                break;

            case 'c':
                logger.info("""
                        ╔════════════════════════╗
                              ║ You logged in as Client║
                              ╚════════════════════════╝
                        """);
                Page("2.Upgrade", user.getRole());
                break;

            default:
                break;
        }

    }

    public void signUp() {
        while (true) {
            if (Back()) return;
            logger.info("Please enter your username :");
            String username = input.next();
            logger.info("Please enter your password :");
            String password = input.next();
            logger.info("Please enter your email :");
            String email = input.next();
            logger.info("Please enter your role :");
            String role = input.next();
            boolean flag = app.getSignUpService().register(username, password, email, role);
            if (flag) {
                break;
            } else {
                logger.info(app.getSignUpService().getMsg());
            }
        }
    }


    public void Page(String specification, char role) {
        while (true) {
            String info = generateMenu(specification, role);
            logger.info(info);
            logger.info("Enter your choice please : ");

            String choose = input.next();
            switch (choose) {
                case "1":
                    accountInformation(role);
                    break;
                case "2":
                    handleChoiceTwo(role);
                    break;
                case "3":
                    searchServiceProvider();
                    break;
                case "4":
                    showUpcomingEvents();
                    break;
                case "5":
                    handleChoiceFive(role);
                    if(role == 's'){
                        return;
                    }
                    break;
                case "6":
                    deleteRoom();
                    break;
                case "7":
                    return;
                case "X":
                    System.exit(0);
                default:
                    break;
            }
        }
    }

    private String generateMenu(String specification, char role) {
        StringBuilder menu = new StringBuilder();
        menu.append("1. Show account information\n")
                .append("    ").append(specification).append("\n")
                .append("3. Search Service Provider\n")
                .append("4. Show upcoming events\n");
        if (role == 'c') {
            menu.append("5. Enter organizer mode\n");
        } else if (role == 'a') {
            menu.append("5. Show all rooms\n")
                    .append("6. Delete room\n");
        }
        if (role == 's') {
            menu.append("5. Log out\n");
        } else {
            menu.append("7. Log out\n");
        }
        menu.append("X. Exit");
        return menu.toString();
    }

    private void handleChoiceTwo(char role) {
        if (role == 'a') {
            app.getAddRoomService().setLoggedInUser(user);
            addRoom();
        } else if (role == 's') {
            complete();
        } else {
            upgrade();
        }
    }

    private void handleChoiceFive(char role) {
        if(role == 'a'){
            showAllRooms();
        }
    }

    public void deleteRoom (){
        showAllRooms();
        logger.info("Please enter the id of the room to be deleted");
        String id=input.next();
        app.deleteRoomService.deleteRoomPerformed(id);
        logger.info(app.deleteRoomService.getMsg());
    }

    public void accountInformation(char role) {
        while (true) {
            String info = """
                    Username : """ + user.getUsername() + """
                    Password : """ + user.getPassword() + """
                    Email : """ + user.getContactEmail() + """
                    """;

            if (role == 'a')
                info += "      Role : Admin";
            else if (role == 's') {
                info += "Role : Service Provider";
            } else {
                info += "Role : Clint";
            }
            logger.info(info);
            if (Back())
                return;
        }
    }

    public void addRoom() {
        while (true) {
            if (Back()) return;
            logger.info("Please enter the name of the new room : ");
            String roomName = input.next();
            logger.info("Please enter the Capacity of the new room : ");
            String roomCapacity = input.next();
            logger.info("Please enter the cost per hour of the new room :");
            String roomCost = input.next();
            logger.info("Please enter the description of the new room :");
            String roomDes = input.next();
            logger.info("Please enter the Availability of the new room :");
            String roomAvailability = input.next();
            boolean flag = app.getAddRoomService().AddRoomPerformed(roomName, roomAvailability, roomCapacity, roomCost, roomDes);
            if (flag) {
                logger.info(app.getAddRoomService().getMsg());
                break;
            } else {
                logger.info(app.getAddRoomService().getMsg());
            }
        }


    }

    public void searchServiceProvider() {

        while (true) {
            if (Back()) return;
            String info = """
                    Search based on :
                          1. Location
                          2. Type
                          3. Price
                    Please enter your choice :
                    """;

            logger.info(info);
            String choose = input.next();
            switch (choose) {
                case "1":
                    app.getSearchSP().setSelectedCriteria(LOCATION_CRITERIA);
                    searchCriteria(LOCATION_CRITERIA);
                    break;
                case "2":
                    app.getSearchSP().setSelectedCriteria(TYPE_CRITERIA);
                    searchCriteria(TYPE_CRITERIA);
                    break;
                case "3":
                    app.getSearchSP().setSelectedCriteria(PRICE_CRITERIA);
                    searchCriteria(PRICE_CRITERIA);
                    break;
                case "":
                    app.getSearchSP().setSelectedCriteria("");
                    logger.info(app.getSearchSP().getErrorMsg());
                    break;
                default:
                    app.getSearchSP().setSelectedCriteria("a");
                    logger.info(app.getSearchSP().getErrorMsg());
                    break;
            }
        }
    }

    public void showUpcomingEvents() {
        for (Event e : app.getCalenderService().showUpcomingEvents()) {
            logger.info(e.getId() + " " + e.getEventName() + " " + e.getEventDescription() + " " + e.getStartDate() + " " + e.getEndDate() + " " + e.getStartClock() + " " + e.getEndClock() + " " + e.getAttendeeCount());
        }
    }

    public void showAllRooms (){
        for (Room r : RoomData.getRooms()){
            logger.info(r.getId() + " " + r.getName() + " " + r.getCapacity() + " " + r.getCostPerHour() + " " + r.getDescription() + " " + r.isAvailable());
        }

    }

    public void complete() {
        while (true) {
            if (Back()) return;
            if (!ServiceProvider.getSPFromData(user.getUsername()).isFirstLogin()) {
                logger.info(app.getSPAccount().getCompleteAccountMsg());
                return;
            }
            String location, productPrice, productType;
            logger.info("To complete your account please enter your:");
            logger.info("Location : ");
            location = input.next();
            logger.info("Product Price : ");
            productPrice = input.next();
            logger.info("Product Type : ");
            productType = input.next();
            productType += " " + input.next();

            boolean flag = app.getSPAccount().CompleteAccountPerform(location, productPrice, productType);

            if (flag) {
                logger.info(app.getSPAccount().getCompleteAccountMsg());
                ServiceProvider.getSPFromData(username).setFirstLogin(false);
                return;
            } else {
                logger.info(app.getSPAccount().getCompleteAccountMsg());
            }
        }
    }

    public void upgrade() {
        while (true) {
            if (Back()) return;
            app.getUpgradeClient().setLoggedInUser(user);
            app.getUpgradeClient().UpgradeClientPerform();
            logger.info(app.getUpgradeClient().getMsg());
        }
    }

    public void printCriteria(List<ServiceProvider> serviceProviders) {
        for (int i = 0; i < serviceProviders.size(); i++) {
            logger.info(String.format("%d. %s", i, serviceProviders.get(i).getUsername()));
        }
    }


    public void searchCriteria(String criteria) {
        List<ServiceProvider> tmpArray = new ArrayList<>();
        switch (criteria) {
            case "Location":
                logger.info("Please enter the name of the location to search :");
                String location = input.next();
                app.getSearchSP().setLocation(location);
                tmpArray.addAll(app.getSearchSP().SearchSPPerformed());
                printCriteria(tmpArray);
                return;
            case "Type":
                logger.info("Please enter the type of the Service provider : ");
                String type = input.next() + " " + input.next();
                app.getSearchSP().setType(type);
                printCriteria(app.getSearchSP().SearchSPPerformed());
                return;
            default:
                logger.info("Please enter the price of the product : ");
                String price = input.next();
                app.getSearchSP().setPrice(price);
                printCriteria(app.getSearchSP().SearchSPPerformed());
                return;
        }
    }


    public boolean Back() {
        logger.info("Please Enter B if you want to go back otherwise enter anything : ");
        String back = input.next();
        if (back.equals("B")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean organizerPage() {
        if (Back()) return true;
        if (!app.getAddEventService().isOrgnaizer(user.getUsername())) {
            logger.info("You must be an Organizer");
            return true;
        }
        while (true) {
            logger.info("""
                    .-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.
                          |  1. Add Event                                  |
                          |  2. Update Event                               |
                          !  3. Delete Event                               !
                          :  4. Search for Service Provider                :
                          .  5. Reserve Room for an Event                  .
                          .  6. Reserve Service Provider for an Event      .
                          :  7. Show upcoming Events                       :
                          !  8. Show information account                   !
                          |  9. Log out                                    |
                          |  X. Exit                                       |
                          `-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-`
                    """);

            logger.info("Pleas enter your choice :");
            String choice = input.next();

            switch (choice) {
                case "1":
                    addEvent();
                    break;
                case "2":
                    updateEvent();
                    break;
                case "3":
                    deleteEvent();
                    break;
                case "4":
                    searchServiceProvider();
                    break;
                case "5":
                    reserveRoom();
                    break;
                case "6":
                    reserveServiceProvider();
                    break;
                case "7":
                    showUpcomingEvents();
                    break;
                case "8":
                    accountInformation(user.getRole());
                    break;
                case "9":
                    return false;
                case "X":
                    System.exit(0);
                default:
                    throw new IllegalArgumentException("Invalid choice: " + choice);
            }


        }

    }

    public void addEvent() {

    }

    public void updateEvent() {

    }

    public void deleteEvent() {

    }

    public void reserveRoom() {

    }

    public void reserveServiceProvider() {

    }
}
