package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {
    private Paint paint1;
    private Paint paint2;
    private RectF rectF;
    private final int radius = 300;

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint1 = new Paint();
        paint2 = new Paint();
        paint2.setStyle(Paint.Style.STROKE);
        rectF = new RectF();
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        rectF = new RectF(getWidth()/2-radius/2,getHeight()/2-radius/2,
                getWidth()/2+radius/2,getHeight()/2+radius/2);
        canvas.drawArc(rectF,30,120,false,paint1);
        canvas.drawArc(rectF,180, 60, false, paint2);
        canvas.drawArc(rectF,260,110,true,paint1);
    }
}
