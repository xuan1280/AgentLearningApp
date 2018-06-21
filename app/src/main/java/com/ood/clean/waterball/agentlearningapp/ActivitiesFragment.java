package com.ood.clean.waterball.agentlearningapp;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ood.clean.waterball.agentlearningapp.Animation.TargetHeightAnimation;
import com.ood.clean.waterball.agentlearningapp.modles.entities.Activity;
import com.ood.clean.waterball.agentlearningapp.modles.entities.UserAssociation;
import com.ood.clean.waterball.agentlearningapp.modles.entities.DataKey;
import com.ood.clean.waterball.agentlearningapp.modles.entities.User;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.ActivityRetrofitRepository;
import com.ood.clean.waterball.agentlearningapp.modles.repositories.UserRetrofitRepository;
import com.ood.clean.waterball.agentlearningapp.presenter.ActivityFragmentPresenter;
import com.ood.clean.waterball.agentlearningapp.utils.Action;
import com.ood.clean.waterball.agentlearningapp.views.base.UsersAndActivityView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ActivitiesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, UsersAndActivityView {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fragmentWaitingPB)
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;
    private final static String TAG = "ActivitiesFragment";
    private final static String USER_KEY = "user_key";
    private List<Activity> activities = new ArrayList<>();
    private MyAdapter myAdapter;
    private String data;
    private DataKey dataKey;
    private User user;
    private int positionByUserStay = 0;
    private LinearLayoutManager layoutManager;
    private static HashMap<Integer, UserAssociation> associationsMap = new HashMap<>();
    private static HashMap<DataKey, Action> activityFragmentAction = new HashMap<>();
    private ActivityFragmentPresenter activityFragmentPresenter = new ActivityFragmentPresenter(new ActivityRetrofitRepository(), new UserRetrofitRepository(), this);

    public ActivitiesFragment() {
        // Required empty public constructor
    }


    public static ActivitiesFragment getInstance(String data, int index, User user) {
        Log.d(TAG, data + " getInstance");
        ActivitiesFragment activitiesFragment = new ActivitiesFragment();
        Bundle bundle = new Bundle();
        String bundleKey = DataKey.values()[index].toString();
        bundle.putString(bundleKey, data);
        bundle.putInt("position", index);
        bundle.putInt("userId", user.getId());
        bundle.putSerializable(USER_KEY, user);
        activitiesFragment.setArguments(bundle);
        return activitiesFragment;
    }

    public static void createActivityFragmentAction(int userId) {
        if (activityFragmentAction.isEmpty()) {
            activityFragmentAction.put(DataKey.JoinActivities, (activityFragmentPresenter) -> activityFragmentPresenter.getUserRelatedActivities(userId, "join"));
            activityFragmentAction.put(DataKey.NewestData, (activityFragmentPresenter) -> activityFragmentPresenter.getRecentActivities(userId, 0, 20));
            activityFragmentAction.put(DataKey.UserPreferencesData, (activityFragmentPresenter) -> activityFragmentPresenter.getUserRelatedActivities(userId, "prefs"));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int keyId = getArguments().getInt("position");
        user = (User) getArguments().getSerializable(USER_KEY);
        assert user != null;
        dataKey = DataKey.values()[keyId];
        data = getArguments().getString(dataKey.toString());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_activities, container, false);
        swipeRefreshLayout = view.findViewById(R.id.joinedActivitiesSwipeRefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        Log.d(TAG, data + " view created");
        activityFragmentAction.get(dataKey).invoke(activityFragmentPresenter);
        activities.clear();
        setupRecyclerView(activities);
        progressBar.setVisibility(View.VISIBLE);
        //Todo three tab should refresh the activity view right now
    }

    private void setupRecyclerView(List<Activity> activities) {
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter(activities, activityFragmentPresenter, user);
        recyclerView.setAdapter(myAdapter);
        if (dataKey == DataKey.NewestData)
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    super.onScrollStateChanged(recyclerView, newState);
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();
                        if (lastVisiblePosition >= layoutManager.getItemCount() - 1) {
                            progressBar.setVisibility(View.VISIBLE);
                            positionByUserStay = activities.size();
                            activityFragmentPresenter.getRecentActivities(user.getId(), activities.size(), 20);
                        }
                    }
                }
            });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onRefresh() {
        Log.d(TAG, "refresh all activities");
        swipeRefreshLayout.setRefreshing(true);
        activityFragmentAction.get(dataKey).invoke(activityFragmentPresenter);
    }

    @Override
    public void onPerformOrCancelActionSuccessfully() {
        Toast.makeText(this.getContext(), "action successfully!", Toast.LENGTH_SHORT).show();
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onPerformOrCancelTargetNotFound() {
        Toast.makeText(this.getContext(), "action target not found!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPerformOrCancelActionInvalid() {
        Toast.makeText(this.getContext(), "action invalid!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetAssociationBetweenUserAndActivityTargetNotFound() {
        Toast.makeText(this.getContext(), "association target not found!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetAssociationSuccessfully(UserAssociation data) {
        associationsMap.put(data.getActivityId(), data);
        for(int i = 0; i < activities.size(); i++)
            if (activities.get(i).getId() == data.getActivityId())
                myAdapter.notifyItemChanged(i);
//        myAdapter.notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onGetUserRelatedActivitiesSuccessfully(List<Activity> datas) {
        activities.clear();
        activities.addAll(datas);
        myAdapter.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetUserRelatedActivitiesTargetNotFound() {
    }

    @Override
    public void onGetUserRelatedActivitiesActionInvalid() {

    }

    @Override
    public void onPushUserPreferencesSuccessfully() {

    }

    @Override
    public void onPushUserPreferencesTargetNotFound() {

    }

    @Override
    public void onPushUserPreferencesBrowsingTimeAndClickTimeInvalid() {

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onActivitiesRefreshSuccessfully(List<Activity> respondActivities) {
        addActivitiesToRecyclerView(respondActivities);
        setupRecyclerView(activities);
        progressBar.setVisibility(View.GONE);
        swipeRefreshLayout.setRefreshing(false);
        myAdapter.notifyDataSetChanged();
        recyclerView.scrollToPosition(positionByUserStay == 0 ? 0 : positionByUserStay - 2);
    }

    private void addActivitiesToRecyclerView(List<Activity> respondActivities) {
        for (Activity activity : respondActivities)
            if (!activities.contains(activity))
                activities.add(activity);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private List<Activity> activities;
        private ActivityFragmentPresenter activityFragmentPresenter;
        private User user;

        MyAdapter(List<Activity> activities, ActivityFragmentPresenter activityFragmentPresenter, User user) {
            this.activities = activities;
            this.activityFragmentPresenter = activityFragmentPresenter;
            this.user = user;
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
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
            @BindView(R.id.joinOrNotCKB)
            CheckBox joinedOrNotCKB;
            @BindView(R.id.interestingWaitingProgressbar)
            ProgressBar interestingWaitingProgressBar;
            private EventHandler eventHandler = new EventHandler();

            ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                itemView.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                eventHandler.cardViewOnClick(v);
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
            Activity activity = activities.get(position);
            User user = this.user;

            holder.titleTxt.setText(activity.getTitle());
            holder.categoryTxt.setText("");
            holder.dateTxt.setText(activity.getStartDate() == null ? "" : activity.getStartDate().substring(0, 10) + " ~ " + activity.getEndDate().substring(0, 10));
            holder.previewTxt.setText(activity.getContent().trim());
            holder.turnToWebSiteBtn.setText(activity.getSource());
            holder.turnToWebSiteBtn.setOnClickListener(view -> turnToWebsiteByClickBtn(activity));
            if (associationsMap.containsKey(activity.getId())) {
                UserAssociation associationMap = associationsMap.get(activity.getId());
                holder.joinedOrNotCKB.setChecked(associationMap.isJoin());
                holder.joinedOrNotTxt.setText(associationMap.isJoin() ? getString(R.string.joined) : getString(R.string.notJoined));
                holder.interestingWaitingProgressBar.setVisibility(View.GONE);
                holder.interestedOrNotImg.setVisibility(View.VISIBLE);
                holder.joinedOrNotCKB.setVisibility(View.VISIBLE);
                holder.joinedOrNotTxt.setVisibility(View.VISIBLE);
                holder.interestedOrNotImg.setOnClickListener(view -> pushPerformOrCancelActionOnActivityByClickInterestedOrNotImgBtn(view, associationMap, user, activity));
                holder.joinedOrNotCKB.setOnClickListener(view -> createDialogForJoinOrCancelTheActivity(holder, associationMap, user, activity));
                holder.interestedOrNotImg.setImageResource(associationMap.isLike() ? R.drawable.interested : R.drawable.not_interested);
                holder.interestingWaitingProgressBar.setVisibility(View.GONE);
            } else {
                holder.joinedOrNotTxt.setText(getString(R.string.notJoined));
                holder.interestedOrNotImg.setVisibility(View.INVISIBLE);
                holder.joinedOrNotCKB.setVisibility(View.INVISIBLE);
                holder.joinedOrNotTxt.setVisibility(View.INVISIBLE);
                holder.interestingWaitingProgressBar.setVisibility(View.VISIBLE);
                holder.joinedOrNotCKB.setOnClickListener(null);
                holder.interestedOrNotImg.setOnClickListener(null);
            }
        }

        private void turnToWebsiteByClickBtn(Activity activity) {
            Uri uri = Uri.parse(activity.getLink());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        private void pushPerformOrCancelActionOnActivityByClickInterestedOrNotImgBtn(View view, UserAssociation associationMap, User user, Activity activity) {
            ImageButton imageButton = view.findViewById(R.id.interestingOrNotImg);
            activityFragmentPresenter.performOrCancelActionOnActivity(user.getId(), activity.getId(), "like", !associationMap.isLike());
            imageButton.setImageResource(associationMap.isLike() ? R.drawable.not_interested : R.drawable.interested);
            associationMap.setLike(!associationMap.isLike());
        }

        private void createDialogForJoinOrCancelTheActivity(ViewHolder holder, UserAssociation associationMap, User user, Activity activity) {
            new AlertDialog.Builder(getContext())
                    .setMessage(associationMap.isJoin() ? getString(R.string.sureAboutCancelThisActivityAction) : getString(R.string.sureAboutJoinThisActivityAction))
                    .setPositiveButton("是", (dialog, which) -> {
                        activityFragmentPresenter.performOrCancelActionOnActivity(user.getId(), activity.getId(), "join", !associationMap.isJoin());
//                        holder.joinedOrNotTxt.setText(!associationMap.isJoin() ? R.string.joined : R.string.notJoined);
//                        holder.joinedOrNotCKB.setChecked(!associationMap.isJoin());
                        if (dataKey == DataKey.JoinActivities) {
                            activities.remove(activity);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("取消", (dialog, which) -> {
                        dialog.dismiss();
                        holder.joinedOrNotCKB.setChecked(associationMap.isJoin());
                    })
                    .show();
        }

        @Override
        public int getItemCount() {
            return activities.size();
        }

        public class EventHandler {

            public void cardViewOnClick(View view) {
                TextView contentTxt = view.findViewById(R.id.activityPreviewTxt);
                scaleCardViewToFitWholeContent(contentTxt);
            }

            private void scaleCardViewToFitWholeContent(final TextView contentTxt) {
                final int startHeight = contentTxt.getHeight();
                contentTxt.setMaxLines(Integer.MAX_VALUE); //show whole content

                int widthSpec = View.MeasureSpec.makeMeasureSpec(contentTxt.getWidth(), View.MeasureSpec.EXACTLY);
                int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
                contentTxt.measure(widthSpec, heightSpec);

                int targetHeight = contentTxt.getMeasuredHeight();

                Animation animation = new TargetHeightAnimation(contentTxt, startHeight, targetHeight, true);
                animation.setDuration(600);
                contentTxt.startAnimation(animation);
            }
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
