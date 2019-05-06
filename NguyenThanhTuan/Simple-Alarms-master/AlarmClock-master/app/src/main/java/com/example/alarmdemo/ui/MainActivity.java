package com.example.alarmdemo.ui;

import java.io.Serializable;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.alarmdemo.AlarmDBHelper;
import com.example.alarmdemo.AlarmDetailsActivity;
import com.example.alarmdemo.AlarmManagerHelper;
import com.example.alarmdemo.AlarmModel;
import com.example.alarmdemo.R;
import com.example.alarmdemo.databinding.ActivityMainBinding;
import com.example.alarmdemo.ui.fragment.AlarmFragment;
import com.example.alarmdemo.ui.fragment.ProfileFragment;
import com.example.alarmdemo.ui.fragment.StopWatchFragment;
import com.example.alarmdemo.ui.fragment.TimerFragment;
import com.example.alarmdemo.ui.fragment.WordClockFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    //    private AlarmListAdapter mAdapter;
    private AlarmDBHelper dbHelper = new AlarmDBHelper(this);
    private List<AlarmModel> mAlarmModels;

    private ActivityMainBinding mBinding;
    private final static String TAG = MainActivity.class.getSimpleName();

    final private Fragment mWordClockFragment = new WordClockFragment();
    private Fragment mAlarmFragment;
    private Fragment mStopWatchFragment;
    private Fragment mTimerFragment;
    private Fragment mProfileFragment;
    private Fragment fragmentActive = mWordClockFragment;
    BroadcastReceiver br;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        configureNavigationBottom();

//        br = new AlarmManagerHelper();
//        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
//        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
//        this.registerReceiver(br, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(br);
    }

    private void configureNavigationBottom() {
        addFragment(mWordClockFragment);
        new Handler().post(() -> mAlarmModels = dbHelper.getAlarms());
        mBinding.bottomNavigation.setOnNavigationItemSelectedListener(bottomNavigationItemSelected);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationItemSelected = item -> {
        switch (item.getItemId()) {
            case R.id.word:
                if (fragmentActive instanceof WordClockFragment) return true;
                showHideFragment(fragmentActive, mWordClockFragment);
                fragmentActive = mWordClockFragment;
                return true;

            case R.id.alarm:

                if (fragmentActive instanceof AlarmFragment) return true;

                if (mAlarmFragment == null) {
                    Bundle args = new Bundle();
                    args.putSerializable("AlarmModel", (Serializable) mAlarmModels);
                    mAlarmFragment = new AlarmFragment();
                    mAlarmFragment.setArguments(args);
                    addFragmentBottomSelected(mAlarmFragment, fragmentActive);
                    fragmentActive = mAlarmFragment;
                    return true;
                }
                showHideFragment(fragmentActive, mAlarmFragment);
                fragmentActive = mAlarmFragment;
                return true;

            case R.id.stop:
                if (fragmentActive instanceof StopWatchFragment) return true;

                if (mStopWatchFragment == null) {
                    mStopWatchFragment = new StopWatchFragment();
                    addFragmentBottomSelected(mStopWatchFragment, fragmentActive);
                    fragmentActive = mStopWatchFragment;
                    return true;
                }

                showHideFragment(fragmentActive, mStopWatchFragment);
                fragmentActive = mStopWatchFragment;
                return true;

            case R.id.timer:
                if (fragmentActive instanceof TimerFragment) return true;

                if (mTimerFragment == null) {
                    mTimerFragment = new TimerFragment();
                    addFragmentBottomSelected(mTimerFragment, fragmentActive);
                    fragmentActive = mTimerFragment;
                    return true;
                }

                showHideFragment(fragmentActive, mTimerFragment);
                fragmentActive = mTimerFragment;
                return true;

            case R.id.profile:
                if (fragmentActive instanceof ProfileFragment) return true;

                if (mProfileFragment == null) {
                    mProfileFragment = new ProfileFragment();
                    addFragmentBottomSelected(mProfileFragment, fragmentActive);
                    fragmentActive = mProfileFragment;
                    return true;
                }

                showHideFragment(fragmentActive, mProfileFragment);
                fragmentActive = mProfileFragment;
                return true;
        }
        return false;
    };

    protected void addFragment(@NonNull Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragment, TAG).commit();
    }

    protected final void addFragmentBottomSelected(@NonNull Fragment add, @NonNull Fragment hide) {

        getSupportFragmentManager()
                .beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .add(R.id.container, add, TAG)
                .hide(hide)
                .commit();
    }

    protected final void showHideFragment(Fragment hide, Fragment show) {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
                .hide(hide).show(show).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            getListAlarm();
        }
    }

    public void setAlarmEnabled(long id, boolean isEnabled) {
        AlarmManagerHelper.cancelAlarms(this);
        AlarmModel model = dbHelper.getAlarm(id);
        model.isEnabled = isEnabled;
        dbHelper.updateAlarm(model);
        getListAlarm();
        AlarmManagerHelper.setAlarms(this);
    }

    public void startAlarmDetailsActivity(long id) {
        Intent intent = new Intent(this, AlarmDetailsActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, 0);
    }

    //    xoa trong danh sach
    public void deleteAlarm(long id) {
        final long alarmId = id;


        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
        builder.setMessage("Do you want delete?").setTitle("Alarm").setCancelable(true)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", (dialog, which) -> {
                    dbHelper.deleteAlarm(alarmId);
                    getListAlarm();
                }).show();
    }

    public void onRefresh() {
        mAlarmModels.clear();
        mAlarmModels = dbHelper.getAlarms();
        ((AlarmFragment) mAlarmFragment).setAlarms(mAlarmModels);
    }

    private void getListAlarm() {
        mAlarmModels.clear();
        mAlarmModels = dbHelper.getAlarms();
        ((AlarmFragment) mAlarmFragment).setAlarms(mAlarmModels);
    }
}
