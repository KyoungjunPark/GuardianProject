package com.example.administrator.guardian.ui.activity.Volunteer;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.example.administrator.guardian.R;

@SuppressWarnings("deprecation")
public class VolunteerTabActivity extends AppCompatActivity {

    static final int Num_Tab = 4;

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.svtoolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.svcontainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.svtabs);
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
