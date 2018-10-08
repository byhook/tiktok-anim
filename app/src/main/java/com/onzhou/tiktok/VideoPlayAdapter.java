package com.onzhou.tiktok;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @anchor: Andy
 * @date: 2018-10-08
 * @description:
 */
public class VideoPlayAdapter extends RecyclerView.Adapter<VideoPlayViewHolder> {

    @Override
    public VideoPlayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_video_cover_item, null);
        return new VideoPlayViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(VideoPlayViewHolder holder, int position) {
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SwitchAnimBean animBean = new SwitchAnimBean();
                animBean.width = view.getMeasuredWidth();
                animBean.height = view.getMeasuredHeight();
                Rect visibleRect = new Rect();
                view.getGlobalVisibleRect(visibleRect);
                animBean.pivotX = visibleRect.left;
                animBean.pivotY = 0;
                VideoPlayActivity.intentStart(view.getContext(), animBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
