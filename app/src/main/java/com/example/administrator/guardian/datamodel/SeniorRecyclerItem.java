package com.example.administrator.guardian.datamodel;

/**
 * Created by Administrator on 2016-04-28.
 */
public class SeniorRecyclerItem {
    private String id;
    private String name;
    private int age;
    private String gender;
    private String phoneNumber;
    private String address;

    public String getId(){return this.id;}
    public String getName(){return this.name;}
    public int getAge(){return this.age;}
    public String getGender(){return this.gender;}
    public String getNumber(){return this.phoneNumber;}
    public String getAddress(){return this.address;}

    public SeniorRecyclerItem(String id, String name, int age, String gender, String address, String phoneNumber){
        this.id=id;
        this.name=name;
        this.age=age;
        this.gender=gender;
        this.address=address;
        this.phoneNumber=phoneNumber;
    }
}
