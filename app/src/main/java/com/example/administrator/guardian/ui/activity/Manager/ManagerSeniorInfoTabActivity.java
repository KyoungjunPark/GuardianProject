package com.example.administrator.guardian.ui.activity.Manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.administrator.guardian.R;

@SuppressWarnings("deprecation")
public class ManagerSeniorInfoTabActivity extends AppCompatActivity {


    static final int Num_Tab = 3;

    private String senior_id;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.mstoolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.mscontainer);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.mstabs);
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
                    return new ManagerManageInfoActivity(mContext,senior_id);
                case 1:
                    return new ManagerManagePulseInfoActivity(mContext);
                case 2:
                    return new ManagerManagePulseActivity(mContext);
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
                    return mContext.getString(R.string.mstitle_section1);
                case 1:
                    return "심박그래프";
                case 2:
                    return mContext.getString(R.string.mstitle_section2);
            }
            return null;
        }
    }



}
