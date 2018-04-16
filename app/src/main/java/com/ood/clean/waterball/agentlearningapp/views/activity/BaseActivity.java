package com.ood.clean.waterball.agentlearningapp.views.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.ActivitiesFragment;
import com.ood.clean.waterball.agentlearningapp.R;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {
    private final static String TAG = "BaseActivity";
    private List<Fragment> pages;
    private User user;
    private PagerAdapter pagerAdapter;
    private String[] options;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();

    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");
        Log.d(TAG, user.getName() + " sign in successfully.");
        initPages();
        initTabs();
    }

    private void initPages() {
        options = getResources().getStringArray(R.array.activityOptions);
        viewPager.setAdapter(pagerAdapter = new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.setOffscreenPageLimit(options.length);
    }

    private void initTabs() {
        tabLayout.setupWithViewPager(viewPager);
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            super.getPageTitle(position);
            return options[position];
        }

        @Override
        public Fragment getItem(int position) {
            String option = options[position];
            return ActivitiesFragment.getInstance(option);
        }

        @Override
        public int getCount() {
            return options.length;
        }
    }
}
