package com.example.administrator.guardian.ui.activity.Volunteer;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.guardian.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class VolunteerFragmentOneDialogActivity extends Dialog implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{

    private TextView vfod_Name;
    private TextView vfod_Age;
    private TextView vfod_Address;
    private TextView vfod_Gender;


    private Button dateTextView;
    private Button timeTextView;

    private int v_year;
    private int v_month;
    private int v_day_of_month;
    private int v_hour_of_day;
    private int v_minute;

    private String name;
    private int age;
    private String gender;
    private String address;

    private Button vfod_left;
    private Button vfod_right;

    private GoogleMap mMap;
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.35f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_volunteer_fragment_one_dialog);

        dateTextView = (Button) findViewById(R.id.vfod_DateButton);
        timeTextView = (Button) findViewById(R.id.vfod_TimeButton);

        //Set the current date & time
        v_year = Calendar.getInstance().get(Calendar.YEAR);
        v_month = Calendar.getInstance().get(Calendar.MONTH);
        v_day_of_month = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        v_hour_of_day = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        v_minute = Calendar.getInstance().get(Calendar.MINUTE);

        dateTextView.setText(v_year+"년 " + v_month +"월 " + v_day_of_month + "일 ");
        setTime();
        //Set the Date & Time ClickListener
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"a",Toast.LENGTH_SHORT).show();
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        VolunteerFragmentOneDialogActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                //dpd.show(, "Datepickerdialog");
            }
        });

        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog dpd = TimePickerDialog.newInstance(
                        VolunteerFragmentOneDialogActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                //dpd.show(getOwnerActivity().getFragmentManager(), "Timepickerdialog");
            }
        });

        vfod_left = (Button)findViewById(R.id.vfod_left);
        vfod_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VolunteerFragmentOneDialogActivity.this.dismiss();
            }
        });
        vfod_right = (Button)findViewById(R.id.vfod_right);
        vfod_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //volunteer request part
                //volunteer request part
                //volunteer request part
                //volunteer request part
                //volunteer request part
                //volunteer request part
                //volunteer request part
                //volunteer request part

            }
        });

        setLayout();
        setTitle(name);
        setContent(age,gender,address);


        mapView = (MapView)findViewById(R.id.vfod_map);
        mapView.onCreate(new Bundle());
        mapView.onResume();
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                LatLng senior_home = new LatLng( 37.56, 126.97);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(senior_home).zoom(16).build();
                mMap.moveCamera( CameraUpdateFactory.newLatLng(senior_home) );
                MarkerOptions optFirst = new MarkerOptions();
                optFirst.position(senior_home);// 위도 • 경도
                optFirst.title("박경준 할아버지");// 제목 미리보기
                optFirst.snippet(address+"");
                mMap.addMarker(optFirst);
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
    }

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        this.v_year = year;
        this.v_month = monthOfYear;
        this.v_day_of_month = dayOfMonth;
        dateTextView.setText(v_year+"년 " + v_month +"월 " + v_day_of_month + "일 ");
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute, int second) {
        this.v_hour_of_day = hourOfDay;
        this.v_minute = minute;
        setTime();
    }
    private void setTime()
    {
        if(v_hour_of_day > 12) {
            timeTextView.setText("오후" + (v_hour_of_day-12) + "시 " + v_minute + "분 ");
        } else {
            timeTextView.setText("오전" + v_hour_of_day + "시 " + v_minute + "분 ");
        }
    }


    public VolunteerFragmentOneDialogActivity(Context context , String name , int age, String gender, String address) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address =  address;

    }

    private void setTitle(String Name){
        if(gender=="남"){
            Name = Name + " 할아버지";
        }
        else{
            Name = Name + " 할머니";
        }

        vfod_Name.setText(Name);
    }

    private void setContent(int age, String gender, String address){
        vfod_Age.setText("나이 : "+age);
        vfod_Gender.setText("성별 : "+ gender);
        vfod_Address.setText("주소 : "+address);
    }

    /*
     * Layout
     */
    private void setLayout(){
        vfod_Name = (TextView) findViewById(R.id.vfod_name);
        vfod_Age = (TextView)findViewById(R.id.vfod_age);
        vfod_Gender = (TextView)findViewById(R.id.vfod_gender);
        vfod_Address = (TextView)findViewById(R.id.vfod_address);
    }
}
