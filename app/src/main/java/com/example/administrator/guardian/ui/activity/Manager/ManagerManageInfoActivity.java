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
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;

@SuppressLint("ValidFragment")
public class ManagerManageInfoActivity extends Fragment {

    Context mContext;
    String senior_id;
    String senior_name;
    String senior_birthdate;
    String senior_gender;
    String senior_address;
    String senior_tel;

    TextView TextseniorNameAgeGender;
    TextView TextseniorAddress;
    TextView TextseniorNumber;

    private GoogleMap mMap;
    MapView mapView;

    public ManagerManageInfoActivity(Context context, String senior_id){
        mContext=context;
        this.senior_id = senior_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_info, null);

        TextseniorNameAgeGender = (TextView)view.findViewById(R.id.TextseniorNameAgeGender);
        TextseniorAddress = (TextView)view.findViewById(R.id.TextseniorAddress);
        TextseniorNumber = (TextView)view.findViewById(R.id.TextseniorNumber);

        TextseniorNameAgeGender.setText(senior_id);

        //mapView = (MapView)view.findViewById(R.id.vfod_map);
        //mapView.onCreate(new Bundle());
        //mapView.onResume();
        /*
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                LatLng senior_home = new LatLng( 37.56, 126.97);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(senior_home).zoom(16).build();
                mMap.moveCamera( CameraUpdateFactory.newLatLng(senior_home) );
                MarkerOptions optFirst = new MarkerOptions();
                optFirst.position(senior_home);// 위도 • 경도
                optFirst.title(seniorName);// 제목 미리보기
                optFirst.snippet(seniorAddress+"");
                mMap.addMarker(optFirst);
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
        */

        return view;
    }
}
