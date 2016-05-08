package com.example.administrator.guardian.ui.activity.Volunteer;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.administrator.guardian.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class VolunteerMapActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.volunteermap);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // volunteer's address
        LatLng home = new LatLng(37.5037660, 126.9564800);
        LatLng home2 = new LatLng(37.5038660, 126.9555800);

        CameraPosition cameraPosition = new CameraPosition.Builder().target(home).zoom(15).build();
        mMap.moveCamera(CameraUpdateFactory.newLatLng(home));
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        MarkerOptions optFirst = new MarkerOptions();
        optFirst.position(home);// 위도 • 경도
        optFirst.title("박경준 할아버지");// 제목 미리보기
        optFirst.snippet("방문하기");
        mMap.addMarker(optFirst);

        MarkerOptions optSecond = new MarkerOptions();
        optSecond.position(home2);// 위도 • 경도
        optSecond.title("박경준 할머니");// 제목 미리보기
        optSecond.snippet("방문하기");
        mMap.addMarker(optSecond);

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Toast.makeText(getApplicationContext(),marker.getTitle()+"",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
/*
    // 첫번째 마커 설정.
    MarkerOptions optFirst = new MarkerOptions();
optFirst.position(position);// 위도 • 경도
        optFirst.title(title);// 제목 미리보기
        optFirst.snippet("Snippet");
        optFirst.icon(BitmapDescriptorFactory
        .fromResource(R.drawable.ic_launcher));
        mGoogleMap.addMarker(optFirst).showInfoWindow();

        // 두번째 마커 설정.
        MarkerOptions optSecond = new MarkerOptions();
        optSecond.position(new LatLng(37.521280, 127.041268));// 위도 • 경도
        optSecond.title(title); // 제목 미리보기
        optSecond.snippet("Snippet2");
        mGoogleMap.addMarker(optSecond).showInfoWindow();

        // 마커 클릭 리스너
        mGoogleMap.setOnMarkerClickListener(new OnMarkerClickListener() {

public boolean onMarkerClick(Marker marker) {
        String text = "[마커 클릭 이벤트] latitude ="
        + marker.getPosition().latitude + ", longitude ="
        + marker.getPosition().longitude;
        Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG)
        .show();
        return false;
        }
        });
*/