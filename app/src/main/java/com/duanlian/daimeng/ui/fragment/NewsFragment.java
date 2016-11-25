package com.duanlian.daimeng.ui.fragment;

import android.view.View;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.utils.LogUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

import static com.duanlian.daimeng.constant.Api.baiduApiKey;
import static com.duanlian.daimeng.constant.Api.yiYuanNews;

/**
 * music页面
 */

public class NewsFragment extends BaseFragment {
    public static BaseFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_news, null);
        view.findViewById(R.id.news_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkHttpUtils.get()
                        .url(yiYuanNews)
                        .addHeader("apikey", baiduApiKey)
                        .build()
                        .execute(new StringCallback() {
                            @Override
                            public void onError(Call call, Exception e, int id) {
                                LogUtils.e(getBaseActivity(), e.toString());
                            }

                            @Override
                            public void onResponse(String response, int id) {
                                LogUtils.e(getBaseActivity(), response.toString());
                            }
                        });


            }
        });
        return view;
    }
}
