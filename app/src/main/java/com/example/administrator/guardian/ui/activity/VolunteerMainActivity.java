package com.example.administrator.guardian.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.administrator.guardian.R;

public class VolunteerMainActivity extends AppCompatActivity {

    Button vtimecheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vtimecheck = (Button)findViewById(R.id.vtimecheck);
        vtimecheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent volunteertimecheck = new Intent(getApplicationContext(), VolunteerTimeActivity.class);
                startActivity(volunteertimecheck);
            }
        });
    }

}
