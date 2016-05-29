package com.example.administrator.guardian.ui.activity.Senior;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.guardian.R;

public class SeniorFragmentThreeScheduleFinishDialog extends Dialog {

    private TextView sftsf_Name;
    private TextView sftsf_Contents;
    private TextView sftsf_vDate;
    private TextView sftsf_vTime;

    private int year;
    private int month;
    private int day;
    private int startHour;
    private int startMinute;
    private int finishHour;
    private int finishMinute;

    private String name;
    private String contents;

    private Button sftsf_button;

    public SeniorFragmentThreeScheduleFinishDialog(Context context, int year, int month, int day, int startHour, int startMinute, int finishHour, int finishMinute, String name, String contents) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.year=year;
        this.month=month;
        this.day=day;
        this.startHour=startHour;
        this.startMinute=startMinute;
        this.finishHour=finishHour;
        this.finishMinute=finishMinute;
        this.name = name;
        this.contents=contents;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_senior_fragment_three_schedule_finish_dialog);

        setLayout();
        Log.d("name",name+"");
        setTitle(name);
        setContent(year,month,day,startHour,startMinute,finishHour,finishMinute,contents);

        sftsf_button = (Button)findViewById(R.id.sftsf_button);
        sftsf_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SeniorFragmentThreeScheduleFinishDialog.this.dismiss();
            }
        });
    }
    private void setTitle(String Name){

        sftsf_Name.setText(Name);
    }

    private void setContent(int year, int month, int day, int startHour, int startMinute, int finishHour,int finishMinute, String contents){

        sftsf_vDate.setText(year+"년 "+ month+"월 "+day+"일 ");
        sftsf_vTime.setText(startHour+":"+startMinute+" ~ "+finishHour+":"+finishMinute);
        sftsf_Contents.setText("내용 : "+contents);
    }

    /*
     * Layout
     */
    private void setLayout(){
        sftsf_Name = (TextView) findViewById(R.id.sftsf_name);
        sftsf_Contents=(TextView)findViewById(R.id.sftsf_content);
        sftsf_vDate= (TextView)findViewById(R.id.sftsf_vDate);
        sftsf_vTime = (TextView)findViewById(R.id.sftsf_vTime);
    }

}
