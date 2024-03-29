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
 * 掂球
 */
public class DialogDianQiu {

    public static Dialog createLoadingDialog(Context context, String msg) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.load_dialog_fireworks, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_view);// 加载布局
        // main.xml中的ImageView
        ImageView spaceshipImage = (ImageView) v.findViewById(R.id.img);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        // 加载动画
       /* Animation hyperspaceJumpAnimation = AnimationUtils.loadAnimation(
                context, R.drawable.dianqiu);
        // 使用ImageView显示动画
        spaceshipImage.startAnimation(hyperspaceJumpAnimation);*/
        tipTextView.setText(msg);// 设置加载信息

        /**
         * 帧动画的
         */
        spaceshipImage.setBackgroundResource(R.drawable.dianqiu);//设置为背景
        final AnimationDrawable mAnimation = (AnimationDrawable) spaceshipImage.getBackground();
        // 为了防止在onCreate方法中只显示第一帧的解决方案之一
       /* spaceshipImage.post(new Runnable() {
            @Override
            public void run() {
                mAnimation.start();

            }
        });*/

        Dialog loadingDialog = new Dialog(context, R.style.CustomDialog);// 创建自定义样式dialog
        mAnimation.start();
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
