package com.example.administrator.guardian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class SeniorFragmentThreeActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_fragment_three);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
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

        recyclerView.setAdapter(new SeniorRecyclerViewAdapter(getApplicationContext(), items, R.layout.activity_senior_fragment_three));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_join, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
