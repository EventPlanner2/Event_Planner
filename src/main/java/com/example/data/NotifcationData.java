package com.example.data;

import com.example.entites.Notification;

import java.util.ArrayList;
import java.util.List;

public class NotifcationData {
    private static List<Notification> notifcations = new ArrayList<>();

    private NotifcationData() {

    }

    private static int id = 1;
    private static int oldSize;
    public static List<Notification> getNotifcations() {
        return notifcations;
    }
    public static void addNotification(String msg){
        oldSize = notifcations.size();
        notifcations.add(new Notification(getID(),msg));
    }
    private static int getID(){
        return id++;
    }

    public static int getOldSize() {
        return oldSize;
    }

    public static void setOldSize(int oldSize) {
        NotifcationData.oldSize = oldSize;
    }
}
