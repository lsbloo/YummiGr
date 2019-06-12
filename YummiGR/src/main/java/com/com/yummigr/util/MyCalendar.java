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

    public String[] getFormatedDataToday(String date_){
        String[]  result = date_.split("/");
        String day = result[0];
        String month = result[1];
        String yt = result[2];
        String[] ww = yt.split(" ");
        String year = ww[0];
        String hour = ww[1];
        String[] exit = new String[] {day,month,year,hour};
        return exit;
    }


}
