package com.example.administrator.guardian.ui.activity.Volunteer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.guardian.R;

public class VolunteerFragmentThreeAwardActivity extends AppCompatActivity {

    int year;
    int month;
    int day;
    int startHour;
    int startMinute;
    int finishHour;
    int finishMinute;
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
        id= intent.getStringExtra("id");
        ////////////////////////////////////////

        year = intent.getExtras().getInt("year");
        month = intent.getExtras().getInt("month");
        day = intent.getExtras().getInt("day");
        startHour = intent.getExtras().getInt("startHour");
        startMinute = intent.getExtras().getInt("startMinute");
        finishHour = intent.getExtras().getInt("finishHour");
        finishMinute = intent.getExtras().getInt("finishMinute");
        name = intent.getExtras().getString("name");

        awardWinner=(TextView)findViewById(R.id.awardWinner);
        awardContent=(TextView)findViewById(R.id.awardContent);
        awardDay=(TextView)findViewById(R.id.awardDay);
        awardSignature=(ImageView)findViewById(R.id.awardSignature);
        awardWinner.setText(id);//id에 해당하는 이름
        awardContent.setText(" 위 사람은 "+month+"월 "+ day+"일, "+startHour+"시 "+startMinute+"분부터 "+ finishHour+"시 "+finishMinute+"분까지 "+name+" 님에게 헌신적으로 봉사하였으므로 이 상장을 수여함");
        awardDay.setText(year+"년 "+month+"월 "+day+"일");
        awardSignature.setImageResource(R.drawable.signature);
    }
}
