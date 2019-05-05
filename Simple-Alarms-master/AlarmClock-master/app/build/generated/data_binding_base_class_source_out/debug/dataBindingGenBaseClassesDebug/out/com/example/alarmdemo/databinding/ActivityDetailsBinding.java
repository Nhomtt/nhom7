package com.example.alarmdemo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import com.example.alarmdemo.CustomSwitch;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class ActivityDetailsBinding extends ViewDataBinding {
  @NonNull
  public final EditText alarmDetailsName;

  @NonNull
  public final CustomSwitch alarmDetailsRepeatFriday;

  @NonNull
  public final CustomSwitch alarmDetailsRepeatMonday;

  @NonNull
  public final CustomSwitch alarmDetailsRepeatSaturday;

  @NonNull
  public final CustomSwitch alarmDetailsRepeatSunday;

  @NonNull
  public final CustomSwitch alarmDetailsRepeatThursday;

  @NonNull
  public final CustomSwitch alarmDetailsRepeatTuesday;

  @NonNull
  public final CustomSwitch alarmDetailsRepeatWednesday;

  @NonNull
  public final CustomSwitch alarmDetailsRepeatWeekly;

  @NonNull
  public final TimePicker alarmDetailsTimePicker;

  @NonNull
  public final TextView alarmLabelTone;

  @NonNull
  public final TextView alarmLabelToneSelection;

  @NonNull
  public final LinearLayout alarmRingtoneContainer;

  @NonNull
  public final View divider1;

  @NonNull
  public final View divider2;

  @NonNull
  public final View divider3;

  @NonNull
  public final View divider4;

  @NonNull
  public final Toolbar toolbar;

  protected ActivityDetailsBinding(Object _bindingComponent, View _root, int _localFieldCount,
      EditText alarmDetailsName, CustomSwitch alarmDetailsRepeatFriday,
      CustomSwitch alarmDetailsRepeatMonday, CustomSwitch alarmDetailsRepeatSaturday,
      CustomSwitch alarmDetailsRepeatSunday, CustomSwitch alarmDetailsRepeatThursday,
      CustomSwitch alarmDetailsRepeatTuesday, CustomSwitch alarmDetailsRepeatWednesday,
      CustomSwitch alarmDetailsRepeatWeekly, TimePicker alarmDetailsTimePicker,
      TextView alarmLabelTone, TextView alarmLabelToneSelection,
      LinearLayout alarmRingtoneContainer, View divider1, View divider2, View divider3,
      View divider4, Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.alarmDetailsName = alarmDetailsName;
    this.alarmDetailsRepeatFriday = alarmDetailsRepeatFriday;
    this.alarmDetailsRepeatMonday = alarmDetailsRepeatMonday;
    this.alarmDetailsRepeatSaturday = alarmDetailsRepeatSaturday;
    this.alarmDetailsRepeatSunday = alarmDetailsRepeatSunday;
    this.alarmDetailsRepeatThursday = alarmDetailsRepeatThursday;
    this.alarmDetailsRepeatTuesday = alarmDetailsRepeatTuesday;
    this.alarmDetailsRepeatWednesday = alarmDetailsRepeatWednesday;
    this.alarmDetailsRepeatWeekly = alarmDetailsRepeatWeekly;
    this.alarmDetailsTimePicker = alarmDetailsTimePicker;
    this.alarmLabelTone = alarmLabelTone;
    this.alarmLabelToneSelection = alarmLabelToneSelection;
    this.alarmRingtoneContainer = alarmRingtoneContainer;
    this.divider1 = divider1;
    this.divider2 = divider2;
    this.divider3 = divider3;
    this.divider4 = divider4;
    this.toolbar = toolbar;
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_details, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<ActivityDetailsBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.activity_details, root, attachToRoot, component);
  }

  @NonNull
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.activity_details, null, false, component)
   */
  @NonNull
  @Deprecated
  public static ActivityDetailsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<ActivityDetailsBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.activity_details, null, false, component);
  }

  public static ActivityDetailsBinding bind(@NonNull View view) {
    return bind(view, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.bind(view, component)
   */
  @Deprecated
  public static ActivityDetailsBinding bind(@NonNull View view, @Nullable Object component) {
    return (ActivityDetailsBinding)bind(component, view, com.example.alarmdemo.R.layout.activity_details);
  }
}
