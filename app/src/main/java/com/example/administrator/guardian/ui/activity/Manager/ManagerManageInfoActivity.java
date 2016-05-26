package com.example.administrator.guardian.ui.activity.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

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

    TextView TextseniorNameAgeGender;
    TextView TextseniorAddress;
    TextView TextseniorNumber;

    private GoogleMap mMap;
    MapView mapView;

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

        TextseniorNameAgeGender = (TextView)view.findViewById(R.id.TextseniorNameAgeGender);
        TextseniorAddress = (TextView)view.findViewById(R.id.TextseniorAddress);
        TextseniorNumber = (TextView)view.findViewById(R.id.TextseniorNumber);

        TextseniorNameAgeGender.setText(senior_name +" ("+senior_age+","+senior_gender+") ");
        TextseniorNumber.setText("전화번호 : "+senior_tel);
        TextseniorAddress.setText(senior_address);
        mapView = (MapView)view.findViewById(R.id.seniorAddressMap);
        mapView.onCreate(new Bundle());
        mapView.onResume();

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                LatLng senior_home = new LatLng( Double.parseDouble(latitude), Double.parseDouble(longitude));
                CameraPosition cameraPosition = new CameraPosition.Builder().target(senior_home).zoom(16).build();
                mMap.moveCamera( CameraUpdateFactory.newLatLng(senior_home) );
                MarkerOptions optFirst = new MarkerOptions();
                optFirst.position(senior_home);// 위도 • 경도
                optFirst.title(senior_name);// 제목 미리보기
                optFirst.snippet(senior_address+"");
                mMap.addMarker(optFirst);
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });


        return view;
    }
}
