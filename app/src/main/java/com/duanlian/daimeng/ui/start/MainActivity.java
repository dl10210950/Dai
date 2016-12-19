package com.duanlian.daimeng.ui.start;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseActivity;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.ui.book.BookFragment;
import com.duanlian.daimeng.ui.joke.JokeFragment;
import com.duanlian.daimeng.ui.music.MusicFragment;
import com.duanlian.daimeng.ui.news.NewsFragment;
import com.duanlian.daimeng.ui.video.VideoFragment;
import com.duanlian.daimeng.ui.view.shinebutton.ShineButton;

/**
 * 主Activity
 */
public class MainActivity extends BaseActivity implements ShineButton.OnCheckedChangeListener {
    private ShineButton music_btn, video_btn, book_btn, news_btn, joke_btn;
    private TextView music_tv, video_tv, book_tv, news_tv, joke_tv;

    @Override
    public void initContentView() {
        setContentView(R.layout.activity_main);
    }


    @Override
    public void initView() {
        Intent intent = getIntent();
        int intentType = intent.getIntExtra("intentType", 1);

        music_btn = (ShineButton) findViewById(R.id.main_shine_btn_music);
        joke_btn = (ShineButton) findViewById(R.id.main_shine_btn_joke);
        video_btn = (ShineButton) findViewById(R.id.main_shine_btn_video);
        book_btn = (ShineButton) findViewById(R.id.main_shine_btn_book);
        news_btn = (ShineButton) findViewById(R.id.main_shine_btn_news);
        music_tv = (TextView) findViewById(R.id.main_music_tv);
        video_tv = (TextView) findViewById(R.id.main_video_tv);
        book_tv = (TextView) findViewById(R.id.main_book_tv);
        news_tv = (TextView) findViewById(R.id.main_news_tv);
        joke_tv = (TextView) findViewById(R.id.main_joke_tv);
        music_btn.setOnCheckStateChangeListener(this);
        video_btn.setOnCheckStateChangeListener(this);
        book_btn.setOnCheckStateChangeListener(this);
        news_btn.setOnCheckStateChangeListener(this);
        joke_btn.setOnCheckStateChangeListener(this);
        if (intentType == 4) {
            changeState("news");
            news_btn.setChecked(true);
        } else {
            changeState("joke");
            joke_btn.setChecked(true);
        }
    }

    @Override
    public void initPresenter() {

    }

    /**
     * 改变按钮颜色和属性
     *
     * @param type
     */
    private void changeState(String type) {
        int color_red = Color.parseColor("#FF0066");
        int color_gray = Color.parseColor("#868686");
        switch (type) {
            case "music":
                music_tv.setTextColor(color_red);
                video_tv.setTextColor(color_gray);
                book_tv.setTextColor(color_gray);
                news_tv.setTextColor(color_gray);
                joke_tv.setTextColor(color_gray);
                music_btn.setClickable(false);
                video_btn.setClickable(true);
                book_btn.setClickable(true);
                news_btn.setClickable(true);
                joke_btn.setClickable(true);
                video_btn.setChecked(false);
                book_btn.setChecked(false);
                news_btn.setChecked(false);
                joke_btn.setChecked(false);
                replaceFregment(MusicFragment.newInstance());
                break;
            case "video":
                music_tv.setTextColor(color_gray);
                video_tv.setTextColor(color_red);
                book_tv.setTextColor(color_gray);
                news_tv.setTextColor(color_gray);
                joke_tv.setTextColor(color_gray);
                music_btn.setClickable(true);
                video_btn.setClickable(false);
                book_btn.setClickable(true);
                news_btn.setClickable(true);
                joke_btn.setClickable(true);
                joke_btn.setChecked(false);
                music_btn.setChecked(false);
                book_btn.setChecked(false);
                news_btn.setChecked(false);
                replaceFregment(VideoFragment.newInstance());
                break;
            case "book":
                music_tv.setTextColor(color_gray);
                video_tv.setTextColor(color_gray);
                book_tv.setTextColor(color_red);
                news_tv.setTextColor(color_gray);
                joke_tv.setTextColor(color_gray);
                music_btn.setClickable(true);
                video_btn.setClickable(true);
                book_btn.setClickable(false);
                news_btn.setClickable(true);
                joke_btn.setClickable(true);
                music_btn.setChecked(false);
                video_btn.setChecked(false);
                news_btn.setChecked(false);
                joke_btn.setChecked(false);
                joke_btn.setClickable(true);
                joke_btn.setChecked(false);
                replaceFregment(BookFragment.newInstance());
                break;
            case "news":
                music_tv.setTextColor(color_gray);
                video_tv.setTextColor(color_gray);
                book_tv.setTextColor(color_gray);
                news_tv.setTextColor(color_red);
                joke_tv.setTextColor(color_gray);
                music_btn.setClickable(true);
                video_btn.setClickable(true);
                book_btn.setClickable(true);
                news_btn.setClickable(false);
                joke_btn.setClickable(true);

                music_btn.setChecked(false);
                book_btn.setChecked(false);
                video_btn.setChecked(false);
                joke_btn.setChecked(false);
                replaceFregment(NewsFragment.newInstance());
                break;
            case "joke":
                music_tv.setTextColor(color_gray);
                video_tv.setTextColor(color_gray);
                book_tv.setTextColor(color_gray);
                news_tv.setTextColor(color_gray);
                joke_tv.setTextColor(color_red);
                music_btn.setClickable(true);
                video_btn.setClickable(true);
                book_btn.setClickable(true);
                news_btn.setClickable(true);
                joke_btn.setClickable(false);
                news_btn.setChecked(false);
                music_btn.setChecked(false);
                book_btn.setChecked(false);
                video_btn.setChecked(false);
                replaceFregment(JokeFragment.newInstance());
                break;
        }

    }

    /**
     * 替换fragment
     *
     * @param fragment
     */
    private void replaceFregment(BaseFragment fragment) {
        replaceFragment(R.id.main_frame, fragment);
    }

    @Override
    public void onCheckedChanged(View view, boolean checked) {

        switch (view.getId()) {
            case R.id.main_shine_btn_music:
                if (checked) changeState("music");
                break;
            case R.id.main_shine_btn_video:
                if (checked) changeState("video");
                break;
            case R.id.main_shine_btn_book:
                if (checked) changeState("book");
                break;
            case R.id.main_shine_btn_news:
                if (checked) changeState("news");
                break;
            case R.id.main_shine_btn_joke:
                if (checked) changeState("joke");
                break;
        }
    }
}
