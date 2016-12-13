package com.duanlian.daimeng.ui.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.NewsRecyclerAdapter;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.bean.YiYuanNewsBean;
import com.duanlian.daimeng.utils.LogUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static com.duanlian.daimeng.constant.Api.baiduApiKey;
import static com.duanlian.daimeng.constant.Api.yiYuanNews;

/**
 * music页面
 */

public class NewsFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NewsRecyclerAdapter mAdapter;
    int allPages;//新闻里面的所有页数
    int lastVisibleItem;
    int currentPage = 2;
    List<YiYuanNewsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = new ArrayList<>();
    List<YiYuanNewsBean.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = new ArrayList<>();

    public static BaseFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_news, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.news_recycler);
        mLinearLayoutManager = new LinearLayoutManager(getBaseActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        getData(1);
        mAdapter = new NewsRecyclerAdapter(getBaseActivity(), contentlist);
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void initData() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //到达底部了
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    //到达底部之后如果footView的状态不是正在加载的状态,就将 他切换成正在加载的状态
                    if (currentPage < allPages) {
                        getData(currentPage);
                        ++currentPage;
                        mAdapter.changeState(1);
                    } else {
                        mAdapter.changeState(2);
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = mLinearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    private void getData(int page) {
        String url = yiYuanNews + "?page=" + page;
        LogUtils.e(url);
        OkHttpUtils.get()
                .url(url)
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
                        doResult(response);
                    }
                });
    }

    /**
     * 请求结果的操作
     */
    private void doResult(String response) {
        try {
            JSONObject object = new JSONObject(response);
            int showapi_res_code = object.optInt("showapi_res_code");
            if (showapi_res_code == 0) {
                Gson gson = new Gson();
                YiYuanNewsBean yiYuanNewsBean = gson.fromJson(response, YiYuanNewsBean.class);
                allPages = yiYuanNewsBean.getShowapi_res_body().getPagebean().getAllPages();
                contentlist = yiYuanNewsBean.getShowapi_res_body().getPagebean().getContentlist();
                list.addAll(contentlist);
                mAdapter.setDataChange(list);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
