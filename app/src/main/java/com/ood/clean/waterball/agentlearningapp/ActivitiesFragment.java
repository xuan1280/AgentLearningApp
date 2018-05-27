package com.ood.clean.waterball.agentlearningapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.ActivityRetrofitRepository;
import com.ood.clean.waterball.agentlearningapp.presenter.ActivityPresenter;
import com.ood.clean.waterball.agentlearningapp.views.base.ActivitiesRefreshView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActivitiesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, ActivitiesRefreshView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private final static String TAG = "ActivitiesFragment";
    private final static String DATA_KEY = "d1";
    private String data;
    private ActivityPresenter activityPresenter = new ActivityPresenter(new ActivityRetrofitRepository(), this);

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
        //TODO 優化
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        swipeRefreshLayout = view.findViewById(R.id.joinedActivitiesSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        activityPresenter.getRecentActivities(null, 0, 20, null, null, null);
        Log.d(TAG, data + " view created");
    }

    private void setupRecyclerView(List<Activity> activities) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        MyAdapter myAdapter = new MyAdapter(activities);
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

    @Override
    public void onActivitiesRefreshSuccessfully(List<Activity> activities) {
        setupRecyclerView(activities);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Activity> activities;

        public MyAdapter(List<Activity> activities) {
            this.activities = activities;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            @BindView(R.id.activityTitleTxt)
            TextView titleTxt;
            @BindView(R.id.activityCategoryTxt)
            TextView categoryTxt;
            @BindView(R.id.activityDateTxt)
            TextView dateTxt;
            @BindView(R.id.activityPreviewTxt)
            TextView previewTxt;
            @BindView(R.id.interestingOrNotImg)
            ImageButton interestedOrNotImg;
            @BindView(R.id.joinOrNotTxt)
            TextView joinedOrNotTxt;
            @BindView(R.id.turnToWebSiteBtn)
            Button turnToWebSiteBtn;

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

        //TODO imgBtn change
        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Log.d("ViewHolder: ", "第" + position + "筆資料");
            Activity activity = activities.get(position);
            holder.titleTxt.setText(activity.getTitle());
            holder.categoryTxt.setText(activity.getTags());
            holder.dateTxt.setText(activity.getStartDate().substring(0, 10) + " ~ " + activity.getEndDate().substring(0, 10));
            holder.previewTxt.setText(activity.getContent());
            holder.interestedOrNotImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageButton imageButton = view.findViewById(R.id.interestingOrNotImg);
                    imageButton.setImageResource(R.drawable.interested);
                }
            });
            holder.joinedOrNotTxt.setText("未參加");
            holder.turnToWebSiteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri uri = Uri.parse(activity.getLink());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return activities.size();
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
