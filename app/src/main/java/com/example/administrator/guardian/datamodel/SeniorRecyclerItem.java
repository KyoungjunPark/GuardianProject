package com.example.administrator.guardian.datamodel;

/**
 * Created by Administrator on 2016-04-28.
 */
public class SeniorRecyclerItem {
    String login_id;
    String user_name;
    String user_birthdate;
    int user_age;
    String user_gender;
    String user_address;
    String user_tel;
    String distance;

    public String getId(){return this.login_id;}
    public String getName(){return this.user_name;}
    public String getBirthDate(){return this.user_birthdate;}
    public int getAge(){return this.user_age;}
    public String getGender(){return this.user_gender;}
    public String getAddress(){return this.user_address;}
    public String getTel(){return this.user_tel;}
    public String getDistance(){return  this.distance;}

    public SeniorRecyclerItem(String id, String name, String birthdate, int age, String gender, String address, String tel, String distance){
        this.login_id=id;
        this.user_name=name;
        this.user_birthdate=birthdate;
        this.user_age=age;
        this.user_gender=gender;
        this.user_address=address;
        this.user_tel=tel;
        this.distance = distance;
    }
}
