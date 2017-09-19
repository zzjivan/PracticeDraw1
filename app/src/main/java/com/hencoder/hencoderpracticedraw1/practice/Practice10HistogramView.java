package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice10HistogramView extends View {

    private int layout_padding_left = 80;

    private final int origin_x = layout_padding_left;
    private final int origin_y = 500;

    private int item_width;
    private int item_padding = 25;

    private final int[] data = {3, 25, 25, 220, 300, 350, 220};
    private final String[] name = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};

    private Paint xyPaint;
    private Paint itemPaint;

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        xyPaint = new Paint();
        xyPaint.setColor(Color.WHITE);
        xyPaint.setTextSize(25);
        xyPaint.setAntiAlias(true);
        itemPaint = new Paint();
        itemPaint.setColor(Color.GREEN);
        itemPaint.setAntiAlias(true);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        item_width = (getWidth() - 2*layout_padding_left - (data.length+1)*item_padding)/7;
        canvas.drawColor(Color.DKGRAY);

        for(int i = 0; i < name.length; i ++) {
            float textWidth = xyPaint.measureText(name[i]);
            canvas.drawText(name[i], origin_x + (i+1)*item_padding + i*item_width + (item_width-textWidth)/2,
                    origin_y+30, xyPaint);
        }

        for(int i = 0; i < data.length; i ++) {
            canvas.drawRect(origin_x + (i+1)*item_padding + i*item_width,
                    origin_y - data[i],
                    origin_x + (i+1)*item_padding + i*item_width + item_width,
                    origin_y,
                    itemPaint);
        }

        canvas.drawLine(origin_x, origin_y, origin_x, origin_y - 350, xyPaint);
        canvas.drawLine(origin_x, origin_y, origin_x + getWidth() - 2*layout_padding_left, origin_y, xyPaint);

        float textWidth = xyPaint.measureText("直方图");
        canvas.drawText("直方图", getWidth()/2-textWidth/2, origin_y + 100, xyPaint);
    }
}
