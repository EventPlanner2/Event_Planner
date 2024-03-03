package com.example.entites;

import com.example.entites.ServiceProvider;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Event {

    private int eventid;
    private String eventName;
    private String eventDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startClock;
    private LocalTime endClock;
    private String pathImage;
    private int attendeeCount;
    private boolean isComplete;
    private int roomID;
    ArrayList<ServiceProvider> serviceEntity;

    public Event(int id, String eventName, String eventDescription, LocalDate startDate, LocalDate endDate,
                 LocalTime startClock, LocalTime endClock, int attendeeCount) {
        this.eventid = id;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startClock = startClock;
        this.endClock = endClock;
        this.attendeeCount = attendeeCount;
        this.isComplete = false;
        this.roomID = -1;
        this.serviceEntity = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return eventid;
    }

    public void setId(int id) {
        this.eventid = id;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartClock() {
        return startClock;
    }

    public void setStartClock(LocalTime startClock) {
        this.startClock = startClock;
    }

    public LocalTime getEndClock() {
        return endClock;
    }

    public void setEndClock(LocalTime endClock) {
        this.endClock = endClock;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public int getAttendeeCount() {
        return attendeeCount;
    }

    public void setAttendeeCount(int attendeeCount) {
        this.attendeeCount = attendeeCount;
    }

    public void setServices(ArrayList<ServiceProvider> services) {
        this.serviceEntity = services;
    }

    public ArrayList<ServiceProvider> getServices() {
        return serviceEntity;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getPathImage() {
        return pathImage;
    }
}
