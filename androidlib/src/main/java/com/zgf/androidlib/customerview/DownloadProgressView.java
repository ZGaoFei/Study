package com.zgf.androidlib.customerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.zgf.androidlib.R;
import com.zgf.androidlib.Utils;

public class DownloadProgressView extends View {
    private static final int DEFAULT_TEXT_SIZE = 14;
    private static final int DEFAULT_WHITER = 0xfff; // 白色
    private static final int DEFAULT_BLUE = 0x0000FF; // 蓝色

    // 背景颜色
    private int bgColor;
    // 进度条颜色
    private int bgProgressColor;
    // 字体默认颜色
    private int textColor;
    // 字体选中颜色
    private int textProgressColor;
    // 字体大小
    private float textSize;
    // 文字内容
    private String text;

    private Paint textPaint;
    private Paint bgPaint;

    private int spacing;

    // 0-100
    private int currentProgress;

    private LinearGradient linearGradient;
    private Matrix matrix;

    // todo 增加圆角或者直角效果，描边或者不描边效果

    public DownloadProgressView(Context context) {
        this(context, null);
    }

    public DownloadProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DownloadProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.DownloadProgressView, 0, 0);
        text = (String) t.getText(R.styleable.DownloadProgressView_android_text);
        textSize = t.getDimension(R.styleable.DownloadProgressView_android_textSize, DEFAULT_TEXT_SIZE);
        textColor = t.getColor(R.styleable.DownloadProgressView_android_textColor, DEFAULT_BLUE);
        textProgressColor = t.getColor(R.styleable.DownloadProgressView_textProgressColor, DEFAULT_WHITER);
        bgColor = t.getColor(R.styleable.DownloadProgressView_backgroundColor, DEFAULT_WHITER);
        bgProgressColor = t.getColor(R.styleable.DownloadProgressView_backgroundProgressColor, DEFAULT_BLUE);
        t.recycle();

        init();
    }

    private void init() {
        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        textPaint.setColor(textColor);

        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);

        spacing = Utils.dip2px(getContext(), 10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        linearGradient = new LinearGradient(0, 0, w, 0,
                new int[]{textProgressColor, textColor},
                new float[]{0, 0},
                Shader.TileMode.CLAMP);
        textPaint.setShader(linearGradient);

        matrix = new Matrix();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        int textWidth = rect.width();
        int textHeight = rect.height();
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            // 宽度两边加10dp
            width = (int) (textWidth + spacing * 2);
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            // 高度两边加10dp
            height = (int) (textHeight + spacing * 2);
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvasBg(canvas);
        canvasText(canvas);
    }

    private void canvasBg(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        if (currentProgress == 100) {
            bgPaint.setColor(bgProgressColor);
            canvas.drawRect(0, 0, width, height, bgPaint);
        } else if (currentProgress == 0) {
            bgPaint.setColor(bgColor);
            canvas.drawRect(0, 0, width, height, bgPaint);
        } else {
            int currentWidth = (int) ((width / 100L) * currentProgress);
            bgPaint.setColor(bgProgressColor);
            canvas.drawRect(0, 0, currentWidth, height, bgPaint);

            bgPaint.setColor(bgColor);
            canvas.drawRect(currentWidth, 0, width, height, bgPaint);
        }
    }

    private void canvasText(Canvas canvas) {
        if (TextUtils.isEmpty(text)) {
            text = "";
        }
        Rect rect = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), rect);
        int textWidth = rect.width();
        int textHeight = rect.height();
        int width = getWidth();
        int height = getHeight();
        // 计算绘制文字的起点
        int x = width / 2 - textWidth / 2;
        int y = height / 2 + textHeight / 2;

        if (currentProgress == 100) {
            textPaint.setColor(textProgressColor);
            canvas.drawText(text, x, y, textPaint);
        } else if (currentProgress == 0) {
            textPaint.setColor(textColor);
            canvas.drawText(text, x, y, textPaint);
        } else {
            int currentWidth = (int) ((width / 100L) * currentProgress);
            matrix.setTranslate(currentWidth, 0);
            linearGradient.setLocalMatrix(matrix);

            canvas.drawText(text, x, y, textPaint);
        }
    }

    public void setProgress(int progress) {
        if (progress < 0) {
            currentProgress = 0;
        } else if (progress > 100) {
            currentProgress = 100;
        } else {
            currentProgress = progress;
        }

        postInvalidate();
    }

    public void setText(String text) {
        this.text = text;

        postInvalidate();
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;

        postInvalidate();
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;

        postInvalidate();
    }

    public void setTextProgressColor(int textProgressColor) {
        this.textProgressColor = textProgressColor;

        postInvalidate();
    }

    public void setBgProgressColor(int bgProgressColor) {
        this.bgProgressColor = bgProgressColor;

        postInvalidate();
    }

    public void setTextSize(int textSize) {
        this.textSize = textSize;

        postInvalidate();
    }
}
