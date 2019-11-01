package com.onzhou.tiktok;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

/**
 * 作者: andy
 * 时间: 2017-01-03
 * 描述:
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 高度
     * 资源
     */
    private int resItemDimen = 2;

    /**
     * 分割线的画笔
     * 分割
     */
    private Paint mPaint;

    public GridItemDecoration() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = resItemDimen;
        if (parent.indexOfChild(view) % 2 == 0) {
            outRect.right = resItemDimen;
        } else {
            outRect.right = 0;
        }
    }
}
