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
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.utils.ConnectServer;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

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

@SuppressLint("ValidFragment")
public class ManagerManageActiveActivity extends Fragment {
    private static final String TAG = "ManagerReceiveActivity";

    Context mContext;
    String senior_id;


    HorizontalBarChart h_BarChart;

    ArrayList<TextView> mma_dtr_time;
    TextView mma_doortime;
    TextView mma_toilettime;
    TextView mma_repritime;
    BarData data;
    int[] activeNum_dtr;
    String[] dataList;
    int responseStatus;

    public ManagerManageActiveActivity(Context context, String senior_id){

        this.mContext = context;
        this.senior_id = senior_id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_manager_manage_active, null);

        mma_dtr_time = new ArrayList<>();
        h_BarChart = (HorizontalBarChart) view.findViewById(R.id.M_senior_active_chart);
        mma_dtr_time.add((mma_doortime = (TextView)view.findViewById(R.id.mma_doortime)));
        mma_dtr_time.add((mma_repritime=(TextView)view.findViewById(R.id.mma_repritime)));
        mma_dtr_time.add((mma_toilettime = (TextView)view.findViewById(R.id.mma_toilettime)));

        getActivityInfo();

        return view;
    }


    public void getActivityInfo(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {

                responseStatus = 1;
                dataList = new String[3];
                activeNum_dtr = new int[3];
            }

            protected void onPostExecute(Boolean params) {
                if(responseStatus == 1){

                    String date = new SimpleDateFormat("yyyyMMddHH").format(new Date());
                    int num0 = Integer.parseInt(date);

                    int num[] = new int[3];
                    for (int i=0; i<3; i++){
                        if(dataList[i] != null){
                            num[i] =  Integer.parseInt(dataList[i].substring(0, 10));
                            mma_dtr_time.get(i).setText((num0/100 - num[i]/100)*24 + (num0%100 - num[i]%100) + "시간 경과");
                        }else{
                            mma_dtr_time.get(i).setText("정보없음");
                        }
                    }

                    //--
                    ArrayList<Integer> activeNumber = new ArrayList<>();
                    activeNumber.add(activeNum_dtr[2]); // 2
                    activeNumber.add(activeNum_dtr[1]); // 1
                    activeNumber.add(activeNum_dtr[0]); // 0

                    ArrayList<BarEntry> entries = new ArrayList<>();
                    entries.add(new BarEntry(activeNumber.get(0), 0));
                    entries.add(new BarEntry(activeNumber.get(1), 1));
                    entries.add(new BarEntry(activeNumber.get(2), 2));

                    BarDataSet dataset = new BarDataSet(entries, "당일 센서값 변동 횟수");

                    ArrayList<String> labels = new ArrayList<String>();
                    labels.add("화장실"); // 2
                    labels.add("냉장고"); // 1
                    labels.add("출입문"); // 0

                    data = new BarData(labels, dataset);
                    dataset.setColors(ColorTemplate.COLORFUL_COLORS);

                    h_BarChart.setData(data);
                    h_BarChart.animateX(1000);
                    //--
                }
            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    for (int i=0; i<3; i++){
                        obj = new URL(ConnectServer.getInstance().getURL("Receive_Activity_Log"));
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                        con.setRequestProperty("Accept", "application/json");

                        con = ConnectServer.getInstance().setHeader(con);
                        con.setDoOutput(true);

                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("type_of_sensor", i);
                        jsonObj.put("senior_id", senior_id);

                        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                        wr.write(jsonObj.toString());
                        wr.flush();

                        BufferedReader rd =null;

                        if(con.getResponseCode() == 200){
                            rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                            String resultValues = rd.readLine();
                            JSONObject object = new JSONObject(resultValues);
                            JSONArray jArr  = object.getJSONArray("data");
                            JSONObject c = jArr.getJSONObject(0);
                            dataList[i] = new String(c.getString("date"));
                            Log.d(TAG, "success");
                        } else {
                            responseStatus *= 0;
                            rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                            Log.d(TAG,"fail : " + rd.readLine());
                        }
                    }
                    for(int i=0; i<3; i++){
                        obj = new URL(ConnectServer.getInstance().getURL("Receive_Activity_Log"));
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                        con.setRequestProperty("Accept", "application/json");

                        con = ConnectServer.getInstance().setHeader(con);
                        con.setDoOutput(true);

                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("type_of_sensor", i);
                        jsonObj.put("senior_id", senior_id);
                        jsonObj.put("start_of_period", new SimpleDateFormat("yyyyMMdd").format(new Date()));

                        OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                        wr.write(jsonObj.toString());
                        wr.flush();

                        BufferedReader rd =null;

                        if(con.getResponseCode() == 200){
                            //Sign up Success
                            rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                            String resultValues = rd.readLine();
                            JSONObject object = new JSONObject(resultValues);
                            JSONArray jArr  = object.getJSONArray("data");

                            activeNum_dtr[i] = jArr.length();

                            Log.d(TAG, "success");
                        } else {
                            //Sign up Fail
                            responseStatus *= 0;
                            rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                            Log.d(TAG,"fail : " + rd.readLine());
                        }
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