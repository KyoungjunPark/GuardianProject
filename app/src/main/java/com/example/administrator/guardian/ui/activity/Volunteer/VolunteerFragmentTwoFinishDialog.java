package com.example.administrator.guardian.ui.activity.Volunteer;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.guardian.R;

public class VolunteerFragmentTwoFinishDialog extends Dialog {

    private int year;
    private int month;
    private int day;
    private int startHour;
    private int startMinute;
    private int finishHour;
    private int finishMinute;

    private TextView vftdf_Name;
    private TextView vftdf_Contents;
    private TextView vftdf_vDate;
    private TextView vftdf_vTime;


    private String name;
    private String gender;
    private String contents;

    private Button vftdf_button;
    private ImageView vftdf_signature;

    public VolunteerFragmentTwoFinishDialog(Context context, int year, int month, int day, int startHour, int startMinute, int finishHour, int finishMinute, String name, String gender, String contents) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.year=year;
        this.month=month;
        this.day=day;
        this.startHour=startHour;
        this.startMinute=startMinute;
        this.finishHour=finishHour;
        this.finishMinute=finishMinute;
        this.name = name;
        this.gender=gender;
        this.contents = contents;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.6f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_volunteer_fragment_two_finish_dialog);

        setLayout();
        setTitle(name);
        setContent(year,month,day,startHour,startMinute,finishHour,finishMinute,contents);


        vftdf_signature.setImageResource(R.drawable.signature);

        vftdf_button = (Button)findViewById(R.id.vftdf_button);
        vftdf_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolunteerFragmentTwoFinishDialog.this.dismiss();
            }
        });
    }
    private void setTitle(String Name){
        if(gender=="남"){
            Name = Name + " 할아버지";
        }
        else{
            Name = Name + " 할머니";
        }

        vftdf_Name.setText(Name);
    }

    private void setContent(int year, int month, int day, int startHour, int startMinute, int finishHour,int finishMinute, String contents){

        vftdf_vDate.setText(year+"년 "+ month+"월 "+day+"일 ");
        vftdf_vTime.setText(startHour+":"+startMinute+" ~ "+finishHour+":"+finishMinute);
        vftdf_Contents.setText(contents);
    }

    /*
     * Layout
     */
    private void setLayout(){
        vftdf_Name = (TextView) findViewById(R.id.vftdf_name);
        vftdf_Contents = (TextView)findViewById(R.id.vftdf_content);
        vftdf_vDate= (TextView)findViewById(R.id.vftdf_vDate);
        vftdf_vTime = (TextView)findViewById(R.id.vftdf_vTime);
        vftdf_signature=(ImageView)findViewById(R.id.vftdf_signature);
    }
}
