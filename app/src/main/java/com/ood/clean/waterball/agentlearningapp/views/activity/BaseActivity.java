package com.ood.clean.waterball.agentlearningapp.views.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.R;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;

public class BaseActivity extends AppCompatActivity {
    private final static String TAG = "BaseActivity";
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        init();
    }

    private void init() {
        user = (User) getIntent().getSerializableExtra("user");
        Log.d(TAG, user.getName() + " sign in successfully.");
    }

}
