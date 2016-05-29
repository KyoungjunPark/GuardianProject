package com.example.administrator.guardian.ui.activity.Senior;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.administrator.guardian.R;

@SuppressLint("ValidFragment")
public class SeniorFragmentOneActivity extends Fragment {

    private static final String TAG = "SeniorMainActivity";
    private static final int REQUEST_ENABLE_BT = 6666;
    private static final int REQUEST_CONNECT_DEVICE = 6667;

    private View view;
    Context mContext;
    int high_zone_2, low_zone_1;
    private Button measure;



    public SeniorFragmentOneActivity(Context context, int high_zone_2, int low_zone_1) {
        this.mContext = context;
        this.high_zone_2 = high_zone_2;
        this.low_zone_1 = low_zone_1;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_senior_fragment_one, null);

        measure = (Button)view.findViewById(R.id.measure);
        measure = (Button)view.findViewById(R.id.measure);
        measure.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getContext(), SeniorFragmentOneMeasureActivity.class);
                intent.putExtra("high_zone_2", high_zone_2);
                intent.putExtra("low_zone_1", low_zone_1);
                startActivity(intent);
            }
        });

        return view;
    }

}