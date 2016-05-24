package com.example.administrator.guardian.ui.activity.Volunteer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.administrator.guardian.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.w3c.dom.Text;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class VolunteerFragmentFourActivity extends Fragment {

    Context mContext;
    TextView textVolunteerTime;
    public VolunteerFragmentFourActivity(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_volunteer_fragment_four, container, false);

        textVolunteerTime = (TextView)view.findViewById(R.id.textVolunteerTime);
        textVolunteerTime.setText("1"+" : "+"30");

        BarChart BarChart = (BarChart) view.findViewById(R.id.volunteer_time_chart);

        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(30f, 0));
        entries.add(new BarEntry(8f, 1));
        entries.add(new BarEntry(22f, 2));
        entries.add(new BarEntry(12f, 3));
        entries.add(new BarEntry(18f, 4));
        entries.add(new BarEntry(19f, 5));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("5/30");
        labels.add("5/31");
        labels.add("6/1");
        labels.add("6/2");
        labels.add("6/3");
        labels.add("6/4");

        BarData data = new BarData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //


        BarChart.setData(data);
        BarChart.animateY(1000);


        return view;
    }
}

