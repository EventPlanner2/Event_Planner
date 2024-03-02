package com.example.entites;



public class Room {

    private int id;
    private String name;
    private int capacity;
    private double costPerHour;
    private String description;
    private boolean Available;

    public Room(int id, String name, int capacity, double costPerHour, String description,boolean Available) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.costPerHour = costPerHour;
        this.description = description;
        this.Available = Available;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getCostPerHour() {
        return costPerHour;
    }

    public void setCostPerHour(double costPerHour) {
        this.costPerHour = costPerHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return Available;
    }

    public void setAvailable(boolean available) {
        Available = available;
    }
}