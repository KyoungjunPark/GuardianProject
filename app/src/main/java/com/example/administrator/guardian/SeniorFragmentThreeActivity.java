package com.example.administrator.guardian;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SeniorFragmentThreeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_fragment_three);

        a= (TextView)findViewById(R.id.anabada);
        a.setText("된다아아아아");

        /*recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        List<SeniorRecyclerItem> items = new ArrayList<>();
        SeniorRecyclerItem[] item = new SeniorRecyclerItem[5];
        item[0] = new SeniorRecyclerItem(0, "박경준1", 23, "남");
        item[1] = new SeniorRecyclerItem(1, "박경준2", 23, "남");
        item[2] = new SeniorRecyclerItem(2, "박경준3", 23, "남");
        item[3] = new SeniorRecyclerItem(3, "박경준4", 23, "남");
        item[4] = new SeniorRecyclerItem(4, "박경준5", 23, "남");

        for (int i = 0; i < 5; i++) items.add(item[i]);

        recyclerView.setAdapter(new SeniorRecyclerViewAdapter(getApplicationContext(), items, R.layout.activity_senior_fragment_three));*/
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //MultiDex.install(this);
    }
}
