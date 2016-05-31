package com.example.administrator.guardian.ui.activity.Volunteer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.datamodel.VolunteerConfirmRecyclerItem;
import com.example.administrator.guardian.ui.adapter.VolunteerConfirmViewAdapter;
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


/**
 * Created by Administrator on 2016-05-11.
 */
@SuppressLint("ValidFragment")
public class VolunteerFragmentTwoActivity extends Fragment{
    private static final String TAG = "VolunteerTwoActivity";
    View view;
    int responseStatus = 1;
    Context mContext;
    RecyclerView recyclerView;
    List<VolunteerConfirmRecyclerItem> items;
    LinearLayoutManager layoutManager;
    public VolunteerFragmentTwoActivity(Context context){
        mContext=context;
    }

    String senior_id;
    String senior_name;
    int senior_age;
    String senior_gender;
    String senior_address;
    String latitude;
    String longitude;
    String startInfo;
    String details;
    int req_hour;
    int type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_volunteer_fragment_two, null);
        getReqInfo();
        return view;
    }
    void getReqInfo() {
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {
            @Override
            protected void onPreExecute() {
                items = new ArrayList<>();
            }
            @Override
            protected void onPostExecute(Boolean params) {
                if(responseStatus == 1){

                    recyclerView = (RecyclerView)view.findViewById(R.id.volunteer_confirm_recyclerView);
                    layoutManager = new LinearLayoutManager(getContext());
                    layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setAdapter(new VolunteerConfirmViewAdapter(getContext(), items, R.layout.activity_volunteer_fragment_two));

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
                            senior_id = (String)dataArray.getJSONObject(i).get("senior_id");

                            senior_name = (String)dataArray.getJSONObject(i).get("user_name");
                            senior_age = (20179999 - dataArray.getJSONObject(i).getInt("user_birthdate"))/10000;
                            senior_gender = (String)dataArray.getJSONObject(i).get("user_gender");
                            senior_address = (String)dataArray.getJSONObject(i).get("user_address");
                            startInfo= (String)dataArray.getJSONObject(i).get("date_from");
                            latitude = (String)dataArray.getJSONObject(i).get("latitude");
                            longitude = (String)dataArray.getJSONObject(i).get("longitude");

                            try{
                                details = (String)dataArray.getJSONObject(i).get("details");
                            }catch (Exception e){
                                details = "";
                            }
                            req_hour = dataArray.getJSONObject(i).getInt("req_hour");

                            if((dataArray.getJSONObject(i).getInt("current_status")) == 2 ){
                                type = 4; // 기간만료
                            }else if( ((Integer)dataArray.getJSONObject(i).getInt("req_type")) == 0
                                    && ((Integer)dataArray.getJSONObject(i).getInt("current_status")) == 0 ){
                                type = 0; // 수락대기
                            }else if(((Integer)dataArray.getJSONObject(i).getInt("req_type")) == 1
                                    && ((Integer)dataArray.getJSONObject(i).getInt("current_status")) == 0 ){
                                type = 2; // 요청중
                            }else if( dataArray.getJSONObject(i).getInt("current_status") == 1 ){
                                if(dataArray.getJSONObject(i).getInt("req_type") == 0){
                                    if(dataArray.getJSONObject(i).getString("signature").compareTo("0") != 0){
                                        type = 6; // 완료
                                    }else if(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) > Long.parseLong(startInfo) &&
                                            Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) < (Long.parseLong(startInfo) + req_hour*100)
                                            ){
                                        type = 5; // 진행중

                                    }else if(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) > (Long.parseLong(startInfo) + req_hour*100)){
                                        type = 7; // 서명필요
                                    }else{
                                        type = 1;// 수락완료
                                    }
                                }else if(dataArray.getJSONObject(i).getInt("req_type") == 1){
                                    if(dataArray.getJSONObject(i).getString("signature").compareTo("0") != 0){
                                        type = 6; // 완료
                                    }else if(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) > Long.parseLong(startInfo) &&
                                            Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) < (Long.parseLong(startInfo) + req_hour*100)
                                            ){
                                        type = 5; // 진행중

                                    }else if(Long.parseLong(new SimpleDateFormat("yyyyMMddHHmm").format(new Date())) > (Long.parseLong(startInfo) + req_hour*100)){
                                        type = 7; // 서명필요
                                    }else{
                                        type = 3;// 요청완료
                                    }
                                }
                            }
                            //   ( senior_id,  senior_name,  senior_age,  senior_gender,  senior_address,  latitude,  longitude,  startInfo, details,  req_hour,  type){

                            items.add(new VolunteerConfirmRecyclerItem(senior_id, senior_name, senior_age,senior_gender,senior_address, latitude, longitude, startInfo,details,req_hour,type ));
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
