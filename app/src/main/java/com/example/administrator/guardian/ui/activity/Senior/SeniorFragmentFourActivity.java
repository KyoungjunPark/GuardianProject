package com.example.administrator.guardian.ui.activity.Senior;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.guardian.utils.ConnectServer;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import com.example.administrator.guardian.R;

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

/**
 * Created by Administrator on 2016-05-05.
 */
@SuppressLint("ValidFragment")
public class SeniorFragmentFourActivity extends Fragment {
    private static final String TAG = "SeniorReceivePulse";


    Context mContext;
    String today_date;
    TextView weekAvgHR;
    TextView minText;
    TextView maxText;
    private ProgressBar pulseProgressBar;
    ArrayList<Double> avgPulse;
    LineChart lineChart;
    private int pulseAverage=0;
    ArrayList<Entry> entries;
    ArrayList<String> labels;
    LineDataSet dataset;
    LineData data;
    int responseStatus;
    int high_zone_2, low_zone_1;

    public SeniorFragmentFourActivity(Context context, int high_zone_2, int low_zone_1) {
        this.responseStatus = 1;
        this.mContext = context;
        this.today_date = new SimpleDateFormat("yyyyMMdd").format(new Date());
        this.high_zone_2 = high_zone_2;
        this.low_zone_1 = low_zone_1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_senior_fragment_four, null);

        lineChart = (LineChart) view.findViewById(R.id.chart);
        pulseProgressBar = (ProgressBar)view.findViewById(R.id.seniorpulseProgressBar);
        weekAvgHR = (TextView)view.findViewById(R.id.seniorPulseAverage);
        minText = (TextView)view.findViewById(R.id.textSMinpulse);
        maxText = (TextView)view.findViewById(R.id.textSMaxpulse);
        avgPulse = new ArrayList<>();

        getHeartrateInfo();

        //심박수 값들



        return view;
    }
    public void getHeartrateInfo(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {

            }

            protected void onPostExecute(Boolean params) {
                if(responseStatus == 1){
                    entries = new ArrayList<>();
                    labels = new ArrayList<String>();
                    dataset = new LineDataSet(entries, "# of Calls");

                    for (int i=0; i<7;i++){
                        entries.add(new Entry(Float.valueOf(avgPulse.get(i) + ""), i));
                        int iDate = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()))-6+i;
                        if(iDate < 1){
                            iDate += 31;
                        }else if(iDate > 31){
                            iDate -= 31;
                        }
                        labels.add(iDate +"");
                    }

                    data = new LineData(labels, dataset);
                    dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
                    dataset.setDrawCubic(true);
                    dataset.setDrawFilled(true);

                    lineChart.setData(data);
                    lineChart.animateY(1000);
                    pulseAverage = 0;
                    for(int i=0;i<avgPulse.size();i++){
                        pulseAverage+=avgPulse.get(i);
                    }
                    pulseAverage=pulseAverage/avgPulse.size();

                    weekAvgHR.setText("주간 평균 심박수 : "+pulseAverage);
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
                    for (int i=0; i<7; i++){
                        obj = new URL(ConnectServer.getInstance().getURL("Receive_Avg_Heartrate_Log"));
                        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                        con.setRequestMethod("POST");
                        con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                        con.setRequestProperty("Accept", "application/json");

                        con = ConnectServer.getInstance().setHeader(con);
                        con.setDoOutput(true);

                        JSONObject jsonObj = new JSONObject();
                        if(Integer.parseInt( (today_date).substring(6,8)) -6 + i < 1 ){
                            jsonObj.put("start_of_period", Integer.parseInt(today_date) - 100 - 6 + i + 31 + "");
                        }else{
                            jsonObj.put("start_of_period", Integer.parseInt(today_date) - 6 + i + "");
                        }

                        if(Integer.parseInt( (today_date).substring(6,8)) -6 + i + 1 < 1 ){
                            jsonObj.put("end_of_period", Integer.parseInt(today_date) - 100 - 6 + i + 1 + 31 + "");
                        }else{
                            jsonObj.put("end_of_period", Integer.parseInt(today_date) - 6 + i + 1 + "");
                        }



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

                            if(c.isNull("avgHR")){
                                Log.d(TAG, "doInBackground: 123123aaaa");
                                avgPulse.add(0.0);
                            }else{
                                avgPulse.add(c.getDouble("avgHR"));
                            }

                            Log.d(TAG, "success");
                        } else {
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
