package com.example.administrator.guardian.ui.activity.Volunteer;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class VolunteerFragmentTwoAcceptDialog extends Dialog {

    private TextView vftda_Name;
    private TextView vftda_Age;
    private TextView vftda_Address;
    private TextView vftda_Contents;
    private TextView vftda_Gender;
    private TextView vftda_vDate;
    private TextView vftda_vTime;


    private int year;
    private int month;
    private int day;
    private int startHour;
    private int startMinute;
    private int finishHour;
    private int finishMinute;

    private String name;
    private int age;
    private String gender;
    private String address;
    private String contents;

    private Button vftda_left;
    private Button vftda_right;

    private GoogleMap mMap;
    MapView mapView;

    public VolunteerFragmentTwoAcceptDialog(Context context, int year, int month, int day, int startHour, int startMinute, int finishHour, int finishMinute, String name, int age, String gender, String address, String contents) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.year=year;
        this.month=month;
        this.day=day;
        this.startHour=startHour;
        this.startMinute=startMinute;
        this.finishHour=finishHour;
        this.finishMinute=finishMinute;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address =  address;
        this.contents = contents;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.35f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_volunteer_fragment_two_accept_dialog);

        setLayout();
        setTitle(name);
        setContent(year,month,day,startHour,startMinute,finishHour,finishMinute,age,gender,address,contents);


        mapView = (MapView)findViewById(R.id.vftda_map);
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
                optFirst.title(name);// 제목 미리보기
                optFirst.snippet(address+"");
                mMap.addMarker(optFirst);
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        vftda_left = (Button)findViewById(R.id.vftda_left);
        vftda_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolunteerFragmentTwoAcceptDialog.this.dismiss();
            }
        });
        vftda_right = (Button)findViewById(R.id.vftda_right);
        vftda_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                VolunteerFragmentTwoAcceptDialog.this.dismiss();
            }
        });
    }

    private void setTitle(String Name){
        if(gender=="남"){
            Name = Name + " 할아버지";
        }
        else{
            Name = Name + " 할머니";
        }

        vftda_Name.setText(Name);
    }

    private void setContent(int year, int month, int day, int startHour, int startMinute, int finishHour,int finishMinute, int age, String gender, String address, String contents){

        vftda_vDate.setText(year+"년 "+ month+"월 "+day+"일 ");
        vftda_vTime.setText(startHour+":"+startMinute+" ~ "+finishHour+":"+finishMinute);
        vftda_Age.setText("나이 : "+age);
        vftda_Gender.setText("성별 : "+ gender);
        vftda_Address.setText("주소 : "+address);
        vftda_Contents.setText("내용 : "+contents);
    }

    /*
     * Layout
     */
    private void setLayout(){
        vftda_Name = (TextView) findViewById(R.id.vftda_name);
        vftda_Age = (TextView)findViewById(R.id.vftda_age);
        vftda_Gender = (TextView)findViewById(R.id.vftda_gender);
        vftda_Address = (TextView)findViewById(R.id.vftda_address);
        vftda_Contents = (TextView)findViewById(R.id.vftda_content);
        vftda_vDate= (TextView)findViewById(R.id.vftda_vDate);
        vftda_vTime = (TextView)findViewById(R.id.vftda_vTime);
    }
}
