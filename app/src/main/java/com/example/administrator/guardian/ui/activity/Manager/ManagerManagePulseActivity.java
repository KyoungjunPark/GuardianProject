package com.example.administrator.guardian.ui.activity.Manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.utils.ConnectServer;
import com.example.administrator.guardian.utils.MakeUTF8Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


@SuppressLint("ValidFragment")
public class ManagerManagePulseActivity extends Fragment {

    Context mContext;
    String senior_id;
    EditText editMaxSeriousPulse;
    EditText editSeriousPulse;
    EditText editMinSeriousPulse;
    Button managePulseBtn;

    int high_zone_2;
    int high_zone_1;
    int low_zone_1;


    public ManagerManagePulseActivity(Context context, String senior_id, int high_zone_2, int high_zone_1, int low_zone_1){
        this.mContext=context;
        this.senior_id = senior_id;
        this.high_zone_2 = high_zone_2;
        this.high_zone_1 = high_zone_1;
        this.low_zone_1 = low_zone_1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_pulse, null);

        editMaxSeriousPulse=(EditText)view.findViewById(R.id.editMaxSeriousPulse);
        editSeriousPulse=(EditText)view.findViewById(R.id.editSeriousPulse);
        editMinSeriousPulse=(EditText)view.findViewById(R.id.editMinSeriousPulse);
        managePulseBtn = (Button)view.findViewById(R.id.managePulseButton);

        editMaxSeriousPulse.setText(high_zone_2+"");
        editSeriousPulse.setText(high_zone_1+"");
        editMinSeriousPulse.setText(low_zone_1);

        managePulseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHeartrate();
            }
        });
        return view;
    }

    void setHeartrate(){

        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {
            }
            protected void onPostExecute(Boolean params) {
                super.onPostExecute(null);
            }
            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("Set_HR"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    //set Request parameter
                    MakeUTF8Parameter parameterMaker = new MakeUTF8Parameter();
                    parameterMaker.setParameter("senior_id", senior_id);
                    parameterMaker.setParameter("high_zone_2", high_zone_2+"");
                    parameterMaker.setParameter("high_zone_1", high_zone_1+"");
                    parameterMaker.setParameter("low_zone_1", low_zone_1+"");

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(parameterMaker.getParameter());
                    wr.flush();

                    BufferedReader rd =null;

                    if(con.getResponseCode() == 200){
                        //Sign up Success
                        rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                        Log.d("Manager Set HR Tab","success");

                    } else {
                        //Sign up Fail
                        rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                        Log.d("Manager Set HR Tab","fail");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }



        });
        ConnectServer.getInstance().execute();
    }
}
