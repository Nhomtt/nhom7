package com.example.alarmdemo;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class AlarmModel implements Serializable , Parcelable {
    public static final int SUNDAY = 0;
    public static final int MONDAY = 1;
    public static final int TUESDAY = 2;
    public static final int WEDNESDAY = 3;
    public static final int THURSDAY = 4;
    public static final int FRDIAY = 5;
    public static final int SATURDAY = 6;

    public long id = -1;
    public int timeHour;
    public int timeMinute;
    public boolean[] repeatingDays;
    public boolean repeatWeekly;
    public Uri alarmTone;
    public String name;
    public boolean isEnabled;

    public AlarmModel() {
        repeatingDays = new boolean[7];
    }

    public AlarmModel(long id, int timeHour, int timeMinute,
                      boolean[] repeatingDays, boolean repeatWeekly, Uri alarmTone,
                      String name, boolean isEnabled) {
        this.id = id;
        this.timeHour = timeHour;
        this.timeMinute = timeMinute;
        this.repeatingDays = repeatingDays;
        this.repeatWeekly = repeatWeekly;
        this.alarmTone = alarmTone;
        this.name = name;
        this.isEnabled = isEnabled;
    }


    protected AlarmModel(Parcel in) {
        id = in.readLong();
        timeHour = in.readInt();
        timeMinute = in.readInt();
        repeatingDays = in.createBooleanArray();
        repeatWeekly = in.readByte() != 0;
        alarmTone = in.readParcelable(Uri.class.getClassLoader());
        name = in.readString();
        isEnabled = in.readByte() != 0;
    }

    public static final Creator<AlarmModel> CREATOR = new Creator<AlarmModel>() {
        @Override
        public AlarmModel createFromParcel(Parcel in) {
            return new AlarmModel(in);
        }

        @Override
        public AlarmModel[] newArray(int size) {
            return new AlarmModel[size];
        }
    };

    public void setRepeatingDay(int dayOfWeek, boolean value) {
        repeatingDays[dayOfWeek] = value;
    }

    public boolean getRepeatingDay(int dayOfWeek) {
        return repeatingDays[dayOfWeek];
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getTimeHour() {
        return timeHour;
    }

    public void setTimeHour(int timeHour) {
        this.timeHour = timeHour;
    }

    public int getTimeMinute() {
        return timeMinute;
    }

    public void setTimeMinute(int timeMinute) {
        this.timeMinute = timeMinute;
    }

    public boolean[] getRepeatingDays() {
        return repeatingDays;
    }

    public void setRepeatingDays(boolean[] repeatingDays) {
        this.repeatingDays = repeatingDays;
    }

    public boolean isRepeatWeekly() {
        return repeatWeekly;
    }

    public void setRepeatWeekly(boolean repeatWeekly) {
        this.repeatWeekly = repeatWeekly;
    }

    public Uri getAlarmTone() {
        return alarmTone;
    }

    public void setAlarmTone(Uri alarmTone) {
        this.alarmTone = alarmTone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }


    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeInt(timeHour);
        dest.writeInt(timeMinute);
        dest.writeBooleanArray(repeatingDays);
        dest.writeByte((byte) (repeatWeekly ? 1 : 0));
        dest.writeParcelable(alarmTone, flags);
        dest.writeString(name);
        dest.writeByte((byte) (isEnabled ? 1 : 0));
    }

    public boolean[] repeatingDays() {
        return repeatingDays;
    }
}
