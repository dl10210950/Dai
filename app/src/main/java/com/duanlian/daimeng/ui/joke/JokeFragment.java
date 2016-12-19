package com.duanlian.daimeng.ui.joke;

import android.view.View;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseFragment;

/**
 * music页面
 */

public class JokeFragment extends BaseFragment{
    public static BaseFragment newInstance() {
        JokeFragment fragment = new JokeFragment();
        return fragment;
    }
    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_joke, null);
        return view;
    }
}
