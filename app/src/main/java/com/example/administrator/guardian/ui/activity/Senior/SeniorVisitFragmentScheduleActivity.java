package com.example.administrator.guardian.ui.activity.Senior;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.guardian.R;


/**
 * Created by Administrator on 2016-05-11.
 */
@SuppressLint("ValidFragment")
public class SeniorVisitFragmentScheduleActivity extends Fragment{

    Context mContext;

    public SeniorVisitFragmentScheduleActivity(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_senior_visit_fragment_schedule, null);


        return view;
    }
}
