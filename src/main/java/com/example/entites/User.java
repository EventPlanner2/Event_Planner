package com.example.entites;

public class User {

    private String username;
    private String password;
    private String contactEmail;

    private Character role;

    private Double budget;


    public User(String username, String password, String contactEmail, Character role) {
        this.username = username;
        this.password = password;
        this.contactEmail = contactEmail;
        this.role = role;
        this.budget = 1000.0;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Character getRole() {
        return role;
    }

    public void setRole(Character role) {
        this.role = role;
    }

    public Double getBudget() {
        return budget;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }
}