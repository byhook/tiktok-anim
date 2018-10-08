package com.onzhou.tiktok;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者: Andy
 * 时间: 2017-01-03
 * 描述:
 * 发现模块
 * 分割线
 */

public class GridItemDecoration extends RecyclerView.ItemDecoration {

    /**
     * 高度
     * 资源
     */
    private int resItemDimen = 10;

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
        outRect.right = resItemDimen;
    }
}
