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
import android.widget.TextView;

import com.example.administrator.guardian.R;

import org.w3c.dom.Text;

@SuppressLint("ValidFragment")
public class ManagerManageInfoActivity extends Fragment {

    Context mContext;
    String seniorName;
    int seniorAge;
    String seniorGender;
    String seniorAddress;
    String seniorNumber;

    TextView TextseniorNameAgeGender;
    TextView TextseniorAddress;
    TextView TextseniorNumber;

    public ManagerManageInfoActivity(Context context, String seniorName, int seniorAge, String seniorGender, String seniorAddress, String seniorNumber){
        mContext=context;
        this.seniorName=seniorName;
        this.seniorAge=seniorAge;
        this.seniorGender=seniorGender;
        this.seniorAddress=seniorAddress;
        this.seniorNumber=seniorNumber;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_info, null);

        TextseniorNameAgeGender = (TextView)view.findViewById(R.id.TextseniorNameAgeGender);
        TextseniorAddress = (TextView)view.findViewById(R.id.TextseniorAddress);
        TextseniorNumber = (TextView)view.findViewById(R.id.TextseniorNumber);
        
        TextseniorNameAgeGender.setText(seniorName+"("+seniorAge+","+seniorGender+")");
        TextseniorNumber.setText("전화번호 : "+seniorNumber);
        TextseniorAddress.setText("주소 : "+seniorAddress);

        return view;
    }
}
