package com.onzhou.tiktok;

import android.animation.Animator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.onzhou.transition.StatusBarUtils;
import com.onzhou.transition.TransitionCallback;
import com.onzhou.transition.TransitionController;
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

    private TransitionController transitionController;

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
        if (transitionController != null) {
            transitionController.transitionRelease();
        }
    }

    private void setupView() {
        mIvCover = (ImageView) findViewById(R.id.video_cover);
        targetAnimBean = getIntent().getParcelableExtra(ANIM_PARAM);

        transitionController = new TransitionController.Builder()
                .with(findViewById(R.id.main_root_layer))
                .setInterpolator(PathInterpolatorCompat.create(0.32F, 0.94F, 0.6F, 1.0F))
                .duration(320)
                .build();
        transitionController.transitionEnter(targetAnimBean, new TransitionCallback() {
            @Override
            public void onTransitionStop() {

            }
        });
    }

    @Override
    public void onBackPressed() {
        if (targetAnimBean != null) {
            transitionController.transitionExit(new TransitionCallback() {
                @Override
                public void onTransitionStop() {
                    finish();
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
