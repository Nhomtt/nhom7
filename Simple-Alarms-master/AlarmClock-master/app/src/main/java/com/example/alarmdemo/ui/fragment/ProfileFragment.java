package com.example.alarmdemo.ui.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alarmdemo.R;
import com.example.alarmdemo.databinding.FragmentProfileBinding;
import com.example.alarmdemo.model.Group;
import com.example.alarmdemo.ui.MainActivity;
import com.example.alarmdemo.ui.adapter.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private MainActivity mActivity;
    private FragmentProfileBinding mViewDataBinding;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        return mViewDataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mActivity.setSupportActionBar(mViewDataBinding.toolbar);
        mViewDataBinding.toolbar.setTitle("Profile");


        List<Group> list = new ArrayList<>();
        list.add(new Group("Trần  Đức  Thịnh", "1451TT0262",R.drawable.ic_avatar));
        list.add(new Group("Nguyễn Thị Minh Châu", "1451TT0268",R.drawable.ic_avatar));
        list.add(new Group("Võ  Nhật Thiện  Minh", "1451TT2015",R.drawable.ic_avatar));
        list.add(new Group("Nguyễn Thành Tuấn", "1451TT1719",R.drawable.ic_avatar));
        list.add(new Group("Huỳnh  Tấn  Hòa", "1451TT1908",R.drawable.ic_avatar));
        GroupAdapter adapter = new GroupAdapter(list, mActivity);
        mViewDataBinding.recyclerName.setLayoutManager(new LinearLayoutManager(mActivity, RecyclerView.VERTICAL, false));
        mViewDataBinding.recyclerName.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL));
        mViewDataBinding.recyclerName.setAdapter(adapter);
    }
}
