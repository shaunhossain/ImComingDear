package com.shaunhossain.imcomingdear.data.testviewgroup;

import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.shaunhossain.imcomingdear.data.utils.Utils;
import com.shaunhossain.imcomingdear.data.view.adapter.SwipeAdapter;

/**
 * Created by adriaboschsaez on 02/02/2018.
 */

public class SwipeViewHelper implements View.OnTouchListener {

    public static final String TAG = SwipeViewHelper.class.getSimpleName();

    public static final int ANIMATION_DURATION = 400;
    public static final float SWIPE_ROTATION = 45f;
    public static final float VIEW_FINAL_OPACITY = 0f;

    public static final float SCREER_PARTITION = 1f / 4;

    public static final int MAX_TIME_TO_ACTIVATE_TOUCH = 1000;
    public static final float MIN_DISTANCE_TO_SWIPE = Utils.convertDpToPixel(12);

    private SwipeView swipeView;
    private View observedView;
    private SwipeAdapter.ViewHolder viewHolder;

    private boolean listenForTouchEvents;
    private float initialViewX;
    private float initialViewY;
    private boolean isInitialTouchHalfBottom;
    private float maxDistanceX;
    private float maxDistanceY;
    private int pointerId;
    private float initialTouchX;
    private float initialTouchY;
    private long timeOfTouch;

    public SwipeViewHelper(SwipeView swipeView) {

        this.swipeView = swipeView;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (!listenForTouchEvents || !swipeView.isEnabled())
            return false;

        switch (event.getAction() & MotionEvent.ACTION_MASK) {

            case MotionEvent.ACTION_DOWN:

                pointerId = event.getPointerId(0);

                initialTouchX = event.getX(0);
                initialTouchY = event.getY(0);

                if (initialTouchY > observedView.getHeight() / 2)
                    isInitialTouchHalfBottom = true;
                else
                    isInitialTouchHalfBottom = false;

                timeOfTouch = System.currentTimeMillis();

                observedView.animate().cancel();

                return true;

            case MotionEvent.ACTION_MOVE:


                int pointerIndex = event.findPointerIndex(pointerId);

                if (pointerIndex < 0) return false;

                float displacementX = event.getX(pointerIndex) - initialTouchX;
                float displacementY = event.getY(pointerIndex) - initialTouchY;

                float newX = observedView.getX() + displacementX;
                float newY = observedView.getY() + displacementY;

                checkMaxDistance(newX, newY);

                observedView.setX(newX);
                observedView.setY(newY);

                float dragX = newX - initialViewX;

                float swipeProgress = Math.min(Math.max(
                        dragX / swipeView.getWidth(), -1), 1);

                setAlphaEffects(swipeProgress);

                float rotation = SWIPE_ROTATION * swipeProgress;

                if (isInitialTouchHalfBottom)
                    observedView.setRotation(-rotation);
                else
                    observedView.setRotation(rotation);

                return true;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:

                if (pointerId == event.getPointerId(event.getActionIndex())) {

                    pointerId = -1;

                    if (System.currentTimeMillis() - timeOfTouch <= MAX_TIME_TO_ACTIVATE_TOUCH) {

                        if (maxDistanceX < MIN_DISTANCE_TO_SWIPE && maxDistanceY < MIN_DISTANCE_TO_SWIPE) {


                            observedView.setX(initialViewX);
                            observedView.setY(initialViewY);
                            observedView.setRotation(0f);

                            resetAlphaEffects();

                            swipeView.onTopViewTouched();

                            return true;
                        }
                    }
                    maxDistanceX = 0;
                    maxDistanceY = 0;

                    checkViewPosition();

                    return true;
                }
        }
        return false;
    }

    private void checkMaxDistance(float newX, float newY) {

        float distanceX = Math.abs(newX - initialViewX);
        float distanceY = Math.abs(newY - initialViewY);

        if (maxDistanceX < distanceX)
            maxDistanceX = distanceX;

        if (maxDistanceY < distanceY)
            maxDistanceY = distanceY;
    }

