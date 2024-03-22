package com.example.data;

import com.example.entites.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomData {
    private static final List<Room> rooms = new ArrayList<>();

    private static int id = 1;
    public RoomData(){
        initializeData();
    }

    public void initializeData(){

        addRoom("Room 1",60,150.0,"First room in system",true);
        Room r1 = new Room(4,"Room 4",55,130,"Fourth room in system",true);
        rooms.add(r1);
    }

    public static void addRoom(String name,int capacity , double cost, String description , boolean available ){

        Room r1 = new Room(getId(),name,capacity,cost,description,available);
        rooms.add(r1);

    }

    public static int getId() {
        return id++;
    }

    public static List<Room> getRooms() {
        return rooms;
    }
}
