package com.duanlian.daimeng.ui.news;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.NewsRecyclerAdapter;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.bean.YiYuanNews;
import com.duanlian.daimeng.constant.Api;
import com.duanlian.daimeng.ui.view.pull_refresh.RefreshHeaderOne;
import com.duanlian.daimeng.ui.view.pull_refresh.RefreshLayout;
import com.duanlian.daimeng.utils.AnimationDialog;
import com.duanlian.daimeng.utils.LogUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

import static com.duanlian.daimeng.constant.Api.YIYUAN_APPID;
import static com.duanlian.daimeng.constant.Api.YIYUAN_SECRET;

/**
 * 新闻列表fragment,全部公用这个fragment
 */
public class NewsListFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private NewsRecyclerAdapter mAdapter;
    private RefreshLayout mRefreshLayout;
    String title;
    private int allPages;
    int lastVisibleItem;
    int currentPage = 1;
    private boolean isLoading;
    List<YiYuanNews.ShowapiResBodyBean.PagebeanBean.ContentlistBean> contentlist = new ArrayList<>();
    List<YiYuanNews.ShowapiResBodyBean.PagebeanBean.ContentlistBean> list = new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {//拿到传递过来的channelName
            title = getArguments().getString("channelName");
            LogUtils.e(title);
        }

    }

    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_news_list, null);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_news_list);
        mRefreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        mLinearLayoutManager = new LinearLayoutManager(getBaseActivity());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mAdapter = new NewsRecyclerAdapter(getBaseActivity(), contentlist);
        mRecyclerView.setAdapter(mAdapter);
        setRefreshLayout();
        mRefreshLayout.autoRefresh();//开启刚进去就自动刷新
        return view;
    }

    /**
     * 设置自定义刷新布局的
     */
    private void setRefreshLayout() {
        RefreshHeaderOne headerOne = new RefreshHeaderOne(getBaseActivity());
        mRefreshLayout.setRefreshHeader(headerOne);
        mRefreshLayout.setRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                getData(1,title);
            }
        });
    }

    @Override
    public void initData() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //到达底部了
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount() && !isLoading) {
                    //到达底部之后如果footView的状态不是正在加载的状态,就将 他切换成正在加载的状态
                    currentPage++;
                    if (currentPage < allPages) {
                        isLoading = true;
                        getData(currentPage, title);
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

    /**
     * 根据不同的频道请求不同的数据
     *
     * @param page
     * @param title
     */
    private void getData(int page, String title) {
        String url = Api.YIYUAN_BASE_URL + "109-35?channelId=&channelName=&maxResult=20&needAllList=0&needContent=0&needHtml=0&page="
                + page + "&showapi_appid=" + YIYUAN_APPID + "&title=" + title + "&showapi_sign=" + YIYUAN_SECRET;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e(getBaseActivity(), e.toString());
                        mRefreshLayout.refreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
//                        LogUtils.e(getBaseActivity(), response.toString());
                        doResult(response);
                        mRefreshLayout.refreshComplete();
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
                YiYuanNews yiYuanNews = gson.fromJson(response, YiYuanNews.class);
                allPages = yiYuanNews.getShowapi_res_body().getPagebean().getAllPages();
                contentlist = yiYuanNews.getShowapi_res_body().getPagebean().getContentlist();
                list.addAll(contentlist);
                mAdapter.setDataChange(list);
                isLoading = false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
