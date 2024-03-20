package com.example;

import com.example.data.EventData;
import com.example.data.UserData;
import com.example.entites.ServiceProvider;
import com.example.entites.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainMenu {
    Logger logs = Logger.getLogger(MainMenu.class.getName());

    private final String WelcomeString = "\n" +
            " __          __    _                                _______        _____  _               _          _____   _                                  \n" +
            " \\ \\        / /   | |                              |__   __|      / ____|| |             | |        |  __ \\ | |                                 \n" +
            "  \\ \\  /\\  / /___ | |  ___  ___   _ __ ___    ___     | |  ___   | (___  | |_  _   _   __| | _   _  | |__) || |  __ _  _ __   _ __    ___  _ __ \n" +
            "   \\ \\/  \\/ // _ \\| | / __|/ _ \\ | '_ ` _ \\  / _ \\    | | / _ \\   \\___ \\ | __|| | | | / _` || | | | |  ___/ | | / _` || '_ \\ | '_ \\  / _ \\| '__|\n" +
            "    \\  /\\  /|  __/| || (__| (_) || | | | | ||  __/    | || (_) |  ____) || |_ | |_| || (_| || |_| | | |     | || (_| || | | || | | ||  __/| |   \n" +
            "     \\/  \\/  \\___||_| \\___|\\___/ |_| |_| |_| \\___|    |_| \\___/  |_____/  \\__| \\__,_| \\__,_| \\__, | |_|     |_| \\__,_||_| |_||_| |_| \\___||_|   \n" +
            "                                                                                              __/ |                                             \n" +
            "                                                                                             |___/                                              \n";
    App app = new App();
    public String username;
    public String password;
    private User user;


    Scanner input = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println(WelcomeString);
            System.out.println(" +--------------------+\n" +
                    " | 1. log in          |\n" +
                    " | 2. Sign up         |\n" +
                    " | X. Exit            |\n" +
                    " +--------------------+");
            System.out.print("Enter your choice please : ");
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
            System.out.println("Please enter the username and the password :");
            System.out.print("Username :");
            username = input.next();
            System.out.print("Password :");
            password = input.next();
            user = app.loginService.LoginPerformed(username, password);
            if (user == null) {
                System.out.println(app.loginService.errorMessage);
            } else {
                app.loggedInUser = user;
                role = user.getRole();
                break;
            }
        }

        app.loggedInUser = user;
        switch (role) {
            case 'a':
                System.out.println("╔════════════════════════╗\n" +
                        "║ You logged in as Admin ║\n" +
                        "╚════════════════════════╝");
                Page("2.Add Room", user.getRole());
                break;

            case 's':
                System.out.println("╔══════════════════════════════════╗\n" +
                        "║ You logged in as Serves Provider ║\n" +
                        "╚══════════════════════════════════╝");
                ServiceProvider.getSPFromData(username).setFirstLogin(true);
                Page("2.Complete", user.getRole());
                break;

            case 'c':
                System.out.println("╔════════════════════════╗\n" +
                        "║ You logged in as Clint ║\n" +
                        "╚════════════════════════╝");
                Page("2.Upgrade", user.getRole());
                break;

            default:
                break;
        }
    }

    public void signUp() {
        while (true) {
            if (Back()) return;
            System.out.print("Please enter your username :");
            String username = input.next();
            System.out.print("Please enter your password :");
            String password = input.next();
            System.out.print("Please enter your email :");
            String email = input.next();
            System.out.print("Please enter your role :");
            String role = input.next();
            boolean flag = app.signUpService.register(username, password, email, role);
            if (flag) {
                break;
            } else {
                System.out.println(app.signUpService.msg);
            }
        }
    }


    public void Page(String specification, char role) {
        while (true) {
            System.out.println("1.Show account information ");
            System.out.println(specification);
            System.out.println("3.Search Serves Provider");
            System.out.println("4.Show upcoming events");
            if(role == 'c'){
                System.out.println("5.Enter organizer mode");
            }
            System.out.println("X.Exit");
            System.out.print("Enter your choice please : ");

            String choose = input.next();
            switch (choose) {
                case "1":
                    accountInformation(role);
                    break;
                case "2": {
                    if (role == 'a') {
                        app.addRoomService.setLoggedInUser(user);
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
                    showUpcomingEvents();
                    break;
                case "5":
                    if(role == 'c'){
                        if(!organizerPage()){
                            return;
                        };
                    }else{
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
            System.out.println("Username : " + user.getUsername());
            System.out.println("Password : " + user.getPassword());
            System.out.println("Email : " + user.getContactEmail());
            if (role == 'a')
                System.out.println("Role : Admin");
            else if (role == 's') {
                System.out.println("Role : Serves Provider");
            } else {
                System.out.println("Role : Clint");
            }
            if (Back())
                return;
        }
    }

    public void addRoom() {
        while (true) {
            if (Back()) return;
            System.out.print("Please enter the information for the new room : ");
            System.out.print("Room name : ");
            String roomName = input.next();
            System.out.print("Capacity : ");
            String roomCapacity = input.next();
            System.out.print("Cost per hour :");
            String roomCost = input.next();
            System.out.print("Description :");
            String roomDes = input.next();
            System.out.print("Availability :");
            String roomAvailability = input.next();
            boolean flag = app.addRoomService.AddRoomPerformed(roomName, roomAvailability, roomCapacity, roomCost, roomDes);
            if (flag) {
                System.out.println(app.addRoomService.getMsg());
                break;
            } else {
                System.out.println(app.addRoomService.getMsg());
            }
        }


    }

    public void searchServiceProvider() {

        while (true) {
            if (Back()) return;
            System.out.println("Search based on : ");
            System.out.println("1.Location ");
            System.out.println("2.Type ");
            System.out.println("3.Price");
            System.out.print("Please enter your choice :");
            String choose = input.next();
            switch (choose) {
                case "1":
                    app.SearchSP.setSelectedCriteria("Location");
                    searchCriteria("Location");
                    break;
                case "2":
                    app.SearchSP.setSelectedCriteria("Type");
                    searchCriteria("Type");
                    break;
                case "3":
                    app.SearchSP.setSelectedCriteria("Price");
                    searchCriteria("Price");
                    break;

                case "":
                    app.SearchSP.setSelectedCriteria("");
                    System.out.println(app.SearchSP.getErrorMsg());
                    break;
                default:
                    app.SearchSP.setSelectedCriteria("a");
                    System.out.println(app.SearchSP.getErrorMsg());
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
                System.out.println(app.SPAccount.getCompleteAccountMsg());
                return;
            }
            String location, productPrice, productType;
            System.out.println("To complete your account please enter your:");
            System.out.print("Location : ");
            location = input.next();
            System.out.print("Product Price : ");
            productPrice = input.next();
            System.out.print("Product Type : ");
            productType = input.next();
            productType += " " + input.next();

            boolean flag = app.SPAccount.CompleteAccountPerform(location, productPrice, productType);

            if (flag) {
                System.out.println(app.SPAccount.getCompleteAccountMsg());
                ServiceProvider.getSPFromData(username).setFirstLogin(false);
                return;
            } else {
                System.out.println(app.SPAccount.getCompleteAccountMsg());
            }
        }
    }

    public void upgrade() {
        while (true) {
            if (Back()) return;
            app.upgradeClient.setLoggedInUser(user);
            app.upgradeClient.UpgradeClientPerform();
            System.out.println(app.upgradeClient.getMsg());
        }
    }

    public void printCriteria(List<ServiceProvider> ServesProviders) {
        for (int i = 0; i < ServesProviders.size(); i++) {
            System.out.println(i + "." + ServesProviders.get(i).getUsername());
        }
    }

    public void searchCriteria(String criteria) {
        List<ServiceProvider> tmpArray = new ArrayList<>();
        if (criteria.equals("Location")) {
            System.out.print("Please enter the name of the location to search :");
            String location = input.next();
            app.SearchSP.setLocation(location);
            System.out.println(UserData.getSps().size() + "this is the size\n");
            tmpArray.addAll(app.SearchSP.SearchSPPerformed());
            printCriteria(tmpArray);
        } else if (criteria.equals("Type")) {
            System.out.print("Please enter the type of the serves provider : ");
            String type = input.next();
            type += " " + input.next();
            app.SearchSP.setType(type);
            printCriteria(app.SearchSP.SearchSPPerformed());
        } else {
            System.out.print("Please enter the price of the product : ");
            String price = input.next();
            app.SearchSP.setPrice(price);
            printCriteria(app.SearchSP.SearchSPPerformed());
        }
    }

    public boolean Back() {
        System.out.print("Please Enter B if you want to go back otherwise enter anything : ");
        String back = input.next();
        if (back.equals("B")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean organizerPage() {
        if (Back()) return true;
        if (!app.addEventService.isOrgnaizer(user.getUsername())) {
            System.out.println("You must be an Organizer");
            return true;
        }
        while (true) {
            System.out.println(".-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-.\n" +
                    "|  1.Add Event                                  |\n" +
                    "|  2.Update Event                               |\n" +
                    "!  3.Delete Event                               !\n" +
                    ":  4.Search for Service Provider                :\n" +
                    ".  5.Reserve Room for an Event                  .\n" +
                    ".  6.Reserve Service Provider for an Event      .\n" +
                    ":  7.Show upcoming Events                       :\n" +
                    "!  8.Show information account                   !\n" +
                    "|  9.Log out                                    |\n" +
                    "|  X.Exit                                       |\n" +
                    "`-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-`");
            System.out.print("Pleas enter your choice :");
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
        while (true) {
            System.out.print("Please enter the user name : ");
            String username = input.next();
            System.out.print("Please enter the information for the new event : ");
            System.out.print("Event name : ");
            String eventName = input.next();
            System.out.print("Description :");
            String eventDes = input.next();
            System.out.print("Start date : ");
            String startDate = input.next();
            System.out.print("End date : ");
            String endDate = input.next();
            System.out.print("Starting hour :");
            String startHour = input.next();
            System.out.print("End hour :");
            String endHour = input.next();
            System.out.print("Attendee count :");
            String attendeeCount = input.next();
            System.out.print("Image path :");
            String imagePath = input.next();
            boolean flag = app.addEventService.addEvent(username, EventData.getEvents().size() + 1, eventName, eventDes, startDate, endDate, startHour, endHour, attendeeCount, imagePath);
            if (flag) {
                System.out.println(app.addEventService.getMsg());
                break;
            } else {
                System.out.println(app.addEventService.getMsg());
            }
        }


    }

    public void updateEvent() {
        while (true) {
            System.out.print("Please enter the user name : ");
            String username= input.next();
            System.out.print("Please enter the new information for your event : ");
            System.out.print("Event name : ");
            String eventName = input.next();
            System.out.print("Event id : ");
            String eventId = input.next();
            System.out.print("Description :");
            String eventDes = input.next();
            System.out.print("Start date : ");
            String startDate = input.next();
            System.out.print("End date : ");
            String endDate = input.next();
            System.out.print("Starting hour :");
            String startHour = input.next();
            System.out.print("End hour :");
            String endHour = input.next();
            System.out.print("Attendee count :");
            String attendeeCount = input.next();
            System.out.print("Image path :");
            String imagePath = input.next();
            boolean flag = app.deleteUpdateEventService.UpdateEventPerform( eventId,username,eventName ,eventDes,startDate,endDate,startHour,endHour,attendeeCount,imagePath);
            if (flag) {
                System.out.println(app.deleteUpdateEventService.getMsg());
                //Created getMsg in DeleteUpdateEvent
                break;
            } else {
                System.out.println(app.deleteUpdateEventService.getMsg());
            }
        }



    }

    public void deleteEvent() {
        while (true){
        System.out.print("Please enter the ID of the event you want to delete : ");
        String eventId=input.next();
        boolean flag =app.deleteUpdateEventService.DeleteEventPerform(eventId);
        if (flag) {
            System.out.println(app.deleteUpdateEventService.getMsg());
            //Created getMsg in DeleteUpdateEvent
            break;

         } else {
            System.out.println(app.deleteUpdateEventService.getMsg());
        }

    }
    }

    public void reserveRoom() {

    }

    public void reserveServiceProvider() {

    }
}
