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
    private String name;
    private boolean available;
    private int capacity;
    private double costPerHour;
    private String description;

    private boolean tmpFlag;
    public AddRoom(List<Room> rooms){
        this.rooms = rooms;
        this.msg= "";
    }

    public boolean addRoomPerformed(String name,String available1,String capacity1, String costPerHour1,String description1){

        if(loggedInUser.getRole() != 'a') {setMsg("only Admin can add rooms");return false;}

        try {

            if(name.isEmpty() || available1.isEmpty() || capacity1.isEmpty() || costPerHour1.isEmpty() || description1.isEmpty()){
                setMsg("There is an empty attributes");
                return false;
            }

            this.name = name;
            if (isRoomExisted(this.name)) {
                setMsg("the room name is already existed");
                return false;
            }

            this.available = available1.equals("Available");

            this.capacity = Integer.parseInt(capacity1);
            if (capacity < 0) {
                setMsg("Capacity can't be negative");
                return false;
            }
            if (capacity > 1000) {
                setMsg("Max Capacity is 1000");
                return false;
            }
            tmpFlag = true;
            this.costPerHour = new BigDecimal(costPerHour1.substring(2)).doubleValue();

            this.description = description1;

            RoomData.addRoom(this.name, this.capacity, this.costPerHour, this.description, this.available);
            String notification = LocalDate.now()+"| "+ "Added Room With Name "+this.name+" And Capacity "+this.capacity+" And CostPerHour "+this.costPerHour;
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
