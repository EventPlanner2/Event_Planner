package com.example.services;


import com.example.entites.Client;
import com.example.entites.Event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.data.EventData.getEvents;


public class BookEvent {


    private String msg;
    private boolean emailSent;

    public BookEvent(){
        msg = "";
    }
    public boolean bookEventPerform(String id,String userName){

        if(getEvents().isEmpty()){
            msg = "There are no upcoming events to book.";
            return false;
        }
        try{
            Client c1 = Client.getClientFromData(userName);
           String temporayUser = c1.getUsername();

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
          

            c1.getEventsBooked ().add(Event.getEventByID(eventid));

            String content = "Hi "+c1.getUsername()+"\n"+"You Have Registered to come to the "+Event.getEventByID(eventid).getEventName()+" Event";

            new EmailSender().sendEmail(c1.getContactEmail(),"Book Confirmation",content);
            msg = "You have successfully booked the event";
            emailSent = true;
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

    public boolean cancelBookEvent(String id,String userName){

        try {
            int eventid = Integer.parseInt(id);
            if(!Event.getEventByID(eventid).getStartDate().isAfter(LocalDate.now()))
            {
                setMsg("This Event has gone");
                return false;
            }
            Client c1 = Client.getClientFromData(userName);
            for (int i = 0; i < c1.getEventsBooked().size(); i++) {
                if (c1.getEventsBooked().get(i).getId() == eventid) {
                    c1.getEventsBooked().remove(i);
                    setMsg("You have successfully canceled an Booking Event");
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
    public List<Event> chooseBookEvent() {
        List<Event> res = new ArrayList<>();
        LocalDate dateNow = LocalDate.now();

        for (Event e : getEvents()) {
            if (e.getStartDate().isAfter(dateNow)) {
                res.add(e);
            }
        }

        return res;
    }
    public List<Event> chooseCancelBookEvent(String userName) {
        List<Event> res = new ArrayList<>();
        LocalDate dateNow = LocalDate.now();

        Client c1 = Client.getClientFromData(userName);
        for (Event e : c1.getEventsBooked()) {
            if (e.getStartDate().isAfter(dateNow)) {
                res.add(e);
            }
        }

        return res;
    }
    public boolean isEmailSent() {
        return emailSent;
    }

    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
