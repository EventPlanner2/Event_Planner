package com.example.services;

import com.example.data.EventData;
import com.example.entites.Client;
import com.example.entites.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import static com.example.data.EventData.getEvents;
import static com.example.data.RoomData.getRooms;

public class BookEvent {


    private String msg;
    private boolean email_sent;

    public BookEvent(){
        msg = "";
    }
    public boolean BookEventPerform(String id,String username){
        if(getEvents().isEmpty()){
            msg = "There are no upcoming events to book.";
            return false;
        }
        try{
            Client c1 = Client.getClientFromData(username);
            String user = c1.getUsername();

            int eventid = Integer.parseInt(id);
            for(Event e : c1.getEventsBooked ()){
                if(e.getId() == eventid){
                    msg = "You Have Already Booked this event";
                    return false;
                }
            }

            if(Event.getEventByID(eventid).getStartDate().isBefore(LocalDate.now())){
                msg = "You Can't Book Event not from List.";
                return false;
            }
          
            //c1.eventsBooked.add(Event.getEventByID(eventid));
            c1.getEventsBooked ().add(Event.getEventByID(eventid));
            msg = "You have successfully booked the event";
            email_sent = true;
            // email sending here (make a class called EmailSender and call the function Sendemail here)

            return true;
        }
        catch (NumberFormatException e){
            if(id.isEmpty())
                msg = "Please select an event to book.";
            else
                msg = "Invalid event ID";
            return false;
        }
        catch (NullPointerException e){
            msg = "You Are not Client";
            return false;
        }



    }

    public boolean cancelBookEvent(String id,String username){

        try {
            int eventid = Integer.parseInt(id);
            if(!Event.getEventByID(eventid).getStartDate().isAfter(LocalDate.now()))
            {
                setMsg("This Event has gone");
                return false;
            }
            Client c1 = Client.getClientFromData(username);
            for (int i = 0; i < c1.getEventsBooked().size(); i++) {
                if (c1.getEventsBooked().get(i).getId() == eventid) {
                    c1.getEventsBooked().remove(i);
                    return true;
                }
            }
        }
        catch (NumberFormatException e){
            setMsg("Invalid event id to cancel booking");
        }
        catch (NullPointerException e){
            setMsg("Event or Client D.N.E");
        }
        return false;

    }
    public ArrayList<Event> chooseBookEvent(){

        ArrayList<Event> res = new ArrayList<>();
        LocalDate dateNow = LocalDate.now();

        for(Event e : getEvents()){
            if(e.getStartDate().isAfter(dateNow)){
                res.add(e);
            }
        }

        return res;
    }

    public ArrayList<Event> chooseCancelBookEvent(String username){

        ArrayList<Event> res = new ArrayList<>();
        LocalDate dateNow = LocalDate.now();

        Client c1 = Client.getClientFromData(username);
        for(Event e : c1.getEventsBooked()){
            if(e.getStartDate().isAfter(dateNow)){
                res.add(e);
            }
        }

        return res;

    }

    public boolean isEmail_sent() {
        return email_sent;
    }

    public void setEmail_sent(boolean email_sent) {
        this.email_sent = email_sent;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
