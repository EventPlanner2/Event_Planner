package com.example.services;

import com.example.data.NotifcationData;
import com.example.entites.Event;
import com.example.entites.Room;
import com.example.entites.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.data.EventData.getEvents;
import static com.example.data.RoomData.getRooms;

public class ReserveRoom {

    private User loggedInUser;
    private List<Event> resEvent;
    private List<Room> resRoom;
    private String msg;

    public ReserveRoom(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        resRoom = new ArrayList<>();
        resEvent = new ArrayList<>();
        msg = "";
    }

    public boolean reserveRoomPerform(String eventID, String roomID) {
        boolean flag = false;
        try {
            int eventId = Integer.parseInt(eventID);
            flag = true;
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

    public void chooseReserveRoom() {
        for (Event e : getEvents()) {
            if (e.getUsername().equals(loggedInUser.getUsername()) && !e.isComplete()) {
                resEvent.add(e);
            }
        }

        for (Room r : getRooms()) {
            if (r.isAvailable()) {
                resRoom.add(r);
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

    // Getter method for resEvent
    public List<Event> getResEvent() {
        return resEvent;
    }

    // Setter method for resEvent
    public void setResEvent(List<Event> resEvent) {
        this.resEvent = resEvent;
    }

    // Getter method for resRoom
    public List<Room> getResRoom() {
        return resRoom;
    }

    // Setter method for resRoom
    public void setResRoom(List<Room> resRoom) {
        this.resRoom = resRoom;
    }
}
