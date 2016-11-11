package com.duanlian.daimeng.ui.fragment;

import android.view.View;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseFragment;

/**
 * music页面
 */

public class MusicFragment  extends BaseFragment{
    public static BaseFragment newInstance() {
        MusicFragment fragment = new MusicFragment();
        return fragment;
    }
    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_music, null);
        return view;
    }
}
