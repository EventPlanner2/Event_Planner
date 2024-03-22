package com.example.services;

import com.example.entites.Client;
import com.example.entites.Event;
import static com.example.data.EventData.getEvents;

public class DeleteUpdateEvent {


    private String msg;
    public AddEvent addEvent;

    public DeleteUpdateEvent(AddEvent addEvent) {
        this.msg = "";
        this.addEvent =addEvent;
    }

    public boolean UpdateEventPerform(String id,String username, String name, String description, String startDate, String
            endDate, String startClock, String endClock, String attendeeCount, String imagePath){

        try{
            int eventid = Integer.parseInt(id);
            if(!DoesEventExists(eventid)){
                msg = "Non-Existing Event";
                return false;
            }
            boolean tmp = DeleteEventPerform(id);

            boolean tmp2 = addEvent.addEvent(username,eventid,name,description,startDate,endDate,startClock,endClock,attendeeCount,imagePath);
            this.msg = addEvent.getMsg();
            return tmp && tmp2;
        }
        catch (NumberFormatException e){
            msg = "Invalid ID";
            return false;
        }


    }

    public boolean DeleteEventPerform(String id_tmp){
        try {
            int id = Integer.parseInt(id_tmp);
            if (!DoesEventExists(id)) {
                msg = "Non-Existing Event";
                return false;
            }
            for (int i = 0; i < getEvents().size(); i++) {
                if (getEvents().get(i).getId() == id) {
                    Client c = Client.getClientFromData(getEvents().get(i).getUsername());
                    c.setNumberEvent(c.getNumberEvent() - 1);
                    getEvents().remove(i);

                    return true;
                }
            }
        }
        catch (NumberFormatException e){
            msg = "Invalid ID";
            return false;
        }

        return false;
    }
    public boolean DoesEventExists(int id){

        for(Event e : getEvents()){
            if(e.getId() == id) return true;
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
