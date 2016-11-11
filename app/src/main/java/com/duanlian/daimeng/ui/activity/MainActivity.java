package com.duanlian.daimeng.ui.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseActivity;
import com.duanlian.daimeng.ui.view.shinebutton.ShineButton;

import java.util.Map;

/**
 * 主Activity
 */
public class MainActivity extends BaseActivity implements ShineButton.OnCheckedChangeListener{
    private ShineButton music_btn,video_btn,book_btn, news_btn;
    private TextView music_tv,video_tv,book_tv, news_tv;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_main);

    }



    @Override
    public void initView() {
        music_btn = (ShineButton) findViewById(R.id.main_shine_btn_music);
        video_btn = (ShineButton) findViewById(R.id.main_shine_btn_video);
        book_btn = (ShineButton) findViewById(R.id.main_shine_btn_book);
        news_btn = (ShineButton) findViewById(R.id.main_shine_btn_news);
        music_tv = (TextView) findViewById(R.id.main_music_tv);
        video_tv = (TextView) findViewById(R.id.main_video_tv);
        book_tv = (TextView) findViewById(R.id.main_book_tv);
        news_tv = (TextView) findViewById(R.id.main_news_tv);
        music_btn.setOnCheckStateChangeListener(this);
        video_btn.setOnCheckStateChangeListener(this);
        book_btn.setOnCheckStateChangeListener(this);
        news_btn.setOnCheckStateChangeListener(this);
        changeState("music");
        music_btn.setChecked(true);
    }

    @Override
    public void initPresenter() {

    }



    private void changeState(String type) {
        switch (type) {
            case "music":
                music_tv.setTextColor(Color.parseColor("#FF0066"));
                video_tv.setTextColor(Color.parseColor("#868686"));
                book_tv.setTextColor(Color.parseColor("#868686"));
                news_tv.setTextColor(Color.parseColor("#868686"));
                video_btn.setChecked(false);
                book_btn.setChecked(false);
                news_btn.setChecked(false);
                break;
            case "video":
                music_tv.setTextColor(Color.parseColor("#868686"));
                video_tv.setTextColor(Color.parseColor("#FF0066"));
                book_tv.setTextColor(Color.parseColor("#868686"));
                news_tv.setTextColor(Color.parseColor("#868686"));
                music_btn.setChecked(false);
                book_btn.setChecked(false);
                news_btn.setChecked(false);
                break;
            case "book":
                music_tv.setTextColor(Color.parseColor("#868686"));
                video_tv.setTextColor(Color.parseColor("#868686"));
                book_tv.setTextColor(Color.parseColor("#FF0066"));
                news_tv.setTextColor(Color.parseColor("#868686"));
                music_btn.setChecked(false);
                video_btn.setChecked(false);
                news_btn.setChecked(false);
                break;
            case "news":
                music_tv.setTextColor(Color.parseColor("#868686"));
                video_tv.setTextColor(Color.parseColor("#868686"));
                book_tv.setTextColor(Color.parseColor("#868686"));
                news_tv.setTextColor(Color.parseColor("#FF0066"));
                music_btn.setChecked(false);
                book_btn.setChecked(false);
                video_btn.setChecked(false);
                break;
        }

    }

    @Override
    public void onCheckedChanged(View view, boolean checked) {
        switch (view.getId()) {
            case R.id.main_shine_btn_music:
                if (checked)changeState("music");
                break;
            case R.id.main_shine_btn_video:
                if (checked)changeState("video");
                break;
            case R.id.main_shine_btn_book:
                if (checked)changeState("book");
                break;
            case R.id.main_shine_btn_news:
                if (checked)changeState("news");
                break;
        }
    }
}
