package com.example.administrator.guardian.ui.activity.Volunteer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.administrator.guardian.R;

import java.util.ArrayList;


@SuppressLint("ValidFragment")
public class VolunteerFragmentFourActivity extends Fragment {

    Context mContext;
    public VolunteerFragmentFourActivity(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_volunteer_fragment_four, container, false);


        return view;
    }
}

