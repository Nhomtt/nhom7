package com.example.alarmdemo;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.alarmdemo.databinding.ActivityDetailsBindingImpl;
import com.example.alarmdemo.databinding.ActivityMainBindingImpl;
import com.example.alarmdemo.databinding.FragmentAlarmBindingImpl;
import com.example.alarmdemo.databinding.FragmentProfileBindingImpl;
import com.example.alarmdemo.databinding.FragmentStopWatchBindingImpl;
import com.example.alarmdemo.databinding.FragmentTimerBindingImpl;
import com.example.alarmdemo.databinding.FragmentWordClockBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYDETAILS = 1;

  private static final int LAYOUT_ACTIVITYMAIN = 2;

  private static final int LAYOUT_FRAGMENTALARM = 3;

  private static final int LAYOUT_FRAGMENTPROFILE = 4;

  private static final int LAYOUT_FRAGMENTSTOPWATCH = 5;

  private static final int LAYOUT_FRAGMENTTIMER = 6;

  private static final int LAYOUT_FRAGMENTWORDCLOCK = 7;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(7);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.alarmdemo.R.layout.activity_details, LAYOUT_ACTIVITYDETAILS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.alarmdemo.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.alarmdemo.R.layout.fragment_alarm, LAYOUT_FRAGMENTALARM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.alarmdemo.R.layout.fragment_profile, LAYOUT_FRAGMENTPROFILE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.alarmdemo.R.layout.fragment_stop_watch, LAYOUT_FRAGMENTSTOPWATCH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.alarmdemo.R.layout.fragment_timer, LAYOUT_FRAGMENTTIMER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.alarmdemo.R.layout.fragment_word_clock, LAYOUT_FRAGMENTWORDCLOCK);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYDETAILS: {
          if ("layout/activity_details_0".equals(tag)) {
            return new ActivityDetailsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_details is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTALARM: {
          if ("layout/fragment_alarm_0".equals(tag)) {
            return new FragmentAlarmBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_alarm is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTPROFILE: {
          if ("layout/fragment_profile_0".equals(tag)) {
            return new FragmentProfileBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_profile is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSTOPWATCH: {
          if ("layout/fragment_stop_watch_0".equals(tag)) {
            return new FragmentStopWatchBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_stop_watch is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTTIMER: {
          if ("layout/fragment_timer_0".equals(tag)) {
            return new FragmentTimerBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_timer is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTWORDCLOCK: {
          if ("layout/fragment_word_clock_0".equals(tag)) {
            return new FragmentWordClockBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_word_clock is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(2);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(7);

    static {
      sKeys.put("layout/activity_details_0", com.example.alarmdemo.R.layout.activity_details);
      sKeys.put("layout/activity_main_0", com.example.alarmdemo.R.layout.activity_main);
      sKeys.put("layout/fragment_alarm_0", com.example.alarmdemo.R.layout.fragment_alarm);
      sKeys.put("layout/fragment_profile_0", com.example.alarmdemo.R.layout.fragment_profile);
      sKeys.put("layout/fragment_stop_watch_0", com.example.alarmdemo.R.layout.fragment_stop_watch);
      sKeys.put("layout/fragment_timer_0", com.example.alarmdemo.R.layout.fragment_timer);
      sKeys.put("layout/fragment_word_clock_0", com.example.alarmdemo.R.layout.fragment_word_clock);
    }
  }
}
