package com.example.administrator.guardian.ui.activity.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.guardian.R;

@SuppressLint("ValidFragment")
public class ManagerManageInfoActivity extends Fragment {

    Context mContext;
    String senior_id;
    String senior_name;
    String senior_birthdate;
    int senior_age;
    String senior_gender;
    String senior_address;
    String senior_tel;
    String latitude;
    String longitude;

    TextView TextseniorName;
    TextView TextseniorAgeGender;
    TextView TextseniorAddress;
    TextView TextseniorNumber;

    private Button mmi_button;

    public ManagerManageInfoActivity(Context context, String senior_id, String senior_name, String senior_birthdate, String senior_gender, String senior_address, String senior_tel, String latitude, String longitude){
        mContext=context;
        this.senior_id = senior_id;
        this.senior_name = senior_name;
        this.senior_birthdate = senior_birthdate;
        this.senior_age = (20179999 - Integer.parseInt(senior_birthdate))/10000;
        this.senior_gender = senior_gender;
        this.senior_address = senior_address;
        this.senior_tel = senior_tel;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_info, null);

        mmi_button = (Button)view.findViewById(R.id.mmi_button);
        TextseniorName = (TextView)view.findViewById(R.id.TextseniorName);
        TextseniorAgeGender = (TextView)view.findViewById(R.id.TextseniorAgeGender);
        TextseniorAddress = (TextView)view.findViewById(R.id.TextseniorAddress);
        TextseniorNumber = (TextView)view.findViewById(R.id.TextseniorNumber);

        TextseniorName.setText(senior_name);
        TextseniorAgeGender.setText("("+senior_age+","+senior_gender+")");
        TextseniorNumber.setText(senior_tel);
        TextseniorAddress.setText(senior_address);

        mmi_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent MMI_Map = new Intent(getContext(), ManagerManageInfoMapActivity.class);
                MMI_Map.putExtra("latitude",latitude);
                MMI_Map.putExtra("longitude",longitude);
                MMI_Map.putExtra("senior_name",senior_name);
                MMI_Map.putExtra("senior_address",senior_address);
                startActivity(MMI_Map);
            }
        });

        return view;
    }
}
