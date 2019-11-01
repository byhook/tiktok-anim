package com.onzhou.tiktok;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

/**
 * @anchor: andy
 * @date: 2018-10-08
 * @description:
 */
public class VideoCoverLayer extends AppCompatImageView {

    public VideoCoverLayer(@NonNull Context context) {
        this(context, null);
    }

    public VideoCoverLayer(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VideoCoverLayer(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        //测试直接硬编码,下一个页面是全屏显示
        Rect location = new Rect();
        View rootView = (View) getParent();
        rootView.getGlobalVisibleRect(location);

        int height = (int) ((float) width / location.right * location.bottom);
        super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY));
    }
}
