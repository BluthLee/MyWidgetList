package com.bluthlee.calendarview;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.NestedScrollingParentHelper;

/**
 * author: DaChao
 * created on: 2019/11/11 16:37
 * description:
 */
public class CalendarNestedScrollingParentHelper extends NestedScrollingParentHelper {

    private ViewGroup viewGroup;

    /**
     * Construct a new helper for a given ViewGroup
     *
     * @param viewGroup
     */
    public CalendarNestedScrollingParentHelper(@NonNull ViewGroup viewGroup) {
        super(viewGroup);
        this.viewGroup = viewGroup;
    }

    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
    }

    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return false;
    }
}
