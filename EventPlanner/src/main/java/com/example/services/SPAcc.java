package com.example.services;

import com.example.data.UserData;
import com.example.entites.ServiceProvider;


public class SPAcc {
    // Service Provider Account

    private boolean completeAccount;

    private ServiceProvider sP;

    private String completeAccountMsg;
    private static String[] acceptedLocations = {"Nablus","Ramallah","Hebron","Jericho"};
    private static String[] acceptedTypes = {"Tables Provider","Chairs Provider"};

    public boolean completeAccountPerform(String location , String price , String type){

        if(!findLocation(location)){setCompleteAccountMsg("invalid location");return false;}
        if(!findType(type)){setCompleteAccountMsg("invalid type of service");return false;}
        try {
            int priceInt1 = Integer.parseInt(price.substring(1));
            UserData.completeSP(sP.getUsername(), location, type, priceInt1);
            completeAccount = false;
            setCompleteAccountMsg("Your Account is completed");
            return true;
        }
        catch (NumberFormatException e){
            setCompleteAccountMsg("invalid format for price");
            return false;
        }
    }


    protected boolean findLocation(String location){
        for(String s : acceptedLocations){
            if(s.equals(location)) return true;
        }
        return false;
    }
    protected boolean findType(String type){
        for(String s : acceptedTypes){
            if(s.equals(type)) return true;
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
        return sP;
    }

    public void setSP(ServiceProvider sp) {
        this.sP = sp;
    }

    public boolean isCompleteAccount() {
        return completeAccount;
    }

    public void setCompleteAccount(boolean completeAccount) {
        this.completeAccount = completeAccount;
    }
}
