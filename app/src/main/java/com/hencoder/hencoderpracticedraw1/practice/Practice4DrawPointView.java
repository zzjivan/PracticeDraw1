package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice4DrawPointView extends View {
    private Paint paint1;
    private Paint paint2;
    private final int padding = 150;

    public Practice4DrawPointView(Context context) {
        super(context);
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint1 = new Paint();
        paint2 = new Paint();
    }

    public Practice4DrawPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPoint() 方法画点
//        一个圆点，一个方点
//        圆点和方点的切换使用 paint.setStrokeCap(cap)：`ROUND` 是圆点，`BUTT` 或 `SQUARE` 是方点
        paint1.setStrokeWidth(100);
        paint1.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(getWidth()/2-padding,getHeight()/2, paint1);

        paint2.setStrokeWidth(100);
        paint2.setStrokeCap(Paint.Cap.BUTT);
        canvas.drawPoint(getWidth()/2+padding,getHeight()/2,paint2);
    }
}
