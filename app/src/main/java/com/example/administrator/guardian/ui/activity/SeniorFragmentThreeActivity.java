package com.example.administrator.guardian.ui.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.SeniorRecyclerItem;
import com.example.administrator.guardian.ui.adapter.SeniorRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class SeniorFragmentThreeActivity extends Fragment {

    RecyclerView recyclerView;
    TextView a;
    private View view;
    List<SeniorRecyclerItem> items;

    Context mContext;
    public SeniorFragmentThreeActivity(Context context) {
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_senior_fragment_three, null);


        recyclerView = (RecyclerView)view.findViewById(R.id.senior_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        items = new ArrayList<>();
        SeniorRecyclerItem[] item = new SeniorRecyclerItem[5];
        item[0] = new SeniorRecyclerItem(0, "박경준1", 23, "남");
        item[1] = new SeniorRecyclerItem(1, "박경준2", 23, "남");
        item[2] = new SeniorRecyclerItem(2, "박경준3", 23, "남");
        item[3] = new SeniorRecyclerItem(3, "박경준4", 23, "남");
        item[4] = new SeniorRecyclerItem(4, "박경준5", 23, "남");

        for (int i = 0; i < 5; i++) items.add(item[i]);

        recyclerView.setAdapter(new SeniorRecyclerViewAdapter(getContext(), items, R.layout.activity_senior_fragment_three));

        return view;
    }

}
