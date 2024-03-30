package com.example.entites;

public class Notification {
    private int id;
    private String msg;

    public Notification(int id, String msg) {
        this.id = id;
        this.msg = msg;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
