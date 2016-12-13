package com.duanlian.daimeng.ui.view.pull_refresh;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.duanlian.daimeng.R;

/**
 * 给下拉刷新添加的头布局,也可以根据自己的喜好去自定义其他的布局
 */
public class RefreshHeaderOne extends FrameLayout implements RefreshHeader {


    private Animation rotate_up;
    private Animation rotate_down;
    private Animation sun_traslate;
    private TextView textView;
    private View arrowIcon;
    private View successIcon;
    private View loadingIcon;
    private View sun;
    AnimationDrawable mAnimation;



    public RefreshHeaderOne(Context context) {
        this(context, null);
    }

    public RefreshHeaderOne(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化动画
        rotate_up = AnimationUtils.loadAnimation(context, R.anim.rotate_up);
        rotate_down = AnimationUtils.loadAnimation(context, R.anim.rotate_down);
        sun_traslate = AnimationUtils.loadAnimation(context, R.anim.translation_sun);
        inflate(context, R.layout.header_one, this);
        textView = (TextView) findViewById(R.id.text);
        arrowIcon = findViewById(R.id.arrowIcon);
        successIcon = findViewById(R.id.successIcon);
        loadingIcon = findViewById(R.id.loadingIcon);
        sun = findViewById(R.id.sun);
        loadingIcon.setBackgroundResource(R.drawable.dianqiu);
        mAnimation = (AnimationDrawable) loadingIcon.getBackground();

    }

    @Override
    public void reset() {
        textView.setText("下拉刷新");
        successIcon.setVisibility(INVISIBLE);
        arrowIcon.setVisibility(VISIBLE);
        arrowIcon.clearAnimation();
        loadingIcon.setVisibility(INVISIBLE);
        loadingIcon.clearAnimation();
    }

    @Override
    public void pull() {
        sun.startAnimation(sun_traslate);
    }

    @Override
    public void refreshing() {
        arrowIcon.setVisibility(INVISIBLE);
        loadingIcon.setVisibility(VISIBLE);
        textView.setText("正在刷新...");
        mAnimation.start();
        arrowIcon.clearAnimation();
    }

    @Override
    public void onPositionChange(float currentPos, float lastPos, float refreshPos, boolean isTouch, State state) {
        // 往上拉
        if (currentPos < refreshPos && lastPos >= refreshPos) {
            if (isTouch && state == State.PULL) {
                textView.setText("下拉刷新");
                arrowIcon.clearAnimation();
                arrowIcon.startAnimation(rotate_down);
            }
            // 往下拉
        } else if (currentPos > refreshPos && lastPos <= refreshPos) {
            if (isTouch && state == State.PULL) {
                textView.setText("释放立即刷新");
                arrowIcon.clearAnimation();
                arrowIcon.startAnimation(rotate_up);
            }
        }
    }

    @Override
    public void complete() {
        loadingIcon.setVisibility(INVISIBLE);
        loadingIcon.clearAnimation();
        successIcon.setVisibility(VISIBLE);
        textView.setText("刷新成功");
    }
}
