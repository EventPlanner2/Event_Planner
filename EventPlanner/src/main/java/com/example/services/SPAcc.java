package com.example.services;

import com.example.data.UserData;
import com.example.entites.ServiceProvider;

import java.util.ArrayList;

public class SPAcc {
    // Service Provider Account

    public boolean completeAccount;

    private ServiceProvider SP;

    private String completeAccountMsg;
    public static String[] AcceptedLocations = {"Nablus","Ramallah","Hebron","Jericho"};
    public static String[] AcceptedTypes = {"Tables Provider","Chairs Provider"};

    public boolean CompleteAccountPerform(String Location , String Price , String Type){

        if(!findLocation(Location)){setCompleteAccountMsg("invalid location");return false;}
        if(!findType(Type)){setCompleteAccountMsg("invalid type of service");return false;}
        try {
            int price = Integer.parseInt(Price.substring(1));
            UserData.completeSP(SP.getUsername(), Location, Type, price);
            completeAccount = false;
            return true;
        }
        catch (NumberFormatException e){
            setCompleteAccountMsg("invalid format for price");
            return false;
        }
    }


    protected boolean findLocation(String Location){
        for(String s : AcceptedLocations){
            if(s.equals(Location)) return true;
        }
        return false;
    }
    protected boolean findType(String Type){
        for(String s : AcceptedTypes){
            if(s.equals(Type)) return true;
        }
        return false;
    }
    public String getCompleteAccountMsg() {
        return completeAccountMsg;
    }

    public void setCompleteAccountMsg(String completeAccountMsg) {
        this.completeAccountMsg = completeAccountMsg;
    }

    public ServiceProvider getSP() {
        return SP;
    }

    public void setSP(ServiceProvider SP) {
        this.SP = SP;
    }
}
