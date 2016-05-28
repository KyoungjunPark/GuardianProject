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
import android.widget.Toast;

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


@SuppressLint("ValidFragment")
public class ManagerManagePulseActivity extends Fragment {
    private static final String TAG = "ManagerHR";

    Context mContext;
    String senior_id;
    EditText editMaxSeriousPulse;
    EditText editSeriousPulse;
    EditText editMinSeriousPulse;
    Button managePulseBtn;
    boolean responseStatus;
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
        managePulseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHeartrate();
            }
        });
        editMaxSeriousPulse.setText(high_zone_2+"");
        editSeriousPulse.setText(high_zone_1+"");
        editMinSeriousPulse.setText(low_zone_1+"");

        managePulseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setHeartrate();
            }
        });
        return view;
    }

    public void setHeartrate(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {
                high_zone_2 = Integer.parseInt(editMaxSeriousPulse.getText().toString());
                high_zone_1 = Integer.parseInt(editSeriousPulse.getText().toString());
                low_zone_1 = Integer.parseInt(editMinSeriousPulse.getText().toString());
                Log.d(TAG, "h2 : "+high_zone_2);
                Log.d(TAG, "h2 : "+high_zone_2);
                Log.d(TAG, "h2 : "+high_zone_2);
            }
            protected void onPostExecute(Boolean params) {

                if(responseStatus == true){
                    Toast.makeText(getActivity(), "심박수 변경 성공", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "심박수 변경 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("Set_HR"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                    con.setRequestProperty("Accept", "application/json");

                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("senior_id", senior_id);
                    jsonObj.put("high_zone_2", high_zone_2);
                    jsonObj.put("high_zone_1", high_zone_1);
                    jsonObj.put("low_zone_1", low_zone_1);

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(jsonObj.toString());
                    wr.flush();

                    BufferedReader rd =null;

                    if(con.getResponseCode() == 200){
                        //Sign up Success
                        responseStatus = true;
                        rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                        String resultValues = rd.readLine();
                        Log.d(TAG, "success");
                    } else {
                        //Sign up Fail
                        responseStatus = false;
                        rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                        Log.d(TAG,"fail : " + rd.readLine());
                    }

                } catch (IOException | JSONException e) {
                    e.printStackTrace();
                }
                return null;
            }

        });
        ConnectServer.getInstance().execute();
    }
}
