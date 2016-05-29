package com.example.administrator.guardian.ui.activity.Volunteer;

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
import com.github.mikephil.charting.charts.BarChart;
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
public class VolunteerFragmentFourActivity extends Fragment {
    private static final String TAG = "VolunteerFragmentFour";

    Context mContext;
    TextView textVolunteerTime;
    int totalHour;
    String thisMonth;
    int responseStatus = 1;
    BarChart BarChart;
    View view;
    ArrayList<BarEntry> entries;
    BarDataSet dataset;
    ArrayList<String> labels;
    BarData data;
    ArrayList<Integer> monthlyHour;

    public VolunteerFragmentFourActivity(Context context){
        mContext=context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_volunteer_fragment_four, container, false);

        textVolunteerTime = (TextView)view.findViewById(R.id.textVolunteerTime);
        BarChart = (BarChart) view.findViewById(R.id.volunteer_time_chart);

        getVolunteerTimeInfo();

        return view;
    }

    public void getVolunteerTimeInfo(){

        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {
                thisMonth = new SimpleDateFormat("yyyyMM").format(new Date());
                monthlyHour = new ArrayList<Integer>();
            }

            protected void onPostExecute(Boolean params) {
                if(responseStatus == 1){
                    entries = new ArrayList<>();
                    labels = new ArrayList<String>();
                    dataset = new BarDataSet(entries, "# of Calls");

                    for (int i=0; i<7;i++){
                        entries.add(new BarEntry(Float.valueOf(monthlyHour.get(i) + ""), i));
                        int iMonth = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()))-6+i;
                        if(iMonth < 1){
                            iMonth += 12;
                        }else if(iMonth > 12){
                            iMonth -= 12;
                        }
                        labels.add(iMonth +"");
                    }

                    data = new BarData(labels, dataset);
                    dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                    BarChart.setData(data);
                    BarChart.animateY(1000);


                    textVolunteerTime.setText(totalHour+"시간");

                }
            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    for (int i=0; i<7; i++){
                        obj = new URL(ConnectServer.getInstance().getURL("Receive_Volunteer_Info"));
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                        con.setRequestProperty("Accept", "application/json");

                        con = ConnectServer.getInstance().setHeader(con);
                        con.setDoOutput(true);

                        JSONObject jsonObj = new JSONObject();
                        jsonObj.put("start_of_period", Integer.parseInt(thisMonth) - 6 + i + "");
                        jsonObj.put("end_of_period", Integer.parseInt(thisMonth) - 6 + i + 1 + "");
                        jsonObj.put("type", 1);


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
                            Log.d(TAG, resultValues.toString());
                            Log.d(TAG, jArr.toString());
                            Log.d(TAG, c.toString());

                            if(c.isNull("sum")){
                                monthlyHour.add(0);
                            }else{
                                monthlyHour.add(c.getInt("sum"));
                            }

                            Log.d(TAG, "success");
                        } else {
                            responseStatus *= 0;
                            rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                            Log.d(TAG,"fail : " + rd.readLine());
                        }
                    }

                    obj = new URL(ConnectServer.getInstance().getURL("Total_Volunteer_Time"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                    con.setRequestProperty("Accept", "application/json");

                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    JSONObject jsonObj = new JSONObject();


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

                        if(c.isNull("total")){
                            totalHour = 0;
                        }else{
                            totalHour = c.getInt("total");
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

