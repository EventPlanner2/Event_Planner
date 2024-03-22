package com.example.services;

import com.example.data.RoomData;
import com.example.entites.Room;

public class DeleteRoom {

    public String msg;

    public DeleteRoom() {
        this.msg = "";
    }

    public boolean deleteRoomPerformed(String id_tmp) {
        try {
            int id = Integer.parseInt(id_tmp);
            if (!doesRoomExists(id)) {
                setMsg("The room Dose not Exists");
                return false;
            }
            for (int i = 0; i < RoomData.getRooms().size(); i++) {
                if (RoomData.getRooms().get(i).getId() == id) {
                    setMsg("The Room with " + id + " has been Deleted");
                    RoomData.getRooms().remove(i);
                    return true;
                }
            }
        } catch (NumberFormatException e) {
            setMsg("Invalid ID");
            return false;
        }
        return false;
    }

    public boolean doesRoomExists(int id) {
        for (Room r : RoomData.getRooms()) {
            if (r.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return this.msg;
    }
}
