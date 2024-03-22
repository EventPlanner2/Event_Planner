package com.example.services;

import com.example.entites.Event;
import com.example.entites.Room;
import com.example.entites.User;
import static com.example.data.EventData.getEvents;
import static com.example.data.RoomData.getRooms;

import java.util.ArrayList;

public class ReserveRoom {

    private User loggedInUser;
    public ArrayList<Event> res_event;
    public ArrayList<Room> res_room;
    public String msg;

    public ReserveRoom(User loggedInUser){
        this.loggedInUser = loggedInUser;
        res_room = new ArrayList<>();
        res_event = new ArrayList<>();
        msg = "";
    }

    public boolean ReserveRoomPerform(String eventID,String roomID){
        boolean flag = false;
        try{

            int eventid = Integer.parseInt(eventID);
            flag = true;
            int roomid = Integer.parseInt(roomID);

            for(Event e : getEvents()){
                if(e.getId()==eventid){
                    if(e.getRoomID() == roomid){
                        msg = "the room is already reserved for the event";
                        return false;
                    }
                    if(e.getRoomID() != -1){
                        msg = "the event is already has a room";
                        return false;
                    }
                    if(!Room.getRoomFromData(roomid).isAvailable()){
                        msg = "the room is already reserved for another event";
                        return false;
                    }
                    if(e.getAttendeeCount() > Room.getRoomFromData(roomid).getCapacity()){
                        msg = "the room is lower capacity than attendance count";
                        return false;
                    }
                    e.setRoomID(roomid);
                    e.setComplete(true);
                    Room.getRoomFromData(roomid).setAvailable(false);
                    msg = "Room has been reserved";
                    return true;
                }
            }
            msg = "the event ID is invalid";
            return false;
        }
        catch (NumberFormatException e){
            if(flag){
                msg = "the room ID is invalid";
            }
            else{
                msg = "the event ID is invalid";
            }

            return false;
        }
        catch (NullPointerException e){
            msg = "the room ID is invalid";
            return false;
        }

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
}
