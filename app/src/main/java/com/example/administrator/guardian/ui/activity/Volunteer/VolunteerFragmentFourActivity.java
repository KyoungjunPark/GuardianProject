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



        return view;
    }
}

