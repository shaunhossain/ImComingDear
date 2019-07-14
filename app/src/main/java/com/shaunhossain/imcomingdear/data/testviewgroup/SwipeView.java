package com.shaunhossain.imcomingdear.data.testviewgroup;

import android.content.Context;
import android.database.DataSetObserver;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.FrameLayout;

import java.util.HashMap;


public class SwipeView extends ViewGroup {

    public static final String TAG = SwipeView.class.getSimpleName();

    public static final int STACK_SIZE = 3;

    private SwipeViewHelper swipeViewHelper;
    private Adapter adapter;
    private DataSetObserver dataSetObserver;
    private SwipeViewListener swipeViewListener;
    private View topView;
    private int viewIndex;
    private HashMap<Integer, View> viewPool;


    public SwipeView(Context context) {
        this(context, null);
    }

    public SwipeView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize() {

        swipeViewHelper = new SwipeViewHelper(this);
        viewPool = new HashMap<>();
        dataSetObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                dataSetChanged();
            }
        };

        setClipToPadding(false);
        setClipChildren(false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        for (int i = getChildCount();
             i < STACK_SIZE && viewIndex < adapter.getCount();
             i++) {

            View child = adapter.getView(viewIndex, getViewFromPool(), this);

            child.setLayerType(LAYER_TYPE_HARDWARE, null);

            int width = getWidth() - (getPaddingLeft() + getPaddingRight());
            int height = getHeight() - (getPaddingTop() + getPaddingBottom());

            LayoutParams params = child.getLayoutParams();
            if (params == null) {

                params = new LayoutParams(
                        FrameLayout.LayoutParams.WRAP_CONTENT,
                        FrameLayout.LayoutParams.WRAP_CONTENT);
            }

            int measureSpecWidth = MeasureSpec.AT_MOST;
            int measureSpecHeight = MeasureSpec.AT_MOST;

            if (params.width == LayoutParams.MATCH_PARENT) {
                measureSpecWidth = MeasureSpec.EXACTLY;
            }

            if (params.height == LayoutParams.MATCH_PARENT) {
                measureSpecHeight = MeasureSpec.EXACTLY;
            }

            child.measure(measureSpecWidth | width, measureSpecHeight | height);
            addViewInLayout(child, 0, params, true);

            child.layout(getPaddingLeft(), getPaddingTop(), getPaddingLeft() + child.getMeasuredWidth(), getPaddingTop() + child.getMeasuredHeight());

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                child.setTranslationZ(-i);
            }

            viewIndex++;
        }

        swipeViewHelper.unregisterObservedView();
        topView = getChildAt(getChildCount() - 1);
        swipeViewHelper.registerObservedView(topView);
    }

    private void dataSetChanged() {

        viewIndex = getChildCount();
        invalidate();
        requestLayout();
    }

    private View getViewFromPool() {

        int size = viewPool.size();

        if (size == 0)
            return null;

        View view = viewPool.get(size - 1);
        viewPool.remove(size - 1);

        return view;
    }

    private void addViewToPool(View view) {

        int size = viewPool.size();
        viewPool.put(size, view);
    }

    private void resetView() {

        topView.setX(getPaddingLeft());
        topView.setY(getPaddingTop());
        topView.setRotation(0f);
        topView.setAlpha(1f);
    }

    private void removeTopView() {

        swipeViewHelper.unregisterObservedView();
        resetView();
        addViewToPool(topView);
        removeView(topView);

        if (viewIndex == adapter.getCount()) {

            swipeViewListener.onViewAboutToEmpty();
        }
    }

    public int getCurrentPosition() {
        return viewIndex - getChildCount();
    }

    public View getTopView() {
        return topView;
    }

    public void setAdapter(@NonNull Adapter newAdapter) {
        if (adapter != null) {
            adapter.unregisterDataSetObserver(dataSetObserver);
        }
        adapter = newAdapter;
        adapter.registerDataSetObserver(dataSetObserver);
    }

    public void setSwipeViewListener(SwipeViewListener listener) {
        swipeViewListener = listener;
    }

    public void onTopViewSwipedToLeft() {

        swipeViewListener.onTopViewSwipedToLeft(getCurrentPosition());

        removeTopView();
    }

    public void onTopViewSwipedToRight() {

        swipeViewListener.onTopViewSwipedToRight(getCurrentPosition());

        removeTopView();
    }

    public void onTopViewTouched() {

        swipeViewListener.onTopViewTouched(getCurrentPosition());
    }

    public void swipeToLeft(View v) {

        if (v != topView)
            return;

        swipeViewHelper.swipeViewToLeft();
    }

    public void swipeToRight(View v) {

        if (v != topView)
            return;

        swipeViewHelper.swipeViewToRight();
    }

    public interface SwipeViewListener {

        void onTopViewSwipedToLeft(int position);

        void onTopViewSwipedToRight(int position);

        void onTopViewTouched(int position);

        void onViewAboutToEmpty();
    }

}
