package com.example.alarmdemo.ui.adapter;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alarmdemo.AlarmModel;
import com.example.alarmdemo.R;
import com.example.alarmdemo.ui.MainActivity;
import com.example.alarmdemo.util.AlarmUtils;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class AlarmListAdapterV2 extends RecyclerView.Adapter<AlarmListAdapterV2.ViewHolderAlarm> {

    private Context mContext;
    private List<AlarmModel> mAlarms;

    private String[] mDays;
    private int mAccentColor = -1;


    public AlarmListAdapterV2(Context context, List<AlarmModel> alarms) {
        mContext = context;
        mAlarms = alarms;
    }

    @NonNull
    @Override
    public ViewHolderAlarm onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.alarm_row, parent, false);
        return new ViewHolderAlarm(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAlarm viewholder, int position) {
        if (mAccentColor == -1) {
            mAccentColor = ContextCompat.getColor(mContext, R.color.colorAccent);
        }

        if (mDays == null) {
            mDays = mContext.getResources().getStringArray(R.array.days_abbreviated);
        }


        AlarmModel model = mAlarms.get(position);
        viewholder.time.setText(String.format(mContext.getString(R.string.alarm_convert), model.timeHour, model.timeMinute));

        viewholder.label.setText(model.name);
        viewholder.days.setText(buildSelectedDays(model));


//        viewholder.txtTime.setText(String.format(mContext.getString(R.string.alarm_convert), model.timeHour, model.timeMinute));
//        viewholder.txtName.setText(model.name);
//
//        viewholder.btnToggle.setOnClickListener(v -> {
//            ((MainActivity) mContext).setAlarmEnabled(model.id, !viewholder.btnToggle.isActivated());
//        });
//
//        viewholder.btnToggle.setActivated(model.isEnabled);
//        viewholder.btnToggle.setText(model.isEnabled ? "OFF" : "ON");
//        int color = model.isEnabled ? ContextCompat.getColor(mContext, R.color.colorWhite65) : ContextCompat.getColor(mContext, R.color.red);
//
//        viewholder.btnToggle.setTextColor(color);
//
//
        viewholder.itemView.setOnClickListener(view -> ((MainActivity) mContext).startAlarmDetailsActivity(model.id));
//
        viewholder.itemView.setOnLongClickListener(v -> {
            ((MainActivity) mContext).deleteAlarm(model.id);
            return true;
        });
    }

    @Override
    public int getItemCount() {

        return mAlarms.size();
    }

    private Spannable buildSelectedDays(AlarmModel alarm) {

        final int numDays = 7;
        final boolean[] days = alarm.repeatingDays();

        final SpannableStringBuilder builder = new SpannableStringBuilder();
        ForegroundColorSpan span;

        int startIndex, endIndex;
        for (int i = 0; i < numDays; i++) {

            startIndex = builder.length();

            final String dayText = mDays[i];
            builder.append(dayText);
            builder.append(" ");

            endIndex = startIndex + dayText.length();


            final boolean isSelected = days[i];
            if (alarm.repeatWeekly) {
                span = new ForegroundColorSpan(mAccentColor);
                builder.setSpan(span, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                if (isSelected) {
                    span = new ForegroundColorSpan(mAccentColor);
                    builder.setSpan(span, startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }

        }

        return builder;

    }

    class ViewHolderAlarm extends RecyclerView.ViewHolder {
        TextView time, amPm, label, days;

        public ViewHolderAlarm(@NonNull View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.ar_time);
            amPm = (TextView) itemView.findViewById(R.id.ar_am_pm);
            label = (TextView) itemView.findViewById(R.id.ar_label);
            days = (TextView) itemView.findViewById(R.id.ar_days);
        }
    }

    public void setAlarms(List<AlarmModel> alarms) {
        mAlarms = alarms;
        notifyDataSetChanged();
    }
}
