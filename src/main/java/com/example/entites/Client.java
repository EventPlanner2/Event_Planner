package com.example.entites;

import com.example.data.UserData;
import java.util.ArrayList;
import java.util.List;

public class Client extends User {

    private boolean isOrganizer;
    private int numberEvent;
    private List<Event> eventsBooked; // Use interface List instead of ArrayList
    public Client(String username, String password, String contactEmail, Character role, boolean isOrganizer) {
        super(username, password, contactEmail, role);
        this.isOrganizer = isOrganizer;
        this.numberEvent = 0;
        eventsBooked = new ArrayList<>(); // Initialize as ArrayList
    }

    public boolean isOrganizer() {
        return isOrganizer;
    }

    public void setOrganizer(boolean organizer) {
        isOrganizer = organizer;
    }

    public static Client getClientFromData(String username) {
        List<Client> clients = UserData.getClients();
        for (Client c : clients) {
            if (c.getUsername().equals(username))
                return c;
        }
        return null;
    }

    public int getNumberEvent() {
        return numberEvent;
    }

    // Setter method for numberEvent
    public void setNumberEvent(int numberEvent) {
        this.numberEvent = numberEvent;
    }

    public List<Event> getEventsBooked() {
        return eventsBooked;
    }

    public void setEventsBooked(List<Event> eventsBooked) {
        this.eventsBooked = eventsBooked;
    }
}
