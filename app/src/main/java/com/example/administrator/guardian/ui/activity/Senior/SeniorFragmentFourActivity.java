package com.example.administrator.guardian.ui.activity.Senior;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint;
import android.content.Context;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.example.administrator.guardian.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016-05-05.
 */
@SuppressLint("ValidFragment")
public class SeniorFragmentFourActivity extends Fragment {

    Context mContext;

    public SeniorFragmentFourActivity(Context context) {
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_senior_fragment_four, null);

        LineChart lineChart = (LineChart) view.findViewById(R.id.chart);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(76f, 0));
        entries.add(new Entry(78f, 1));
        entries.add(new Entry(80f, 2));
        entries.add(new Entry(73f, 3));
        entries.add(new Entry(77f, 4));
        entries.add(new Entry(80f, 5));

        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        ArrayList<String> labels = new ArrayList<String>();
        labels.add("5/30");
        labels.add("5/31");
        labels.add("6/1");
        labels.add("6/2");
        labels.add("6/3");
        labels.add("6/4");

        LineData data = new LineData(labels, dataset);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);
        //dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(1000);

        return view;
    }

}