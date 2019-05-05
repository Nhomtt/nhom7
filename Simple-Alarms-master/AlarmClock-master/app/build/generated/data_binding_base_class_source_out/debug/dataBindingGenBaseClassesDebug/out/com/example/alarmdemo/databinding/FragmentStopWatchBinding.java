package com.example.alarmdemo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentStopWatchBinding extends ViewDataBinding {
  @NonNull
  public final Button btnContinueCountTime;

  @NonNull
  public final Button btnLapCountTime;

  @NonNull
  public final Button btnResetCountTime;

  @NonNull
  public final Button btnStartCountTime;

  @NonNull
  public final Button btnStopCountTime;

  @NonNull
  public final RelativeLayout layoutCountTime;

  @NonNull
  public final ListView listViewTimeLap;

  @NonNull
  public final LinearLayout subLayoutCountTime1;

  @NonNull
  public final LinearLayout subLayoutCountTime2;

  @NonNull
  public final RelativeLayout subLayoutCountTime3;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView txtCountTime;

  @NonNull
  public final TextView txtCountTimePercent;

  protected FragmentStopWatchBinding(Object _bindingComponent, View _root, int _localFieldCount,
      Button btnContinueCountTime, Button btnLapCountTime, Button btnResetCountTime,
      Button btnStartCountTime, Button btnStopCountTime, RelativeLayout layoutCountTime,
      ListView listViewTimeLap, LinearLayout subLayoutCountTime1, LinearLayout subLayoutCountTime2,
      RelativeLayout subLayoutCountTime3, Toolbar toolbar, TextView txtCountTime,
      TextView txtCountTimePercent) {
    super(_bindingComponent, _root, _localFieldCount);
    this.btnContinueCountTime = btnContinueCountTime;
    this.btnLapCountTime = btnLapCountTime;
    this.btnResetCountTime = btnResetCountTime;
    this.btnStartCountTime = btnStartCountTime;
    this.btnStopCountTime = btnStopCountTime;
    this.layoutCountTime = layoutCountTime;
    this.listViewTimeLap = listViewTimeLap;
    this.subLayoutCountTime1 = subLayoutCountTime1;
    this.subLayoutCountTime2 = subLayoutCountTime2;
    this.subLayoutCountTime3 = subLayoutCountTime3;
    this.toolbar = toolbar;
    this.txtCountTime = txtCountTime;
    this.txtCountTimePercent = txtCountTimePercent;
  }

  @NonNull
  public static FragmentStopWatchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_stop_watch, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentStopWatchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentStopWatchBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.fragment_stop_watch, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentStopWatchBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_stop_watch, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentStopWatchBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentStopWatchBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.fragment_stop_watch, null, false, component);
  }

  public static FragmentStopWatchBinding bind(@NonNull View view) {
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
  public static FragmentStopWatchBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentStopWatchBinding)bind(component, view, com.example.alarmdemo.R.layout.fragment_stop_watch);
  }
}
