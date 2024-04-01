package com.example.services;

import com.example.data.NotifcationData;
import com.example.data.RoomData;
import com.example.entites.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AddRoom {


    private List<Room> rooms;
    private String msg;

    private User loggedInUser;


    private boolean tmpFlag;
    public AddRoom(List<Room> rooms){
        this.rooms = rooms;
        this.msg= "";
    }

    public boolean addRoomPerformed(String name1, String available1, String capacity1, String costPerHour1, String description1){

        if(loggedInUser.getRole() != 'a') {setMsg("only Admin can add rooms");return false;}

        try {

            if(name1.isEmpty() || available1.isEmpty() || capacity1.isEmpty() || costPerHour1.isEmpty() || description1.isEmpty()){
                setMsg("There is an empty attributes");
                return false;
            }

            String name = name1;
            if (isRoomExisted(name)) {
                setMsg("the room name is already existed");
                return false;
            }

            boolean available = available1.equals("Available");

            int capacity = Integer.parseInt(capacity1);
            if (capacity < 0) {
                setMsg("Capacity can't be negative");
                return false;
            }
            if (capacity > 1000) {
                setMsg("Max Capacity is 1000");
                return false;
            }
            tmpFlag = true;
            double costPerHour = new BigDecimal(costPerHour1.substring(1)).doubleValue();

            String description = description1;

            RoomData.addRoom(name, capacity, costPerHour, description, available);
            String notification = LocalDate.now()+"| "+ "Added Room With Name "+name+" And Capacity "+ capacity +" And CostPerHour "+ costPerHour;
            NotifcationData.addNotification(notification);
            setMsg("The room has been added ");
            return true;
        }
        catch (NumberFormatException e){
            if(!tmpFlag){
                setMsg("invalid format for Capacity");
                return false;
            }
            else{
                setMsg("invalid format for Cost Per Hour");
                return false;
            }

        }


    }

    public boolean isRoomExisted(String name){
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