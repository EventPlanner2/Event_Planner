package com.example.entites;

import java.time.LocalDate;
import java.time.LocalTime;

public class DateEvent {
    private String startDate;
    private String endDate;
    private String startClock;
    private String endClock;

    public DateEvent(String startDate, String endDate, String startClock, String endClock) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.startClock = startClock;
        this.endClock = endClock;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartClock() {
        return startClock;
    }

    public void setStartClock(String startClock) {
        this.startClock = startClock;
    }

    public String getEndClock() {
        return endClock;
    }

    public void setEndClock(String endClock) {
        this.endClock = endClock;
    }
}
