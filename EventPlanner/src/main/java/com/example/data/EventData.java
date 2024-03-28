package com.example.data;

import com.example.entites.Client;
import com.example.entites.Event;
import com.example.entites.EventBuilder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventData {
    private static final String EVENT_NAME = "Ali Turabi's Event";
    private static final String CREATOR_NAME = "Ahmad";


    private static final List<Event> events = new ArrayList<>();
    private static int ID_CURRENT = 4;

    public EventData() {
        initializeData();
    }

    private  void initializeData() {

        Event e1 = new EventBuilder ().setUsername ( CREATOR_NAME ).setId ( 1 ).setEventName ( EVENT_NAME ).setEventDescription ( "cars event" ).setStartDate ( LocalDate.of ( 2024, 3, 15 ) ).setEndDate ( LocalDate.of ( 2024, 4, 15 ) ).setStartClock ( LocalTime.of ( 19, 0 ) ).setEndClock ( LocalTime.of ( 23, 0 ) ).setAttendeeCount ( 50 ).createEvent ();
        e1.setPathImage("image_path1");

        Event e2 = new EventBuilder ().setUsername ( CREATOR_NAME ).setId ( 2 ).setEventName ( EVENT_NAME ).setEventDescription ( "mpo wer bmw version" ).setStartDate ( LocalDate.of ( 2025, 6, 20 ) ).setEndDate ( LocalDate.of ( 2025, 6, 20 ) ).setStartClock ( LocalTime.of ( 8, 0 ) ).setEndClock ( LocalTime.of ( 9, 0 ) ).setAttendeeCount ( 70 ).createEvent ();
        e2.setPathImage("image_path2");

        Event e3 = new EventBuilder ().setUsername ( CREATOR_NAME ).setId ( 3 ).setEventName ( EVENT_NAME ).setEventDescription ( "mpower bmw version" ).setStartDate ( LocalDate.of ( 2025, 6, 20 ) ).setEndDate ( LocalDate.of ( 2025, 6, 20 ) ).setStartClock ( LocalTime.of ( 10, 0 ) ).setEndClock ( LocalTime.of ( 11, 0 ) ).setAttendeeCount ( 100 ).createEvent ();
        e3.setPathImage("image_path3");


        events.add(e1);
        events.add(e2);
        events.add(e3);


    }

    public static List<Event> getEvents() {
        return events;
    }

    public static void addEvent(Event event) {
        events.add(event);
    }


}
