package com.example.administrator.guardian.datamodel;

/**
 * Created by Administrator on 2016-05-10.
 */
public class VolunteerRequestRecyclerItem {
    private String id;
    private String name;
    private int age;
    private String gender;

    public String getId(){return this.id;}
    public String getName(){return this.name;}
    public int getAge(){return this.age;}
    public String getGender(){return this.gender;}

    public VolunteerRequestRecyclerItem(String id, String name, int age, String gender){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender=gender;
    }
}