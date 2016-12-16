package com.duanlian.daimeng.ui.book;

import android.view.View;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseFragment;

/**
 * music页面
 */

public class BookFragment extends BaseFragment {
    public static BaseFragment newInstance() {
        BookFragment fragment = new BookFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_book, null);
        return view;
    }
}
