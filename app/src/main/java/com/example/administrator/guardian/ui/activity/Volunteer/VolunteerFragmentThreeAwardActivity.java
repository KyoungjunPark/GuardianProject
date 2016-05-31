package com.example.administrator.guardian.ui.activity.Volunteer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.guardian.R;

public class VolunteerFragmentThreeAwardActivity extends AppCompatActivity {

    String year;
    String month;
    String day;
    String hour;
    String minute;
    int req_hour;
    String name;

    // ------------------------------
    String id;
    //------------------------------
    TextView awardWinner;
    TextView awardContent;
    TextView awardDay;

    ImageView awardSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_fragment_three_award);

        Intent intent =getIntent();
        ////////////////////////////////////////
        id= intent.getExtras().getString("id");
        year = intent.getExtras().getString("year");
        month = intent.getExtras().getString("month");
        day = intent.getExtras().getString("day");
        hour = intent.getExtras().getString("hour");
        minute = intent.getExtras().getString("minute");
        req_hour = intent.getExtras().getInt("req_hour");
        name = intent.getExtras().getString("name");

        awardWinner=(TextView)findViewById(R.id.awardWinner);
        awardContent=(TextView)findViewById(R.id.awardContent);
        awardDay=(TextView)findViewById(R.id.awardDay);
        awardSignature=(ImageView)findViewById(R.id.awardSignature);
        awardWinner.setText(id);//id에 해당하는 이름
        awardContent.setText(" 위 사람은 "+month+"월 "+ day+"일 "+hour+"시부터 "+ req_hour+"시간동안 "+name+" 님에게 헌신적으로 봉사하였음을 확인함.");
        awardDay.setText(year+"년 "+month+"월 "+day+"일");
        awardSignature.setImageResource(R.drawable.signature);
    }
    void getImage(){

    }
}
