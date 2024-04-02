package com.example.services;

import com.example.entites.Client;
import com.example.entites.User;

public class UpgradeClient {

    private User loggedInUser;
    private Client loggedInClient;
    private String msg;

    public UpgradeClient(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public void upgradeClientPerform(){
        if(loggedInUser == null){
            setMsg("You need to login first");
            return;
        }
        try {
            loggedInClient = Client.getClientFromData(loggedInUser.getUsername());
            if (loggedInClient.isOrganizer()) {
                setMsg("the account is already upgraded");
                return;
            }
            loggedInClient.setOrganizer(true);
            setMsg("Your Account has been upgraded");
        }
        catch (NullPointerException e){
            setMsg("only clients can upgrade their accounts");

        }
    }
    public void cancelUpgradeClientPerform(){
        setMsg("You are not Organizer anymore ");
        loggedInClient.setOrganizer(false);
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Client getLoggedInClient() {
        return loggedInClient;
}
}