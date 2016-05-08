package com.example.administrator.guardian.ui.activity.Volunteer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.guardian.R;

public class VolunteerMainActivity extends AppCompatActivity {

    Button vtimecheck;
    Button visitbutton;
    TextView vtime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vtime = (TextView)findViewById(R.id.vtime);
        vtime.setText("01 "+":"+" 30");

        vtimecheck = (Button)findViewById(R.id.vtimecheck);
        vtimecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volunteertimecheck = new Intent(getApplicationContext(), VolunteerTimeActivity.class);
                startActivity(volunteertimecheck);
            }
        });

        visitbutton = (Button)findViewById(R.id.visitbutton);
        visitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volunteermap = new Intent(getApplicationContext(), VolunteerMapActivity.class);
                startActivity(volunteermap);
            }
        });
    }

}
