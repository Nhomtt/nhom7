package com.example.alarmdemo.ui.fragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.alarmdemo.AlarmModel;
import com.example.alarmdemo.R;
import com.example.alarmdemo.databinding.FragmentAlarmBinding;
import com.example.alarmdemo.ui.MainActivity;
import com.example.alarmdemo.ui.adapter.AlarmListAdapterV2;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlarmFragment extends Fragment {

    private FragmentAlarmBinding mViewDataBinding;
    private MainActivity mActivity;
    private List<AlarmModel> alarmModels;
    private AlarmListAdapterV2 alarmListAdapter;

    public AlarmFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            alarmModels = (List<AlarmModel>) getArguments().getSerializable("AlarmModel");
            Log.e(">>>>>: ", alarmModels.toString());
        }

//        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, container, false);
        return mViewDataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mActivity.setSupportActionBar(mViewDataBinding.toolbar);
        mViewDataBinding.toolbar.setTitle("Alarm");

        setupRecyclerView();

        mViewDataBinding.swipe.setOnRefreshListener(() -> {
            alarmModels.clear();
            alarmListAdapter.notifyDataSetChanged();
            new Handler().postDelayed(() -> {
                mActivity.onRefresh();
                mViewDataBinding.swipe.setRefreshing(false);
            }, 100);

        });

        mViewDataBinding.fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.startAlarmDetailsActivity(-1);
            }
        });
    }


    private void setupRecyclerView() {
        if (alarmModels.isEmpty()) {
            alarmModels = new ArrayList<>();
        }
        alarmListAdapter = new AlarmListAdapterV2(mActivity, alarmModels);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL);
        mViewDataBinding.recyclerTime.addItemDecoration(itemDecoration);
        mViewDataBinding.recyclerTime.setLayoutManager(layoutManager);
        mViewDataBinding.recyclerTime.setAdapter(alarmListAdapter);
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.alarm_list, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId() == R.id.action_add_new_alrm) {
//            mActivity.startAlarmDetailsActivity(-1);
//        }
//        return true;
//    }

    public void setAlarms(List<AlarmModel> alarms) {

        alarmListAdapter.setAlarms(alarms);
    }
}
