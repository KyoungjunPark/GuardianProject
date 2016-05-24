package com.example.administrator.guardian.ui.activity.Manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.administrator.guardian.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class ManagerManagePulseInfoActivity extends Fragment {

    Context mContext;
    private ProgressBar managerpulseProgressBar;
    private int pulseAverage;

    public ManagerManagePulseInfoActivity(Context context){
        mContext=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_pulse_info, null);

        LineChart lineChart = (LineChart) view.findViewById(R.id.manager_chart);
        managerpulseProgressBar = (ProgressBar)view.findViewById(R.id.managerpulseProgressBar);


        //심박수 값들
        ArrayList<Integer> pulse = new ArrayList<>();
        pulse.add(76);
        pulse.add(77);
        pulse.add(78);
        pulse.add(79);
        pulse.add(80);
        pulse.add(81);

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(pulse.get(0), 0));
        entries.add(new Entry(pulse.get(1), 1));
        entries.add(new Entry(pulse.get(2), 2));
        entries.add(new Entry(pulse.get(3), 3));
        entries.add(new Entry(pulse.get(4), 4));
        entries.add(new Entry(pulse.get(5), 5));

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
        dataset.setDrawFilled(true);

        lineChart.setData(data);
        lineChart.animateY(1000);

        for(int i=0;i<pulse.size();i++){
            pulseAverage+=pulse.get(i);
        }
        pulseAverage=pulseAverage/pulse.size();
        //심박수 값이 들어가야함
        managerpulseProgressBar.setProgress(pulseAverage);
        //progress bar가 40부터 시작해서 40을 빼줘야함
        managerpulseProgressBar.incrementProgressBy(-40);

        return view;
    }
}
