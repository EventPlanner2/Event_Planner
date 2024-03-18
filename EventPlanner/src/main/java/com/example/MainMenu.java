package com.example;

import com.example.entites.ServiceProvider;
import com.example.entites.User;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

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
            System.out.print("Enter your choose please : ");
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
            System.out.println("4.Log out");
            System.out.println("X.Exit");
            System.out.print("Enter your choose please : ");

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
                    return;
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
            System.out.print("Please enter the name of the new room : ");
            String roomName = input.next();
            System.out.print("Please enter the Capacity of the new room : ");
            String roomCapacity = input.next();
            System.out.print("Please enter the cost per hour of the new room :");
            String roomCost = input.next();
            System.out.print("Please enter the description of the new room :");
            String roomDes = input.next();
            System.out.print("Please enter the Availability of the new room :");
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
            System.out.print("Please enter your choose :");
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
        List<ServiceProvider> tmpArray = null;
        if (criteria.equals("Location")) {
            System.out.print("Please enter the name of the location to search :");
            String location = input.next();
            app.SearchSP.setLocation(location);
            tmpArray = app.SearchSP.SearchSPPerformed();
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

}
