package com.example.administrator.guardian.ui.activity.Volunteer;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Calendar;

public class VolunteerFragmentOneRequestActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener{
    private static final String TAG = "VolunteerReqActivity";

    int responseStatus = 0;
    private TextView vfor_Name;
    private TextView vfor_Age;
    private TextView vfor_Address;
    private TextView vfor_Gender;


    private Button dateTextView;
    private Button timeTextView;

    private int v_year;
    private int v_month;
    private int v_day_of_month;
    private int v_hour_of_day;
    private int v_minute;
    String info_message;

    private String id;
    private String name;
    private int age;
    private String gender;
    private String address;
    private double latitude, longitude;
    private Button vfor_left;
    private Button vfor_right;

    private GoogleMap mMap;
    MapView mapView;

    View view;
    Context mContext;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_fragment_one_request);

        Intent intent = getIntent();
        try{
            id = intent.getExtras().getString("id");
        }catch(Exception e){
            id = "";
        }
        name = intent.getExtras().getString("name");
        age = intent.getExtras().getInt("age");
        gender = intent.getExtras().getString("gender");
        address = intent.getExtras().getString("address");
        latitude = intent.getExtras().getDouble("latitude");
        longitude = intent.getExtras().getDouble("longitude");

        dateTextView = (Button) findViewById(R.id.vfor_DateButton);
        timeTextView = (Button) findViewById(R.id.vfor_TimeButton);

        //Set the current date & time
        v_year = Calendar.getInstance().get(Calendar.YEAR);
        v_month = Calendar.getInstance().get(Calendar.MONTH);
        v_month+=1;
        v_day_of_month = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        v_hour_of_day = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        v_minute = Calendar.getInstance().get(Calendar.MINUTE);

        dateTextView.setText(v_year + "년 " + v_month + "월 " + v_day_of_month + "일 ");
        setTime();
        //Set the Date & Time ClickListener
        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "a", Toast.LENGTH_SHORT).show();
                Calendar now = Calendar.getInstance();
                DatePickerDialog dpd = DatePickerDialog.newInstance(
                        VolunteerFragmentOneRequestActivity.this,
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH)
                );
                dpd.show(getFragmentManager(), "Datepickerdialog");
            }
        });

        timeTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog dpd = TimePickerDialog.newInstance(
                        VolunteerFragmentOneRequestActivity.this,
                        now.get(Calendar.HOUR_OF_DAY),
                        now.get(Calendar.MINUTE),
                        true
                );
                dpd.show(getFragmentManager(), "Timepickerdialog");
            }
        });

        vfor_left = (Button) findViewById(R.id.vfor_left);
        vfor_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            finish();
            }
        });
        vfor_right = (Button) findViewById(R.id.vfor_right);
        vfor_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                request();
                finish();
            }
        });

        setLayout();
        setTitle(name);
        setContent(age,gender,address);


        mapView = (MapView)findViewById(R.id.vfor_map);
        mapView.onCreate(new Bundle());
        mapView.onResume();
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mMap = googleMap;

                LatLng senior_home = new LatLng( latitude, longitude);
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
            timeTextView.setText("오후 " + (v_hour_of_day-12) + "시 " + v_minute + "분 ");
        } else {
            timeTextView.setText("오전 " + v_hour_of_day + "시 " + v_minute + "분 ");
        }
    }

    private void setTitle(String Name){
        if(gender.compareTo("남") == 0){
            Name = Name + " 할아버지";
        }
        else{
            Name = Name + " 할머니";
        }

        vfor_Name.setText(Name);
    }

    private void setContent(int age, String gender, String address){
        vfor_Age.setText("나이 : "+age);
        vfor_Gender.setText("성별 : "+ gender);
        vfor_Address.setText("주소 : "+address);
    }

    /*
     * Layout
     */
    private void setLayout(){
        vfor_Name = (TextView) findViewById(R.id.vfor_name);
        vfor_Age = (TextView)findViewById(R.id.vfor_age);
        vfor_Gender = (TextView)findViewById(R.id.vfor_gender);
        vfor_Address = (TextView)findViewById(R.id.vfor_address);
    }

    void request(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {
            String date_from;
            NumberFormat numformat;

            @Override
            protected void onPreExecute() {
                //Collect the input data
                if (v_hour_of_day > 12) {
                    info_message = new String("일시 : " + v_year + "년 " + v_month + "월 " + v_day_of_month + "일 " + "오후" + (v_hour_of_day - 12) + "시 " + v_minute + "분 ");
                } else {
                    info_message = new String ("일시 : " + v_year + "년 " + v_month + "월 " + v_day_of_month + "일 " + "오전" + v_hour_of_day + "시 " + v_minute + "분 ");
                }
                numformat = NumberFormat.getIntegerInstance();
                numformat.setMinimumIntegerDigits(2);
                date_from = ""+ v_year + numformat.format(v_month) + numformat.format(v_day_of_month)
                        + numformat.format(v_hour_of_day) + numformat.format(v_minute);
            }

            @Override
            protected void onPostExecute(Boolean params){
                if(responseStatus == 1){
                    Toast.makeText(VolunteerFragmentOneRequestActivity.this.getApplicationContext(), "신청 완료", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(VolunteerFragmentOneRequestActivity.this.getApplicationContext(), "신청 실패", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("Request"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    //set Request parameter
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("date_from", date_from);
                    jsonObj.put("req_hour", 1);
                    jsonObj.put("senior_id", id);
                    jsonObj.put("details", info_message);
                    Log.d(TAG, "doInBackground: "+id);
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(jsonObj.toString());
                    wr.flush();

                    BufferedReader rd =null;

                    if(con.getResponseCode() == 200){
                        //Request Success
                        responseStatus = 1;
                        rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                        String resultValues = rd.readLine();
                        Log.d(TAG,"Connect Success: " + resultValues);
                    }else {
                        //Request Fail
                        responseStatus = 0;
                        rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                        Log.d(TAG,"Connect Fail: " + rd.readLine());
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
