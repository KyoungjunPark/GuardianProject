package com.example.administrator.guardian.ui.activity.Login;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.utils.ConnectServer;
import com.example.administrator.guardian.utils.MakeUTF8Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class JoinContentsActivity extends AppCompatActivity {

    private static final String TAG = "JoinContentsActivity";
    private static final int LOAD_GOOGLE_MAP = 1000;

    public static final int JOIN_PERMITTED = 200;
    public static final int JOIN_DENIED = 404;

    private Button toMap;
    private String type;   // senior?? or volunteer??
    private Button seniorJoinButton;
    private Button birth;

    private EditText my_id;
    private EditText my_name;
    private EditText my_pw;
    private EditText my_pn1;
    private EditText my_pn2;
    private EditText my_pn3;
    private RadioButton radioButton_man;
    private RadioButton radioButton_woman;


    private String address;
    private String latitude;
    private String longitude;
    private boolean isMan;
    //must be modified
    private String age = "66";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joincontents);

        toMap=(Button)findViewById(R.id.tomap);
        birth=(Button)findViewById(R.id.birth);
        seniorJoinButton = (Button) findViewById(R.id.seniorjoinbutton);
        my_id = (EditText) findViewById(R.id.my_id);
        my_name = (EditText) findViewById(R.id.my_name);
        my_pw = (EditText) findViewById(R.id.my_pw);
        my_pn1 = (EditText) findViewById(R.id.my_pn1);
        my_pn2 = (EditText) findViewById(R.id.my_pn2);
        my_pn3 = (EditText)findViewById(R.id.my_pn3);
        radioButton_man = (RadioButton) findViewById(R.id.radioButton);
        radioButton_woman = (RadioButton) findViewById(R.id.radioButton2);

        my_pn1.setNextFocusDownId(R.id.my_pn2);
        my_pn2.setNextFocusDownId(R.id.my_pn3);

        Intent intent = getIntent();
        type = intent.getAction();

        birth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogDatePicker();
            }
        });

        toMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Go_Map = new Intent(getApplicationContext(), MapsActivity.class);
                startActivityForResult(Go_Map,LOAD_GOOGLE_MAP);
            }
        });

        seniorJoinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent Go_loginActivity = new Intent(getApplicationContext(), LoginActivity.class);
                //startActivity(Go_loginActivity);
                //finish();

                //Send join data to server
                ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {
                    String login_id;
                    String login_pw;
                    String user_type;
                    String user_name;
                    String user_gender;
                    String user_tel;

                    @Override
                    protected void onPreExecute() {
                        //Collect the input data
                        login_id = my_id.getText().toString();
                        login_pw = my_pw.getText().toString();
                        user_type = getIntent().getStringExtra("type");
                        user_name = my_name.getText().toString();
                        isMan = radioButton_man.isChecked();
                        user_gender = isMan ? "man" : "woman";
                        user_tel = my_pn1.getText().toString() + my_pn2.getText().toString() + my_pn3.getText().toString();

                        //must be modified
                        address = "This is test Address";
                        latitude = "31.232323";
                        longitude = "132.232312";
                    }

                    @Override
                    protected Boolean doInBackground(String... params) {
                        URL obj = null;
                        try {
                            obj = new URL(ConnectServer.getInstance().getURL("Sign_Up"));
                            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                            con.setDoOutput(true);

                            //set Request parameter
                            MakeUTF8Parameter parameterMaker = new MakeUTF8Parameter();
                            parameterMaker.setParameter("login_id", login_id);
                            parameterMaker.setParameter("login_pw", login_pw);
                            parameterMaker.setParameter("user_type", user_type);
                            parameterMaker.setParameter("user_name", user_name);
                            parameterMaker.setParameter("user_age", age);
                            parameterMaker.setParameter("user_gender", user_gender);
                            parameterMaker.setParameter("user_address", address);
                            parameterMaker.setParameter("user_tel", user_tel);
                            parameterMaker.setParameter("latitude", latitude);
                            parameterMaker.setParameter("longitude", longitude);

                            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                            wr.write(parameterMaker.getParameter());
                            wr.flush();

                            BufferedReader rd =null;

                            if(con.getResponseCode() == JOIN_PERMITTED){
                                //Sign up Success
                                rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                                Log.d(TAG,"Sign up Success: " + rd.readLine());

                            } else {
                                //Sign up Fail
                                rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                                Log.d(TAG,"Sign up Fail: " + rd.readLine());
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                });
                ConnectServer.getInstance().execute();

            }
        });
    }

    private void DialogDatePicker(){
        Calendar c = Calendar.getInstance();
        int cyear = c.get(Calendar.YEAR);
        int cmonth = c.get(Calendar.MONTH);
        int cday = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog.OnDateSetListener mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    // onDateSet method
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        birth.setText("   "+year+" 년"+"    "+monthOfYear+" 월"+"   "+dayOfMonth+" 일");
                        long now = System.currentTimeMillis();
                        Date date = new Date(now);
                        SimpleDateFormat CurYearFormat = new SimpleDateFormat("yyyy");
                        String nowyear = CurYearFormat.format(date);
                        int numyear = Integer.parseInt(nowyear);
                        int intAge=numyear-year+1;          //나이 int값
                        age=String.valueOf(intAge);
                    }
                };
        DatePickerDialog alert = new DatePickerDialog(this,  mDateSetListener, cyear, cmonth, cday);
        alert.setTitle("생년월일 입력");
        alert.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK)
        {
            if(requestCode==LOAD_GOOGLE_MAP)
            {
                toMap.setText(data.getStringExtra("Address"));
                address = data.getStringExtra("Address");
                latitude = data.getStringExtra("latitude");
                longitude = data.getStringExtra("longitude");
            }
        } else{
            //When resultCode is false

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_registration, menu);
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
}
