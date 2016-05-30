package com.example.administrator.guardian.datamodel;

/**
 * Created by Administrator on 2016-05-22.
 */
public class SeniorScheduleRecyclerItem {
    String volunteer_id;
    String startInfo;
    String details;
    String volunteer_name ="";
    int volunteer_age =0;
    String volunteer_gender="";
    int req_hour;
    int type;

    public String getId(){return this.volunteer_id;}
    public String getName(){return this.volunteer_name;}
    public int getAge(){return this.volunteer_age;}
    public String getGender(){return this.volunteer_gender;}
    public String getStartInfo(){return this.startInfo;}
    public String getDetails(){return this.details;}
    public int getReqHour(){return this.req_hour;}
    public int getType(){ return this.type;}

    public SeniorScheduleRecyclerItem(String volunteer_id, String volunteer_name, int volunteer_age, String volunteer_gender, String startInfo, String details, int req_hour, int type){
        this.volunteer_id = volunteer_id;
        this.volunteer_name = volunteer_name;
        this.volunteer_age = volunteer_age;
        this.volunteer_gender = volunteer_gender;
        this.startInfo = startInfo;
        this.details = details;
        this.req_hour = req_hour;
        this.type=type;
    }
}
