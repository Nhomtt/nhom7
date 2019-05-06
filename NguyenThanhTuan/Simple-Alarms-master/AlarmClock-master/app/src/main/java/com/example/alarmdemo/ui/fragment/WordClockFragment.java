package com.example.alarmdemo.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import com.example.alarmdemo.DateAndTime;
import com.example.alarmdemo.R;
import com.example.alarmdemo.databinding.FragmentWordClockBinding;
import com.example.alarmdemo.ui.MainActivity;

public class WordClockFragment extends Fragment {

    private FragmentWordClockBinding mViewDataBinding;
    private MainActivity mActivity;
    private Thread threadCurrentTime;
    private boolean timeRunning = true;
    private static final int UPDATE_CURRET_TIME = 1;

    public WordClockFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_word_clock, container, false);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mActivity.setSupportActionBar(mViewDataBinding.toolbar);
        mViewDataBinding.toolbar.setTitle("Word Clock");
        mViewDataBinding.txtCurrentTime.setTypeface(Typeface.createFromAsset(mActivity.getAssets(),
                "fonts/DigitalDismay.otf"));
        mViewDataBinding.txtSecond.setTypeface(Typeface.createFromAsset(mActivity.getAssets(),
                "fonts/DigitalDismay.otf"));

        threadCurrentTime = new Thread(runCurrentTime);
        threadCurrentTime.start();
    }


    private Runnable runCurrentTime = new Runnable() {
        @Override
        public void run() {
            while (timeRunning) {
                handler.sendEmptyMessage(UPDATE_CURRET_TIME);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == UPDATE_CURRET_TIME) {
                mViewDataBinding.txtAMPM.setText(DateAndTime.getAMPM());
                mViewDataBinding.txtCurrentTime.setText(DateAndTime.getTime());
                mViewDataBinding.txtSecond.setText(DateAndTime.getSecond());
                mViewDataBinding.txtCurrentDate.setText(DateAndTime.getDate());
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        timeRunning = false;
    }
}

