package com.example.services;

import com.example.data.EventData;
import com.example.entites.Client;
import com.example.entites.Event;
import com.example.entites.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Calender {

    private String msg;
    private User loggedInUser;
    private ArrayList<Event> resEvents;
    private Client c1;

    public Calender(User loggedInUser) {
        msg = "";
        this.loggedInUser = loggedInUser;
        resEvents = new ArrayList<>();

    }

    public boolean calenderPerform(String pastOrFuture) {
        boolean flagPast = pastOrFuture.equals("past");

        try {
            c1 = Client.getClientFromData(loggedInUser.getUsername());
            for (Event e : c1.getEventsBooked()) {
                boolean past = false;
                boolean future = false;
                if (flagPast && e.getStartDate().isBefore(LocalDate.now())) {
                    past = true;
                } else if (!flagPast && e.getStartDate().isAfter(LocalDate.now())) {
                    future = true;
                }
                if (past || future) resEvents.add(e);

            }

        } catch (NullPointerException e) {
            // do nothing
        }
        return true;
    }

    public Event showEventDetails(String id) {

        try {
            c1 = Client.getClientFromData(loggedInUser.getUsername());
            int eventId = Integer.parseInt(id);
            for (Event e : c1.getEventsBooked()) {
                if (e.getId() == eventId) return e;
            }
            setMsg("This Event is not on the list");
            return null;
        } catch (NumberFormatException e) {
            setMsg("Invalid event id to show");
            return null;
        } catch (NullPointerException e) {
            //do nothing
        }
        return null;
    }

    public List<Event> showUpcomingEvents() {
        ArrayList<Event> resTmp = new ArrayList<>();
        for (Event e : EventData.getEvents()) {
            if (e.getStartDate().isAfter(LocalDate.now())) {
                resTmp.add(e);
            }
        }
        return resTmp;
    }

    // only for steps
    public void clearCalenderEvents() {
        if (!resEvents.isEmpty()) resEvents.clear();
    }

    public List<Event> getResEvents() {
        return resEvents;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}