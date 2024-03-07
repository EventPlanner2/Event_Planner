package com.example.data;

import com.example.entites.Admin;
import com.example.entites.Client;
import com.example.entites.ServiceProvider;
import com.example.entites.User;

import java.util.ArrayList;
import java.util.List;

public class UserData {

    private static List<User> users = new ArrayList<>();
    private static List<Client> clients = new ArrayList<>();
    private static List<ServiceProvider> sps = new ArrayList<>();

    public UserData(){

        initializeData();

    }
    private static void initializeData(){
        // initial objects (could be replaced by database)

        User u1 = new User("Admin","123456","Admin@Gmail.com",'a'); // Admin
        ServiceProvider u2 = new ServiceProvider("FactoryX","FactoryX123","Factoryx@Gmail.com",'s'); // Service-provider
        Client u3 = new Client("Ahmad","Ahmad12345","Ahmad123@Gmail.com",'c',false); // Client
        Client u4 = new Client("Ali Turabi","123456789","ali123@Gmail.com",'c',true); // Client


        users.add(u1);users.add(u2);users.add(u3);users.add(u4);
        clients.add(u3);
        clients.add(u4);
        sps.add(u2);
    }

    public List<User> getUsers(){
        return users;
    }

    public static void addUser(String username , String password , String email , Character role){
        User obj = null;
        if(role == 'a'){
            obj = new Admin(username,password,email,role);
        }
        else if(role == 's'){
           sps.add(new ServiceProvider(username,password,email,role));
           obj = sps.get(sps.size() - 1);
        }
        else if(role == 'c'){
            clients.add(new Client(username,password,email,role,false));
            obj = clients.get(clients.size() - 1);
        }
        
        users.add(obj);
    }

    public static void completeSP(String username,String Location,String type,int price){
        for(ServiceProvider sp : sps){
            if(sp.getUsername().equals(username)){
                sp.setType(type);
                sp.setPrice(price);
                sp.setLocation(Location);
                sp.setFirstLogin(false);
            }
        }
    }
    public static List<ServiceProvider> getSps() {
        return sps;
    }

    public static List<Client> getClients() {
        return clients;
    }
}
