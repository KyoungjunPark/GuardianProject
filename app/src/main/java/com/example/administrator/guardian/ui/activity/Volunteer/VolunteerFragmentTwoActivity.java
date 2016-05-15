package com.example.administrator.guardian.ui.activity.Volunteer;

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
public class VolunteerFragmentTwoActivity extends Fragment{

    Context mContext;

    public VolunteerFragmentTwoActivity(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_volunteer_fragment_two, null);


        return view;
    }
}
