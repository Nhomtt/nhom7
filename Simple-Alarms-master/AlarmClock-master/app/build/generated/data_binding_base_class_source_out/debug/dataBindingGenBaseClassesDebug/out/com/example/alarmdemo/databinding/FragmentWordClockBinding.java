package com.example.alarmdemo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentWordClockBinding extends ViewDataBinding {
  @NonNull
  public final LinearLayout layoutCurrentTime;

  @NonNull
  public final Toolbar toolbar;

  @NonNull
  public final TextView txtAMPM;

  @NonNull
  public final TextView txtCurrentDate;

  @NonNull
  public final TextView txtCurrentTime;

  @NonNull
  public final TextView txtSecond;

  protected FragmentWordClockBinding(Object _bindingComponent, View _root, int _localFieldCount,
      LinearLayout layoutCurrentTime, Toolbar toolbar, TextView txtAMPM, TextView txtCurrentDate,
      TextView txtCurrentTime, TextView txtSecond) {
    super(_bindingComponent, _root, _localFieldCount);
    this.layoutCurrentTime = layoutCurrentTime;
    this.toolbar = toolbar;
    this.txtAMPM = txtAMPM;
    this.txtCurrentDate = txtCurrentDate;
    this.txtCurrentTime = txtCurrentTime;
    this.txtSecond = txtSecond;
  }

  @NonNull
  public static FragmentWordClockBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_word_clock, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentWordClockBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentWordClockBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.fragment_word_clock, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentWordClockBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_word_clock, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentWordClockBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentWordClockBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.fragment_word_clock, null, false, component);
  }

  public static FragmentWordClockBinding bind(@NonNull View view) {
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
  public static FragmentWordClockBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentWordClockBinding)bind(component, view, com.example.alarmdemo.R.layout.fragment_word_clock);
  }
}
