package com.example.administrator.guardian.ui.activity.Manager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.guardian.R;

@SuppressLint("ValidFragment")
public class ManagerManagePulseInfoActivity extends Fragment {

    Context mContext;

    public ManagerManagePulseInfoActivity(Context context){
        mContext=context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_pulse_info, null);

        return view;
    }
}
