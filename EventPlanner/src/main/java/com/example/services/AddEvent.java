package com.example.services;

import com.example.Main;
import com.example.data.EventData;
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

    public boolean addEvent (String username,int eventId, String name, String description, String startDate, String
        endDate, String startClock, String endClock, String attendeeCount, String imagePath){


        if ( extracted ( name, description, startDate, endDate, startClock, endClock, attendeeCount, imagePath ) )
            return false;
        if (isOrgnaizer(username)&&canAddEvent(username)) {
            Event event = new EventBuilder ().setUsername ( username ).setId ( EventData.getEvents ().size () + 1 ).setEventName ( name ).setEventDescription ( description ).setStartDate ( LocalDate.parse ( startDate ) ).setEndDate ( LocalDate.parse ( endDate ) ).setStartClock ( LocalTime.parse ( startClock ) ).setEndClock ( LocalTime.parse ( endClock ) ).setAttendeeCount ( Integer.parseInt ( attendeeCount ) ).createEvent ();
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

    private boolean extracted(String name, String description, String startDate, String endDate, String startClock, String endClock, String attendeeCount, String imagePath) {
        if ( name.isEmpty()) {
            setMsg("missing name");
            return true;
        }
        if ( name.length() < 3) {
            setMsg("invalid name");
            return true;
        }
        if ( description.isEmpty()) {
            setMsg("missing description");
            return true;
        }
        if ( description.length() < 20) {
            setMsg("invalid description");
            return true;
        }
        if ( startDate.isEmpty()) {
            setMsg("missing start date");
            return true;
        }
        else {
           try {
               LocalDate.parse( startDate ); // Attempt to parse start clock
           } catch (DateTimeParseException e) {
              setMsg("invalid start date");
               return true;
           }
        }
        if ( endDate.isEmpty()) {
            setMsg("missing end date");
            return true;
        }
        else {
            try {
                LocalDate.parse( endDate ); // Attempt to parse end clock
            } catch (DateTimeParseException e) {
                setMsg("invalid end date");
                return true;
            }
        }
        if ( startClock.isEmpty()) {
            setMsg("missing start clock");
            return true;
        }else {
            try {
                LocalTime.parse( startClock ); // Attempt to parse start clock
            } catch (DateTimeParseException e) {
                setMsg("invalid start clock");
                return true;
            }
        }
        if ( endClock.isEmpty()) {
            setMsg("missing end clock");
            return true;
        }else {
            try {
                LocalTime.parse( endClock ); // Attempt to parse start clock
            } catch (DateTimeParseException e) {
                setMsg("invalid end clock");
                return true;
            }
        }
        if ( attendeeCount.isEmpty()) {
            setMsg("missing attendee count");
            return true;
        } else {
            try {
                int count = Integer.parseInt( attendeeCount ); // Attempt to parse attendee count
                if (count <= 0) {
                    setMsg("invalid attendee count");
                    return true;
                }
            } catch (NumberFormatException e) {
                setMsg("invalid attendee count");
                return true;
            }
        }
        if ( imagePath.isEmpty()) {
            setMsg("missing image path");
            return true;
        } else {
            try{
                ClassLoader classLoader = Main.class.getClassLoader();
                File imageFile = new File(classLoader.getResource( imagePath ).getFile());
                if (!imageFile.exists()) {
                    setMsg("invalid image path");
                    return true;
                }
            }
            catch (NullPointerException e){
                setMsg("invalid image path");
                return true;
            }

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
}
