package com.example.services;
import com.example.entites.Client;
import com.example.entites.Room;

import static com.example.data.EventData.getEvents;
import static com.example.data.RoomData.getRooms;
public class DeleteRoom {

    String msg;
    public boolean DeleteRoomPerform(String id){

        try {
            int roomid = Integer.parseInt(id);
            if(!DoesRoomExists(roomid)){

                setMsg("Non-Existing Room");
                return false;
            }
            for (int i = 0; i < getRooms().size(); i++) {
                if (getRooms().get(i).getId() == roomid) {

                    getRooms().remove(i);
                    setMsg("Room with ID " + id + "Has Been Deleted");
                    return true;
                }
            }
        }
        catch (NumberFormatException e){
            setMsg("Invalid Room ID");
        }
        return false;
    }
    public boolean DoesRoomExists(int id){
        for(Room r : getRooms()){
            if(r.getId() == id) return true;
        }
        return false;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}