package com.example.services;

import com.example.entites.Event;
import com.example.entites.Room;
import com.example.entites.ServiceProvider;
import com.example.entites.User;
import io.cucumber.java.bs.A;

import java.util.ArrayList;

import static com.example.data.EventData.getEvents;
import static com.example.data.UserData.getSps;
import static com.example.entites.ServiceProvider.getSPFromData;

public class ReserveSP {


    public ArrayList<Event> res_event;
    public ArrayList<ServiceProvider> res_sps;
    public String msg;

    public ReserveSP(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        res_event = new ArrayList<>();
        res_sps = new ArrayList<>();
        msg = "";
    }

    private User loggedInUser;

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public boolean ReserveSPPerform(String EventID,String SPUsername){
        try {
            ServiceProvider sp = getSPFromData(SPUsername);
            if(sp.isFirstLogin()){
                msg = "incomplete account of service provider";
                return false;
            }
            int eventid = Integer.parseInt(EventID);
            for (Event e : getEvents()) {
                if (e.getId() == eventid) {
                    e.serviceEntity.add(sp);
                    msg = "Service Provider has been reserved";
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

    public void ChooseReserveSP(){

        for(Event e : getEvents()){
            if(e.getUsername().equals(loggedInUser.getUsername())){
                res_event.add(e);
            }
        }

        for(ServiceProvider sp : getSps()){
            if(!sp.isFirstLogin()){
                res_sps.add(sp);
            }
        }


    }

}
