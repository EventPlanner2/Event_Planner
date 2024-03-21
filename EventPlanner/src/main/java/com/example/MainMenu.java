package com.example;

import com.example.entites.ServiceProvider;
import com.example.entites.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class MainMenu {
    private static final Logger logger = Logger.getLogger(MainMenu.class.getName());
    private final String WelcomeString = """
        \n
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
    public String username;
    public String password;
    private User user;


    Scanner input = new Scanner(System.in);

    public void menu() {
        while (true) {
            logger.info(WelcomeString);
            logger.info(" +--------------------+\n" +
                    "       | 1. log in          |\n" +
                    "       | 2. Sign up         |\n" +
                    "       | X. Exit            |\n" +
                    "       +--------------------+");
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
                    System.exit(0);
                default:
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
            user = app.getLoginService ().LoginPerformed(username, password);
            if (user == null) {
                logger.info( app.getLoginService ().errorMessage);
            } else {
                app.setLoggedInUser ( user );
                role = user.getRole();
                break;
            }
        }

        app.setLoggedInUser ( user );
        switch (role) {
            case 'a':
                logger.info("╔════════════════════════╗\n" +
                        "      ║ You logged in as Admin ║\n" +
                        "      ╚════════════════════════╝");
                Page("2.Add Room", user.getRole());
                break;

            case 's':
                logger.info("╔═══════════════════════════════════╗\n" +
                        "      ║ You logged in as Service Provider ║\n" +
                        "      ╚═══════════════════════════════════╝");
                ServiceProvider.getSPFromData(username).setFirstLogin(true);
                Page("2.Complete", user.getRole());
                break;

            case 'c':
                logger.info("╔════════════════════════╗\n" +
                        "      ║ You logged in as Clint ║\n" +
                        "      ╚════════════════════════╝");
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
            boolean flag = app.getSignUpService ().register(username, password, email, role);
            if (flag) {
                break;
            } else {
                logger.info( app.getSignUpService ().msg);
            }
        }
    }


    public void Page(String specification, char role) {
        while (true) {
            String info = "1.Show account information \n" +
                    "      " + specification + "\n" +
                    "      3.Search Service Provider \n" +
                    "      4.Show upcoming events \n";
            if (role == 'c') {//log out , show all rooms ,INFO: 6 spaces"      "
                info += "      5.Enter organizer mode \n";
            } else if (role == 'a') {
                info += "      5.Show all rooms \n" +
                        "      6.Delete room \n";

            }
            if (role == 's') {
                info += "      5.Log out \n";
            } else {
                info += "      7.Log out \n";
            }
            info += "      X.Exit";

            logger.info(info);
            logger.info("Enter your choice please : ");

            String choose = input.next();
            switch (choose) {
                case "1":
                    accountInformation(role);
                    break;
                case "2": {
                    if (role == 'a') {
                        app.getAddRoomService ().setLoggedInUser(user);
                        addRoom();
                    } else if (role == 's') {
                        complete();
                    } else {
                        upgrade();
                    }
                }
                break;
                case "3":
                    searchServiceProvider();
                    break;
                case "4":
                    return;
                //showUpcomingEvents();
                // break;
                case "5":
                    if (role == 'c') {
                        if (!organizerPage()) {
                            return;
                        }
                    } else {
                        break;
                    }
                    break;
                case "X":
                    System.exit(0);
                default:
                    break;
            }
        }
    }


    public void accountInformation(char role) {
        while (true) {
            String info = "Username : " + user.getUsername() + "\n" +
                    "      Password : " + user.getPassword() + "\n" +
                    "      Email : " + user.getContactEmail() + "\n";
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
            boolean flag = app.getAddRoomService ().AddRoomPerformed(roomName, roomAvailability, roomCapacity, roomCost, roomDes);
            if (flag) {
                logger.info( app.getAddRoomService ().getMsg());
                break;
            } else {
                logger.info( app.getAddRoomService ().getMsg());
            }
        }


    }

    public void searchServiceProvider() {

        while (true) {
            if (Back()) return;
            String info = "Search based on : \n" +
                    "      1.Location  \n" +
                    "      2.Type \n" +
                    "      3.Price \n" +
                    "Please enter your choice :";
            logger.info(info);
            String choose = input.next();
            switch (choose) {
                case "1":
                    app.getSearchSP ().setSelectedCriteria("Location");
                    searchCriteria("Location");
                    break;
                case "2":
                    app.getSearchSP ().setSelectedCriteria("Type");
                    searchCriteria("Type");
                    break;
                case "3":
                    app.getSearchSP ().setSelectedCriteria("Price");
                    searchCriteria("Price");
                    break;

                case "":
                    app.getSearchSP ().setSelectedCriteria("");
                    logger.info( app.getSearchSP ().getErrorMsg());
                    break;
                default:
                    app.getSearchSP ().setSelectedCriteria("a");
                    logger.info( app.getSearchSP ().getErrorMsg());
                    break;
            }
        }
    }

    public void showUpcomingEvents() {

    }

    public void complete() {
        while (true) {
            if (Back()) return;
            if (!ServiceProvider.getSPFromData(user.getUsername()).isFirstLogin()) {
                logger.info( app.getSPAccount ().getCompleteAccountMsg());
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

            boolean flag = app.getSPAccount ().CompleteAccountPerform(location, productPrice, productType);

            if (flag) {
                logger.info( app.getSPAccount ().getCompleteAccountMsg());
                ServiceProvider.getSPFromData(username).setFirstLogin(false);
                return;
            } else {
                logger.info( app.getSPAccount ().getCompleteAccountMsg());
            }
        }
    }

    public void upgrade() {
        while (true) {
            if (Back()) return;
            app.getUpgradeClient ().setLoggedInUser(user);
            app.getUpgradeClient ().UpgradeClientPerform();
            logger.info( app.getUpgradeClient ().getMsg());
        }
    }

    public void printCriteria(List<ServiceProvider> ServiceProviders) {
        for (int i = 0; i < ServiceProviders.size(); i++) {
            logger.info(i + "." + ServiceProviders.get(i).getUsername());
        }
    }

    public void searchCriteria(String criteria) {
        List<ServiceProvider> tmpArray = new ArrayList<>();
        if (criteria.equals("Location")) {
            logger.info("Please enter the name of the location to search :");
            String location = input.next();
            app.getSearchSP ().setLocation(location);
            tmpArray.addAll( app.getSearchSP ().SearchSPPerformed());
            printCriteria(tmpArray);
        } else if (criteria.equals("Type")) {
            logger.info("Please enter the type of the Service provider : ");
            String type = input.next();
            type += " " + input.next();
            app.getSearchSP ().setType(type);
            printCriteria( app.getSearchSP ().SearchSPPerformed());
        } else {
            logger.info("Please enter the price of the product : ");
            String price = input.next();
            app.getSearchSP ().setPrice(price);
            printCriteria( app.getSearchSP ().SearchSPPerformed());
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
        if (!app.getAddEventService ().isOrgnaizer(user.getUsername())) {
            logger.info("You must be an Organizer");
            return true;
        }
        while (true) {
            logger.info(".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n" +
                    "      |  1.Add Event                                  |\n" +
                    "      |  2.Update Event                               |\n" +
                    "      !  3.Delete Event                               !\n" +
                    "      :  4.Search for Service Provider                :\n" +
                    "      .  5.Reserve Room for an Event                  .\n" +
                    "      .  6.Reserve Service Provider for an Event      .\n" +
                    "      :  7.Show upcoming Events                       :\n" +
                    "      !  8.Show information account                   !\n" +
                    "      |  9.Log out                                    |\n" +
                    "      |  X.Exit                                       |\n" +
                    "      `-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-`");
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
                    break;
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
