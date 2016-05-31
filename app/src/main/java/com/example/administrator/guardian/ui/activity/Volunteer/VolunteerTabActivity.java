package com.example.administrator.guardian.ui.activity.Volunteer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Vibrator;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.guardian.R;
import com.example.administrator.guardian.ui.activity.Login.LoginActivity;

@SuppressWarnings("deprecation")
public class VolunteerTabActivity extends AppCompatActivity {

    static final int Num_Tab = 4;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    private Button notification;
    private Vibrator mVibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.svtoolbar);
        setSupportActionBar(toolbar);

        notification=(Button)findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Toast.makeText(getApplicationContext(),"a",Toast.LENGTH_LONG).show();
                NotificationSomethins1();
                mVibe = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                mVibe.vibrate(1000);
            }
        });
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.svcontainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.svtabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        super.onCreateOptionsMenu(menu);
        MenuItem item = menu.add(0, 1, 0, "logout");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Intent logout = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(logout);
                finish();
                return true;
        }
        return false;
    }

    public void NotificationSomethins1() {

        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Resources res = getResources();

        Intent notificationIntent = new Intent(this, VolunteerTabActivity.class);
        notificationIntent.putExtra("notificationId", 9999); //전달할 값
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("의심알림")
                .setContentText("주변 노인분을 방문해주세요!!")
                .setTicker("주변 노인분을 방문해주세요!!!!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.icon))
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setWhen(System.currentTimeMillis())
                .setDefaults( Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS)
                .setNumber(13);

        Notification n = builder.build();
        nm.notify(1234, n);
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
                    return new VolunteerFragmentOneActivity(mContext);
                case 1:
                    return new VolunteerFragmentTwoActivity(mContext);
                case 2:
                    return new VolunteerFragmentThreeActivity(mContext);
                case 3:
                    return new VolunteerFragmentFourActivity(mContext);
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
                    return mContext.getString(R.string.v_title_section1);
                case 1:
                    return mContext.getString(R.string.v_title_section2);
                case 2:
                    return mContext.getString(R.string.v_title_section3);
                case 3:
                    return mContext.getString(R.string.v_title_section4);
            }
            return null;
        }
    }
}
