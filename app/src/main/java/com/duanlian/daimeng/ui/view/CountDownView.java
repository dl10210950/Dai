package com.duanlian.daimeng.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.duanlian.daimeng.R;


/**
 * 仿迅雷手机客户端  广告倒计时View
 */

public class CountDownView extends View {
//圆形里面的颜色
    private static final int BACKGROUND_COLOR = 0x50555555;
    //边框宽度
    private static final float BORDER_WIDTH = 6f;
    //边框颜色
    private static final int BORDER_COLOR = Color.RED;
    private static final String TEXT = "跳过广告";
    private static final float TEXT_SIZE = 30f;
    private static final int TEXT_COLOR = 0xFFFFFFFF;

    private int backgroundColor;
    private float borderWidth;
    private int borderColor;
    private String text;
    private int textColor;
    private float textSize;

    private Paint circlePaint;
    private TextPaint textPaint;
    private Paint borderPaint;

    private float progress = 0;
    private StaticLayout staticLayout;

    private CountDownTimerListener listener;

    public CountDownView(Context context) {
        this(context, null);
    }

    public CountDownView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CountDownView);
        backgroundColor = ta.getColor(R.styleable.CountDownView_background_color, BACKGROUND_COLOR);
        borderWidth = ta.getDimension(R.styleable.CountDownView_border_width, BORDER_WIDTH);
        borderColor = ta.getColor(R.styleable.CountDownView_border_color, BORDER_COLOR);
        text = ta.getString(R.styleable.CountDownView_text);
        if (text == null) {
            text = TEXT;
        }
        textSize = ta.getDimension(R.styleable.CountDownView_text_size, TEXT_SIZE);
        textColor = ta.getColor(R.styleable.CountDownView_text_color, TEXT_COLOR);
        ta.recycle();
        init();
    }

    private void init() {
        circlePaint = new Paint();
        circlePaint.setAntiAlias(true);
        circlePaint.setDither(true);
        circlePaint.setColor(backgroundColor);
        circlePaint.setStyle(Paint.Style.FILL);

        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setDither(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTextAlign(Paint.Align.CENTER);

        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setDither(true);
        borderPaint.setColor(borderColor);
        borderPaint.setStrokeWidth(borderWidth);
        borderPaint.setStyle(Paint.Style.STROKE);

        int textWidth = (int) textPaint.measureText(text.substring(0, (text.length() + 1) / 2));
        staticLayout = new StaticLayout(text, textPaint, textWidth, Layout.Alignment.ALIGN_NORMAL, 1F, 0, false);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode != MeasureSpec.EXACTLY) {
            width = staticLayout.getWidth();
        }
        if (heightMode != MeasureSpec.EXACTLY) {
            height = staticLayout.getHeight();
        }
        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int min = Math.min(width, height);
        //画底盘
        canvas.drawCircle(width / 2, height / 2, min / 2, circlePaint);
        //画边框
        RectF rectF;
        if (width > height) {
            rectF = new RectF(width / 2 - min / 2 + borderWidth / 2, 0 + borderWidth / 2, width / 2 + min / 2 - borderWidth / 2, height - borderWidth / 2);
        } else {
            rectF = new RectF(borderWidth / 2, height / 2 - min / 2 + borderWidth / 2, width - borderWidth / 2, height / 2 - borderWidth / 2 + min / 2);
        }
        canvas.drawArc(rectF, -90, progress, false, borderPaint);
        //画单行居中的文字
//       canvas.drawText("稍等片刻", width / 2, height / 2 - textPaint.descent() + textPaint.getTextSize() / 2, textPaint);
        canvas.translate(width / 2, height / 2 - staticLayout.getHeight() / 2);
        staticLayout.draw(canvas);
    }

    private CountDownTimer countDownTimer;

    public void start() {
        if (listener != null) {
            listener.onStartCount();
        }
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(3600, 36) {
                @Override
                public void onTick(long millisUntilFinished) {
                    progress = ((3600 - millisUntilFinished) / 3600f) * 360;
                    invalidate();
                }

                @Override
                public void onFinish() {
                    progress = 360;
                    invalidate();
                    if (listener != null) {
                        listener.onFinishCount();
                    }
                }
            };
        }
        countDownTimer.cancel();
        countDownTimer.start();

    }

    public void setCountDownTimerListener(CountDownTimerListener listener) {
        this.listener = listener;
    }

    public interface CountDownTimerListener {

        void onStartCount();

        void onFinishCount();
    }
}
