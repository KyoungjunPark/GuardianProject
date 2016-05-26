package com.example.administrator.guardian.ui.activity.Manager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.utils.ConnectServer;
import com.example.administrator.guardian.utils.MakeUTF8Parameter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("deprecation")
public class ManagerSeniorInfoTabActivity extends AppCompatActivity {
    private static final String TAG = "ManagerTabActivity";
    static final int Num_Tab = 4;

    private String senior_id;
    private String senior_name;
    private String senior_birthdate;
    private String senior_gender;
    private String senior_address;
    private String senior_tel;
    private String latitude;
    private String longitude;
    private int high_zone_2;
    private int high_zone_1;
    private int low_zone_1;

    private TabLayout tabLayout;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_senior_info_tab);


        Intent intent = getIntent();
        senior_id = intent.getExtras().getString("senior_id");
        Log.d("ktk", senior_id +"");

        Toolbar toolbar = (Toolbar) findViewById(R.id.mstoolbar);
        setSupportActionBar(toolbar);

        getSeniorInfo();


    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        Context mContext;

        public SectionsPagerAdapter(Context context, FragmentManager fm) {
            super(fm);
            mContext=context;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new ManagerManageInfoActivity(mContext,senior_id, senior_name, senior_birthdate, senior_gender, senior_address, senior_tel, latitude, longitude);
                case 1:
                    return new ManagerManageActiveActivity(mContext, senior_id);
                case 2:
                    return new ManagerManagePulseInfoActivity(mContext, senior_id);
                case 3:
                    return new ManagerManagePulseActivity(mContext, senior_id, high_zone_2, high_zone_1, low_zone_1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 4 total pages.
            return Num_Tab;
        }

        @SuppressWarnings("DefaultLocale")
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return mContext.getString(R.string.mstitle_section1);
                case 1:
                    return mContext.getString(R.string.mstitle_section2);
                case 2:
                    return mContext.getString(R.string.mstitle_section3);
                case 3:
                    return mContext.getString(R.string.mstitle_section4);
            }
            return null;
        }
    }

    public void getSeniorInfo(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {
            @Override
            protected void onPreExecute() {
                mViewPager = (ViewPager) findViewById(R.id.mscontainer);
                tabLayout = (TabLayout) findViewById(R.id.mstabs);
            }
            protected void onPostExecute(Boolean params) {
                // Create the adapter that will return a fragment for each of the three
                // primary sections of the activity.

                mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());

                // Set up the ViewPager with the sections adapter.
                mViewPager.setAdapter(mSectionsPagerAdapter);
                tabLayout.setupWithViewPager(mViewPager);
            }
            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("Senior_Info"));
                    HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                    con = ConnectServer.getInstance().setHeader(con);
                    con.setDoOutput(true);

                    //set Request parameter
                    MakeUTF8Parameter parameterMaker = new MakeUTF8Parameter();
                    parameterMaker.setParameter("senior_id", senior_id);

                    OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
                    wr.write(parameterMaker.getParameter());
                    wr.flush();

                    BufferedReader rd =null;

                    if(con.getResponseCode() == 200){
                        //Sign up Success
                        rd = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
                        String resultValues = rd.readLine();
                        Log.d(TAG,"Connect Success: " + resultValues);
                        JSONObject object = new JSONObject(resultValues);
                        JSONObject dataObj = (JSONObject)object.get("data");

                        senior_name= (String)dataObj.get("user_name");
                        senior_birthdate=(String)dataObj.get("user_birthdate");
                        senior_gender=(String)dataObj.get("user_gender");
                        senior_address=(String)dataObj.get("user_address");
                        senior_tel=(String)dataObj.get("user_tel");
                        high_zone_2=(int)dataObj.get("high_zone_2");
                        high_zone_1=(int)dataObj.get("high_zone_1");
                        low_zone_1=(int)dataObj.get("low_zone_1");
                        latitude = (String)dataObj.get("latitude");
                        longitude = (String)dataObj.get("longitude");
                    } else {
                        //Sign up Fail
                        rd = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
                        Log.d("ManagerTab","fail/" + senior_id);
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
