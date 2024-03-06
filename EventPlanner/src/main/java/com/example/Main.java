package com.example;


import com.example.entites.Client;
import com.example.services.AddEvent;

public class Main {

    public static void main(String[] args) {   AddEvent addEventService=new AddEvent()
            ;
        String username;
        int eventId;
        String name;
        String description;
        String startDate;
        String endDate;
        String startClock;
        String endClock;
        String attendeeCount;
        String imagePath;
        username = "Ali Turabi";
        eventId = 11;
        name = "Ali Turabi's Event";
        description = "cars event and explination about many thing about cars in generally";
        startDate = "2024-03-15";
        endDate = "2024-03-15";
        startClock = "19:00";
        endClock = "23:00";
        attendeeCount = "50";
        imagePath = "C:/Users/USER-M/Downloads/sm20121213_120309-IMG_2953.jpg";
        boolean v= addEventService.Addevent(username,eventId , name, description, startDate, endDate,
                startClock, endClock, attendeeCount, imagePath);
        username = "Ali Turabi";
        eventId =2;
        name = "Ali Turabi's Event";
        description = "cars event and explination about many thing about cars in generally";
        startDate = "2024-03-15";
        endDate = "2024-03-15";
        startClock = "19:00";
        endClock = "23:00";
        attendeeCount = "50";
        imagePath = "C:/Users/USER-M/Downloads/sm20121213_120309-IMG_2953.jpg";


        v= addEventService.Addevent(username,eventId , name, description, startDate, endDate,
                startClock, endClock, attendeeCount, imagePath);
        username = "Ali Turabi";
        eventId =2;
        name = "Ali Turabi's Event";
        description = "cars event and explination about many thing about cars in generally";
        startDate = "2024-03-15";
        endDate = "2024-03-15";
        startClock = "19:00";
        endClock = "23:00";
        attendeeCount = "50";
        imagePath = "C:/Users/USER-M/Downloads/sm20121213_120309-IMG_2953.jpg";
        v= addEventService.Addevent(username,eventId , name, description, startDate, endDate,
                startClock, endClock, attendeeCount, imagePath);
        Client c = Client.getClientFromData(username);

        if(v==false)System.out.println("it's not added");else
        System.out.println(c.getNumberEvent());

    }
}