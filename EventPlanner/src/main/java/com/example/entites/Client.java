package com.example.entites;

import com.example.data.UserData;

import java.util.List;

public class Client extends User{

    private boolean isOrganizer;

    public Client(String username, String password, String contactEmail, Character role,boolean isOrganizer) {
        super(username, password, contactEmail, role);
        this.isOrganizer = isOrganizer;
    }

    public boolean isOrganizer() {
        return isOrganizer;
    }

    public void setOrganizer(boolean organizer) {
        isOrganizer = organizer;
    }

    public static Client getClientFromData(String username){
        List<Client> clients = UserData.getClients();
        for(Client c : clients){
            if(c.getUsername().equals(username)) return c;
        }

        return null;
    }
}
