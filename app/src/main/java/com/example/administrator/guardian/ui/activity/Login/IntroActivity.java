package com.example.administrator.guardian.ui.activity.Login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.ui.activity.Manager.ManagerMainActivity;
import com.example.administrator.guardian.ui.activity.Senior.SeniorTabActivity;
import com.example.administrator.guardian.ui.activity.Volunteer.VolunteerTabActivity;
import com.example.administrator.guardian.utils.ConnectServer;
import com.example.administrator.guardian.utils.GlobalVariable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;


public class IntroActivity extends Activity {

    Handler handler;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    GlobalVariable globalVariable;
    int responseStatus=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        handler = new Handler();
        handler.postDelayed(rIntent,2000);

    }
    Runnable rIntent = new Runnable(){
        @Override
        public void run(){
            globalVariable = (GlobalVariable)getApplication();
            pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
            if( pref.getString("token", "").compareTo("") != 0 ){
                tokenTest();
            }

        }
    };
    @Override
    public void onBackPressed(){
        super.onBackPressed();
        handler.removeCallbacks(rIntent);
    }

    private void getUserInfo()
    {
        //Send join data to server
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {
            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("User_Info"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con = ConnectServer.getInstance().setHeader(con);

                    con.setDoOutput(true);

                    JSONObject jsonObj = new JSONObject();
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

                        JSONObject c = jArr.getJSONObject(0);
                        globalVariable.setUser_name(c.getString("user_name"));

                    }else {
                        //Login Fail
                        rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                        String returnMessage = rd.readLine();
                        Log.d("intro","Login Fail: " + returnMessage);
                    }
                } catch(IOException | JSONException e){
                    e.printStackTrace();
                }
                return null;
            }
        });
        ConnectServer.getInstance().execute();

    }

    public void tokenTest(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {

            }
            protected void onPostExecute(Boolean params) {
                if(responseStatus == 1){
                    String user_type = pref.getString("userType","");
                    ConnectServer.getInstance().setToken(pref.getString("token",""));
                    getUserInfo();
                    Log.d("intro-test", pref.getString("token","") + " / "+user_type);
                    if(user_type.equals("senior")){
                        globalVariable.setLoginType(0);
                        Intent firstMainActivity = new Intent(getApplicationContext(), SeniorTabActivity.class);
                        startActivity(firstMainActivity);
                        finish();
                    } else if(user_type.equals("volunteer")){
                        globalVariable.setLoginType(1);
                        Intent firstMainActivity = new Intent(getApplicationContext(), VolunteerTabActivity.class);
                        startActivity(firstMainActivity);
                        finish();
                    } else if(user_type.equals("manager")){
                        globalVariable.setLoginType(2);
                        Intent firstMainActivity = new Intent(getApplicationContext(), ManagerMainActivity.class);
                        startActivity(firstMainActivity);
                        finish();
                    } else {
                        globalVariable.setLoginType(-1);
                        //error case
                    }
                }else{
                    Intent Login = new Intent(getApplicationContext(), LoginActivity.class);
                    Login.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(Login);
                    finish();
                }
            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("TokenTest"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    con.setRequestMethod("POST");
                    con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                    con.setRequestProperty("Accept", "application/json");

                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    JSONObject jsonObj = new JSONObject();
                    jsonObj.put("token",pref.getString("token", ""));
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(jsonObj.toString());

                    wr.flush();

                    BufferedReader rd =null;

                    if(con.getResponseCode() == 200){
                        responseStatus = 1;
                    } else {
                        responseStatus = 0;
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
