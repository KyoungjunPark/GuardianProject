package com.example.administrator.guardian.ui.activity.Senior;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.guardian.R;


public class SeniorFragmentTwoDialog extends Dialog {

    private TextView sName;
    private TextView sAge;
    private TextView sAddress;
    private TextView sGender;

    private String name;
    private int age;
    private String gender;
    private String address;
    private String phoneNumber;
    private Button bt_left;
    private Button bt_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_senior_fragment_two_dialog);

        bt_left = (Button)findViewById(R.id.bt_left);
        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SeniorFragmentTwoDialog.this.dismiss();
            }
        });
        bt_right = (Button)findViewById(R.id.bt_right);
        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Dial = "tel:"+phoneNumber;
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse(Dial));
                try {
                    getContext().startActivity(intent);
                }catch(SecurityException e){
                    Log.d("SecurityException","try/catch gulrim");}
            }
        });

        setLayout();
        setTitle(name);
        setContent(age,gender,address);
    }

    public SeniorFragmentTwoDialog(Context context , String name , int age, String gender, String address, String phoneNumber) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address =  address;
        this.phoneNumber = phoneNumber;

    }

    private void setTitle(String Name){
        if(gender.compareTo(new String("남")) == 0){
            Name = Name + " 할아버지";
        }else{
            Name = Name + " 할머니";
        }

        sName.setText(Name);
    }

    private void setContent(int age, String gender, String address){
        sAge.setText("나이 : "+age);
        sGender.setText("성별 : "+ gender);
        sAddress.setText("주소 : "+address);
    }

    /*
     * Layout
     */
    private void setLayout(){
        sName = (TextView) findViewById(R.id.ss_name);
        sAge = (TextView)findViewById(R.id.ss_age);
        sGender = (TextView)findViewById(R.id.ss_gender);
        sAddress = (TextView)findViewById(R.id.ss_address);
    }
}