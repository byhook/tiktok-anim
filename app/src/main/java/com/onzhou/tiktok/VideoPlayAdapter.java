package com.onzhou.tiktok;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.onzhou.transition.TransitionParam;
import com.onzhou.transition.TransitionUtils;

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
                TransitionParam animBean = TransitionUtils.getSourceViewParam(view);
                VideoPlayActivity.intentStart(view.getContext(), animBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
