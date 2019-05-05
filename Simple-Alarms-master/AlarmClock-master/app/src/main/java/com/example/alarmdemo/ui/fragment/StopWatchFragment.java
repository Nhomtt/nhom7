package com.example.alarmdemo.ui.fragment;


import android.annotation.SuppressLint;
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

import com.example.alarmdemo.R;
import com.example.alarmdemo.databinding.FragmentStopWatchBinding;
import com.example.alarmdemo.ui.MainActivity;
import com.example.alarmdemo.model.InforTimeLap;
import com.example.alarmdemo.ui.adapter.InforTimeLapAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class StopWatchFragment extends Fragment implements View.OnClickListener {

    private int hourSchedule, minuteSchedule, secondSchedule, hourCount = 0,
            minuteCount = 0, secondCount = 0,
            timePercent = 0, countTimePercent = 0, progressBarStatus = 0,
            indexOfItemTime = 0;
    private MainActivity mActivity;
    private FragmentStopWatchBinding mViewDataBinding;
    private static final int RUN_COUNT_TIME = 3;
    private Thread threadCountTime;
    private boolean countingTime = false;
    private long miliis = 0;
    private ArrayList<InforTimeLap> inforTimeLaps;
    private InforTimeLapAdapter inforTimeLapAdapter;

    public StopWatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_stop_watch, container, false);
        return mViewDataBinding.getRoot();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (MainActivity) getActivity();
        mActivity.setSupportActionBar(mViewDataBinding.toolbar);
        mViewDataBinding.toolbar.setTitle("StopWatch");
        mViewDataBinding.txtCountTime.setTypeface(Typeface.createFromAsset(mActivity.getAssets(),
                "fonts/DigitalDismay.otf"));
        mViewDataBinding.txtCountTimePercent.setTypeface(Typeface.createFromAsset(mActivity.getAssets(),
                "fonts/DigitalDismay.otf"));
        mViewDataBinding.btnStartCountTime.setOnClickListener(this);
        mViewDataBinding.btnStopCountTime.setOnClickListener(this);
        mViewDataBinding.btnLapCountTime.setOnClickListener(this);
        mViewDataBinding.btnContinueCountTime.setOnClickListener(this);
        mViewDataBinding.btnResetCountTime.setOnClickListener(this);

        inforTimeLaps = new ArrayList<InforTimeLap>();
        inforTimeLapAdapter = new InforTimeLapAdapter(mActivity,
                R.layout.listview_item, inforTimeLaps);
        mViewDataBinding.listViewTimeLap.setAdapter(inforTimeLapAdapter);

    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == RUN_COUNT_TIME) {
                timePercent++;
                countTimePercent++;
                if (timePercent == 100) {
                    timePercent = 0;
                }
                mViewDataBinding.txtCountTimePercent.setText("." + timePercent);
                if (countTimePercent % 100 == 0) {
                    countTimePercent = 0;
                    secondCount++;
                    if (secondCount == 60) {
                        secondCount = 0;
                        minuteCount++;
                    }
                    if (minuteCount == 60) {
                        hourCount++;
                    }
                    mViewDataBinding.txtCountTime.setText((hourCount < 10 ? ("0" + hourCount)
                            : hourCount)
                            + ":"
                            + (minuteCount < 10 ? ("0" + minuteCount)
                            : minuteCount)
                            + ":"
                            + (secondCount < 10 ? ("0" + secondCount)
                            : secondCount));
                }
            }
        }

    };

    private Runnable runCountTime = () -> {
        while (countingTime) {
            handler.sendEmptyMessage(RUN_COUNT_TIME);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
// Button bat dau - dong ho dem
            case R.id.btnStartCountTime:
                if (!countingTime) {
                    countingTime = true;
                    threadCountTime = new Thread(runCountTime);
                    threadCountTime.start();
                    mViewDataBinding.btnStartCountTime.setVisibility(RelativeLayout.GONE);
                    mViewDataBinding.btnStopCountTime.setVisibility(RelativeLayout.VISIBLE);
                    mViewDataBinding.btnLapCountTime.setVisibility(RelativeLayout.VISIBLE);
                    miliis = System.currentTimeMillis();
                }
                break;

            // Button dung - dong ho dem
            case R.id.btnStopCountTime:
                countingTime = false;
                mViewDataBinding.btnStopCountTime.setVisibility(RelativeLayout.GONE);
                mViewDataBinding.btnLapCountTime.setVisibility(RelativeLayout.GONE);
                mViewDataBinding.btnContinueCountTime.setVisibility(RelativeLayout.VISIBLE);
                mViewDataBinding.btnResetCountTime.setVisibility(RelativeLayout.VISIBLE);
                break;

            // Button ghep thoi gian - dong ho dem
            case R.id.btnLapCountTime:
                indexOfItemTime++;
                String itemTime = mViewDataBinding.txtCountTime.getText().toString();
                String itemPercentSecond = mViewDataBinding.txtCountTimePercent.getText().toString();
                miliis = System.currentTimeMillis() - miliis;
                inforTimeLaps.add(0, new InforTimeLap(indexOfItemTime + "",
                        itemTime, itemPercentSecond));
                miliis = 0;
                inforTimeLapAdapter.notifyDataSetChanged();
                break;

            // Button tiep tuc - dong ho dem
            case R.id.btnContinueCountTime:
                countingTime = true;
                threadCountTime = new Thread(runCountTime);
                threadCountTime.start();
                mViewDataBinding.btnContinueCountTime.setVisibility(RelativeLayout.GONE);
                mViewDataBinding.btnResetCountTime.setVisibility(RelativeLayout.GONE);
                mViewDataBinding.btnStopCountTime.setVisibility(RelativeLayout.VISIBLE);
                mViewDataBinding.btnLapCountTime.setVisibility(RelativeLayout.VISIBLE);
                break;

            // Button xac lap lai - dong ho dem
            case R.id.btnResetCountTime:
                countingTime = false;
                hourCount = 0;
                minuteCount = 0;
                secondCount = 0;
                timePercent = 0;
                mViewDataBinding.txtCountTime.setText("00:00:00");
                mViewDataBinding.txtCountTimePercent.setText(".00");
                indexOfItemTime = 0;
                inforTimeLaps.clear();
                inforTimeLapAdapter.notifyDataSetChanged();
                mViewDataBinding.btnContinueCountTime.setVisibility(RelativeLayout.GONE);
                mViewDataBinding.btnResetCountTime.setVisibility(RelativeLayout.GONE);
                mViewDataBinding.btnStartCountTime.setVisibility(RelativeLayout.VISIBLE);
                break;
        }
    }
}