    private void setAlphaEffects(float swipeProgress) {

        boolean isSwipeProgressLT0 = (swipeProgress < 0) ? true : false;

        if (isSwipeProgressLT0) {
            swipeProgress = -swipeProgress;
        }

        float progress = Math.min(swipeProgress / SCREER_PARTITION, 1);

        viewHolder.dislikeButton.setAlpha(1f - progress);
        viewHolder.likeButton.setAlpha(1f - progress);

        if (isSwipeProgressLT0)
            viewHolder.dislikeView.setAlpha(progress);
        else
            viewHolder.likeView.setAlpha(progress);
    }

    private void resetAlphaEffects() {

        viewHolder.dislikeButton.setAlpha(1f);
        viewHolder.likeButton.setAlpha(1f);
        viewHolder.dislikeView.setAlpha(0f);
        viewHolder.likeView.setAlpha(0f);
    }

    private void checkViewPosition() {

        float viewCenterX = observedView.getX() + (observedView.getWidth() / 2);
        float xMaxToSwipeLeft = swipeView.getWidth() * SCREER_PARTITION;
        float xMinToSwipeRight = swipeView.getWidth() - xMaxToSwipeLeft;

        if (viewCenterX < xMaxToSwipeLeft) {

            swipeViewToLeft();

        } else if (viewCenterX > xMinToSwipeRight) {

            swipeViewToRight();

        } else {

            resetAlphaEffects();
            resetPosition();
        }
    }

    public void swipeViewToLeft() {

        if (!listenForTouchEvents)
            return;

        deactivateEvents();

        listenForTouchEvents = false;

        float swipeRotation = isInitialTouchHalfBottom ? SWIPE_ROTATION : -SWIPE_ROTATION;

        observedView.animate().cancel();
        observedView.animate()
                .x(-swipeView.getWidth() + observedView.getX())
                .rotation(swipeRotation)
                .alpha(VIEW_FINAL_OPACITY)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new DecelerateInterpolator())
                .setListener((AnimationUtils) (s) -> {

                    isInitialTouchHalfBottom = false;
                    resetAlphaEffects();
                    swipeView.onTopViewSwipedToLeft();
                });
    }

    public void swipeViewToRight() {

        if (!listenForTouchEvents)
            return;

        deactivateEvents();

        float swipeRotation = isInitialTouchHalfBottom ? -SWIPE_ROTATION : SWIPE_ROTATION;

        observedView.animate().cancel();
        observedView.animate()
                .x(swipeView.getWidth() + observedView.getX())
                .rotation(swipeRotation)
                .alpha(VIEW_FINAL_OPACITY)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new DecelerateInterpolator())
                .setListener((AnimationUtils) (s) -> {

                    isInitialTouchHalfBottom = false;
                    resetAlphaEffects();
                    swipeView.onTopViewSwipedToRight();
                });
    }

    private void resetPosition() {
        
        observedView.animate()
                .x(initialViewX)
                .y(initialViewY)
                .rotation(0f)
                .alpha(1f)
                .setDuration(ANIMATION_DURATION)
                .setInterpolator(new OvershootInterpolator())
                .setListener(null);
    }

    private void deactivateEvents() {

        viewHolder.dislikeButton.setOnClickListener(null);
        viewHolder.likeButton.setOnClickListener(null);
        listenForTouchEvents = false;
    }

    public void registerObservedView(@NonNull View view) {

        observedView = view;
        viewHolder = (SwipeAdapter.ViewHolder) view.getTag();
        initialViewX = swipeView.getPaddingLeft();
        initialViewY = swipeView.getPaddingTop();
        view.setOnTouchListener(this);
        listenForTouchEvents = true;
    }

    public void unregisterObservedView() {
        if (observedView != null)
            observedView.setOnTouchListener(null);

        listenForTouchEvents = false;
        observedView = null;
    }

}
