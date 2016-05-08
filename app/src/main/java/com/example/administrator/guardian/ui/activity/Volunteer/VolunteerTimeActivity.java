package com.example.administrator.guardian.ui.activity.Volunteer;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.SeniorRecyclerItem;
import com.example.administrator.guardian.datamodel.VolunteerTimeRecyclerItem;
import com.example.administrator.guardian.ui.adapter.SeniorRecyclerViewAdapter;
import com.example.administrator.guardian.ui.adapter.VolunteerTimeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class VolunteerTimeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<VolunteerTimeRecyclerItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_time);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.volunteer_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        VolunteerTimeRecyclerItem[] item = new VolunteerTimeRecyclerItem[5];
        item[0] = new VolunteerTimeRecyclerItem(0, 2016,4,13, 1,0,3,0, "박경준1");
        item[1] = new VolunteerTimeRecyclerItem(1, 2016,4,14, 2,0,4,0,  "박경준2");
        item[2] = new VolunteerTimeRecyclerItem(2, 2016,4,15, 3,0,5,0, "박경준3");
        item[3] = new VolunteerTimeRecyclerItem(3, 2016,4,16, 4,0,6,0, "박경준4");
        item[4] = new VolunteerTimeRecyclerItem(4, 2016,4,17, 5,0,7,0, "박경준5");

        for (int i = 0; i < 5; i++) items.add(item[i]);

        recyclerView.setAdapter(new VolunteerTimeRecyclerViewAdapter(getApplicationContext(), items, R.layout.content_volunteer_time));

    }

}
