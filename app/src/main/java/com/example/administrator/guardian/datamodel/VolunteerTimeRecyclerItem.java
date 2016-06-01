package com.example.administrator.guardian.datamodel;

/**
 * Created by Administrator on 2016-05-06.
 */
public class VolunteerTimeRecyclerItem {
    private String vol_name;
    private String senior_name;
    private String startInfo;
    private String year;
    private String month;
    private String day;
    private String hour;
    private String minute;
    private int req_hour;
    private String senior_id;

    public String getSenior_id(){return senior_id;}
    public String  getVol_name(){return vol_name;}
    public String getSenior_name(){return senior_name;}
    public String getStartInfo(){return startInfo;}
    public String getYear(){return year;}
    public String getMonth(){return month;}
    public String getDay(){return day;}
    public String getHour(){return hour;}
    public String getMinute(){return minute;}
    public int getReq_hour(){return req_hour;}

    public VolunteerTimeRecyclerItem(String vol_name, String senior_id, String senior_name, String startInfo, String year, String month, String day, String hour, String minute, int req_hour){
        this.vol_name = vol_name;
        this.senior_id = senior_id;
        this.senior_name = senior_name;
        this.startInfo = startInfo;
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.req_hour = req_hour;
    }
}
