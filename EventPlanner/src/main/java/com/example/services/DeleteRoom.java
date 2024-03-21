package com.example.services;

import com.example.data.RoomData;
import com.example.entites.Room;

import static com.example.data.RoomData.getRooms;

public class DeleteRoom {

    public String msg;

    public DeleteRoom() {
        this.msg = "";
    }

    public boolean deleteRoomPerform(String id_tmp) {
        try {
            int id = Integer.parseInt(id_tmp);
            if (!doesRoomExists(id)) {
                msg = "Non-Existing Room";
                return false;
            }
            for (int i = 0; i < RoomData.getRooms().size(); i++) {
                if (getRooms().get(i).getId() == id) {
                    getRooms().remove(i);
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            msg = "Invalid ID";
            return false;
        }
        return false;
    }

    public boolean doesRoomExists(int id) {
        for (Room e : RoomData.getRooms()) {
            if (e.getId() == id) return true;
        }
        return false;
    }


}
