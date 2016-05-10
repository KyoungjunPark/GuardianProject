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
import com.example.administrator.guardian.datamodel.VolunteerRequestRecyclerItem;
import com.example.administrator.guardian.ui.adapter.VolunteerRequestViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class VolunteerRequestActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    List<VolunteerRequestRecyclerItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_request);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView)findViewById(R.id.volunteer_request_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        VolunteerRequestRecyclerItem[] item = new VolunteerRequestRecyclerItem[5];
        item[0] = new VolunteerRequestRecyclerItem(0, "박경준", 33, "남", "흑석역");
        item[1] = new VolunteerRequestRecyclerItem(1, "박경준", 33, "남", "흑석역");
        item[2] = new VolunteerRequestRecyclerItem(2, "박경준", 33, "남", "흑석역");
        item[3] = new VolunteerRequestRecyclerItem(3, "박경준", 33, "남", "흑석역");
        item[4] = new VolunteerRequestRecyclerItem(4, "박경준", 33, "남", "흑석역");

        for (int i = 0; i < 5; i++) items.add(item[i]);

        recyclerView.setAdapter(new VolunteerRequestViewAdapter(getApplicationContext(), items, R.layout.content_volunteer_request));
    }

}
