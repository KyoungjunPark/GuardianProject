package com.example.administrator.guardian.ui.activity.Volunteer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.guardian.R;

public class VolunteerFragmentThreeAwardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_fragment_three_award);

        Intent intent =getIntent();
        int year = intent.getExtras().getInt("year");
        int month = intent.getExtras().getInt("month");
        int day = intent.getExtras().getInt("day");
        int startHour = intent.getExtras().getInt("startHour");
        int startMinute = intent.getExtras().getInt("startMinute");
        int finishHour = intent.getExtras().getInt("finishHour");
        int finishMinute = intent.getExtras().getInt("finishMinute");
        String name = intent.getExtras().getString("name");

    }
}
