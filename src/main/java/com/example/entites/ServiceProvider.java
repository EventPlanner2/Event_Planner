package com.example.entites;

import com.example.data.UserData;

import java.util.List;

public class ServiceProvider extends User{

    private boolean firstLogin;
    private String type;
    private String Location;
    private int price;
    public ServiceProvider(String username, String password, String contactEmail, Character role) {
        super(username, password, contactEmail, role);
        firstLogin = true;
        type = "";
        Location = "";
        price = 0;
    }

    public static ServiceProvider getSPFromData(String username){
        List<ServiceProvider> sps = UserData.getSps();
        for(ServiceProvider s : sps){
            if(s.getUsername().equals(username)) return s;
        }

        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        this.firstLogin = firstLogin;
    }
}
