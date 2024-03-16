package com.example;

import com.example.entites.ServiceProvider;
import com.example.entites.User;

import java.util.List;
import java.util.Scanner;

public class MainMenu {

    private final String WelcomeString = "Welcome To Study Planner";
    App app = new App();
    public String username;
    public String password;
    private User user;


    Scanner input = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println(WelcomeString);
            System.out.println("1. log in");
            System.out.println("2. Sign up");
            System.out.println("X. Exit");
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
        boolean flag = false;
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
                role = user.getRole();
                break;
            }
        }

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
                break;
            } else {
                System.out.println(app.addRoomService.getMsg());
            }
        }


    }

    public void searchServiceProvider() {

        List<ServiceProvider> tmpArray = null;
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
                    tmpArray = app.SearchSP.SearchSPPerformed();
                    printCriteria(tmpArray);
                    break;
                case "2":
                    app.SearchSP.setSelectedCriteria("Type");
                    tmpArray = app.SearchSP.SearchSPPerformed();
                    printCriteria(tmpArray);
                    break;
                case "3":
                    app.SearchSP.setSelectedCriteria("Price");
                    tmpArray = app.SearchSP.SearchSPPerformed();
                    printCriteria(tmpArray);
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
            System.out.println(i + ServesProviders.get(i).getUsername());
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
