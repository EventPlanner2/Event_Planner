package com.example.entites;

import java.time.LocalDate;
import java.time.LocalTime;

public class EventBuilder {
    private String username;
    private int id;
    private String eventName;
    private String eventDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startClock;
    private LocalTime endClock;
    private int attendeeCount;
    private String imagePath;

    public EventBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public EventBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public EventBuilder setEventName(String eventName) {
        this.eventName = eventName;
        return this;
    }

    public EventBuilder setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
        return this;
    }

    public EventBuilder setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public EventBuilder setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public EventBuilder setStartClock(LocalTime startClock) {
        this.startClock = startClock;
        return this;
    }

    public EventBuilder setEndClock(LocalTime endClock) {
        this.endClock = endClock;
        return this;
    }

    public EventBuilder setAttendeeCount(int attendeeCount) {
        this.attendeeCount = attendeeCount;
        return this;
    }

    public Event createEvent() {
        DateEvent dateEvent = new DateEvent(
                startDate.toString(),
                endDate.toString(),
                startClock.toString(),
                endClock.toString()
        );
        return new Event ( username, id, eventName, eventDescription, dateEvent, attendeeCount,imagePath);
    }

    public String getImagePath() {
        return imagePath;
    }

    public EventBuilder setImagePath(String imagePath) {
        this.imagePath = imagePath;
        return this;
    }
}