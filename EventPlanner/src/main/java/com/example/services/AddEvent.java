package com.example.services;

import com.example.data.EventData;
import com.example.data.UserData;
import com.example.entites.Client;
import com.example.entites.Event;
import com.example.entites.User;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AddEvent {

    public boolean AddingEvent;
    private String msg;
    private String username;
    private User loggedInUser;
    private int eventId;
    private String eventName;
    private String eventDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startClock;
    private LocalTime endClock;
    private int attendeeCount;
    private String imagePath;


    public boolean addEvent (String username,int eventId, String name, String description, String startDate, String
        endDate, String startClock, String endClock, String attendeeCount, String imagePath){


        if (name.isEmpty()) {
            setMsg("missing name");
            return false;
        }
        if (name.length() < 3) {
            setMsg("invalid name");
            return false;
        }
        if (description.isEmpty()) {
            setMsg("missing description");
            return false;
        }
        if (description.length() < 20) {
            setMsg("invalid description");
            return false;
        }
        if (startDate.isEmpty()) {
            setMsg("missing start date");
            return false;
        }
        else {
           try {
               LocalDate.parse(startDate); // Attempt to parse start clock
           } catch (DateTimeParseException e) {
              setMsg("invalid start date");
              return false;
           }
        }
        if (endDate.isEmpty()) {
            setMsg("missing end date");
            return false;
        }
        else {
            try {
                LocalDate.parse(endDate); // Attempt to parse end clock
            } catch (DateTimeParseException e) {
                setMsg("invalid end date");
                return false;
            }
        }
        if (startClock.isEmpty()) {
            setMsg("missing start clock");
            return false;
        }else {
            try {
                LocalTime.parse(startClock); // Attempt to parse start clock
            } catch (DateTimeParseException e) {
                setMsg("invalid start clock");
                return false;
            }
        }
        if (endClock.isEmpty()) {
            setMsg("missing end clock");
            return false;
        }else {
            try {
                LocalTime.parse(endClock); // Attempt to parse start clock
            } catch (DateTimeParseException e) {
                setMsg("invalid end clock");
                return false;
            }
        }
        if (attendeeCount.isEmpty()) {
            setMsg("missing attendee count");
            return false;
        } else {
            try {
                int count = Integer.parseInt(attendeeCount); // Attempt to parse attendee count
                if (count <= 0) {
                    setMsg("invalid attendee count");
                    return false;
                }
            } catch (NumberFormatException e) {
                setMsg("invalid attendee count");
                return false;
            }
        }
        if (imagePath.isEmpty()) {
            setMsg("missing image path");
            return false;
        } else {
            File imageFile = new File(imagePath);
            if (!imageFile.exists()) {
                setMsg("invalid image path");
                return false;
            }
        }
        if (isOrgnaizer(username)&&canAddEvent(username)) {
            Event event = new Event(username, EventData.getEvents().size(), name, description, LocalDate.parse(startDate),
                    LocalDate.parse(endDate), LocalTime.parse(startClock), LocalTime.parse(endClock),
                    Integer.parseInt(attendeeCount));
            event.setPathImage(imagePath);
            msg = "";
            setMsg("The Event is created");
            EventData.addEvent(event);
            Client c = Client.getClientFromData(username);

            c.setNumberEvent(c.getNumberEvent() + 1);
            return true;
        }
        return false;
    }

    public String getMsg() {
            return msg;
    }

    public void setMsg(String msg) {
        this.msg =  msg;
    }

    public boolean isOrgnaizer(String username){
        Client c=Client.getClientFromData(username);
        if(c==null) {
            setMsg("The user is not an orgnizer");
            return false;}
        else {
            if(c.isOrganizer())
                return true;


        }

        return false;
    }

    public boolean canAddEvent(String userName){
        Client c=Client.getClientFromData(userName);
        if(c==null) {
            setMsg("User DNE");
            return false;
        }else{
            return c.getNumberEvent() < 3;
        }
    }


}
