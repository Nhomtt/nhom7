package com.example.alarmdemo;

import android.app.AlarmManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;

public class AlarmService extends JobIntentService {
    public static String TAG = AlarmService.class.getSimpleName();

//    @Override
//    public IBinder onBind(Intent intent) {
//        Log.e(TAG, "onBind");
//        return null;
//    }

    /* Give the Job a Unique Id */
    private static final int JOB_ID = 1000;
    public static void enqueueWork(Context ctx, Intent intent) {
        enqueueWork(ctx, AlarmService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {

        Log.e(TAG, "onHandleWork");
//        Intent alarmIntent = new Intent(getBaseContext(), AlarmScreen.class);
//        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        alarmIntent.putExtras(intent);
//        getApplication().startActivity(alarmIntent);
    }

//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        Log.e(TAG, "onStartCommand");
//        Intent alarmIntent = new Intent(getBaseContext(), AlarmScreen.class);
//        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        alarmIntent.putExtras(intent);
//        getApplication().startActivity(alarmIntent);
//
////        AlarmManagerHelper.setAlarms(this);
//
//        return super.onStartCommand(intent, flags, startId);
//    }
}
