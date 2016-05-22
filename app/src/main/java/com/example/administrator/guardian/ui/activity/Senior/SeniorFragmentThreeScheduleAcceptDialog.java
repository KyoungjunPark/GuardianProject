package com.example.administrator.guardian.ui.activity.Senior;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.guardian.R;

public class SeniorFragmentThreeScheduleAcceptDialog extends Dialog {

    private TextView sftsa_Name;
    private TextView sftsa_Age;
    private TextView sftsa_Gender;
    private TextView sftsa_vDate;
    private TextView sftsa_vTime;

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

    private Button sftsa_left;
    private Button sftsa_right;

    public SeniorFragmentThreeScheduleAcceptDialog(Context context, int year, int month, int day, int startHour, int startMinute, int finishHour, int finishMinute, String name, int age, String gender) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.year=year;
        this.month=month;
        this.day=day;
        this.startHour=startHour;
        this.startMinute=startMinute;
        this.finishHour=finishHour;
        this.finishMinute=finishMinute;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.35f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_senior_fragment_three_schedule_accept_dialog);

        setLayout();
        setTitle(name);
        setContent(year,month,day,startHour,startMinute,finishHour,finishMinute,age,gender);

        sftsa_left = (Button)findViewById(R.id.sftsa_left);
        sftsa_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SeniorFragmentThreeScheduleAcceptDialog.this.dismiss();
            }
        });
        sftsa_right = (Button)findViewById(R.id.sftsa_right);
        sftsa_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SeniorFragmentThreeScheduleAcceptDialog.this.dismiss();
            }
        });
    }
    private void setTitle(String Name){

        sftsa_Name.setText(Name);
    }

    private void setContent(int year, int month, int day, int startHour, int startMinute, int finishHour,int finishMinute, int age, String gender){

        sftsa_vDate.setText(year+"년 "+ month+"월 "+day+"일 ");
        sftsa_vTime.setText(startHour+":"+startMinute+" ~ "+finishHour+":"+finishMinute);
        sftsa_Age.setText("나이 : "+age);
        sftsa_Gender.setText("성별 : "+ gender);
    }

    /*
     * Layout
     */
    private void setLayout(){
        sftsa_Name = (TextView) findViewById(R.id.sftsa_name);
        sftsa_Age = (TextView)findViewById(R.id.sftsa_age);
        sftsa_Gender = (TextView)findViewById(R.id.sftsa_gender);
        sftsa_vDate= (TextView)findViewById(R.id.sftsa_vDate);
        sftsa_vTime = (TextView)findViewById(R.id.sftsa_vTime);
    }

}
