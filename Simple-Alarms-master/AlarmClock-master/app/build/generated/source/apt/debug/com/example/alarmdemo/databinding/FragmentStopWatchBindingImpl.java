package com.example.alarmdemo.databinding;
import com.example.alarmdemo.R;
import com.example.alarmdemo.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentStopWatchBindingImpl extends FragmentStopWatchBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 1);
        sViewsWithIds.put(R.id.layoutCountTime, 2);
        sViewsWithIds.put(R.id.subLayoutCountTime1, 3);
        sViewsWithIds.put(R.id.txtCountTime, 4);
        sViewsWithIds.put(R.id.txtCountTimePercent, 5);
        sViewsWithIds.put(R.id.subLayoutCountTime2, 6);
        sViewsWithIds.put(R.id.listViewTimeLap, 7);
        sViewsWithIds.put(R.id.subLayoutCountTime3, 8);
        sViewsWithIds.put(R.id.btnStartCountTime, 9);
        sViewsWithIds.put(R.id.btnStopCountTime, 10);
        sViewsWithIds.put(R.id.btnLapCountTime, 11);
        sViewsWithIds.put(R.id.btnContinueCountTime, 12);
        sViewsWithIds.put(R.id.btnResetCountTime, 13);
    }
    // views
    @NonNull
    private final android.widget.LinearLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentStopWatchBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 14, sIncludes, sViewsWithIds));
    }
    private FragmentStopWatchBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[12]
            , (android.widget.Button) bindings[11]
            , (android.widget.Button) bindings[13]
            , (android.widget.Button) bindings[9]
            , (android.widget.Button) bindings[10]
            , (android.widget.RelativeLayout) bindings[2]
            , (android.widget.ListView) bindings[7]
            , (android.widget.LinearLayout) bindings[3]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.RelativeLayout) bindings[8]
            , (androidx.appcompat.widget.Toolbar) bindings[1]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[5]
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