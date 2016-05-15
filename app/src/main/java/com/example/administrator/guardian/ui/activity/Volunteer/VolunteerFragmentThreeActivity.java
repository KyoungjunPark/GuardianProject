package com.example.administrator.guardian.ui.activity.Volunteer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.VolunteerTimeRecyclerItem;
import com.example.administrator.guardian.ui.adapter.VolunteerTimeRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("ValidFragment")
public class VolunteerFragmentThreeActivity extends Fragment {

    Context mContext;
    RecyclerView recyclerView;
    List<VolunteerTimeRecyclerItem> items;

    public VolunteerFragmentThreeActivity(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_volunteer_fragment_three, null);

        recyclerView = (RecyclerView)view.findViewById(R.id.volunteer_time_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
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

        recyclerView.setAdapter(new VolunteerTimeRecyclerViewAdapter(getContext(), items, R.layout.activity_volunteer_fragment_three));
        return view;
    }
}

