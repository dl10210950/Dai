package com.duanlian.daimeng.ui.news;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseActivity;

public class NewsDetailsActivity extends BaseActivity {
    private WebView webView;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_news_details);
    }

    @Override
    public void initView() {
        String url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webview_news_details);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public void initPresenter() {

    }
}
