package com.example.administrator.guardian.ui.activity.Senior;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
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

    private String startInfo;
    private int reqHour;
    private String name;
    private String details;

    private Button sftsf_button;

    public SeniorFragmentThreeScheduleFinishDialog(Context context, String startInfo, int reqHour, String name, String details) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.startInfo = startInfo;
        this.reqHour = reqHour;
        this.name = name;
        this.details=details;
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
        setContent(startInfo, reqHour, details);

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

    private void setContent(String startInfo, int reqHour, String details){

        sftsf_vDate.setText(startInfo);
        sftsf_vTime.setText(reqHour+"");
        sftsf_Contents.setText("내용 : "+details);
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
