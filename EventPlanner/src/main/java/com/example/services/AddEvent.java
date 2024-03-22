package com.example.services;

import com.example.Main;
import com.example.data.EventData;

import com.example.data.NotifcationData;
import com.example.data.UserData;
import com.example.entites.Client;
import com.example.entites.Event;
import com.example.entites.EventBuilder;
import com.example.entites.User;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class AddEvent {

    private boolean AddingEvent;
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


    public boolean addEvent(String username, int eventId, String name, String description, String startDate, String
            endDate, String startClock, String endClock, String attendeeCount, String imagePath) {

        if (nameVerification(name)) return false;
        if (description.isEmpty()) {
            setMsg("missing description");
            return false;
        }
        if (description.length() < 20) {
            setMsg("invalid description");
            return false;
        }
        if (!validateDate(startDate, "start date")) return false;
        if (!validateDate(endDate, "end date")) return false;
        if (!validateTime(startClock, "start clock")) return false;
        if (!validateTime(endClock, "end clock")) return false;
        if (!validateAttendeeCount(attendeeCount)) return false;
        if (!validateImagePath(imagePath)) return false;
        if (!isOrgnaizer(username) || !canAddEvent(username)) return false;

        Event event = createEvent(username, eventId, name, description, startDate, endDate, startClock, endClock, attendeeCount, imagePath);
        if (event == null) {
            return false;
        }

        msg = "The Event is created";
        EventData.addEvent(event);
        Client c = Client.getClientFromData(username);
        c.setNumberEvent(c.getNumberEvent() + 1);

        String notification = LocalDate.now()+"| "+ "Event With Name "+name+" And StartDate "+startDate+" Has Been Added";
        NotifcationData.addNotification(notification);
        return true;

    }




    public boolean nameVerification(String name) {
        if ( name.isEmpty()) {
            setMsg("missing name");
            return true;
        }
        if ( name.length() < 3) {
            setMsg("invalid name");
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
            setMsg("User can't add Event");
            return c.getNumberEvent() < 3;
        }
    }

    public boolean isAddingEvent() {
        return AddingEvent;
    }

    public void setAddingEvent(boolean addingEvent) {
        AddingEvent = addingEvent;
    }

    private boolean validateDate(String date, String fieldName) {
        if (date.isEmpty()) {
            setMsg("missing " + fieldName);
            return false;
        }
        try {
            LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            setMsg("invalid " + fieldName);
            return false;
        }
    }

    private boolean validateTime(String time, String fieldName) {
        if (time.isEmpty()) {
            setMsg("missing " + fieldName);
            return false;
        }
        try {
            LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            setMsg("invalid " + fieldName);
            return false;
        }
    }

    private boolean validateAttendeeCount(String attendeeCount) {
        if (attendeeCount.isEmpty()) {
            setMsg("missing attendee count");
            return false;
        }
        try {
            int count = Integer.parseInt(attendeeCount);
            if (count <= 0) {
                setMsg("invalid attendee count");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            setMsg("invalid attendee count");
            return false;
        }
    }

    private boolean validateImagePath(String imagePath) {
        if (imagePath.isEmpty()) {
            setMsg("missing image path");
            return false;
        }
        try {
            ClassLoader classLoader = Main.class.getClassLoader();
            File imageFile = new File(classLoader.getResource(imagePath).getFile());
            if (!imageFile.exists()) {
                setMsg("invalid image path");
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            setMsg("invalid image path");
            return false;
        }
    }

    private Event createEvent(String username, int eventId, String name, String description, String startDate, String
            endDate, String startClock, String endClock, String attendeeCount, String imagePath) {
        try {
            return new EventBuilder().setUsername(username).setId(eventId).setEventName(name).setEventDescription(description)
                    .setStartDate(LocalDate.parse(startDate)).setEndDate(LocalDate.parse(endDate))
                    .setStartClock(LocalTime.parse(startClock)).setEndClock(LocalTime.parse(endClock))
                    .setAttendeeCount(Integer.parseInt(attendeeCount)).createEvent();
        } catch (DateTimeParseException | NumberFormatException e) {
            setMsg("Invalid input format");
            return null;
        }
    }
}
