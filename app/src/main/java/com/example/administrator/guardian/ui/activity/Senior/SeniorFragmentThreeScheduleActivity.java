package com.example.administrator.guardian.ui.activity.Senior;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.SeniorRecyclerItem;
import com.example.administrator.guardian.datamodel.SeniorScheduleRecyclerItem;
import com.example.administrator.guardian.datamodel.VolunteerConfirmRecyclerItem;
import com.example.administrator.guardian.ui.adapter.SeniorScheduleRecyclerViewAdapter;
import com.example.administrator.guardian.ui.adapter.VolunteerConfirmViewAdapter;

import java.util.ArrayList;
import java.util.List;


public class SeniorFragmentThreeScheduleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<SeniorScheduleRecyclerItem> items;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_fragment_three_schedule);


        recyclerView = (RecyclerView)findViewById(R.id.senior_schedule_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        SeniorScheduleRecyclerItem[] item = new SeniorScheduleRecyclerItem[5];
        item[0] = new SeniorScheduleRecyclerItem("0",2016,5,23,1,0,3,0, "박경준1", 20, "여","",1);
        item[1] = new SeniorScheduleRecyclerItem("1",2016,5,23,1,0,3,0, "박경준2", 21, "남","",2);
        item[2] = new SeniorScheduleRecyclerItem("2",2016,5,23,1,0,3,0, "박경준3", 22, "남","",3);
        item[3] = new SeniorScheduleRecyclerItem("3",2016,5,23,1,0,3,0, "박경준4", 23, "남","",4);
        item[4] = new SeniorScheduleRecyclerItem("4",2016,5,23,1,0,3,0, "박경준5", 24, "남","말동무",5);

        for (int i = 0; i < 5; i++) items.add(item[i]);

        recyclerView.setAdapter(new SeniorScheduleRecyclerViewAdapter(getApplicationContext(), items, R.layout.activity_senior_fragment_three_schedule));
    }
}
