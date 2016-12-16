package com.duanlian.daimeng.ui.news;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.CommonFragPagerAdapter;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.database.DataBase;

import java.util.ArrayList;
import java.util.List;

/**
 * music页面
 */

public class NewsFragment extends BaseFragment {


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
        mTabs = (TabLayout) view.findViewById(R.id.tabs);
        mViewPager = (ViewPager) view.findViewById(R.id.view_pager);
        mAddChannelIv = (ImageView) view.findViewById(R.id.add_channel_iv);
        framList = new ArrayList<>();
        titleList = new ArrayList<>();
        titleList.clear();
        titleList.add("头条");
        titleList.add("国内");
        titleList.add("国际");
        titleList.addAll(getData());
        for (int i = 0; i < titleList.size(); i++) {
            NewsListFragment listFragments = createListFragments(titleList.get(i));
            framList.add(listFragments);
        }
//        CommonUtils.dynamicSetTabLayoutMode(mTabs);
        if (titleList.size() <= 3) {
            mTabs.setTabMode(TabLayout.MODE_FIXED);
        } else {
            mTabs.setTabMode(TabLayout.MODE_SCROLLABLE);
        }
        mTabs.setupWithViewPager(mViewPager);
        mPagerAdapter = new CommonFragPagerAdapter(getChildFragmentManager());
        mPagerAdapter.addTitles(titleList);
        mPagerAdapter.addPagers(framList);
        mViewPager.setAdapter(mPagerAdapter);
        mAddChannelIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddChannelActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private NewsListFragment createListFragments(String channelName) {
        NewsListFragment fragment = new NewsListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("channelName", channelName);
        fragment.setArguments(bundle);
        return fragment;
    }


    /**
     * 通过查找数据库,拿到里面的数据
     */
    private List<String> getData() {
        List<String> list = new ArrayList<>();
        Cursor query = DataBase.getInstances(getBaseActivity()).query();
        if (query.moveToFirst()) {
            do {
                String channel = query.getString(query.getColumnIndex("channel"));
                list.add(channel);
            } while (query.moveToNext());
        }
        //关闭查询游标
        query.close();
        return list;
    }
}
