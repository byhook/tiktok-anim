


可以先看看今日头条效果

![](https://github.com/byhook/tiktok-anim/blob/master/screenshots/toutiao.webp)


## 功能分析

点击列表上的一个`item`,该`item`会放大,最后直接全屏播放小视频,刚开始看上去,以为是个`共享元素的转场动画`,
后来想到,`共享元素`要在android 5.0以上支持,而今日头条显然不会只支持5.0版本以上

笔者想到的一种方案就是进入Activity之后,在`onCreate`的生命周期回调,计算缩放的动画参数,思路如下

1.点击列表上的`item`,获取该`view`的屏幕位置信息,立即传入进入目标比如`VideoPlayAcitvity`中
2.`onCreate`生命周期方法中进行动画播放

看上去也不难,但实际有几个问题,第一步需要设置`VideoPlayAcitvity`透明,并且去掉转场动画

## 功能使用
```
transitionController = new TransitionController.Builder()
        .with(findViewById(R.id.main_root_layer))
        //今日头条默认参数
        .setInterpolator(PathInterpolatorCompat.create(0.32F, 0.94F, 0.6F, 1.0F))
        .duration(320)
        .build();
transitionController.transitionEnter(targetAnimBean, new TransitionCallback() {
    @Override
    public void onTransitionStop() {
        //显示小视频界面评论等控件
    }
});
```

来看看实际效果:

![](https://github.com/byhook/tiktok-anim/blob/master/screenshots/mine.webp)

## 功能实现
给出部分关键的逻辑
```
/**
 * 进入和退出的动画
 * @param enterAnimation 是否入场动画
 * @param animatorListener 监听动画回调
 */
private void transitionStart(boolean enterAnimation, Animator.AnimatorListener animatorListener) {
    //标识我们点击的View在屏幕中可见的高度
    int visibleHeight = transitionParam.bottom - transitionParam.top;

    //计算缩放的宽和高起点,需要和外部的控件宽高看起来一致才比较细腻
    float scaleXStart = (float) transitionParam.width / targetWidth;
    float scaleYStart = (float) transitionParam.height / targetHeight;

    animView.setPivotX(0);
    animView.setPivotY(0);

    int startTransX = transitionParam.left;
    int startTransY;
    if (transitionParam.bottom == targetHeight) {
        //滑动到屏幕底部去了,这时候以点击的控件顶部为位移起始点
        startTransY = transitionParam.top;
    } else if (visibleHeight < transitionParam.height) {
        //滑动到屏幕顶部去了
        startTransY = transitionParam.bottom - transitionParam.height;
    } else {
        startTransY = transitionParam.top;
    }

    if (enterAnimation) {
        //显示动画设置移动的起始位置,关闭动画只指定目标位移位置
        animView.setTranslationX(startTransX);
        animView.setTranslationY(startTransY);
    }

    //设置缩放点
    animView.setScaleX(enterAnimation ? scaleXStart : 1.0F);
    animView.setScaleY(enterAnimation ? scaleYStart : 1.0F);

    viewAnimator = animView.animate();
    //头条参数
    viewAnimator.setInterpolator(timeInterpolator);
    animView.setVisibility(View.VISIBLE);
    viewAnimator.setDuration(duration)
            .setListener(animatorListener)
            .scaleX(enterAnimation ? 1.0F : scaleXStart)
            .scaleY(enterAnimation ? 1.0F : scaleYStart)
            .translationX(enterAnimation ? 0.0F : startTransX)
            .translationY(enterAnimation ? 0.0F : startTransY)
            .start();
}
```

## 手势滑动
手势滑动主要也是拦截触摸事件,计算移动的距离,根据移动的距离,等比的缩放指定的`容器`
`待后续会更新`

[项目地址](https://github.com/byhook/tiktok-anim)
