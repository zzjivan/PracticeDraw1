package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Practice11PieChartView extends View {

    // 3 | 4
    // 2 | 1
    private final int AREA1 = 1;
    private final int AREA2 = 2;
    private final int AREA3 = 3;
    private final int AREA4 = 4;

    private final int[] data = {1,6,6,60,108,139,40};
    private final String[] name = {"Froyo","Gingerbread","Ice Cream Sandwich",
            "Jelly Bean","KitKat","Lollipop","Marshmallow"};
    private final int[] color = {Color.WHITE, Color.MAGENTA, Color.GRAY,
            Color.parseColor("#33B05E"), Color.parseColor("#0080D9"), Color.RED, Color.YELLOW};

    private final int scale_offset = 20;
    private final int item_padding = 1;

    private final int layout_left_padding = 200;
    private final int layout_top_padding = 80;
    private final int radius = 250;
    private final int center_x = layout_left_padding+radius;
    private final int center_y = layout_top_padding+radius+scale_offset;

    private final int first_line_length = 20;
    private final int line_left_bound_x = center_x-radius-50;
    private final int line_right_bound_x = center_x+radius+100;
    private final int line_text_padding = 20;

    private Paint pi_paint;
    private Paint line_paint;
    private Paint text_paint;
    private Path path;
    private RectF rect_default;

    private String selected = name[3];


    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        pi_paint = new Paint();
        pi_paint.setAntiAlias(true);
        line_paint = new Paint();
        line_paint.setStyle(Paint.Style.STROKE);
        line_paint.setStrokeWidth(4);
        line_paint.setColor(Color.GRAY);
        line_paint.setAntiAlias(true);
        text_paint = new Paint();
        text_paint.setColor(Color.WHITE);
        text_paint.setAntiAlias(true);
        text_paint.setTextSize(25);
        path = new Path();
        rect_default = new RectF(center_x-radius, center_y-radius, center_x+radius,center_y+radius);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 圆饼、引导线、文字的绘制可以在一个for循环中完成
     * 分开写的目的是便于查看，容易理解
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        canvas.drawColor(Color.parseColor("#306E8C"));

        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#306E8C"));

        //圆饼的绘制  start -------------------------------
        int currentArc = -data[0];
        for(int i = 0; i < data.length; i ++) {
            path.reset();
            path.moveTo(center_x, center_y);
            pi_paint.setColor(color[i]);
            if(selected.equals(name[i])) {
                double delta_x = Math.abs(scale_offset * Math.cos((currentArc+data[i]/2)*Math.PI/180));
                double delta_y = Math.abs(scale_offset * Math.sin((currentArc+data[i]/2)*Math.PI/180));
                RectF rect = new RectF(center_x-radius, center_y-radius, center_x+radius,center_y+radius);
                switch (getArea(currentArc+data[i]/2)) {
                    case AREA1:
                        path.moveTo(center_x+(float)delta_x, center_y+(float)delta_y);
                        rect.offset((float)delta_x,(float)delta_y);
                        path.arcTo(rect, currentArc, data[i]-item_padding);
                        break;
                    case AREA2:
                        path.moveTo(center_x-(float)delta_x, center_y+(float)delta_y);
                        rect.offset(-(float)delta_x, (float)delta_y);
                        path.arcTo(rect, currentArc, data[i]-item_padding);
                        break;
                    case AREA3:
                        path.moveTo(center_x-(float)delta_x, center_y-(float)delta_y);
                        rect.offset(-(float)delta_x,-(float)delta_y);
                        path.arcTo(rect, currentArc, data[i]-item_padding);
                        break;
                    case AREA4:
                        path.moveTo(center_x+(float)delta_x, center_y-(float)delta_y);
                        rect.offset((float)delta_x,-(float)delta_y);
                        path.arcTo(rect, currentArc, data[i]-item_padding);
                        break;
                    default:
                        path.arcTo(rect_default, currentArc, data[i]-item_padding);
                        Log.e("zjzhu","getArea error");
                        break;
                }
            } else {
                path.arcTo(rect_default, currentArc, data[i] - item_padding);
            }
            currentArc += data[i];
            canvas.drawPath(path,pi_paint);
        }
        //圆饼的绘制  end------------------------

        //名称引导线绘制  start---------------------------
        //内容上可以和圆饼的绘制合并一起写，分开看着更清晰
        int offset = 0;
        for(int i = 0; i < name.length; i ++) {
            offset = 0;
            path.reset();
            float delta_ancho_x = (float)Math.abs(Math.cos((currentArc+data[i]/2)*Math.PI/180));
            float delta_ancho_y = (float)Math.abs(Math.sin((currentArc+data[i]/2)*Math.PI/180));

            //是否因为被选中而偏移
            if(name[i].equals(this.selected))
                offset = scale_offset;

            switch (getArea(currentArc+data[i]/2)) {
                case AREA1:
                    path.moveTo(center_x+(offset+radius)*delta_ancho_x,center_y+(offset+radius)*delta_ancho_y);
                    path.lineTo(center_x+(offset+first_line_length+radius)*delta_ancho_x,
                            center_y+(offset+first_line_length+radius)*delta_ancho_y);
                    path.lineTo(line_right_bound_x, center_y+(offset+first_line_length+radius)*delta_ancho_y);

                    canvas.drawText(name[i],
                            line_right_bound_x+line_text_padding,
                            center_y+(offset+first_line_length+radius)*delta_ancho_y,
                            text_paint);
                    break;
                case AREA2:
                    path.moveTo(center_x-(offset+radius)*delta_ancho_x,center_y+(offset+radius)*delta_ancho_y);
                    path.lineTo(center_x-(offset+first_line_length+radius)*delta_ancho_x,
                            center_y+(offset+first_line_length+radius)*delta_ancho_y);
                    path.lineTo(line_left_bound_x, center_y+(offset+first_line_length+radius)*delta_ancho_y);

                    canvas.drawText(name[i],
                            line_left_bound_x-text_paint.measureText(name[i])-line_text_padding,
                            center_y+(offset+first_line_length+radius)*delta_ancho_y,
                            text_paint);
                    break;
                case AREA3:
                    path.moveTo(center_x-(offset+radius)*delta_ancho_x,center_y-(offset+radius)*delta_ancho_y);
                    path.lineTo(center_x-(offset+first_line_length+radius)*delta_ancho_x,
                            center_y-(offset+first_line_length+radius)*delta_ancho_y);
                    path.lineTo(line_left_bound_x, center_y-(offset+first_line_length+radius)*delta_ancho_y);

                    canvas.drawText(name[i],
                            line_left_bound_x-text_paint.measureText(name[i])-line_text_padding,
                            center_y-(offset+first_line_length+radius)*delta_ancho_y,
                            text_paint);
                    break;
                case AREA4:
                    path.moveTo(center_x+(offset+radius)*delta_ancho_x,center_y-(offset+radius)*delta_ancho_y);
                    path.lineTo(center_x+(offset+first_line_length+radius)*delta_ancho_x,
                            center_y-(offset+first_line_length+radius)*delta_ancho_y);
                    path.lineTo(line_right_bound_x, center_y-(offset+first_line_length+radius)*delta_ancho_y);

                    canvas.drawText(name[i],
                            line_right_bound_x+line_text_padding,
                            center_y-(offset+first_line_length+radius)*delta_ancho_y,
                            text_paint);
                    break;
                default:
                    break;
            }
            canvas.drawPath(path,line_paint);
            currentArc += data[i];
        }
        //名称引导线绘制  end-----------------------------
        Paint paintx = new Paint();
        paintx.setColor(Color.WHITE);
        paintx.setTextSize(40);
        paintx.setAntiAlias(true);
        canvas.drawText("饼图",center_x, center_y+radius+100, paintx);
    }

    private int getArea(int degree) {
        while(degree < 0)
            degree += 360;
        if(degree >= 360)
            degree = degree % 360;

        if(degree <= 90) {
            return AREA1;
        } else if(degree <= 180) {
            return AREA2;
        } else if(degree <= 270) {
            return AREA3;
        } else if(degree < 360) {
            return AREA4;
        }
        return -1;
    }
}
