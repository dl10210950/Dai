package com.duanlian.daimeng.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseActivity;
import com.duanlian.daimeng.ui.view.CountDownView;

/**
 * 欢迎页
 */
public class SplashActivity extends BaseActivity {
    private CountDownView mCountDownView;
    int count = 0;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_splash);

    }

    @Override
    public void initView() {
        mCountDownView = (CountDownView) findViewById(R.id.countdown);
        mCountDownView.start();
        mCountDownView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 1;
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        });
        mCountDownView.setCountDownTimerListener(new CountDownView.CountDownTimerListener() {
            @Override
            public void onStartCount() {

            }

            @Override
            public void onFinishCount() {
                if (count == 0) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                } else {
                    finish();
                }


            }
        });

    }

    @Override
    public void initPresenter() {

    }

}
