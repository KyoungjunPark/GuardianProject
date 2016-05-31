package com.example.administrator.guardian.ui.activity.Senior;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.administrator.guardian.utils.ConnectServer;
import com.github.gcacace.signaturepad.views.SignaturePad;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.administrator.guardian.R;
import com.google.android.gms.vision.Frame;

public class SeniorFragmentThreeScheduleFinishDialog extends Dialog {
    private static final String TAG = "SeniorThreeFinish";

    private TextView sftsf_Name;
    private TextView sftsf_Contents;
    private TextView sftsf_vDate;
    private TextView sftsf_vTime;

    private int year;
    private int month;
    private int day;
    private int startHour;
    private int startMinute;
    private int finishHour;
    private int finishMinute;

    private String startInfo;
    private int reqHour;
    private String name;
    private String details;

    private Button sftsf_button;

    private FrameLayout senior_signature_layout;
    private SignaturePad mSignaturePad;

    public SeniorFragmentThreeScheduleFinishDialog(Context context, String startInfo, int reqHour, String name, String details) {
        super(context , android.R.style.Theme_Translucent_NoTitleBar);
        this.startInfo = startInfo;
        this.reqHour = reqHour;
        this.name = name;
        this.details=details;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.activity_senior_fragment_three_schedule_finish_dialog);

        senior_signature_layout=(FrameLayout)findViewById(R.id.senior_signature_layout);
        mSignaturePad = (SignaturePad) findViewById(R.id.signature_pad);
        mSignaturePad.setOnSignedListener(new SignaturePad.OnSignedListener() {
            @Override
            public void onStartSigning() {
            }

            @Override
            public void onSigned() {
            }

            @Override
            public void onClear() {
            }
        });

        setLayout();
        Log.d("name",name+"");
        setTitle(name);
        setContent(startInfo, reqHour, details);

        sftsf_button = (Button)findViewById(R.id.sftsf_button);
        sftsf_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finishRequest();
                ////////////////////////////////////////////////////////////////////////////////////////////

                //signature result
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();


                //////////////////////////////////////////////////////////////
                //SeniorFragmentThreeScheduleFinishDialog.this.dismiss();
            }
        });
    }
    private void setTitle(String Name){

        sftsf_Name.setText(Name);
    }

    private void setContent(String startInfo, int reqHour, String details){

        sftsf_vDate.setText(startInfo);
        sftsf_vTime.setText(reqHour+"");
        sftsf_Contents.setText("내용 : "+details);
    }

    /*
     * Layout
     */
    private void setLayout(){
        sftsf_Name = (TextView) findViewById(R.id.sftsf_name);
        sftsf_Contents=(TextView)findViewById(R.id.sftsf_content);
        sftsf_vDate= (TextView)findViewById(R.id.sftsf_vDate);
        sftsf_vTime = (TextView)findViewById(R.id.sftsf_vTime);
    }
    public void finishRequest(){
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
                    obj = new URL(ConnectServer.getInstance().getURL("Finish_Request"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    //set Request parameter
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("date_from", startInfo);
                    // 토큰과 date_from 이 primary key가 되고
                    // 이미지 파일 보내야 함

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
