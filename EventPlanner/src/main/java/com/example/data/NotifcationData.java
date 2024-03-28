package com.example.data;

import com.example.entites.Notification;

import java.util.ArrayList;

public class NotifcationData {
    private static ArrayList<Notification> notifcations = new ArrayList<>();

    private NotifcationData() {

    }

    private static int ID = 1;
    public static int oldSize;
    public static ArrayList<Notification> getNotifcations() {
        return notifcations;
    }
    public static void addNotification(String msg){
        oldSize = notifcations.size();
        notifcations.add(new Notification(getID(),msg));
    }
    private static int getID(){
        return ID++;
    }
}
