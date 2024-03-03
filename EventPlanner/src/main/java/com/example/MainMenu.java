package com.example;

import com.example.entites.User;

import java.util.Scanner;

public class MainMenu {

    private static String WelcomeString = new String("Welcome To Study Planner");
    App app = new App();
    public String username;
    public String password;


    Scanner input = new Scanner(System.in);

    public void menu() {
        System.out.println(WelcomeString);
        while (true) {
            System.out.println("Choose 1 or 2 or 3 please : ");
            System.out.println("1. log in");
            System.out.println("2. Sign up");
            System.out.println("3. Exit");
            int choose = input.nextInt();
            switch (choose) {
                case 1: {
                    logIn();
                    break;
                }
                case 2: {
                    SignUp();
                    break;
                }
                case 3:
                    System.exit(0);
                default:
                    continue;
            }
        }
    }

    public void logIn() {
        System.out.println("Please enter the username and the password :");
        System.out.print("Username :");
        username = input.next();
        System.out.println("Password :");
        password = input.next();
        User user = app.loginService.LoginPerformed(username, password);
        char role = user.getRole();

        switch (role) {
            case 'a':
                adminPage();

            case 's':
                serviceProviderPage();

            case 'c':
                clintPage();
        }
    }

    public void SignUp() {
        while (true) {
            System.out.print("Please enter your username :");
            String username = input.next();
            System.out.print("Please enter your password :");
            String password = input.next();
            System.out.print("Please enter your email :");
            String email = input.next();
            System.out.print("Please enter your role");
            String role = input.next();
            app.signUpService.register(username, password, email, role);
            if (app.signUpService.username_existed(username)) {
                System.out.println("This user name is already exists /n Please enter a valid user name");
            } else if (app.signUpService.email_existed(email)) {
                System.out.println("This email is already exists /n Please enter a valid email");
            } else {
                break;
            }
        }
    }

    private void adminPage() {

    }

    private void serviceProviderPage() {

    }

    private void clintPage() {

    }
}
