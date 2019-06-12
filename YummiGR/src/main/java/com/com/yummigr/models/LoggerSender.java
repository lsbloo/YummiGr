package com.com.yummigr.models;
import javax.persistence.*;


@Entity
public class LoggerSender {



    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private long id;


    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Column(name="month" , length = 512)
    private String month;

    @Column(name="year" , length = 512)
    private String year;

    @Column(name="hour" , length = 512)
    private String hour;

    @Column(name="tracker" , length = 512)
    private String tracker;

    @Column(name="day" , length = 512)
    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }


    public LoggerSender(){}

    /**
     *
     * @param month
     * @param year
     * @param hour
     * @param tracker
     */
    public LoggerSender(String day,String month,String year ,String hour, String tracker){
        setMonth(month);
        setHour(hour);
        setTracker(tracker);
        setYear(year);
        setDay(day);
    }
    public String getTracker() {
        return tracker;
    }

    public void setTracker(String tracker) {
        this.tracker = tracker;
    }

    public String getYear() {
        return year;
    }

    public String getHour() {
        return hour;
    }

    public String getMonth() {
        return month;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setYear(String year) {
        this.year = year;
    }

}
