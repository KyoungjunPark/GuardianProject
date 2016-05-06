package com.example.administrator.guardian.datamodel;

/**
 * Created by Administrator on 2016-05-06.
 */
public class VolunteerTimeRecyclerItem {
    private int id;
    private int VolunteerYear;
    private int VolunteerMonth;
    private int VolunteerDay;
    private int VolunteerStartTimeHour;
    private int VolunteerStartTimeMinute;
    private int VolunteerFinishTimeHour;
    private int VolunteerFinishTimeMinute;
    private String SeniorName;

    public int getId(){return this.id;}
    public int getYear(){return this.VolunteerYear;}
    public int getMonth(){return this.VolunteerMonth;}
    public int getDay(){return this.VolunteerDay;}
    public int getStartHour(){return this.VolunteerStartTimeHour;}
    public int getStartMinute(){return this.VolunteerStartTimeMinute;}
    public int getFinishHour(){return this.VolunteerFinishTimeHour;}
    public int getFinishMinute(){return this.VolunteerFinishTimeMinute;}
    public String getName(){return this.SeniorName;}

    public VolunteerTimeRecyclerItem(int id, int year, int month, int day, int starthour, int startminute, int finishhour, int finishminute, String name){
        this.id=id;
        this.VolunteerYear=year;
        this.VolunteerMonth=month;
        this.VolunteerDay=day;
        this.VolunteerStartTimeHour=starthour;
        this.VolunteerStartTimeMinute=startminute;
        this.VolunteerFinishTimeHour=finishhour;
        this.VolunteerFinishTimeMinute=finishminute;
        this.SeniorName=name;
    }
}
