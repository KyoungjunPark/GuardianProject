package com.example.administrator.guardian.datamodel;

/**
 * Created by Administrator on 2016-04-28.
 */
public class SeniorRecyclerItem {
    private int id;
    private String name;
    private int age;
    private String gender;

    public int getId(){return this.id;}
    public String getName(){return this.name;}
    public int getAge(){return this.age;}
    public String getGender(){return this.gender;}

    public SeniorRecyclerItem(int id, String name, int age, String gender){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender=gender;
    }
}
