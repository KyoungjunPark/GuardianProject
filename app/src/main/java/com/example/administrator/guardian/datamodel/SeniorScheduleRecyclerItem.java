package com.example.administrator.guardian.datamodel;

/**
 * Created by Administrator on 2016-05-22.
 */
public class SeniorScheduleRecyclerItem {
    private String id;
    private int year;
    private int month;
    private int day;
    private int startHour;
    private int startMinute;
    private int finishHour;
    private int finishMinute;
    private String name;
    private int age;
    private String gender;
    private String contents;
    private int type;

    public int getYear(){return this.year;}
    public int getMonth(){return this.month;}
    public int getDay(){return this.day;}
    public int getStartHour(){return this.startHour;}
    public int getStartMinute(){return this.startMinute;}
    public int getFinishHour(){return this.finishHour;}
    public int getFinishMinute(){return this.finishMinute;}

    public String getId(){return this.id;}
    public String getName(){return this.name;}
    public int getAge(){return this.age;}
    public String getGender(){return this.gender;}
    public String getContents(){return this.contents;}
    public int getType(){return this.type;}

    public SeniorScheduleRecyclerItem(String id, int year, int month, int day, int startHour, int startMinute, int finishHour, int finishMinute, String name, int age, String gender, String contents, int type){
        this.id=id;
        this.year=year;
        this.month=month;
        this.day=day;
        this.startHour=startHour;
        this.startMinute=startMinute;
        this.finishHour=finishHour;
        this.finishMinute=finishMinute;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.contents=contents;
        this.type=type;
    }
}
