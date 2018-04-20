package com.ood.clean.waterball.agentlearningapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActivitiesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.recyclerView) RecyclerView recyclerView;
    private final static String TAG = "ActivitiesFragment";
    private final static String DATA_KEY = "d1";
    private String data;

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    public static ActivitiesFragment getInstance(String data) {
        Log.d(TAG, data + " getInstance");
        ActivitiesFragment activitiesFragment = new ActivitiesFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DATA_KEY, data);
        activitiesFragment.setArguments(bundle);
        return activitiesFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data = getArguments().getString(DATA_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_activities, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        Log.d(TAG, data + " view created");
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        MyAdapter myAdapter = new MyAdapter();
        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "refresh all activities");
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        public MyAdapter() {
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.activityTitleTxt) TextView titleTxt;
            @BindView(R.id.activityCategoryTxt) TextView categoryTxt;
            @BindView(R.id.activityDateTxt) TextView dateTxt;
            @BindView(R.id.activityPreviewTxt) TextView previewTxt;
            @BindView(R.id.interestingOrNotImg) ImageButton interestedOrNotImg;
            @BindView(R.id.joinOrNotTxt) TextView joinedOrNotTxt;
            @BindView(R.id.turnToWebSiteBtn) Button turnToWebSiteBtn;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }

        @Override
        public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_activity_item, parent, false);
            return new MyAdapter.ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.d("ViewHolder: ", "第" + position + "筆資料");
            holder.titleTxt.setText("國際資訊展");
            holder.categoryTxt.setText("展覽類");
            holder.dateTxt.setText("2018/4/15");
            holder.previewTxt.setText("國際資訊展就在明天，多款電腦，歡迎參觀，快來搶便宜喔");
            holder.interestedOrNotImg.setImageResource(R.drawable.interested1);
            holder.joinedOrNotTxt.setText("未參加");
            holder.turnToWebSiteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse("https://www.google.com/");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
