package com.duanlian.daimeng.ui.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.MusicFragPagerAdapter;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.ui.fragment.music.LocalMusicFragment;
import com.duanlian.daimeng.ui.fragment.music.NetMusicFragment;
import com.duanlian.daimeng.ui.view.stickylayout.StickyNavLayout;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.y;

/**
 * music页面
 */

public class MusicFragment extends BaseFragment {
    private ViewPager mViewPager;
    private TabLayout mTab;
    private LinearLayout mLinearLayout;
    private StickyNavLayout stickyNavLayout;
    private MusicFragPagerAdapter mPagerAdapter;
    private List<BaseFragment> mFragList;
    private List<String> titleList;
    private RelativeLayout mRelative;
    private boolean isSticky=false;

    public static BaseFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_music, null);
        mViewPager = (ViewPager) view.findViewById(R.id.id_stickynavlayout_viewpager);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.id_stickynavlayout_topview);
        mTab = (TabLayout) view.findViewById(R.id.id_stickynavlayout_indicator);
        stickyNavLayout = (StickyNavLayout) view.findViewById(R.id.stick);
        mRelative = (RelativeLayout) view.findViewById(R.id.common_relative);
        mFragList = new ArrayList<>();
        titleList = new ArrayList<>();
        mFragList.add(LocalMusicFragment.newInstance());
        mFragList.add(NetMusicFragment.newInstance());
        titleList.add("本地音乐");
        titleList.add("网络音乐");
        //设置tab的模式
        mTab.setTabMode(TabLayout.MODE_FIXED);
        mTab.setupWithViewPager(mViewPager);
        mPagerAdapter = new MusicFragPagerAdapter(getChildFragmentManager());
        mPagerAdapter.setTitles(titleList);
        mPagerAdapter.setPagers(mFragList);
        mViewPager.setAdapter(mPagerAdapter);

        ViewHelper.setAlpha(mRelative, 0f);
        stickyNavLayout.setOnStickStateChangeListener(onStickStateChangeListener);
        return view;
    }

    @Override
    public void initData() {
    }
    private StickyNavLayout.onStickStateChangeListener onStickStateChangeListener = new StickyNavLayout.onStickStateChangeListener() {
        @Override
        public void isStick(boolean isStick) {
                if (isStick) {

//                    Toast.makeText(SimpleStickActivity.this, "本宝宝悬浮了", Toast.LENGTH_LONG).show();
                } else {
//                    Toast.makeText(SimpleStickActivity.this, "本宝宝又不悬浮了", Toast.LENGTH_LONG).show();
                }
            }

        @Override
        public void scrollPercent(float percent) {
            if (percent != 0) {
                mRelative.setVisibility(View.VISIBLE);
            } else {
                mRelative.setVisibility(View.GONE);
            }
            ViewHelper.setAlpha(mRelative, percent);
        }
    };
}
