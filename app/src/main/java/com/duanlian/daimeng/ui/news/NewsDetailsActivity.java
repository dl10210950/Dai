package com.duanlian.daimeng.ui.news;

import android.app.Dialog;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseActivity;
import com.duanlian.daimeng.utils.AnimationDialog;
import com.duanlian.daimeng.utils.LogUtils;

public class NewsDetailsActivity extends BaseActivity {
    private WebView webView;
    private TextView title;
    private ImageView iv_back;
    Dialog dialog;


    @Override
    public void initContentView() {
        setContentView(R.layout.activity_news_details);
    }

    @Override
    public void initView() {
        title = (TextView) findViewById(R.id.header_htv_subtitle);
        iv_back = (ImageView) findViewById(R.id.header_layout_leftview);
        title.setText("新闻详情");
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //烟花dialog
        dialog = AnimationDialog.createLoadingDialog(this, "客官,马上就好....");
        dialog.show();
        String url = getIntent().getStringExtra("url");
        webView = (WebView) findViewById(R.id.webview_news_details);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebviewclient());
        webView.loadUrl(url);
    }

    @Override
    public void initPresenter() {

    }

    private class MyWebviewclient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            dialog.dismiss();
            LogUtils.e("加载完成");
            super.onPageFinished(view, url);
        }

        @Override
        public void onPageCommitVisible(WebView view, String url) {
            super.onPageCommitVisible(view, url);
            LogUtils.e("onPageCommitVisible");
            dialog.dismiss();
        }
    }

}
