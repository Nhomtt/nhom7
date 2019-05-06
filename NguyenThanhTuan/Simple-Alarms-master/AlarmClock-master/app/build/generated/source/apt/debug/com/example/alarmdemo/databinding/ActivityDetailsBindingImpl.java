package com.example.alarmdemo.databinding;
import com.example.alarmdemo.R;
import com.example.alarmdemo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityDetailsBindingImpl extends ActivityDetailsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.alarm_details_time_picker, 2);
        sViewsWithIds.put(R.id.alarm_details_name, 3);
        sViewsWithIds.put(R.id.divider2, 4);
        sViewsWithIds.put(R.id.alarm_details_repeat_weekly, 5);
        sViewsWithIds.put(R.id.divider1, 6);
        sViewsWithIds.put(R.id.alarm_details_repeat_sunday, 7);
        sViewsWithIds.put(R.id.alarm_details_repeat_monday, 8);
        sViewsWithIds.put(R.id.alarm_details_repeat_tuesday, 9);
        sViewsWithIds.put(R.id.alarm_details_repeat_wednesday, 10);
        sViewsWithIds.put(R.id.alarm_details_repeat_thursday, 11);
        sViewsWithIds.put(R.id.alarm_details_repeat_friday, 12);
        sViewsWithIds.put(R.id.alarm_details_repeat_saturday, 13);
        sViewsWithIds.put(R.id.divider4, 14);
        sViewsWithIds.put(R.id.alarm_ringtone_container, 15);
        sViewsWithIds.put(R.id.alarm_label_tone, 16);
        sViewsWithIds.put(R.id.alarm_label_tone_selection, 17);
        sViewsWithIds.put(R.id.divider3, 18);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityDetailsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 19, sIncludes, sViewsWithIds));
    }
    private ActivityDetailsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.EditText) bindings[3]
            , (com.example.alarmdemo.CustomSwitch) bindings[12]
            , (com.example.alarmdemo.CustomSwitch) bindings[8]
            , (com.example.alarmdemo.CustomSwitch) bindings[13]
            , (com.example.alarmdemo.CustomSwitch) bindings[7]
            , (com.example.alarmdemo.CustomSwitch) bindings[11]
            , (com.example.alarmdemo.CustomSwitch) bindings[9]
            , (com.example.alarmdemo.CustomSwitch) bindings[10]
            , (com.example.alarmdemo.CustomSwitch) bindings[5]
            , (android.widget.TimePicker) bindings[2]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[17]
            , (android.widget.LinearLayout) bindings[15]
            , (android.view.View) bindings[6]
            , (android.view.View) bindings[4]
            , (android.view.View) bindings[18]
            , (android.view.View) bindings[14]
            , (androidx.appcompat.widget.Toolbar) bindings[1]
            );
        this.mboundView0 = (android.widget.LinearLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}