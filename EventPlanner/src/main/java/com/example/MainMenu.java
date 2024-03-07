package com.example;

import com.example.entites.User;

import java.util.Scanner;

public class MainMenu {

    private static String WelcomeString = new String("Welcome To Study Planner");
    App app = new App();
    public String username;
    public String password;
    private User user;


    Scanner input = new Scanner(System.in);

    public void menu() {
        System.out.println(WelcomeString);
        boolean flag = false;
        boolean flag2 = false;
        while (true) {
            System.out.println("1. log in");
            System.out.println("2. Sign up");
            System.out.println("X. Exit");
            System.out.print("Enter your choose please : ");
            String choose = input.next();
            switch (choose) {
                case "1": {
                    flag = logIn();
                    if (flag)
                        break;
                }
                case "2": {
                    flag2 = signUp();
                    if (flag2) {
                        break;
                    }
                }
                default:
                    System.exit(0);
            }
            if (flag) {
                break;
            }
            if (flag2) {
                continue;
            }
        }
    }

    public boolean logIn() {
        char role = 'a';
        while (true) {
            System.out.print("Please Enter B if you want to go back : ");
            String back = input.next();
            if (back.equals("B")) menu();
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
                System.out.println("/n You logged in as Admin");
                adminPage();
                break;

            case 's':
                System.out.println("/n You logged in as Serves Provider");
                serviceProviderPage();
                break;

            case 'c':
                System.out.println("/n You logged in as Clint");
                clintPage();
                break;
        }
        return true;
    }

    public boolean signUp() {
        while (true) {
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
        return true;
    }

    private void adminPage() {
        while (true) {
            System.out.println("1.Show account information ");
            System.out.println("2.Add Room");
            System.out.println("3.Search Serves Provider");
            System.out.println("4.Log out");
            System.out.println("<- Back");
            System.out.println("X.Exit");
            System.out.print("Enter your choose please or Enter B to go Back : ");
            String choose = input.next();
            switch (choose) {
                case "1":
                    if (accountInformation(user.getRole()))
                        break;
                case "2":
                    addRoom();
                    break;
                case "3":
                    searchServiceProvider();
                    break;
                case "4":
                    menu();
                    break;
                case "X":
                    System.exit(0);
                default:
                    break;
            }
            if (choose == "B") {
                break;
            }
        }

    }

    private void serviceProviderPage() {
        while (true) {
            System.out.println("1.Show account information ");
            System.out.println("2.Complete");
            System.out.println("3.Search Serves Provider");
            System.out.println("4.Log out");
            System.out.println("<- Back");
            System.out.println("X.Exit");
            System.out.print("Enter your choose please or Enter B to go Back : ");
            String choose = input.next();
            switch (choose) {
                case "1":
                    if (accountInformation(user.getRole()))
                        break;
                case "2":
                    complete();
                    break;
                case "3":
                    searchServiceProvider();
                    break;
                case "4":
                    menu();
                    break;
                case "X":
                    System.exit(0);
                default:
                    break;
            }
            if (choose == "B") {
                break;
            }
        }
    }

    private void clintPage() {
        System.out.println("1.Show account information ");
        System.out.println("2.Upgrade");
        System.out.println("3.Search Serves Provider");
        System.out.println("4.Log out");
        System.out.println("<- Back");
        System.out.println("X.Exit");
    }


    public boolean accountInformation(char role) {
        System.out.println("Username : " + user.getUsername());
        System.out.println("Password : " + user.getPassword());
        System.out.println("Email : " + user.getContactEmail());
        System.out.println("Role : " + user.getRole());
        System.out.println("<-Back ");
        System.out.println("Please enter B to go back");
        String back = input.next();
        if (back == "B") {
            return true;
        } else {
            return false;
        }
    }

    public void addRoom() {
        while (true) {
            System.out.print("Please enter the name of the new room ");
            String roomName = input.next();
            System.out.print("Please enter the Capacity of the new room ");
            String roomCapacity = input.next();
            System.out.print("Please enter the cost per hour of the new room ");
            String roomCost = input.next();
            System.out.print("Please enter the description of the new room ");
            String roomDes = input.next();
            System.out.print("Please enter the Availability of the new room ");
            String roomAvailability = input.next();
            boolean flag = app.addRoomService.AddRoomPerformed(roomName, roomAvailability, roomCapacity, roomCost, roomDes);
            if (flag) {
                break;
            } else {
                System.out.println(app.addRoomService.getMsg());
            }
        }


    }

    public void Upgrade(){



    }

    public void searchServiceProvider() {

    }

    public void complete() {


    }
}
