package com.example.alarmdemo.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.lang.Deprecated;
import java.lang.Object;

public abstract class FragmentAlarmBinding extends ViewDataBinding {
  @NonNull
  public final FloatingActionButton fabAdd;

  @NonNull
  public final RecyclerView recyclerTime;

  @NonNull
  public final SwipeRefreshLayout swipe;

  @NonNull
  public final Toolbar toolbar;

  protected FragmentAlarmBinding(Object _bindingComponent, View _root, int _localFieldCount,
      FloatingActionButton fabAdd, RecyclerView recyclerTime, SwipeRefreshLayout swipe,
      Toolbar toolbar) {
    super(_bindingComponent, _root, _localFieldCount);
    this.fabAdd = fabAdd;
    this.recyclerTime = recyclerTime;
    this.swipe = swipe;
    this.toolbar = toolbar;
  }

  @NonNull
  public static FragmentAlarmBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot) {
    return inflate(inflater, root, attachToRoot, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, root, attachToRoot, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAlarmBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup root, boolean attachToRoot, @Nullable Object component) {
    return ViewDataBinding.<FragmentAlarmBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.fragment_alarm, root, attachToRoot, component);
  }

  @NonNull
  public static FragmentAlarmBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, DataBindingUtil.getDefaultComponent());
  }

  /**
   * This method receives DataBindingComponent instance as type Object instead of
   * type DataBindingComponent to avoid causing too many compilation errors if
   * compilation fails for another reason.
   * https://issuetracker.google.com/issues/116541301
   * @Deprecated Use DataBindingUtil.inflate(inflater, R.layout.fragment_alarm, null, false, component)
   */
  @NonNull
  @Deprecated
  public static FragmentAlarmBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable Object component) {
    return ViewDataBinding.<FragmentAlarmBinding>inflateInternal(inflater, com.example.alarmdemo.R.layout.fragment_alarm, null, false, component);
  }

  public static FragmentAlarmBinding bind(@NonNull View view) {
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
  public static FragmentAlarmBinding bind(@NonNull View view, @Nullable Object component) {
    return (FragmentAlarmBinding)bind(component, view, com.example.alarmdemo.R.layout.fragment_alarm);
  }
}
