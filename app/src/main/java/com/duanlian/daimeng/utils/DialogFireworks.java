package com.duanlian.daimeng.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.duanlian.daimeng.R;

/**
 * 烟花dialog
 */

public class DialogFireworks {
    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.load_dialog_fireworks, null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_view);// 加载布局
        ImageView spaceshipImage = (ImageView) view.findViewById(R.id.img);
        TextView tipTextView = (TextView) view.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息
        /**
         * 设置帧动画
         */
        spaceshipImage.setBackgroundResource(R.drawable.fireworks);
        AnimationDrawable animationDrawable = (AnimationDrawable) spaceshipImage.getBackground();
        Dialog loadingDialog = new Dialog(context, R.style.CustomDialog);// 创建自定义样式dialog
        animationDrawable.start();
        Window window = loadingDialog.getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.dimAmount = 0f;
        window.setAttributes(params);

        loadingDialog.setCancelable(true);// 不可以用“返回键”取消
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));// 设置布局
        return loadingDialog;
    }

}
