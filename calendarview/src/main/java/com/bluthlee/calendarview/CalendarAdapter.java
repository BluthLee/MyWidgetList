package com.bluthlee.calendarview;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * author: DaChao
 * created on: 2019/11/8 21:22
 * description:
 */
public abstract class CalendarAdapter<T extends CalendarAdapter.ItemViewHolder> {

    private List<View> viewPool;

    public CalendarAdapter() {
        viewPool = new ArrayList<>();
    }

    public abstract T onCreateCalendarItemViewHolder();

    public abstract void onBindCalendarItemViewHolder(T viewHolder);

    public static class ItemViewHolder {
        public ItemViewHolder(View view) {

        }
    }

}
