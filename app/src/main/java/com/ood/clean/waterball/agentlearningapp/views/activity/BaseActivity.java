package com.ood.clean.waterball.agentlearningapp.views.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.ood.clean.waterball.agentlearningapp.ActivitiesFragment;
import com.ood.clean.waterball.agentlearningapp.R;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final static String TAG = "BaseActivity";
    private List<Fragment> fragments = new ArrayList<>();
    private User user;
    private String[] options;
    @BindView(R.id.navigationView) NavigationView navigationView;
    @BindView(R.id.drawerlayout) DrawerLayout drawerLayout;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.tabs) TabLayout tabLayout;
    @BindView(R.id.pager) ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");
        Log.d(TAG, user.getName() + " sign in successfully.");
        options = getResources().getStringArray(R.array.activityOptions);
        for (String option: options)
            fragments.add(ActivitiesFragment.getInstance(option));
        setupViewPagerAndTabLayout();
        setupActionBarDrawerToggle();
        navigationView.setNavigationItemSelectedListener(this);

    }

    private void setupViewPagerAndTabLayout() {
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupActionBarDrawerToggle() {
        Log.d(TAG, "setupActionBarDrawerToggle");
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Log.d(TAG, "onNavigationItemSelected .. " + item.getTitle());
        item.setChecked(true);
        drawerLayout.closeDrawers();
        ActivitiesFragment.getInstance(item.getTitle().toString());
        return true;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.d(TAG, "get Page Title .. " + options[position]);
            super.getPageTitle(position);
            return options[position];
        }

        @Override
        public Fragment getItem(int position) {
            Log.d(TAG, "get Fragment .. " + position);
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return options.length;
        }
    }
}
