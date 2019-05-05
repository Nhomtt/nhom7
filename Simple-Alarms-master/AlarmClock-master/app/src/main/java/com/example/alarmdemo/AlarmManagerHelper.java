package com.example.alarmdemo;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Objects;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class AlarmManagerHelper extends BroadcastReceiver {
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String TIME_HOUR = "timeHour";
    public static final String TIME_MINUTE = "timeMinute";
    public static final String TONE = "alarmTone";

    String TAG = this.getClass().getSimpleName();


    public static final String CUSTOM_INTENT = "com.test.intent.action.ALARM";

    static Context ctx;

    @Override
    public void onReceive(Context context, Intent intent) {
//        setAlarms(context);
        ctx = context.getApplicationContext();
        /* enqueue the job */
        Log.e(TAG, "onReceive");
        AlarmService.enqueueWork(context, intent);
    }

    public static void cancelAlarm() {
        AlarmManager alarm = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);

        /* cancel any pending alarm */
        alarm.cancel(getPendingIntent());
    }

    private static PendingIntent getPendingIntent() {

        Intent alarmIntent = new Intent(ctx, AlarmService.class);
        alarmIntent.setAction(CUSTOM_INTENT);

        return PendingIntent.getBroadcast(ctx, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }


    public static void setAlarm(boolean force) {
        cancelAlarm();
        AlarmManager alarm = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        // EVERY X MINUTES
        long delay = (1000 * 60);
        long when = System.currentTimeMillis();
        if (!force) {
            when += delay;
        }

        /* fire the broadcast */
//        alarm.set(AlarmManager.RTC_WAKEUP, when, getPendingIntent());
        int SDK_INT = Build.VERSION.SDK_INT;
        if (SDK_INT < Build.VERSION_CODES.KITKAT)
            alarm.set(AlarmManager.RTC_WAKEUP, when, getPendingIntent());
        else if (SDK_INT < Build.VERSION_CODES.M)
            alarm.setExact(AlarmManager.RTC_WAKEUP, when, getPendingIntent());
        else {
            alarm.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, when, getPendingIntent());
        }
    }


    /**/
    public static void setAlarms(Context context) {

        cancelAlarms(context);
        AlarmDBHelper dbHelper = new AlarmDBHelper(context);

        List<AlarmModel> alarms = dbHelper.getAlarms();

        for (AlarmModel alarm : alarms) {
            if (alarm.isEnabled) {
                PendingIntent pIntent = createPendingIntent(context, alarm);

                Calendar calendar = Calendar.getInstance();
//
                calendar.set(Calendar.HOUR_OF_DAY, alarm.timeHour);
                calendar.set(Calendar.MINUTE, alarm.timeMinute);
                calendar.set(Calendar.SECOND, 0);

                final int nowDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
                final int nowHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                final int nowMinute = Calendar.getInstance().get(Calendar.MINUTE);
                boolean alarmSet = false;
                Log.e(">>>>Alarm: ", "nowDay: " + nowDay + " nowHour " + nowHour + " nowMinute " + nowMinute);
                /*Baos thuc nguyen tuan*/
                if (alarm.repeatWeekly) {
                    if ((alarm.timeHour == nowHour) && (alarm.timeMinute == nowMinute)) {

                        calendar.set(Calendar.DAY_OF_WEEK, nowDay);
                        setAlarm(context, calendar, pIntent);
                        alarmSet = true;
                    }
                } else {
                    for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++dayOfWeek) {
                        if (alarm.getRepeatingDay(dayOfWeek - 1) && dayOfWeek >= nowDay && !(dayOfWeek == nowDay && alarm.timeHour < nowHour)
                                && !(dayOfWeek == nowDay && alarm.timeHour == nowHour && alarm.timeMinute <= nowMinute)) {
                            calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                            Log.i("abc123", "set Alarmed: " + nowDay + " : " + alarm.timeHour + " : " + alarm.timeMinute);

                            setAlarm(context, calendar, pIntent);
                            alarmSet = true;
                            break;
                        }
                    }
                }
//
//                Log.e(">>>>Alarm: ", "Lowns hon");
//
//                if ((alarm.timeHour > nowHour) || ((alarm.timeHour == nowHour) && (alarm.timeMinute > nowMinute))) {
//                    Log.e(">>>>Alarm: ", "Lowns hon");
//                    calendar.set(Calendar.DAY_OF_WEEK, nowDay);
//                    setAlarm(context, calendar, pIntent);
//                    alarmSet = true;
//                } else {
//                    Log.e(">>>>Alarm: ", "Be hon");
//                    for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++dayOfWeek) {
//                        if (alarm.getRepeatingDay(dayOfWeek - 1) && dayOfWeek >= nowDay && !(dayOfWeek == nowDay && alarm.timeHour < nowHour)
//                                && !(dayOfWeek == nowDay && alarm.timeHour == nowHour && alarm.timeMinute <= nowMinute)) {
//                            calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
//                            Log.i("abc123", "set Alarmed: " + nowDay + " : " + alarm.timeHour + " : " + alarm.timeMinute);
//
//                            setAlarm(context, calendar, pIntent);
//                            alarmSet = true;
//                            break;
//                        }
//                    }
//                }
//
                if (!alarmSet) {
                    for (int dayOfWeek = Calendar.SUNDAY; dayOfWeek <= Calendar.SATURDAY; ++dayOfWeek) {
                        if (alarm.getRepeatingDay(dayOfWeek - 1) && dayOfWeek <= nowDay && alarm.repeatWeekly) {
                            calendar.set(Calendar.DAY_OF_WEEK, dayOfWeek);
                            calendar.add(Calendar.WEEK_OF_YEAR, 1);

                            setAlarm(context, calendar, pIntent);
                            break;
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NewApi")
    public static void setAlarm(Context context, Calendar calendar, PendingIntent pIntent) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pIntent);
//		alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), PendingIntent.getBroadcast(context, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
//		Toast.makeText(this, "Alarm scheduled for tomorrow", Toast.LENGTH_SHORT).show();
    }

    public static void cancelAlarms(Context context) {
        AlarmDBHelper dbHelper = new AlarmDBHelper(context);

        List<AlarmModel> alarms = dbHelper.getAlarms();

        if (alarms != null && !alarms.isEmpty()) {
            for (AlarmModel alarm : alarms) {
                if (alarm.isEnabled) {
                    PendingIntent pIntent = createPendingIntent(context, alarm);

                    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    alarmManager.cancel(pIntent);
                }
            }
        }
    }

    private static PendingIntent createPendingIntent(Context context, AlarmModel model) {
        Intent intent = new Intent(context, AlarmService.class);
        intent.putExtra(ID, model.id);
        intent.putExtra(NAME, model.name);
        intent.putExtra(TIME_HOUR, model.timeHour);
        intent.putExtra(TIME_MINUTE, model.timeMinute);
        intent.putExtra(TONE, model.alarmTone.toString());

        return PendingIntent.getService(context, (int) model.id, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
