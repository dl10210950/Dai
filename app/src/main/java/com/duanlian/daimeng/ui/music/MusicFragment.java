package com.duanlian.daimeng.ui.music;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.adapter.CommonFragPagerAdapter;
import com.duanlian.daimeng.base.BaseFragment;
import com.duanlian.daimeng.ui.view.AutoViewPager;
import com.duanlian.daimeng.ui.view.CircleImageView;
import com.duanlian.daimeng.ui.view.IosBottomDialog;
import com.duanlian.daimeng.ui.view.many_searchview.SearchView;
import com.duanlian.daimeng.ui.view.many_searchview.controller.ChangeArrowController;
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
    private CommonFragPagerAdapter mPagerAdapter;
    private List<BaseFragment> mFragList;
    private List<String> titleList;
    private RelativeLayout mRelative;
    private AutoViewPager mAutoViewPager;
    private CircleImageView alwaysShowHead;
    private ImageView hint_img;
    private EditText mEditText;
    private SearchView mSearchView;
    private SwipeRefreshLayout swipe;

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
        hint_img = (ImageView) view.findViewById(R.id.common_edit_hint_search);
        mEditText = (EditText) view.findViewById(R.id.common_edit);
        mSearchView = (SearchView) view.findViewById(R.id.common_search);
        mSearchView.setController(new ChangeArrowController());
        mFragList = new ArrayList<>();
        titleList = new ArrayList<>();
        mFragList.add(LocalMusicFragment.newInstance());
        mFragList.add(NetMusicFragment.newInstance());
        titleList.add("本地音乐");
        titleList.add("网络音乐");
        //设置tab的模式
        mTab.setTabMode(TabLayout.MODE_FIXED);
        mTab.setupWithViewPager(mViewPager);
        mPagerAdapter = new CommonFragPagerAdapter(getChildFragmentManager());
        mPagerAdapter.setTitles(titleList);
        mPagerAdapter.setPagers(mFragList);
        mViewPager.setAdapter(mPagerAdapter);
        //给轮播图添加图片
        mAutoViewPager.addContent(CommonUtils.getImageView(R.mipmap.viewpager, getBaseActivity()));
        mAutoViewPager.addContent(CommonUtils.getImageView(R.mipmap.viewpager1, getBaseActivity()));
        mAutoViewPager.addContent(CommonUtils.getImageView(R.mipmap.veiwpager2, getBaseActivity()));
        mAutoViewPager.addContent(CommonUtils.getImageView(R.mipmap.viewpager3, getBaseActivity()));
        mAutoViewPager.startScroll();
        //设置顶部导航栏隐藏
        ViewHelper.setAlpha(mRelative, 0f);
        setViewListener();
        swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipe.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe.setRefreshing(false);
                    }
                }, 3000);
            }
        });
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
        //自定义搜索按钮的监听
        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.startAnim();
            }
        });

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().isEmpty()) {
                    hint_img.setVisibility(View.VISIBLE);
                } else {
                    hint_img.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void initData() {
    }

}
