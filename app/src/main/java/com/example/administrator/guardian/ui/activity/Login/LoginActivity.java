package com.example.administrator.guardian.ui.activity.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.ui.activity.Manager.ManagerMainActivity;
import com.example.administrator.guardian.ui.activity.Senior.SeniorTabActivity;
import com.example.administrator.guardian.ui.activity.Volunteer.VolunteerTabActivity;
import com.example.administrator.guardian.utils.ConnectServer;
import com.example.administrator.guardian.utils.MakeUTF8Parameter;
import com.yarolegovich.lovelydialog.LovelyInfoDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private Context mContext;
    private Button loginbutton;
    private Button joinbutton;
    private EditText idEditText;
    private EditText pwEditText;

    private Handler messageHandler;
    SharedPreferences pref;

    private final int LOGIN_PERMITTED = 200;

    Button tov;//for volunteer activity test
    Button tom;//for manager activity test
    Button tos;
    public void onStop(){
        super.onStop();
        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();




        editor.putString("idEditText", idEditText.getText().toString());
        editor.putString("pwEditText", pwEditText.getText().toString());
        editor.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);

        mContext = this;
        messageHandler = new Handler(Looper.getMainLooper()) {

            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 0){
                    new LovelyInfoDialog(mContext)
                            .setTopColorRes(R.color.wallet_holo_blue_light)
                            .setIcon(R.mipmap.ic_not_interested_black_24dp)
                            //This will add Don't show again checkbox to the dialog. You can pass any ID as argument
                            .setTitle("서버와의 통신 문제")
                            .setMessage((String)msg.obj)
                            .show();
                }
            }};

        idEditText = (EditText) findViewById(R.id.idEditText);
        idEditText.setText(pref.getString("idEditText", ""));
        pwEditText = (EditText) findViewById(R.id.pwEditText);
        pwEditText.setText(pref.getString("pwEditText", ""));

        loginbutton = (Button)findViewById(R.id.loginbutton);
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent firstMainActivity = new Intent(getApplicationContext(), SeniorMainActivity.class);
                if(idEditText.getText().toString().equals("")
                        || pwEditText.getText().toString().equals("")){
                    new LovelyInfoDialog(v.getContext())
                            .setTopColorRes(R.color.wallet_holo_blue_light)
                            .setIcon(R.mipmap.ic_not_interested_black_24dp)
                            .setTitle("경고")
                            .setMessage("아이디와 비밀번호를 입력해주세요.")
                            .show();
                } else{
                    sendLoginInfoToServer();
                }

            }
        });

        joinbutton = (Button)findViewById(R.id.joinbutton);
        joinbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_joinActivity = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(Go_joinActivity);
            }
        });

        tov = (Button)findViewById(R.id.tov);
        tov.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent vtest = new Intent(getApplicationContext(), VolunteerTabActivity.class);
                startActivity(vtest);
            }
        });

        tom = (Button)findViewById(R.id.tom);
        tom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mtest = new Intent(getApplicationContext(), ManagerMainActivity.class);
                startActivity(mtest);
            }
        });
        tos = (Button)findViewById(R.id.tos);
        tos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent stest = new Intent(getApplicationContext(), SeniorTabActivity.class);
                startActivity(stest);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void sendLoginInfoToServer()
    {
        //Send join data to server
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {
            String login_id;
            String login_pw;

            @Override
            protected void onPreExecute() {
                //Collect the input data
                login_id = idEditText.getText().toString();
                login_pw = pwEditText.getText().toString();
            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("Sign_In"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                    con.setDoOutput(true);

                    //set Request parameter
                    MakeUTF8Parameter parameterMaker = new MakeUTF8Parameter();
                    parameterMaker.setParameter("login_id", login_id);
                    parameterMaker.setParameter("login_pw", login_pw);
                    Log.d("ktk", parameterMaker.getParameter());
                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(parameterMaker.getParameter());
                    wr.flush();

                    BufferedReader rd =null;

                    if(con.getResponseCode() == LOGIN_PERMITTED){
                        //Sign up Success
                        rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                        String resultValues = rd.readLine();
                        Log.d(TAG,"Login Success: " + resultValues);
                        JSONObject object = new JSONObject(resultValues);
                        Boolean login_status = (Boolean) object.get("login_status");
                        String token = (String) object.get("token");
                        String user_type = (String) object.get("user_type");
                        Log.d(TAG,"Received Data: " + login_status + "//" + token + "//" + user_type);
                        ConnectServer.getInstance().setToken(token);
                        ConnectServer.getInstance().setType(user_type);

                        if(user_type.equals("senior")){
                            Intent firstMainActivity = new Intent(getApplicationContext(), SeniorTabActivity.class);
                            startActivity(firstMainActivity);
                        } else if(user_type.equals("volunteer")){
                            Intent firstMainActivity = new Intent(getApplicationContext(), VolunteerTabActivity.class);
                            startActivity(firstMainActivity);
                        } else if(user_type.equals("manager")){
                            Intent firstMainActivity = new Intent(getApplicationContext(), ManagerMainActivity.class);
                            startActivity(firstMainActivity);
                        } else {
                            //error case
                            Log.d(TAG, "UNEXPECTED PATH!!!");
                        }
                }else {
                        //Login Fail
                        rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                        String returnMessage = rd.readLine();
                        Log.d(TAG,"Login Fail: " + returnMessage);
                        Message message = messageHandler.obtainMessage(0, returnMessage);
                        message.sendToTarget();
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
