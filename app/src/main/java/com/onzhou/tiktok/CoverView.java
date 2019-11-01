package com.onzhou.tiktok;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @anchor: Andy
 * @date: 2018-10-17
 * @description:
 */
public class CoverView extends AppCompatImageView {

    public CoverView(Context context) {
        super(context);
    }

    public CoverView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoverView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int width = (int) ((float) height / 16 * 9);
        super.onMeasure(heightMeasureSpec, heightMeasureSpec);
    }
}
