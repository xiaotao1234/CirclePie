package com.example.viewtestall;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CirclePieView extends View {

    private int mWidth;
    private int mRadius;
    private int mheight;
    RectF rectF;
    List<Integer> mColorList;
    Map<String, Integer> mDataMap;
    private Paint paint;
    private Paint paint1;
    private Paint paint2;
    List<Paint> paints;
    Paint paintrect;
    private float value;
    private int mwidthSize;
    private int mheightSize;
    private int mwidthModle;
    private int mheightModle;
    int redius;
    List<Integer> arclist;
    private Paint centerpaint;

    public CirclePieView(Context context) {
        super(context);
        init();
    }

    public CirclePieView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public CirclePieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        paints = new ArrayList<>();
        arclist = new ArrayList<>();
        arclist.add(30);
        arclist.add(100);
        arclist.add(80);
        arclist.add(80);
        arclist.add(70);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint1 = new Paint();
        paint1.setColor(Color.BLUE);
        paint1.setAntiAlias(true);
        paint2 = new Paint();
        paint2.setColor(Color.BLACK);
        paint2.setAntiAlias(true);
        paintrect = new Paint();
        paintrect.setColor(Color.BLUE);
        paintrect.setAntiAlias(true);
        mRadius = 1000;
        paints.add(paint);
        paints.add(paint1);
        paints.add(paint2);
        paints.add(paint1);
        paints.add(paint2);
        centerpaint = new Paint();
        centerpaint.setAntiAlias(true);
        centerpaint.setColor(Color.WHITE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mwidthSize = MeasureSpec.getSize(widthMeasureSpec);
        mheightSize = MeasureSpec.getSize(heightMeasureSpec);
        mwidthModle = MeasureSpec.getMode(widthMeasureSpec);
        mheightModle = MeasureSpec.getMode(heightMeasureSpec);
        if (mwidthModle == MeasureSpec.EXACTLY) {
            mWidth = mwidthSize;
        } else {
            mWidth = getPaddingLeft() + getPaddingRight() + mRadius * 2 + 100;
            if (mwidthModle == MeasureSpec.AT_MOST) {
                mWidth = Math.min(mWidth, mwidthSize);
            }
        }
        if (mheightModle == MeasureSpec.EXACTLY) {
            mheight = mheightSize;
        } else {
            mheight = getPaddingTop() + getPaddingBottom() + mRadius * 2 + 100;
            if (mheightModle == MeasureSpec.AT_MOST) {
                mheight = Math.min(mheight, mheightSize);
            }
        }
        redius = Math.min(mWidth, mheight) / 2;
        rectF = new RectF(0, 0, redius * 2, redius * 2);
        setMeasuredDimension(mWidth, mheight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintme(canvas, value);
//        canvas.drawArc(rectF, 0, value, true, paint);
//        if (value < arclist.get(1) && value > arclist.get(0)) {
//            canvas.drawArc(rectF, arclist.get(0), value - 1, true, paint1);
//        }
    }

    private void paintme(Canvas canvas, float value) {//绘制函数，这里主要做的是针对传来的动画值进行相应的绘
        // 制，在动画值没有达到相应模块的绘制阈值时，就不进行绘制，若达到了阈值，就绘制到相应的动画值，若超过了阈
        // 值，就只绘制到相应模块的最大值处，即arclist.get(i)中取出的值处。
        int num = 0;
        for (int i = 0; i < arclist.size(); i++) {
            if (value > num && value < num + arclist.get(i)) {
                canvas.drawArc(rectF, num, value - num, true, paints.get(i));
            } else if (value >= num + arclist.get(i)) {
                canvas.drawArc(rectF, num, arclist.get(i)-1, true, paints.get(i));
            }
            num = num + arclist.get(i);
        }
        canvas.drawCircle(redius,redius,redius/2,centerpaint);
    }

    public void initAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 360);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    public void setDatas(LinkedList<Integer> mColorList, LinkedHashMap<String, Integer> mDataMap) {
        this.mColorList = mColorList;
        this.mDataMap = mDataMap;
    }
}
