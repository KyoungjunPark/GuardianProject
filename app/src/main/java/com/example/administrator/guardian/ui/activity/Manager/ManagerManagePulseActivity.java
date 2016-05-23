package com.example.administrator.guardian.ui.activity.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.administrator.guardian.R;


@SuppressLint("ValidFragment")
public class ManagerManagePulseActivity extends Fragment {

    Context mContext;
    EditText editMaxSeriousPulse;
    EditText editSeriousPulse;
    EditText editMinSeriousPulse;

    int max;
    int middle;
    int min;


    public ManagerManagePulseActivity(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_pulse, null);

        editMaxSeriousPulse=(EditText)view.findViewById(R.id.editMaxSeriousPulse);
        editSeriousPulse=(EditText)view.findViewById(R.id.editSeriousPulse);
        editMinSeriousPulse=(EditText)view.findViewById(R.id.editMinSeriousPulse);

        max=Integer.parseInt(editMaxSeriousPulse.getText().toString());
        middle=Integer.parseInt(editSeriousPulse.getText().toString());
        min=Integer.parseInt(editMinSeriousPulse.getText().toString());

        
        return view;
    }
}
