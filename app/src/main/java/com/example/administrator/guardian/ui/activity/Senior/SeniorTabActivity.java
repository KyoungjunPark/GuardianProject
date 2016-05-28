package com.example.administrator.guardian.ui.activity.Senior;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.design.widget.TabLayout;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.guardian.R;

@SuppressWarnings("deprecation")
public class SeniorTabActivity extends AppCompatActivity {

    static final int Num_Tab = 4;

    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */

    private ViewPager mViewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.stoolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.

        // Set up the ViewPager with the sections adapter.

        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
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
                    return new SeniorFragmentOneActivity(mContext);
                case 1:
                    return new SeniorFragmentTwoActivity(mContext);
                case 2:
                    return new SeniorFragmentThreeActivity(mContext);
                case 3:
                    return new SeniorFragmentFourActivity(mContext);
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
}