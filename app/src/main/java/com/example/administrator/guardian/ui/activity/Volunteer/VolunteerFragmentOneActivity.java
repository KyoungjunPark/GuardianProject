package com.example.administrator.guardian.ui.activity.Volunteer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.VolunteerRequestRecyclerItem;
import com.example.administrator.guardian.ui.adapter.VolunteerRequestViewAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class VolunteerFragmentOneActivity extends Fragment {

        Context mContext;
        RecyclerView recyclerView;
        List<VolunteerRequestRecyclerItem> items;

        public VolunteerFragmentOneActivity(Context context){
            mContext=context;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_volunteer_fragment_one, null);

            recyclerView = (RecyclerView)view.findViewById(R.id.volunteer_request_recyclerView);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);

            items = new ArrayList<>();
            VolunteerRequestRecyclerItem[] item = new VolunteerRequestRecyclerItem[5];
            item[0] = new VolunteerRequestRecyclerItem(0, "박경준1", 33, "남");
            item[1] = new VolunteerRequestRecyclerItem(1, "박경준2", 33, "남");
            item[2] = new VolunteerRequestRecyclerItem(2, "박경준3", 33, "남");
            item[3] = new VolunteerRequestRecyclerItem(3, "박경준4", 33, "남");
            item[4] = new VolunteerRequestRecyclerItem(4, "박경준5", 33, "남");

            for (int i = 0; i < 5; i++) items.add(item[i]);

            recyclerView.setAdapter(new VolunteerRequestViewAdapter(getContext(), items, R.layout.activity_volunteer_fragment_one));
            return view;
        }
}

