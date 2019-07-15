package com.com.yummigr.models;

public class MyLogger  {


    public String action;

    public String date;

    public String user_identifier;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public String getUser_identifier() {
        return user_identifier;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setUser_identifier(String user_identifier) {
        this.user_identifier = user_identifier;
    }


    public MyLogger(){}

    public MyLogger(String action, String date, String user_identifier){
        setAction(action);
        setDate(date);
        setUser_identifier(user_identifier);

    }
}
