package com.example.administrator.guardian.ui.activity.Senior;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.guardian.R;

@SuppressLint("ValidFragment")
public class SeniorVisitFragmentCheckActivity extends Fragment {

        Context mContext;

        public SeniorVisitFragmentCheckActivity(Context context){
            mContext=context;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.activity_senior_visit_fragment_schedule, null);


            return view;
        }
}

