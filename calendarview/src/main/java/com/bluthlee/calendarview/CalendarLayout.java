package com.bluthlee.calendarview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.view.NestedScrollingParent3;


/**
 * author: DaChao
 * created on: 2019/11/5 17:32
 * description:
 */
public class CalendarLayout extends ViewGroup implements NestedScrollingParent3 {

    private final int STATUS_FOLD = 0, STATUS_EXPANDING = 1, STATUS_EXPAND = 2;

    private int status = STATUS_FOLD;

    /**
     * 折叠的高度，等于展开状态的高度减去折叠状态的高度
     */
    private int foldPx = 0;

    private CalendarNestedScrollingParentHelper nestedScrollingParentHelper;
    private CalendarAdapter mAdapter;

    public CalendarLayout(Context context) {
        super(context);
        init();
    }

    public CalendarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CalendarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CalendarLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //类似linearlayout的纵向线性布局

        int maxWidth = 0;
        int childState = 0;
        int totalHeight = getPaddingLeft() + getPaddingRight();

        int calendarViewIndex = -1;

        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);

            if (child instanceof CalendarView) {
                calendarViewIndex = i;
            }
            int heightUsed = totalHeight;
            if (calendarViewIndex != -1 && i > calendarViewIndex) {
            }
            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, totalHeight);
            final LayoutParams lp = (LayoutParams) child.getLayoutParams();
            maxWidth = Math.max(maxWidth, child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin);
            totalHeight += child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            childState = combineMeasuredStates(childState, child.getMeasuredState());
        }

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, childState),
                resolveSizeAndState(totalHeight, heightMeasureSpec,
                        childState << MEASURED_HEIGHT_STATE_SHIFT));
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int totalTop = getPaddingTop();
        for (int i = 0; i < getChildCount(); i++) {
            final View child = getChildAt(i);
            final int childWidth = child.getMeasuredWidth();
            final int childHeight = child.getMeasuredHeight();
            int childLeft = getPaddingLeft();

            child.layout(childLeft, totalTop, childLeft + childWidth, totalTop + childHeight);

            if (status == STATUS_FOLD) {
                child.offsetTopAndBottom(-100);
            }

            totalTop += childHeight;
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        if (p instanceof LayoutParams) {
            return new LayoutParams((LayoutParams) p);
        } else if (p instanceof MarginLayoutParams) {
            return new LayoutParams((MarginLayoutParams) p);
        }
        return new LayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new LayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type, @NonNull int[] consumed) {
        nestedScrollingParentHelper.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed, type, consumed);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull View child, @NonNull View target, int axes, int type) {
        return nestedScrollingParentHelper.onStartNestedScroll(child, target, axes, type);
    }

    @Override
    public void onNestedScrollAccepted(@NonNull View child, @NonNull View target, int axes, int type) {

    }

    @Override
    public void onStopNestedScroll(@NonNull View target, int type) {

    }

    @Override
    public void onNestedScroll(@NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed, int type) {

    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    private void init() {
        nestedScrollingParentHelper = new CalendarNestedScrollingParentHelper(this);
    }

    private int getStatus() {
        return status;
    }

    public void setAdapter(CalendarAdapter adapter) {
        mAdapter = adapter;
    }

    public static class LayoutParams extends MarginLayoutParams {
        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source) {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }

        public LayoutParams(LayoutParams source) {
            super(source);
        }
    }

}
