package com.duanlian.daimeng.ui.video;

import android.view.View;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseFragment;

/**
 * music页面
 */

public class VideoFragment extends BaseFragment{
    public static BaseFragment newInstance() {
        VideoFragment fragment = new VideoFragment();
        return fragment;
    }
    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_video, null);
        return view;
    }
}
