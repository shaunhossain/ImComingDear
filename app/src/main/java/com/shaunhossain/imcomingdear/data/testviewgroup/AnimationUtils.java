package com.shaunhossain.imcomingdear.data.testviewgroup;

/**
 * Created by adriaboschsaez on 07/02/2018.
 */

import android.animation.Animator;

public interface AnimationUtils extends Animator.AnimatorListener {

    @Override
    default void onAnimationStart(Animator animation) {
    }

    @Override
    default void onAnimationCancel(Animator animation) {
    }

    @Override
    default void onAnimationRepeat(Animator animation) {
    }

}
