package com.ood.clean.waterball.agentlearningapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.container) FrameLayout sadasdsda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TestFragment testFragment = TestFragment.getInstance("HAHA");
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, testFragment, "test")
                .addToBackStack("test")
                .commit();
    }
}
