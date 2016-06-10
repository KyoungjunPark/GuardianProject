package com.example.administrator.guardian.ui.activity.Senior;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.SeniorScheduleRecyclerItem;
import com.example.administrator.guardian.ui.adapter.SeniorScheduleRecyclerViewAdapter;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class SeniorFragmentThreeScheduleActivity extends AppCompatActivity {
    private static final String TAG = "SeniorThreeSchedule";

    RecyclerView recyclerView;
    List<SeniorScheduleRecyclerItem> items;
    LinearLayoutManager layoutManager;
    private Button sfts_back;
    int responseStatus = 1;

    String volunteer_id, volunteer_name;
    int volunteer_age;
    String volunteer_gender;
    String startInfo, details;
    int req_hour, type;
    private int tmpCode = 0;

    private SeniorScheduleRecyclerViewAdapter seniorScheduleRecyclerViewAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_fragment_three_schedule);

        sfts_back = (Button)findViewById(R.id.sfts_back);
        sfts_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getReqInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "resume start");
        if(tmpCode != 0)
            getReqInfo();
        else tmpCode = 1;
    }

    public void getReqInfo(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {
            @Override
            protected void onPreExecute() {
                items = new ArrayList<SeniorScheduleRecyclerItem>();
            }
            @Override
            protected void onPostExecute(Boolean params) {
                if(responseStatus == 1){
                    Log.d(TAG, "responseStatus start: " + items.size());
                    seniorScheduleRecyclerViewAdapter = new SeniorScheduleRecyclerViewAdapter(getApplicationContext(), items, R.layout.activity_senior_fragment_three_schedule);
                    seniorScheduleRecyclerViewAdapter.setAdapter(seniorScheduleRecyclerViewAdapter);
                    recyclerView = (RecyclerView)findViewById(R.id.senior_schedule_recyclerView);
                    layoutManager = new LinearLayoutManager(getApplicationContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(seniorScheduleRecyclerViewAdapter);
                }
            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                        obj = new URL(ConnectServer.getInstance().getURL("Request_List"));
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                        con.setRequestProperty("Accept", "application/json");

                        con = ConnectServer.getInstance().setHeader(con);
                        con.setDoOutput(true);

                        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                        wr.flush();

                        BufferedReader rd =null;

                        if(con.getResponseCode() == 200){
                            rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                            String resultValues = rd.readLine();
                            Log.d(TAG, "doInBackground: "+resultValues);
                            JSONObject object = new JSONObject(resultValues);
                            JSONArray dataArray = object.getJSONArray("data");
                            Log.d(TAG, "doInBackground: "+dataArray.toString());

                            for (int i=0; i<dataArray.length(); i++){
                                try{
                                    volunteer_id = (String)dataArray.getJSONObject(i).get("volunteer_id");
                                }catch (Exception e){
                                    volunteer_id = "";
                                }
                                try{
                                    volunteer_name = (String)dataArray.getJSONObject(i).get("user_name");
                                }catch (Exception e){
                                    volunteer_name= "자원봉사자 미지정";
                                }
                                try{
                                    volunteer_age = (20179999 - dataArray.getJSONObject(i).getInt("user_birthdate"))/10000;
                                }catch (Exception e){
                                    volunteer_age= 0;
                                }
                                try{
                                    volunteer_gender = (String)dataArray.getJSONObject(i).get("user_gender");
                                }catch (Exception e){
                                    volunteer_gender= "";
                                }

                                startInfo= (String)dataArray.getJSONObject(i).get("date_from");
                                try{
                                    details = (String)dataArray.getJSONObject(i).get("details");

                                }catch (Exception e){
                                    details = "";
                                }
                                req_hour = (Integer)dataArray.getJSONObject(i).getInt("req_hour");

                                if((dataArray.getJSONObject(i).getInt("current_status")) == 2 ){
                                    type = 4; // 기간만료
                                }else if( ((Integer)dataArray.getJSONObject(i).getInt("req_type")) == 0
                                        && ((Integer)dataArray.getJSONObject(i).getInt("current_status")) == 0 ){
                                    type = 0; // 요청중
                                }else if(((Integer)dataArray.getJSONObject(i).getInt("req_type")) == 1
                                        && ((Integer)dataArray.getJSONObject(i).getInt("current_status")) == 0 ){
                                    type = 2; // 수락대기

                                }else if( dataArray.getJSONObject(i).getInt("current_status") == 1 ){
                                    if(dataArray.getJSONObject(i).getInt("req_type") == 0){
                                        if(dataArray.getJSONObject(i).getString("signature").compareTo("0") != 0){
                                            type = 6; // 완료
                                        }else if(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) > Long.parseLong(startInfo) &&
                                                Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) < (Long.parseLong(startInfo) + req_hour*100)
                                                ){
                                            type = 5; //진행중

                                        }else if(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) > (Long.parseLong(startInfo) + req_hour*100)){
                                            Log.d(TAG, "doInBackground:1 " + Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())));
                                            Log.d(TAG, "doInBackground:1 " + Long.parseLong(startInfo) + req_hour);

                                            type = 7; // 서명필요
                                        }else{
                                           type = 1;// 요청완료
                                        }
                                    }else if(dataArray.getJSONObject(i).getInt("req_type") == 1){
                                        if(dataArray.getJSONObject(i).getString("signature").compareTo("0") != 0){
                                            type = 6; // 완료
                                        }else if(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) > Long.parseLong(startInfo) &&
                                                Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) <(Long.parseLong(startInfo) + req_hour*100)
                                                ){
                                            type = 5; // 진행중

                                        }else if(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) > (Long.parseLong(startInfo) + req_hour*100)){
                                            type = 7; // 서명필요
                                        }else{
                                            type = 3;// 수락완료
                                        }
                                    }
                                }

                                items.add(new SeniorScheduleRecyclerItem(volunteer_id,volunteer_name, volunteer_age,volunteer_gender, startInfo,details,req_hour,type ));
                            }
                            Log.d(TAG, "success : " + items.size());
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
