package com.example.services;


import com.example.data.NotifcationData;
import com.example.entites.Event;
import com.example.entites.ServiceProvider;
import com.example.entites.User;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.data.EventData.getEvents;
import static com.example.data.UserData.getSps;
import static com.example.entites.ServiceProvider.getSPFromData;

public class ReserveSP {




    private List<Event> res_Event;
    private List<ServiceProvider> res_Sps;
    private String msg;

    public ReserveSP(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        res_Event = new ArrayList<>();
        res_Sps = new ArrayList<>();
        msg = "";
    }

    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean reserveSPPerform(String eventID,String sapUsername){
        try {
            ServiceProvider sp = getSPFromData(sapUsername);
            if(sp.isFirstLogin()){
                msg = "incomplete account of service provider";
                return false;
            }
            int eventid = Integer.parseInt(eventID);
            for (Event e : getEvents()) {
                if (e.getId() == eventid) {
                    e.serviceEntity.add(sp);
                    msg = "Service Provider has been reserved";
                    String notification = LocalDate.now()+"| "+ "Service Provider "+sp.getUsername()+" Has Been Reserved For Event "+e.getEventName();
                    NotifcationData.addNotification(notification);
                    return true;
                }
            }
        }
        catch (NumberFormatException e){
            msg = "the event ID is invalid";
            return false;
        }
        catch (NullPointerException e){
            msg = "non-existed service provider";
            return false;
        }
        msg = "the event ID is invalid";
        return false;

    }

    public void chooseReserveSP(){

        for(Event e : getEvents()){
            if(e.getUsername().equals(loggedInUser.getUsername())){
                res_Event.add(e);
            }
        }

        for(ServiceProvider sp : getSps()){
            if(!sp.isFirstLogin()){
                res_Sps.add(sp);
            }
        }


    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    // Getter method for res_event
    public List<Event> getRes_event() {
        return res_Event;
    }

    // Setter method for res_event
    public void setRes_event(List<Event> res_event) {
        this.res_Event = res_event;
    }

    public List<ServiceProvider> getRes_Sps() {
        return res_Sps;
    }

    public void setRes_Sps(List<ServiceProvider> res_Sps) {
        this.res_Sps = res_Sps;
    }
}
