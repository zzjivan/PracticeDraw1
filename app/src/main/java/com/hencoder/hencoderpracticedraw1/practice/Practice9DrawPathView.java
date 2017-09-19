package com.hencoder.hencoderpracticedraw1.practice;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {
    private Paint paint;
    private Path path;
    private RectF rectF1;
    private RectF rectF2;

    private final int circleRadius = 80;

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        path = new Path();
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        练习内容：使用 canvas.drawPath() 方法画心形
        rectF1 = new RectF(getWidth()/2, getHeight()/2-50-circleRadius,
                getWidth()/2+2*circleRadius, getHeight()/2-50+circleRadius);
        rectF2 = new RectF(getWidth()/2-2*circleRadius, getHeight()/2-50-circleRadius,
                getWidth()/2, getHeight()/2-50+circleRadius);
        path.addArc(rectF2, -225, 225);
        path.arcTo(rectF1, 180,225,false);
        path.lineTo(getWidth()/2,getHeight()/2+160);
        canvas.drawPath(path, paint);
    }
}
