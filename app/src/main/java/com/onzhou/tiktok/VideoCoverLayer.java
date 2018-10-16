package com.onzhou.tiktok;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * @anchor: andy
 * @date: 2018-10-08
 * @description:
 */
public class VideoCoverLayer extends AppCompatImageView {

    public VideoCoverLayer(@NonNull Context context) {
        super(context);
    }

    public VideoCoverLayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public VideoCoverLayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //测试直接硬编码
        Rect rect = new Rect();
        ViewGroup viewGroup = (ViewGroup) getParent();
        viewGroup.getGlobalVisibleRect(rect);
        int height = (int) ((float) width / rect.right * rect.bottom);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
