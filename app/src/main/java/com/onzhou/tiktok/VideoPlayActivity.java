package com.onzhou.tiktok;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.onzhou.transition.StatusBarUtils;
import com.onzhou.transition.TransitionController;
import com.onzhou.transition.TransitionMaker;
import com.onzhou.transition.TransitionParam;
import com.onzhou.transition.TransitionUtils;

/**
 * @anchor: Andy
 * @date: 2018-10-08
 * @description:
 */
public class VideoPlayActivity extends FragmentActivity {

    private static final String ANIM_PARAM = "ANIM_PARAM";

    /**
     * 封面图
     */
    private ImageView mIvCover;

    /**
     * 外部控件位置参数
     */
    private TransitionParam targetAnimBean;

    private TransitionMaker transitionMaker;

    public static void intentStart(Context context, TransitionParam animBean) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra(ANIM_PARAM, animBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.enableStatusBar(this, true);
        setContentView(R.layout.activity_video_play);
        setupView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (transitionMaker != null) {
            transitionMaker.transitionRelease();
        }
    }

    private void setupView() {
        mIvCover = (ImageView) findViewById(R.id.video_cover);
        targetAnimBean = getIntent().getParcelableExtra(ANIM_PARAM);

        //获取目标宽高,即我们当前需要动画处理最终缩放的宽高
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        int targetWidth = displayMetrics.widthPixels;
        int targetHeight = displayMetrics.heightPixels;

        transitionMaker = new TransitionController.Builder()
                .with(mIvCover)
                .setInterpolator(PathInterpolatorCompat.create(0.32F, 0.94F, 0.6F, 1.0F))
                .duration(320)
                .targetWH(targetWidth, targetHeight)
                .build();

        transitionMaker.transitionEnter(targetAnimBean);
    }

    @Override
    public void onBackPressed() {
        if (targetAnimBean != null) {
            transitionMaker.transitionExit(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        } else {
            finish();
        }
    }


    @Override
    public void finish() {
        super.finish();
        TransitionUtils.finishTransition(this);
    }
}
