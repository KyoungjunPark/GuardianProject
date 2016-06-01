package com.example.administrator.guardian.ui.activity.Volunteer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.utils.ConnectServer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class VolunteerFragmentThreeAwardActivity extends AppCompatActivity {
    private static final String TAG = "VolunteerAcceptDialog";

    String year;
    String month;
    String day;
    String hour;
    String minute;
    int req_hour;
    String name;
    String senior_id;
    String startInfo;
    // ------------------------------
    String id;
    //------------------------------
    TextView awardWinner;
    TextView awardContent;
    TextView awardDay;

    ImageView awardSignature;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_fragment_three_award);
        getImage();
    }

    public Bitmap decodeImage(String base64){
        byte[] bImage = Base64.decode(base64, Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(bImage);
        Bitmap bitmap = BitmapFactory.decodeStream(bais);
        return bitmap;
    }
    public void getImage(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {

            @Override
            protected void onPreExecute() {

                Intent intent =getIntent();
                ////////////////////////////////////////
                id= intent.getExtras().getString("id");
                senior_id = intent.getExtras().getString("senior_id");
                startInfo = intent.getExtras().getString("startInfo");
                year = intent.getExtras().getString("year");
                month = intent.getExtras().getString("month");
                day = intent.getExtras().getString("day");
                hour = intent.getExtras().getString("hour");
                minute = intent.getExtras().getString("minute");
                req_hour = intent.getExtras().getInt("req_hour");
                name = intent.getExtras().getString("name");

                awardWinner=(TextView)findViewById(R.id.awardWinner);
                awardContent=(TextView)findViewById(R.id.awardContent);
                awardDay=(TextView)findViewById(R.id.awardDay);
                awardSignature=(ImageView)findViewById(R.id.awardSignature);
            }
            @Override
            protected void onPostExecute(Boolean params){
                awardSignature.setImageBitmap(bitmap);

                awardWinner.setText(id);//id에 해당하는 이름
                awardContent.setText(" 위 사람은 "+month+"월 "+ day+"일 "+hour+"시부터 "+ req_hour+"시간동안 "+name+" 님에게 헌신적으로 봉사하였음을 확인함.");
                awardDay.setText(year+"년 "+month+"월 "+day+"일");

            }


            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("Get_Signature"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    //con.setRequestMethod("POST");
                    //con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                    //con.setRequestProperty("Accept", "application/json");

                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    //set Request parameter
                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("date_from", startInfo);
                    jsonObj.put("senior_id", senior_id);
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

                        JSONObject object = new JSONObject(resultValues);
                        Log.d("ktk", object.toString());
                        String base64_data = object.getString("data");
                        Log.d(TAG, "doInBackground: "+base64_data);
                        bitmap = decodeImage(base64_data);

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
