package com.ood.clean.waterball.agentlearningapp.views.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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
        pages = new ArrayList<>();
        pages.add(ActivitiesFragment.getInstance(getString(R.string.activityParticipated)));
        pages.add(ActivitiesFragment.getInstance(getString(R.string.latestNews)));
        pages.add(ActivitiesFragment.getInstance(getString(R.string.recommendedNews)));
        viewPager.setAdapter(pagerAdapter = new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    private void initTabs() {
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.activityParticipated)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.latestNews)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.recommendedNews)));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm){
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return pages.get(position);
        }

        @Override
        public int getCount() {
            return pages.size();
        }
    }
}
