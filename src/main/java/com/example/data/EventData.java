package com.example.data;


import com.example.entites.Event;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class EventData {

    private static List<Event> events = new ArrayList<>();
    public static int id_current = 4;

    public EventData() {
        initializeData();
    }

    private static void initializeData() {
        // Initialize event objects
        Event e1 = new Event("Ahmad",1, "Ali Turabi's Event", "cars event",
                LocalDate.of(2024, 3, 15), LocalDate.of(2024, 4, 15),
                LocalTime.of(19, 0), LocalTime.of(23, 0), 50);
        e1.setPathImage("image_path1"); // Set image path for event 1

        Event e2 = new Event("Ahmad",2, "Ali Turabi's Event", "mpower bmw version",
                LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 20),
                LocalTime.of(8, 0), LocalTime.of(9, 0), 100);
        e2.setPathImage("image_path2"); // Set image path for event 2

        Event e3 = new Event("Ahmad",3, "Ali Turabi's Event", "mpower bmw version",
                LocalDate.of(2025, 6, 20), LocalDate.of(2025, 6, 20),
                LocalTime.of(10, 0), LocalTime.of(11, 0), 100);
        e3.setPathImage("image_path3"); // Set image path for event 3

        // Add events to the list
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

    public static int generateID(){
        return id_current++;
    }


}
