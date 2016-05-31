package com.example.administrator.guardian.datamodel;

/**
 * Created by Administrator on 2016-05-21.
 */
public class VolunteerConfirmRecyclerItem {
    String senior_id;
    String senior_name;
    int senior_age;
    String senior_gender;
    String senior_address;
    String latitude;
    String longitude;
    String startInfo;
    String details;
    int req_hour;
    int type;

    public String getSenior_id(){return this.senior_id;}
    public String getSenior_name(){return this.senior_name;}
    public int getSenior_age(){return this.senior_age;}
    public String getSenior_gender(){return this.senior_gender;}
    public String getSenior_address(){return this.senior_address;}
    public String getLatitude(){return  this.latitude;}
    public String getLongitude(){return this.longitude;}
    public String getStartInfo(){return this.startInfo;}
    public String getDetails(){return this.details;}
    public int getReq_hour(){return this.req_hour;}
    public int getType(){return this.type;}

    public VolunteerConfirmRecyclerItem(String senior_id, String senior_name, int senior_age, String senior_gender, String senior_address, String latitude, String longitude, String startInfo,String details, int req_hour, int type){
        this.senior_id = senior_id;
        this.senior_name = senior_name;
        this.senior_age = senior_age;
        this.senior_gender = senior_gender;
        this.senior_address = senior_address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.startInfo = startInfo;
        this.details = details;
        this.req_hour = req_hour;
        this.type = type;
    }
}