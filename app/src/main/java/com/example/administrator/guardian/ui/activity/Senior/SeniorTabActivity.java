package com.example.administrator.guardian.ui.activity.Senior;

import android.content.Context;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("deprecation")
public class SeniorTabActivity extends AppCompatActivity {
    private static final String TAG = "SeniorTabActivity";

    static final int Num_Tab = 4;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    private ViewPager mViewPager;
    private int high_zone_2, low_zone_1;
    Toolbar toolbar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_tab);
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
                    return new SeniorFragmentOneActivity(mContext, high_zone_2, low_zone_1);
                case 1:
                    return new SeniorFragmentTwoActivity(mContext);
                case 2:
                    return new SeniorFragmentThreeActivity(mContext);
                case 3:
                    return new SeniorFragmentFourActivity(mContext, high_zone_2, low_zone_1);
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return Num_Tab;
        }

        @SuppressWarnings("DefaultLocale")
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return mContext.getString(R.string.title_section1);
                case 1:
                    return mContext.getString(R.string.title_section2);
                case 2:
                    return mContext.getString(R.string.title_section3);
                case 3:
                    return mContext.getString(R.string.title_section4);
            }
            return null;
        }
    }

    public void getSeniorInfo(){
        ConnectServer.getInstance().setAsncTask(new AsyncTask<String, Void, Boolean>() {


            @Override
            protected void onPreExecute() {

            }
            protected void onPostExecute(Boolean params) {
                toolbar = (Toolbar) findViewById(R.id.stoolbar);
                setSupportActionBar(toolbar);

                mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());
                mViewPager = (ViewPager) findViewById(R.id.container);
                mViewPager.setAdapter(mSectionsPagerAdapter);

                TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(mViewPager);
            }

            @Override
            protected Boolean doInBackground(String... params) {
                URL obj = null;
                try {
                    obj = new URL(ConnectServer.getInstance().getURL("Senior_Info"));
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
                        Log.d(TAG, "success: "+object.toString());
                        JSONArray jArr  = object.getJSONArray("data");

                        JSONObject c = jArr.getJSONObject(0);
                        //senior_name = c.getString("user_name");
                        //senior_birthdate= c.getString("user_birthdate");
                        //senior_gender= c.getString("user_gender");
                        //senior_address= c.getString("user_address");
                        //senior_tel= c.getString("user_tel");
                        //latitude= c.getString("latitude");
                        //longitude= c.getString("longitude");
                        high_zone_2= c.getInt("high_zone_2");
                        //high_zone_1= c.getInt("high_zone_1");
                        low_zone_1= c.getInt("low_zone_1");
                    } else {
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