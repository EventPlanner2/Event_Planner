package com.example.services;

import com.example.data.EventData;
import com.example.entites.Client;
import com.example.entites.Event;
import com.example.entites.User;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;

public class AddEvent {

    private String msg;
private String username;
    private User loggedInUser;
    private int eventId;
    private String eventName;
    private String eventDescription;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startClock;
    private LocalTime endClock;
    private int attendeeCount;
    private String imagePath;


    public boolean AddEventPerformed(int eid ,String name, String description, String startDate, String endDate,
                                     String startClock, String endClock, String attendeeCount, String imagePath) {
        msg = "";
        // Check for missing or invalid name
        if (name.isEmpty()) {
            setMsg("Missing name ID");
            return false;
        } else if (name.length() < 3) {
            setMsg("The name of Event must be at least 3 characters long");
            return false;
        }

        // Check for missing or invalid event ID
//       if (eventId <= 0) {
//           setMsg("Missing event ID");
//           return false;
//       }
      EventData t = new EventData();
       List<Event> event = t.getEvents();
      for (Event events : event) {
            if (events.getUsername().equals(username))
            if(events.getId()==eid)
            {
               setMsg("Invalid event ID is used");
               return false; // Event ID is already used

          }
      }


            // Check for missing or invalid username , can make event?
//        if (username.isEmpty()) {
//            setMsg("Missing username");
//            return false;
//        }
//
//        Client c=Client.getClientFromData(username);
//        if(c==null) {
//            setMsg("The user is not an orgnizer");
//            return false;}
//
//            if (c.getNumberEvent()>=4) {
//                setMsg("The organizer already created 3 events");
//                return false;
//            }


            // Check for missing or invalid description
            if (description.isEmpty()) {
                setMsg("Missing description");
                return false;
            } else if (description.length() < 20) {
                setMsg("Description must be at least 20 characters long");
                return false;
            }

            // Check for missing or invalid start date
            if (startDate.isEmpty()) {
                setMsg("Missing start date");
                return false;
            } else {
                try {
                    LocalDate.parse(startDate); // Attempt to parse start date
                } catch (DateTimeParseException e) {
                    setMsg("Invalid start date");
                    return false;
                }
            }

            // Check for missing or invalid end date
            if (endDate.isEmpty()) {
                setMsg("Missing end date");
                return false;
            } else {
                try {
                    LocalDate.parse(endDate); // Attempt to parse end date
                } catch (DateTimeParseException e) {
                    setMsg("Invalid end date");
                    return false;
                }
            }

            // Check for missing or invalid start clock
            if (startClock.isEmpty()) {
                setMsg("Missing start clock");
                return false;
            } else {
                try {
                    LocalTime.parse(startClock); // Attempt to parse start clock
                } catch (DateTimeParseException e) {
                    setMsg("Invalid start clock");
                    return false;
                }
            }

            // Check for missing or invalid end clock
            if (endClock.isEmpty()) {
                setMsg("Missing end clock");
                return false;
            } else {
                try {
                    LocalTime.parse(endClock); // Attempt to parse end clock
                } catch (DateTimeParseException e) {
                    setMsg("Invalid end clock");
                    return false;
                }
            }

            // Check for missing or invalid attendee count
            if (attendeeCount.isEmpty()) {
                setMsg("Missing attendee count");
                return false;
            } else {
                try {
                    int count = Integer.parseInt(attendeeCount); // Attempt to parse attendee count
                    if (count <= 0) {
                        setMsg("Invalid attendee count");
                        return false;
                    }
                } catch (NumberFormatException e) {
                    setMsg("Invalid attendee count");
                    return false;
                }
            }
            // Check for missing or invalid image path
            if (imagePath.isEmpty()) {
                setMsg("Missing image path");
                return false;
            } else {
                File imageFile = new File(imagePath);
                if (!imageFile.exists()) {
                    setMsg("Invalid image path");
                    return false;
                }
            }

            setMsg("Every values is valid");
            return true;

        }
        public boolean addEvent (String username,int eventId, String name, String description, String startDate, String
        endDate,
                String startClock, String endClock, String attendeeCount, String imagePath){
            if (AddEventPerformed(eventId, name, description, startDate, endDate,
                    startClock, endClock, attendeeCount, imagePath) == false) {

                msg = "";
                setMsg("The Event can't be created");
                return false;
            }

           if (isOrgnaizer(username)&&canAddEvent(username)) {
               Event event = new Event(username, eventId, name, description, LocalDate.parse(startDate),
                       LocalDate.parse(endDate), LocalTime.parse(startClock), LocalTime.parse(endClock),
                       Integer.parseInt(attendeeCount));
               event.setPathImage(imagePath);
               msg = "";
               setMsg("The Event is created ");
               EventData.addEvent(event);
               Client c = Client.getClientFromData(username);

               c.setNumberEvent(c.getNumberEvent() + 1);
               return true;
           }
            return false;
           }










    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {

            this.msg =  msg;

    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }


    public boolean isOrgnaizer(String username){
        Client c=Client.getClientFromData(username);
        if(c==null) {
            setMsg("The user is not an orgnizer");
            return false;}
        else {
            if(c.isOrganizer())
                return true;


        }

        return false;
    }
//    public boolean canAddEvent(String username){
//        Client c=Client.getClientFromData(username);
//        if (c.getNumberEvent() >= 3) {
//            setMsg("The organizer has already created 3 events");
//            return false;
//        }
//
//        return true;
//    }
    public boolean canAddEvent(String userName){
        Client c=Client.getClientFromData(userName);
        if(c==null) {
            setMsg("User DNE");
            return false;
        }else{
           // c.setNumberEvent(0);
if(c.getNumberEvent()==0)
    return true;}
//        if(c.getNumberEvent()==0){
//        setMsg("The organizer has already created 3 events");
//        return true;
//    }
   return false;
    }


//public boolean validEventId(int id){
//    EventData t = new EventData();
//      List<Event> event = t.getEvents();
//      for (Event events : event) {
//          if (events.getId() == eventId) {
//               setMsg("Invalid event ID is used");
//              return false; // Event ID is already used
//
//         }
//      }
//      return true;
//}

}
