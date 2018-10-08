package com.onzhou.tiktok;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * @anchor: Andy
 * @date: 2018-10-08
 * @description:
 */
public class VideoPlayActivity extends FragmentActivity {

    private ImageView mIvCover;

    private SwitchAnimBean mSwitchAnimBean;

    public static void intentStart(Context context, SwitchAnimBean animBean) {
        Intent intent = new Intent(context, VideoPlayActivity.class);
        intent.putExtra("animBean", animBean);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);
        setupView();
    }

    private void setupView() {
        mIvCover = (ImageView) findViewById(R.id.video_cover);

        mSwitchAnimBean = getIntent().getParcelableExtra("animBean");
        setupAnim(true, null);
    }

    private void setupAnim(boolean show, Animator.AnimatorListener animatorListener) {
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        int heightPixels = getResources().getDisplayMetrics().heightPixels - 66;
        float scaleXStart = (float) mSwitchAnimBean.width / widthPixels;
        float scaleYStart = (float) mSwitchAnimBean.height / heightPixels;

        final PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", show ? scaleXStart : 1.0F, show ? 1.0F : scaleXStart);
        final PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", show ? scaleYStart : 1.0F, show ? 1.0F : scaleYStart);
        ObjectAnimator mAlphaAnimator = ObjectAnimator.ofPropertyValuesHolder(mIvCover, scaleX, scaleY);
        mIvCover.setPivotX(mSwitchAnimBean.pivotX);
        mIvCover.setPivotY(mSwitchAnimBean.pivotY);

        mAlphaAnimator.setDuration(2000);
        if (animatorListener != null) {
            mAlphaAnimator.addListener(animatorListener);
        }
        mAlphaAnimator.setInterpolator(new LinearInterpolator());
        mAlphaAnimator.start();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:

                break;
            case MotionEvent.ACTION_MOVE:

                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        if (mSwitchAnimBean != null) {
            setupAnim(false, null);
            mIvCover.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 230);
        } else {
            finish();
        }
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
