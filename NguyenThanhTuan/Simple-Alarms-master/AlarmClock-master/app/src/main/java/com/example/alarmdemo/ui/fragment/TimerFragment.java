package com.example.alarmdemo.ui.fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.alarmdemo.DateAndTime;
import com.example.alarmdemo.R;
import com.example.alarmdemo.databinding.FragmentTimerBinding;
import com.example.alarmdemo.model.InforTimeLap;
import com.example.alarmdemo.model.Sound;
import com.example.alarmdemo.ui.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimerFragment extends Fragment implements View.OnClickListener {
    private static final int RUN_SCHEDULE_TIME = 2;
    private MainActivity mActivity;
    private FragmentTimerBinding mViewDataBinding;
    private int hourSchedule, minuteSchedule, secondSchedule, hourCount = 0,
            minuteCount = 0, secondCount = 0, countClickScheduleTime = 0,
            timePercent = 0, countTimePercent = 0, progressBarStatus = 0,
            indexOfItemTime = 0;
    private long miliis = 0;
    private Sound sound;
    private boolean timeRunning = true, countingTime = false,
            schedulingTime = false;
    private Thread threadCurrentTime, threadCountTime, threadScheduleTime;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_timer, container, false);
        return mViewDataBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mActivity.setSupportActionBar(mViewDataBinding.toolbar);
        mViewDataBinding.toolbar.setTitle("Timer");
        sound = new Sound(mActivity);
        mViewDataBinding.edtHour.setTypeface(Typeface.createFromAsset(mActivity.getAssets(),
                "fonts/DigitalDismay.otf"));
        mViewDataBinding.edtMinute.setTypeface(Typeface.createFromAsset(mActivity.getAssets(),
                "fonts/DigitalDismay.otf"));
        mViewDataBinding.edtSecond.setTypeface(Typeface.createFromAsset(mActivity.getAssets(),
                "fonts/DigitalDismay.otf"));

        mViewDataBinding.btnStartScheduleTime.setOnClickListener(this);
        mViewDataBinding.btnResetScheduleTime.setOnClickListener(this);
        mViewDataBinding.btnOKScheduleTime.setOnClickListener(this);

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == RUN_SCHEDULE_TIME) {
                mViewDataBinding.edtHour.setText(hourSchedule < 10 ? "0" + hourSchedule
                        : hourSchedule + "");
                mViewDataBinding.edtMinute.setText(minuteSchedule < 10 ? "0" + minuteSchedule
                        : minuteSchedule + "");
                mViewDataBinding.edtSecond.setText(secondSchedule < 10 ? "0" + secondSchedule
                        : secondSchedule + "");
                mViewDataBinding.progressBarScheduleTime.setProgress(progressBarStatus);
                if (hourSchedule == 0 && minuteSchedule == 0
                        && secondSchedule == 0) {
                    schedulingTime = false;
                    countClickScheduleTime = 0;
                    mViewDataBinding.btnOKScheduleTime.setVisibility(RelativeLayout.VISIBLE);
                    mViewDataBinding.btnStartScheduleTime.setVisibility(RelativeLayout.GONE);
                    mViewDataBinding.btnResetScheduleTime.setVisibility(RelativeLayout.GONE);
                    sound.playSoundLoop();
                }
            }
        }
    };
    private Runnable runScheduleTime = new Runnable() {
        @Override
        public void run() {
            while (schedulingTime) {
                if (secondSchedule > 0)
                    secondSchedule--;
                if (secondSchedule == 0 && minuteSchedule > 0) {
                    secondSchedule = 59;
                    minuteSchedule--;
                }
                if (minuteSchedule == 0 && hourSchedule > 0) {
                    minuteSchedule = 59;
                    hourSchedule--;
                }
                progressBarStatus++;
                handler.sendEmptyMessage(RUN_SCHEDULE_TIME);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            // Button bat dau - bo hen gio
            case R.id.btnStartScheduleTime:
                countClickScheduleTime++;
                switch (countClickScheduleTime % 3) {
                    case 1:
                        if (!schedulingTime) {
                            schedulingTime = true;
                            hourSchedule = Integer.parseInt(mViewDataBinding.edtHour.getText()
                                    .toString());
                            minuteSchedule = Integer.parseInt(mViewDataBinding.edtMinute.getText()
                                    .toString());
                            secondSchedule = Integer.parseInt(mViewDataBinding.edtSecond.getText()
                                    .toString());

                            int totalSecond = hourSchedule * 3600 + minuteSchedule * 60
                                    + secondSchedule;
                            mViewDataBinding.progressBarScheduleTime.setMax(totalSecond);
                            mViewDataBinding.progressBarScheduleTime.setSecondaryProgress(totalSecond);
                            mViewDataBinding.progressBarScheduleTime.setProgress(0);

                            threadScheduleTime = new Thread(runScheduleTime);
                            threadScheduleTime.start();
                            changeBtnStartScheduleToStop();
                            mViewDataBinding.btnResetScheduleTime.setEnabled(true);
                            mViewDataBinding.btnResetScheduleTime
                                    .setBackgroundResource(R.drawable.background_button_reset);
                            mViewDataBinding.btnResetScheduleTime.setTextColor(Color.WHITE);
                        }
                        break;

                    case 2:
                        schedulingTime = false;
                        mViewDataBinding.btnStartScheduleTime
                                .setBackgroundResource(R.drawable.background_button_start);
                        mViewDataBinding.btnStartScheduleTime.setText("Continue");
                        mViewDataBinding.btnStartScheduleTime.setTextColor(Color.parseColor("#2962ff"));
                        break;
                    case 0:
                        schedulingTime = true;
                        threadScheduleTime = new Thread(runScheduleTime);
                        threadScheduleTime.start();
                        changeBtnStartScheduleToStop();
                        countClickScheduleTime++;
                        break;
                }
                break;

            // Button xac lap lai - bo hen gio
            case R.id.btnResetScheduleTime:
                schedulingTime = false;
                countClickScheduleTime = 0;
                hourSchedule = 0;
                minuteSchedule = 0;
                secondSchedule = 0;
                mViewDataBinding.edtHour.setText("00");
                mViewDataBinding.edtMinute.setText("00");
                mViewDataBinding.edtSecond.setText("00");
                changeStatusStart();
                break;

            // Button ok - bo hen gio
            case R.id.btnOKScheduleTime:
                sound.stopSound();
                changeStatusStart();
                break;


        }
    }

    private void changeBtnStartScheduleToStop() {
        mViewDataBinding.btnStartScheduleTime
                .setBackgroundResource(R.drawable.background_button_stop);
        mViewDataBinding.btnStartScheduleTime.setText("Stop");
        mViewDataBinding.btnStartScheduleTime.setTextColor(Color.WHITE);
    }

    private void changeStatusStart() {
        progressBarStatus = 0;
        mViewDataBinding.progressBarScheduleTime.setMax(100);
        mViewDataBinding.progressBarScheduleTime.setProgress(50);
        mViewDataBinding.progressBarScheduleTime.setSecondaryProgress(75);
        mViewDataBinding.btnStartScheduleTime.setVisibility(RelativeLayout.VISIBLE);
        mViewDataBinding.btnResetScheduleTime.setVisibility(RelativeLayout.VISIBLE);
        mViewDataBinding.btnOKScheduleTime.setVisibility(RelativeLayout.GONE);
        mViewDataBinding.btnStartScheduleTime
                .setBackgroundResource(R.drawable.background_button_start);
        mViewDataBinding.btnStartScheduleTime.setText("Start");
        mViewDataBinding.btnStartScheduleTime.setTextColor(Color.parseColor("#2962ff"));
        mViewDataBinding.btnResetScheduleTime.setEnabled(false);
        mViewDataBinding.btnResetScheduleTime
                .setBackgroundResource(R.drawable.background_button_reset_false);
        mViewDataBinding.btnResetScheduleTime.setTextColor(getResources().getColor(
                R.color.grey_500));
    }
}
