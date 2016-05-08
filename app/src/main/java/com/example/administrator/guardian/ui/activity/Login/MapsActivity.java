package com.example.administrator.guardian.ui.activity.Login;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.administrator.guardian.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity {

    private GoogleApiClient mGoogleApiClient;
    final int REQUEST_PLACE_PICKER = 1;
    TextView inputAdd,latitudeAndlongitude;
    private GoogleMap mMap;
    MapView mapView;
    Button inputAddressButton;
    double dLatitude=37.5037660;
    double dLongitude=126.9564800;
    String placeName;
    String placeAddress;
    String placeID, dLa, dLo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        inputAdd=(TextView)findViewById(R.id.inputAddress);
        latitudeAndlongitude=(TextView)findViewById(R.id.latitudeAndlongitude);
        inputAddressButton=(Button)findViewById(R.id.InputAddressButton);

        mapView = (MapView)findViewById(R.id.more_map);
        mapView.onCreate(new Bundle());
        mapView.onResume();
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                LatLng seoul = new LatLng( 37.56, 126.97 );
                CameraPosition cameraPosition = new CameraPosition.Builder().target(seoul).zoom(11).build();
                mMap.moveCamera( CameraUpdateFactory.newLatLng(seoul) );
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        inputAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
                    Intent intent = intentBuilder.build(MapsActivity.this);
                    startActivityForResult(intent, REQUEST_PLACE_PICKER);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

       inputAddressButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("Address",placeAddress);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PLACE_PICKER) {
            if (resultCode == Activity.RESULT_OK) {
                final Place place = PlacePicker.getPlace(data, MapsActivity.this);
                 placeAddress = (String)place.getAddress();
                placeName = (String)place.getName();
                 placeID = place.getId();
                 dLatitude = place.getLatLng().latitude;
                 dLongitude =place.getLatLng().longitude;
                Log.d("longitude 2 latitude 2",dLongitude+" "+dLatitude);

                inputAdd.setText(placeName);

                dLa= String.format("%.7f",dLatitude);
                dLo= String.format("%.7f",dLongitude);
                latitudeAndlongitude.setText("("+dLa+", "+dLo+")");
                Log.d(dLa,dLo);

//                String attribution = PlacePicker.getAttributions(data);
//                if(attribution == null){
//                    attribution = "";
//                }

                mapView.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {

                        mMap = googleMap;
                        try {
                            MapsInitializer.initialize(getApplicationContext());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        mMap.clear();
                        LatLng Place = new LatLng(dLatitude, dLongitude);
                        CameraPosition cameraPosition = new CameraPosition.Builder().target(Place).zoom(17).build();
                        mMap.addMarker(new MarkerOptions().position(Place).title("yeah").snippet("hello"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(Place));
                        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                        //mMap.getUiSettings().setScrollGesturesEnabled(false);
                    }
                });
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
