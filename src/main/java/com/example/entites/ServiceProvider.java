package com.example.entites;

import com.example.data.UserData;

public class ServiceProvider extends User {

    private boolean firstLogin;
    private String type;
    private String location;

    private int price;

    public ServiceProvider(String username, String password, String contactEmail, Character role) {
        super(username, password, contactEmail, role);
        firstLogin = true;
        type = "";
        location = "";
        price = 0;
    }

    public static ServiceProvider getSPFromData(String username) {
        for (ServiceProvider s : UserData.getSps()) {
            if (s.getUsername().equals(username)) return s;
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
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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