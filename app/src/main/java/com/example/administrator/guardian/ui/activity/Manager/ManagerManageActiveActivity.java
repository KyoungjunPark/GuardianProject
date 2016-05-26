package com.example.administrator.guardian.ui.activity.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ManagerManageActiveActivity extends Fragment {

    Context mContext;
    String senior_id;
    private int activeDoor=0;
    private int activeRepri=4;
    private int activeToilet=5;
    HorizontalBarChart h_BarChart;

    TextView mma_door;
    TextView mma_doortime;
    TextView mma_toilettime;
    TextView mma_repritime;
    BarData data;

    public ManagerManageActiveActivity(Context context, String senior_id){

        this.mContext = context;
        this.senior_id = senior_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_active, null);

        h_BarChart = (HorizontalBarChart) view.findViewById(R.id.M_senior_active_chart);
        mma_toilettime = (TextView)view.findViewById(R.id.mma_toilettime);
        mma_repritime=(TextView)view.findViewById(R.id.mma_repritime);

        mma_door = (TextView)view.findViewById(R.id.mma_door);
        mma_door.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addActiveDoor();
                Log.d("door",activeDoor+"");
                h_BarChart.setData(data);
            }
        });


        //심박수 값들
        ArrayList<Integer> activeNumber = new ArrayList<>();
        activeNumber.add(activeToilet);
        activeNumber.add(activeRepri);
        activeNumber.add(activeDoor);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(activeNumber.get(0), 0));
        entries.add(new BarEntry(activeNumber.get(1), 1));
        entries.add(new BarEntry(activeNumber.get(2), 2));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("화장실");
        labels.add("냉장고");
        labels.add("출입문");

        data = new BarData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);

        h_BarChart.setData(data);
        h_BarChart.animateX(1000);

        return view;
    }


    public void addActiveDoor(){
        activeDoor++;
    }
    public void addActiveRepri(){
        activeRepri++;
    }
    public void addActiveToilet(){
        activeToilet++;
    }
}