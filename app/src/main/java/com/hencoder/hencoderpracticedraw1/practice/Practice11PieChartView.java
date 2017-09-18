package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {

    private final int[] data = {40,1,6,6,60,100,140};
    private final String[] name = {"Marshmallow","Froyo","Gingerbread","Ice Cream Sandwich","Jelly Bean","KitKat","Lollipop"};
    private final int[] color = {Color.YELLOW, Color.WHITE, Color.MAGENTA, Color.GRAY, Color.parseColor("#33B05E"), Color.parseColor("#0080D9"), Color.RED};

    private final int scale_offset = 20;
    private final int item_padding = 1;

    private final int layout_left_padding = 110;
    private final int radius = 250;
    private final int center_x = layout_left_padding+radius;
    private final int center_y = radius+scale_offset;


    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        canvas.drawColor(Color.parseColor("#306E8C"));

        RectF rectF = new RectF(center_x-radius, center_y-radius, center_x+radius,center_y+radius);
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#306E8C"));
        int currentArc = -data[0];
        for(int i = 0; i < data.length-1; i ++) {
            Paint paint1 = new Paint();
            paint1.setAntiAlias(true);
            paint1.setColor(color[i]);
            canvas.drawArc(rectF,currentArc,data[i],true,paint1);
            currentArc += data[i];
            canvas.drawArc(rectF,currentArc,item_padding,true,paint);
            currentArc += item_padding;
        }
        Paint paint2 = new Paint();
        paint2.setColor(color[color.length-1]);
        paint2.setAntiAlias(true);
        RectF rectF1 = rectF;
        rectF1.offset(-scale_offset, -scale_offset);
        canvas.drawArc(rectF1,currentArc,data[data.length-1],true,paint2);
    }
}
