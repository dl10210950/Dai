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
import com.duanlian.daimeng.ui.view.AutoViewPager;
import com.duanlian.daimeng.ui.view.CircleImageView;
import com.duanlian.daimeng.ui.view.IosBottomDialog;
import com.duanlian.daimeng.ui.view.stickylayout.StickyNavLayout;
import com.duanlian.daimeng.utils.CommonUtils;
import com.nineoldandroids.view.ViewHelper;

import java.util.ArrayList;
import java.util.List;

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
    private AutoViewPager mAutoViewPager;
    private CircleImageView alwaysShowHead;

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
        mAutoViewPager = (AutoViewPager) view.findViewById(R.id.music_viewpager);
        alwaysShowHead = (CircleImageView) view.findViewById(R.id.common_head_show);
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
        mAutoViewPager.addContent(CommonUtils.getImageView(R.mipmap.viewpager,getBaseActivity()));
        mAutoViewPager.addContent(CommonUtils.getImageView(R.mipmap.viewpager1,getBaseActivity()));
        mAutoViewPager.addContent(CommonUtils.getImageView(R.mipmap.veiwpager2,getBaseActivity()));

        mAutoViewPager.addContent(CommonUtils.getImageView(R.mipmap.viewpager3,getBaseActivity()));
        mAutoViewPager.startScroll();
        //设置顶部导航栏隐藏
        ViewHelper.setAlpha(mRelative, 0f);
        setViewListener();
        return view;
    }

    private void setViewListener() {
        //一直显示的头像的监听
        alwaysShowHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IosBottomDialog.Builder builder = new IosBottomDialog.Builder(getBaseActivity());
                builder.setTitle("我是标题", Color.BLACK)
                        .addOption("操作1", R.color.black, new IosBottomDialog.OnOptionClickListener() {
                            @Override
                            public void onOptionClick() {
                                showToast("操作一");
                            }
                        })
                        .addOption("操作2", R.color.black, new IosBottomDialog.OnOptionClickListener() {
                            @Override
                            public void onOptionClick() {
                                showToast("操作二");
                            }
                        })
                        .create()
                        .show();
            }
        });
        //悬浮条的监听
        stickyNavLayout.setOnStickStateChangeListener(new StickyNavLayout.onStickStateChangeListener() {
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
        });
    }

    @Override
    public void initData() {
    }



}
