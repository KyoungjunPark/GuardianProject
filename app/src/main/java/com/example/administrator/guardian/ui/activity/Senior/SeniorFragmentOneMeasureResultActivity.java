package com.example.administrator.guardian.ui.activity.Senior;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.utils.ConnectServer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class SeniorFragmentOneMeasureResultActivity extends AppCompatActivity {
    private static final String TAG = "SeniorOneResult";

    private TextView pulseResult, minText, maxText;
    private Button measureback;
    private ProgressBar pulseProgressBar;
    private int high_zone_2, low_zone_1;
    private int responseStatus = 1;
    private int pulseAverage;
    private ArrayList<Integer> pulseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_fragment_one_measure_result);

        Intent intent = getIntent();
        high_zone_2 = intent.getExtras().getInt("high_zone_2");
        low_zone_1 = intent.getExtras().getInt("low_zone_1");
        minText = (TextView)findViewById(R.id.textMinpulse);
        maxText = (TextView)findViewById(R.id.textMaxpulse);
        pulseResult = (TextView)findViewById(R.id.pulseResult);
        measureback = (Button)findViewById(R.id.sfomrback);
        measureback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        pulseProgressBar = (ProgressBar)findViewById(R.id.seniorpulseProgressBar);

        getHeartrateInfo();
    }

    public void getHeartrateInfo(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {
                pulseList = new ArrayList<Integer>();
            }

            protected void onPostExecute(Boolean params) {
                if(responseStatus == 1){

                    pulseAverage = 0;
                    for(int i=0;i<pulseList.size();i++){
                        pulseAverage+=pulseList.get(i);
                    }
                    pulseAverage=pulseAverage/pulseList.size();

                    pulseResult.setText(pulseAverage+"");
                    minText.setText(low_zone_1+"");
                    maxText.setText(high_zone_2+"");
                    pulseProgressBar.setMax(high_zone_2 - low_zone_1);
                    //심박수 값이 들어가야함
                    pulseProgressBar.setProgress(pulseAverage);
                    //progress bar가 40부터 시작해서 40을 빼줘야함
                    pulseProgressBar.incrementProgressBy( low_zone_1*(-1));

                }
            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                        obj = new URL(ConnectServer.getInstance().getURL("Receive_Heartrate_Log"));
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                        con.setRequestProperty("Accept", "application/json");

                        con = ConnectServer.getInstance().setHeader(con);
                        con.setDoOutput(true);

                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("num", 3);

                        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                        wr.write(jsonObj.toString());
                        wr.flush();

                        BufferedReader rd =null;

                        if(con.getResponseCode() == 200){
                            rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                            String resultValues = rd.readLine();
                            JSONObject object = new JSONObject(resultValues);
                            JSONArray dataArray = object.getJSONArray("data");
                            for (int i=0; i<dataArray.length(); i++){
                                int value = (int)dataArray.getJSONObject(i).get("heartrate");
                                pulseList.add(value);
                            }

                            Log.d(TAG, "success");
                        } else {
                            responseStatus *= 0;
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
