package com.grum_i_lendvaj.ljubimciapp.view;

import android.content.Context;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewParent;
import android.widget.TimePicker;

/**
 * Created by vilim on 03-Feb-17.
 */

public class AggressiveTimePicker extends TimePicker {

    public AggressiveTimePicker(Context context) {
        super(context);
    }

    public AggressiveTimePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AggressiveTimePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(21)
    public AggressiveTimePicker(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        ViewParent parent = getParent();
        if (parent != null)
            parent.requestDisallowInterceptTouchEvent(true);
        return false;
    }
}
