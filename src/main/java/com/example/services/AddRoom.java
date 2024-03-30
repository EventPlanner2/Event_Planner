package com.example.services;

import com.example.data.RoomData;
import com.example.entites.*;
import io.cucumber.java.hu.De;

import java.math.BigDecimal;
import java.util.List;

public class AddRoom {


    private List<Room> rooms;
    private String msg;

    private User loggedInUser;
    private String name;
    private boolean Available;
    private int capacity;
    private double CostPerHour;
    private String Description;

    private boolean tmp_flag;
    public AddRoom(List<Room> rooms){
        this.rooms = rooms;
        this.msg= "";
    }

    public boolean AddRoomPerformed(String name,String Available,String Capacity, String CostPerHour,String Description){

        if(loggedInUser.getRole() != 'a') {setMsg("only Admin can add rooms");return false;}

        try {

            if(name.isEmpty() || Available.isEmpty() || Capacity.isEmpty() || CostPerHour.isEmpty() || Description.isEmpty()){
                setMsg("There is an empty attributes");
                return false;
            }

            this.name = name;
            if (IsRoomExisted(this.name)) {
                setMsg("the room name is already existed");
                return false;
            }

            this.Available = Available.equals("Available");

            this.capacity = Integer.parseInt(Capacity);
            if (capacity < 0) {
                setMsg("Capacity can't be negative");
                return false;
            }
            if (capacity > 1000) {
                setMsg("Max Capacity is 1000");
                return false;
            }
            tmp_flag = true;
            this.CostPerHour = new BigDecimal(CostPerHour.substring(1)).doubleValue();

            this.Description = Description;

            RoomData.addRoom(this.name, this.capacity, this.CostPerHour, this.Description, this.Available);
            return true;
        }
        catch (NumberFormatException e){
            if(!tmp_flag){
                setMsg("invalid format for Capacity");
                return false;
            }
            else{
                setMsg("invalid format for Cost Per Hour");
                return false;
            }

        }


    }

    public boolean IsRoomExisted(String name){
        for(Room r : rooms){
            if(r.getName().equals(name)) return true;
        }

        return false;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
