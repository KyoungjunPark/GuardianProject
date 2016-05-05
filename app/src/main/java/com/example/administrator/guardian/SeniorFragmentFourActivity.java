package com.example.administrator.guardian;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint;
import android.content.Context;
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


        return view;
    }
}