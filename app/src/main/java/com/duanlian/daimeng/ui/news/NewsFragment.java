package com.duanlian.daimeng.ui.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.CommonFragPagerAdapter;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.utils.CommonUtils;
import com.duanlian.daimeng.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * music页面
 */

public class NewsFragment extends BaseFragment {


    Toolbar mToolbar;
    TabLayout mTabs;
    ImageView mAddChannelIv;
    ViewPager mViewPager;
    private CommonFragPagerAdapter mPagerAdapter;
    private List<String> titleList;
    private List<BaseFragment> framList;

    public static BaseFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_news, null);
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mTabs = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        framList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.add("头条");
        titleList.add("国内");
        titleList.add("国际");
        titleList.add("体育");
        titleList.add("科技");
        titleList.add("社会");
        for (int i = 0; i < titleList.size(); i++) {
            NewsListFragment listFragments = createListFragments(titleList.get(i));
            framList.add(listFragments);
        }
        CommonUtils.dynamicSetTabLayoutMode(mTabs);
        mTabs.setupWithViewPager(mViewPager);
        mPagerAdapter = new CommonFragPagerAdapter(getChildFragmentManager());
        mPagerAdapter.addTitles(titleList);
        LogUtils.e(titleList.size()+"");
        mPagerAdapter.addPagers(framList);
        mViewPager.setAdapter(mPagerAdapter);
        return view;
    }
    private NewsListFragment createListFragments(String channelName) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("channelName", channelName);
        fragment.setArguments(bundle);
        return fragment;
    }

}
