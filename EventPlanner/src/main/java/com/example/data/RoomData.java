package com.example.data;

import com.example.entites.Room;

import java.util.ArrayList;
import java.util.List;

public class RoomData {
    private static List<Room> rooms = new ArrayList<>();

    private static int id = 0;
    public RoomData(){
        initializeData();
    }

    public void initializeData(){

        addRoom("Room 1",40,150.0,"First room in system",true);

    }

    public static void addRoom(String name,int capacity , double cost, String description , boolean available ){

        Room r1 = new Room(getId(),name,capacity,cost,description,available);
        rooms.add(r1);

    }

    public static int getId() {
        return id++;
    }

    public List<Room> getRooms() {
        return rooms;
    }
}
