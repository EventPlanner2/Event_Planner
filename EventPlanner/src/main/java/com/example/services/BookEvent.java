package com.example.services;

import com.example.entites.Client;
import com.example.entites.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import static com.example.data.EventData.getEvents;

public class BookEvent {


    public String msg;
    public boolean email_sent;

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


}
