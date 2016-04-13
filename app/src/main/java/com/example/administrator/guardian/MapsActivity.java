package com.example.administrator.guardian;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity {

    TextView place;
    private GoogleMap mMap;
    MapView mapView;
    String a,b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        final int REQUEST_PLACE_PICKER = 1;

        place=(TextView)findViewById(R.id.place);
        mapView = (MapView)findViewById(R.id.more_map);
        mapView.onCreate(new Bundle());
        mapView.onResume();

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;
                try {
                    MapsInitializer.initialize(getApplicationContext());
                } catch (Exception e) {
                    e.printStackTrace();
                }

                // Add a marker in Sydney and move the camera
                mMap.clear();
                LatLng gangnam = new LatLng(37.5172360, 127.0473250);
                CameraPosition cameraPosition = new CameraPosition.Builder().target(gangnam).zoom(16).build();
                mMap.addMarker(new MarkerOptions().position(gangnam).title("yeah").snippet("hello"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(gangnam));
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                //mMap.getUiSettings().setScrollGesturesEnabled(false);
            }
        });
    }
}
