package com.duanlian.daimeng.ui.book;

import android.view.View;

import com.duanlian.daimeng.R;
import com.duanlian.daimeng.base.BaseFragment;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Types.BoomType;

/**
 * music页面
 */

public class BookFragment extends BaseFragment {
    private BoomMenuButton bmb;

    public static BaseFragment newInstance() {
        BookFragment fragment = new BookFragment();
        return fragment;
    }

    @Override
    public View initView() {
        View view = View.inflate(getBaseActivity(), R.layout.fragment_book, null);
        bmb = (BoomMenuButton) view.findViewById(R.id.bmb);
        return view;
    }
}
