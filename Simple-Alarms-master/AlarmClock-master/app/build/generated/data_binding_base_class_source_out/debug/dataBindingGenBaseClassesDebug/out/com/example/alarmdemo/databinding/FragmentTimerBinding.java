package com.example.alarmdemo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentTimerBinding extends ViewDataBinding {
  @NonNull
  public final Button btnOKScheduleTime;

  @NonNull
  public final Button btnResetScheduleTime;

  @NonNull
  public final Button btnStartScheduleTime;

  @NonNull
  public final EditText edtHour;

  @NonNull
  public final EditText edtMinute;

  @NonNull
  public final EditText edtSecond;

  @NonNull
  public final RelativeLayout layoutScheduleTime;

  @NonNull
  public final ProgressBar progressBarScheduleTime;

  @NonNull
  public final Toolbar toolbar;

  protected FragmentTimerBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnOKScheduleTime, Button btnResetScheduleTime, Button btnStartScheduleTime,
      EditText edtHour, EditText edtMinute, EditText edtSecond, RelativeLayout layoutScheduleTime,
      ProgressBar progressBarScheduleTime, Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnOKScheduleTime = btnOKScheduleTime;
    this.btnResetScheduleTime = btnResetScheduleTime;
    this.btnStartScheduleTime = btnStartScheduleTime;
    this.edtHour = edtHour;
    this.edtMinute = edtMinute;
    this.edtSecond = edtSecond;
    this.layoutScheduleTime = layoutScheduleTime;
    this.progressBarScheduleTime = progressBarScheduleTime;
    this.toolbar = toolbar;
  }

  @NonNull
  public static FragmentTimerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_timer, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentTimerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentTimerBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.fragment_timer, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentTimerBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_timer, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentTimerBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentTimerBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.fragment_timer, null, false, component);
  }

  public static FragmentTimerBinding bind(@NonNull View view) {
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
  public static FragmentTimerBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentTimerBinding)bind(component, view, com.example.alarmdemo.R.layout.fragment_timer);
  }
}
