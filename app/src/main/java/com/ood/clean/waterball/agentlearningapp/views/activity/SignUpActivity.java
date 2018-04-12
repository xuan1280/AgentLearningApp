package com.ood.clean.waterball.agentlearningapp.views.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ood.clean.waterball.agentlearningapp.R;

public class SignUpActivity extends AppCompatActivity {
    private final static String TAG = "SignUpActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
    }

    private void init() {
        Log.d(TAG, "start to sign up");
    }
}
