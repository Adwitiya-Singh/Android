package com.example.adwitiyasingh.uiandanimations;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by adwitiyasingh on 5/1/17.
 */

public class CustomView extends View {

   private Paint paint;
    private  int colour;
    private  int radius=0;


    // Called from XML file( XML CONSTRUCTOR)
    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.CustomView);
        colour = ta.getColor(R.styleable.CustomView_circleColour,Color.BLACK);
        ta.recycle();
        init();

    }
    // Called when you initiate an object in java( JAVA CONSTRUCTOR)
    public CustomView(Context context) {
        super(context);
        init();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(getWidth()/2,getHeight()/2,radius,paint);

//        Path path = new Path();
//        path.moveTo(200,200);
//        path.lineTo(500,500);
//        path.lineTo(500,1000);
//        path.lineTo(200,200);
//        canvas.drawPath(path,paint);
        super.onDraw(canvas);
    }

    private void init(){
        paint = new Paint();
        paint.setColor(colour);
        post(animate);

//        ValueAnimator anim = ValueAnimator.ofFloat(0f, 400f);
//        anim.setDuration(200);
//        anim.start();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(10);

    }
    private Runnable animate = new Runnable() {
        @Override
        public void run() {
            if(radius<400){
                radius+=10;
                invalidate();;
            }
            postDelayed(this,10);
        }
    };


}
