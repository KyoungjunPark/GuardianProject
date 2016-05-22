package com.example.administrator.guardian.ui.activity.Volunteer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.VolunteerConfirmRecyclerItem;
import com.example.administrator.guardian.datamodel.VolunteerRequestRecyclerItem;
import com.example.administrator.guardian.ui.adapter.VolunteerConfirmViewAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016-05-11.
 */
@SuppressLint("ValidFragment")
public class VolunteerFragmentTwoActivity extends Fragment{

    Context mContext;
    RecyclerView recyclerView;
    List<VolunteerConfirmRecyclerItem> items;

    public VolunteerFragmentTwoActivity(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_volunteer_fragment_two, null);

        recyclerView = (RecyclerView)view.findViewById(R.id.volunteer_confirm_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        VolunteerConfirmRecyclerItem[] item = new VolunteerConfirmRecyclerItem[5];
        item[0] = new VolunteerConfirmRecyclerItem("0",2016,5,23,1,0,3,0, "박경준1", 80, "여","서울특별시 영등포구 여의도동 국회의사당 1층 로비","",1);
        item[1] = new VolunteerConfirmRecyclerItem("1",2016,5,23,1,0,3,0, "박경준2", 79, "남","서울특별시 영등포구 여의도동 국회의사당 1층 로비","말동무",2);
        item[2] = new VolunteerConfirmRecyclerItem("2",2016,5,23,1,0,3,0, "박경준3", 78, "남","서울특별시 영등포구 여의도동 국회의사당 1층 로비","",3);
        item[3] = new VolunteerConfirmRecyclerItem("3",2016,5,23,1,0,3,0, "박경준4", 77, "남","서울특별시 영등포구 여의도동 국회의사당 1층 로비","",4);
        item[4] = new VolunteerConfirmRecyclerItem("4",2016,5,23,1,0,3,0, "박경준5", 76, "남","서울특별시 영등포구 여의도동 국회의사당 1층 로비","말동무",5);

        for (int i = 0; i < 5; i++) items.add(item[i]);

        recyclerView.setAdapter(new VolunteerConfirmViewAdapter(getContext(), items, R.layout.activity_volunteer_fragment_two));
        return view;
    }
}
