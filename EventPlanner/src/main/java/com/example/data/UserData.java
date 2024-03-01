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
    public UserData(){

        initializeData();

    }
//hello


//hello
    private static void initializeData(){
        // initial objects (could be replaced by database)

        User u1 = new User("Admin","123456","Admin@Gmail.com",'a'); // Admin
        User u2 = new User("FactoryX","FactoryX123","Factoryx@Gmail.com",'s'); // Service-provider
        Client u3 = new Client("Ahmad","Ahmad12345","Ahmad123@Gmail.com",'c',false); // Client


        users.add(u1);users.add(u2);users.add(u3);
        clients.add(u3);
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
            obj = new ServiceProvider(username,password,email,role);
        }
        else if(role == 'c'){
            clients.add(new Client(username,password,email,role,false));
            obj = clients.get(clients.size() - 1);
        }
        
        users.add(obj);
    }

    public static List<Client> getClients() {
        return clients;
    }
}
