package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice2DrawCircleView extends View {
    private final int radius = 160;
    private final int padding = 40;

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;

    public Practice2DrawCircleView(Context context) {
        super(context);
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        paint1 = new Paint();
        paint2 = new Paint();
        paint3 = new Paint();
        paint4 = new Paint();
    }

    public Practice2DrawCircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawCircle() 方法画圆
//        一共四个圆：1.实心圆 2.空心圆 3.蓝色实心圆 4.线宽为 20 的空心圆
        paint1.setAntiAlias(true);
        canvas.drawCircle(getWidth()/2-padding-radius,radius,radius, paint1);

        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(getWidth()/2+padding+radius,radius,radius, paint2);

        paint3.setAntiAlias(true);
        paint3.setColor(Color.BLUE);
        canvas.drawCircle(getWidth()/2-padding-radius,radius+padding+2*radius,radius,paint3);

        paint4.setAntiAlias(true);
        paint4.setStyle(Paint.Style.STROKE);
        paint4.setStrokeWidth(30);
        canvas.drawCircle(getWidth()/2+padding+radius,radius+padding+2*radius,radius,paint4);
    }
}
