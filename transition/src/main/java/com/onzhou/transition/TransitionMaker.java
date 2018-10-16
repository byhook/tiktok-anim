package com.onzhou.transition;

import android.animation.Animator;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * @anchor: andy
 * @date: 18-10-16
 */

public interface TransitionMaker {

    /**
     * 进入转场动画
     */
    void transitionEnter(TransitionParam param);

    /**
     * 退出转场动画
     */
    void transitionExit(Animator.AnimatorListener animatorListener);

    /**
     * 释放资源
     */
    void transitionRelease();

}
