package com.example.administrator.guardian.ui.activity.Volunteer;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.utils.ConnectServer;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class VolunteerFragmentTwoAcceptDialog extends Dialog {
    private static final String TAG = "VolunteerAcceptDialog";
    int responseStatus = 0;
    private TextView vftda_Name;
    private TextView vftda_Age;
    private TextView vftda_Address;
    private TextView vftda_Contents;
    private TextView vftda_Gender;
    private TextView vftda_vDate;
    private TextView vftda_vTime;
    private String senior_id;
    private String startInfo;
    private int req_hour;
    private String senior_name;
    private int senior_age;
    private String senior_gender;
    private String senior_address;
    private String latitude;
    private String longitude;
    private String details;


    private Button vftda_left;
    private Button vftda_right;

    private GoogleMap mMap;
    MapView mapView;

    public VolunteerFragmentTwoAcceptDialog(Context context, String senior_id, String startInfo, int req_hour, String senior_name, int senior_age, String senior_gender, String address, String latitude, String longitude, String details) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.senior_id = senior_id;
        this.startInfo = startInfo;
        this.req_hour = req_hour;
        this.senior_name = senior_name;
        this.senior_age = senior_age;
        this.senior_gender = senior_gender;
        this.senior_address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.details = details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.1f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_volunteer_fragment_two_accept_dialog);

        setLayout();
        setTitle(senior_name);
        setContent();


        mapView = (MapView)findViewById(R.id.vftda_map);
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

        vftda_left = (Button)findViewById(R.id.vftda_left);
        vftda_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptReq();
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
        if(senior_gender.compareTo("남") == 0){
            Name = Name + " 할아버지";
        }
        else{
            Name = Name + " 할머니";
        }

        vftda_Name.setText(Name);
    }

    private void setContent(){

        vftda_Age.setText("나이 : "+senior_age);
        vftda_Gender.setText("성별 : "+ senior_gender);
        vftda_Address.setText("주소 : "+senior_address);
        vftda_Contents.setText(details);
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
    }


    public void acceptReq(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
            }
            @Override
            protected void onPostExecute(Boolean params){
                if(responseStatus == 1){
                    Toast.makeText(VolunteerFragmentTwoAcceptDialog.this.getContext(), "수락 완료", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(VolunteerFragmentTwoAcceptDialog.this.getContext(), "수락 실패", Toast.LENGTH_SHORT).show();
                }
                VolunteerFragmentTwoAcceptDialog.this.dismiss();
            }
            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("Accept_Request"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    //set Request parameter
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("senior_id", senior_id);
                    jsonObj.put("date_from", startInfo);

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(jsonObj.toString());
                    wr.flush();

                    BufferedReader rd =null;

                    if(con.getResponseCode() == 200){
                        //Request Success
                        responseStatus = 1;
                        rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                        String resultValues = rd.readLine();
                        Log.d(TAG,"Successs");
                    }else {
                        //Request Fail
                        responseStatus = 0;
                        rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                        Log.d(TAG,"Fail: " + rd.readLine());
                    }
                } catch(IOException | JSONException e){
                    e.printStackTrace();
                }
                return null;
            }
        });
        ConnectServer.getInstance().execute();
    }
}
