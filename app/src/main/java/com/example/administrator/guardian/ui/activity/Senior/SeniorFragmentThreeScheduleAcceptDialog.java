package com.example.administrator.guardian.ui.activity.Senior;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.utils.ConnectServer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeniorFragmentThreeScheduleAcceptDialog extends Dialog {
    private static final String TAG = "SeniorThreeAccept";

    private TextView sftsa_Content;
    private TextView sftsa_Name;
    private TextView sftsa_Age;
    private TextView sftsa_Gender;
    private TextView sftsa_vDate;
    private TextView sftsa_vTime;

    private int year;
    private int month;
    private int day;
    private int startHour;
    private int startMinute;
    private int finishHour;
    private int finishMinute;

    private String startInfo;
    private String details;
    private int reqHour;
    private String name;
    private int age;
    private String gender;

    private Button sftsa_left;
    private Button sftsa_right;

    public SeniorFragmentThreeScheduleAcceptDialog(Context context, String startInfo, String details, int reqHour, String name, int age, String gender) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.startInfo = startInfo;
        this.details = details;
        this.reqHour = reqHour;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.6f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_senior_fragment_three_schedule_accept_dialog);

        setLayout();
        setTitle(name);
        setContent(startInfo,reqHour, age,gender);

        sftsa_left = (Button)findViewById(R.id.sftsa_left);
        sftsa_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acceptReq();
                SeniorFragmentThreeScheduleAcceptDialog.this.dismiss();
            }
        });
        sftsa_right = (Button)findViewById(R.id.sftsa_right);
        sftsa_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SeniorFragmentThreeScheduleAcceptDialog.this.dismiss();
            }
        });
    }
    private void setTitle(String Name){

        sftsa_Name.setText(Name);
    }

    private void setContent(String startInfo, int reqHour, int age, String gender){
        sftsa_vDate.setText(startInfo);
        sftsa_vTime.setText(reqHour+"");
        sftsa_Age.setText("나이 : "+age);
        sftsa_Gender.setText("성별 : "+ gender);
    }

    /*
     * Layout
     */
    private void setLayout(){
        sftsa_Name = (TextView) findViewById(R.id.sftsa_name);
        sftsa_Age = (TextView)findViewById(R.id.sftsa_age);
        sftsa_Gender = (TextView)findViewById(R.id.sftsa_gender);
        sftsa_vDate = (TextView)findViewById(R.id.sftsa_vDate);
        sftsa_vTime = (TextView)findViewById(R.id.sftsa_vTime);
    }
    public void acceptReq(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {

            @Override
            protected void onPreExecute() {
            }
            @Override
            protected void onPostExecute(Boolean params){

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
                    jsonObj.put("date_from", startInfo);

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(jsonObj.toString());
                    wr.flush();

                    BufferedReader rd =null;

                    if(con.getResponseCode() == 200){
                        //Request Success
                        rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                        String resultValues = rd.readLine();
                        Log.d(TAG,"Successs");
                    }else {
                        //Request Fail
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
