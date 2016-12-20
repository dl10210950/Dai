package com.duanlian.daimeng.ui.joke;

import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.JokeListViewAdapter;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.bean.YiYuanJokePicture;
import com.duanlian.daimeng.bean.YiYuanJokeText;
import com.duanlian.daimeng.constant.Api;
import com.duanlian.daimeng.ui.view.AutoViewPager;
import com.duanlian.daimeng.ui.view.pull_refresh.RefreshHeaderOne;
import com.duanlian.daimeng.ui.view.pull_refresh.RefreshLayout;
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
 * music页面
 */

public class JokeFragment extends BaseFragment {
    private ListView mListView;
    private AutoViewPager mAutoViewPager;
    private JokeListViewAdapter mAdapter;
    List<YiYuanJokeText.ContentlistBean> contentlist = new ArrayList<>();
    List<YiYuanJokeText.ContentlistBean> list = new ArrayList<>();
    private int allpage;
    private int page = 1;
    private boolean flag;
    private RefreshLayout mRefreshLayout;

    public static BaseFragment newInstance() {
        JokeFragment fragment = new JokeFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_joke, null);
        mListView = (ListView) view.findViewById(R.id.joke_listview);
        mRefreshLayout = (RefreshLayout) view.findViewById(R.id.refreshLayout);
        View headView = View.inflate(getBaseActivity(), R.layout.auto_viewpager, null);
        mAutoViewPager = (AutoViewPager) headView.findViewById(R.id.auto_viewpager);
        mListView.addHeaderView(headView);
        mAdapter = new JokeListViewAdapter(getBaseActivity(), contentlist);
        mListView.setAdapter(mAdapter);
        setListViewListener();
        setRefreshLayout();
        getPictureData(1);
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
                getTextData(1);
            }
        });
    }

    private void setListViewListener() {
        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (mListView.getLastVisiblePosition() == mAdapter.getCount() && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    page++;
                    if (page <= allpage) {//如果页数=总页数了
                        getTextData(page); //请求还没结束
                    } else {
                        mAdapter.changeState(2);//把状态2传递过去,通知那边显示没有更多...
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount && !flag) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
        });
    }

    private void getTextData(int page) {
        String url = Api.YIYUAN_BASE_URL + "341-1?maxResult=8&page=" + page +
                "&showapi_appid=" + YIYUAN_APPID + "&showapi_sign=" + YIYUAN_SECRET;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e(e.toString());
                        mRefreshLayout.refreshComplete();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        doTextResult(response);
//                        LogUtils.e(response.toString());
                        mRefreshLayout.refreshComplete();

                    }
                });
    }

    private void doTextResult(String response) {
        try {
            JSONObject object = new JSONObject(response);
            int i = object.optInt("showapi_res_code");
            if (i == 0) {
                Gson gson = new Gson();
                YiYuanJokeText yiYuanJokeText = gson.fromJson(response, YiYuanJokeText.class);
                allpage = yiYuanJokeText.showapi_res_body.allPages;
                contentlist = yiYuanJokeText.showapi_res_body.contentlist;
                list.addAll(contentlist);
                mAdapter.setDataChange(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 拿图片数据
     *
     * @param page
     */
    private void getPictureData(int page) {
        String url = Api.YIYUAN_BASE_URL + "341-2?maxResult=20&page=" + page +
                "&showapi_appid=" + YIYUAN_APPID + "&showapi_sign=" + YIYUAN_SECRET;
        OkHttpUtils.get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e(e.toString());
                        getTextData(1);
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        doPictureResult(response);
                        getTextData(1);

                    }
                });

    }

    private void doPictureResult(String response) {
        try {
            JSONObject object = new JSONObject(response);
            int showapi_res_code = object.optInt("showapi_res_code");
            if (showapi_res_code == 0) {
                Gson gson = new Gson();
                YiYuanJokePicture yiYuanJokePicture = gson.fromJson(response, YiYuanJokePicture.class);
                List<String> imgUrlList = new ArrayList<>();
                ImageView imageView[] = new ImageView[5];
                for (int i = 0; i < 5; i++) {
                    String img = yiYuanJokePicture.getShowapi_res_body().getContentlist().get(i).getImg();
                    imgUrlList.add(img);
                    imageView[i] = new ImageView(getBaseActivity());
                    Glide.with(getBaseActivity())
                            .load(imgUrlList.get(i))
                            .centerCrop()
                            .placeholder(R.mipmap.viewpager3)
                            .into(imageView[i]);
                    mAutoViewPager.addContent(imageView[i]);
                }

                mAutoViewPager.startScroll();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
