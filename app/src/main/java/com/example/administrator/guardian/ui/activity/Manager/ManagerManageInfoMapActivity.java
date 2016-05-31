package com.example.administrator.guardian.ui.activity.Manager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.guardian.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ManagerManageInfoMapActivity extends AppCompatActivity {


    private GoogleMap mMap;
    MapView mapView;

    String latitude;
    String longitude;
    String senior_name;
    String senior_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_manage_info_map);

        Intent intent=getIntent();
        latitude = intent.getStringExtra("latitude");
        longitude = intent.getStringExtra("longitude");
        senior_name = intent.getStringExtra("senior_name");
        senior_address = intent.getStringExtra("senior_address");

        mapView = (MapView)findViewById(R.id.seniorAddressMap);
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
    }
}
