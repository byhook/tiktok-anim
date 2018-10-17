package com.onzhou.transition;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;

/**
 * @anchor: andy
 * @date: 18-10-16
 */

public class TransitionUtils {

    /**
     * 获取源View的数据
     *
     * @param sourceView
     * @return
     */
    public static TransitionParam getSourceViewParam(View sourceView) {
        if (sourceView == null) {
            throw new NullPointerException("source view is null");
        } else {
            int width = sourceView.getMeasuredWidth();
            int height = sourceView.getMeasuredHeight();
            if (width > 0 && height > 0) {
                //宽高都有效
                TransitionParam animBean = new TransitionParam();
                animBean.width = width;
                animBean.height = height;
                Rect visibleRect = new Rect();
                sourceView.getGlobalVisibleRect(visibleRect);

                animBean.left = visibleRect.left;
                animBean.right = visibleRect.right;
                animBean.top = visibleRect.top;
                animBean.bottom = visibleRect.bottom;
                return animBean;
            }
        }
        return null;
    }


    public static void finishTransition(Activity activity) {
        if (activity != null) {
            activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        }
    }

}
