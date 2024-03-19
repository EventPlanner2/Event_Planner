package com.example;

import com.example.data.UserData;
import com.example.entites.Client;
import com.example.entites.ServiceProvider;
import com.example.services.AddEvent;
import io.cucumber.java.bs.A;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MainMenu mainMenu =new MainMenu();
        mainMenu.menu();
    }
}