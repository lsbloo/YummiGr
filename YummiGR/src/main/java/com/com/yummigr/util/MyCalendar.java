package com.com.yummigr.util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
public class MyCalendar {

    private Date date;

    public MyCalendar(){
        this.date = new Date();

    }

    public String getDateToday(){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String date_ = dateFormat.format(date);
       return  date_;

    }



}
