package com.example.administrator.guardian.ui.activity.Manager;

import android.annotation.SuppressLint;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SuppressLint("ValidFragment")
public class ManagerManagePulseInfoActivity extends Fragment {

    Context mContext;
    String senior_id;
    String today_date;
    private ProgressBar managerpulseProgressBar;
    private int pulseAverage;

    int[] dateAvg;

    public ManagerManagePulseInfoActivity(Context context, String senior_id){

        this.mContext = context;
        this.senior_id = senior_id;
        this.today_date = new SimpleDateFormat("yyyyMMdd").format(new Date());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_pulse_info, null);

        LineChart lineChart = (LineChart) view.findViewById(R.id.manager_chart);
        managerpulseProgressBar = (ProgressBar)view.findViewById(R.id.managerpulseProgressBar);


        //심박수 값들
        ArrayList<Integer> pulse = new ArrayList<>();
        pulse.add(70);
        pulse.add(71);
        pulse.add(72);
        pulse.add(73);
        pulse.add(74);
        pulse.add(75);
        pulse.add(76);

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<String>();
        LineDataSet dataset = new LineDataSet(entries, "# of Calls");

        for (int i=0; i<7;i++){
            entries.add(new Entry(pulse.get(i), i));
            labels.add(Integer.parseInt(new SimpleDateFormat("MM/dd").format(new Date()))-6+i +"");
        }

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
