package com.example.services;


import com.example.data.NotifcationData;


import com.example.entites.Event;
import com.example.entites.Room;
import com.example.entites.User;
import static com.example.data.EventData.getEvents;
import static com.example.data.RoomData.getRooms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReserveRoom {

    private User loggedInUser;
    private List<Event> res_event;
    private List<Room> res_room;
    private String msg;

    public ReserveRoom(User loggedInUser){
        this.loggedInUser = loggedInUser;
        res_room = new ArrayList<>();
        res_event = new ArrayList<>();
        msg = "";
    }

    public boolean ReserveRoomPerform(String eventID, String roomID) {
        boolean flag =false;
        try {
            int eventId = Integer.parseInt(eventID);
            flag =true;
            int roomId = Integer.parseInt(roomID);

            for (Event e : getEvents()) {
                if (e.getId() == eventId) {
                    if (!validateRoomReservation(e, roomId)) {
                        return false;
                    }

                    reserveRoom(e, roomId);
                    return true;
                }
            }

            msg = "the event ID is invalid";
            return false;
        } catch (NumberFormatException e) {
            msg = "the " + (flag ? "room" : "event") + " ID is invalid";
            return false;
        } catch (NullPointerException e) {
            msg = "the event ID is invalid";
            return false;
        }
    }

    private boolean validateRoomReservation(Event event, int roomId) {
        if (event.getRoomID() == roomId) {
            msg = "the room is already reserved for the event";
            return false;
        }
        if (event.getRoomID() != -1) {
            msg = "the event is already has a room";
            return false;
        }
        if (!Room.getRoomFromData(roomId).isAvailable()) {
            msg = "the room is already reserved for another event";
            return false;
        }
        if (event.getAttendeeCount() > Room.getRoomFromData(roomId).getCapacity()) {
            msg = "the room is lower capacity than attendance count";
            return false;
        }
        return true;
    }

    private void reserveRoom(Event event, int roomId) {
        event.setRoomID(roomId);
        event.setComplete(true);
        Room.getRoomFromData(roomId).setAvailable(false);
        msg = "Room has been reserved";
        String notification = LocalDate.now().toString() + "| " +
                "Room with name " + Room.getRoomFromData(roomId).getName() +
                " has been reserved for Event " + event.getEventName();
        NotifcationData.addNotification(notification);
    }

    public void ChooseReserveRoom(){

        for(Event e : getEvents()){
            if(e.getUsername().equals(loggedInUser.getUsername()) && !e.isComplete()){
                res_event.add(e);
            }
        }

        for(Room r : getRooms()){
            if(r.isAvailable()){
                res_room.add(r);
            }
        }

    }

    public User getLoggedInUser() {
        return loggedInUser;
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



    // Getter method for res_event
    public List<Event> getResEvent() {
        return res_event;
    }

    // Setter method for res_event
    public void setResEvent(List<Event> res_event) {
        this.res_event = res_event;
    }
    // Getter method for res_room
    public List<Room> getResRoom() {
        return res_room;
    }

    // Setter method for res_room
    public void setResRoom(List<Room> res_room) {
        this.res_room = res_room;
    }

}
