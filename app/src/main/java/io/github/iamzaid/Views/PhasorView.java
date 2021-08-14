package io.github.iamzaid.Views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

import io.github.iamzaid.Phasor;

public class PhasorView extends View {

    private Timer timer;
    private Canvas canvas;
    final Float originX = 370/2f;
    final Float originY = 290/2f;
    Paint primary = new Paint();
    Paint black = new Paint();
    Paint red = new Paint();
    Paint blue = new Paint();
    Double angle;

    public PhasorView(Context context) {
        super(context);
        init(null);
    }

    public PhasorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public PhasorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public PhasorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;

        primary.setColor(Color.parseColor("#A5DACF"));
        red.setColor(Color.RED);
        blue.setColor(Color.BLUE);
        black.setColor(Color.BLACK);
        primary.setStyle(Paint.Style.FILL);
        black.setStrokeWidth(5);
        blue.setStrokeWidth(5);
        red.setStrokeWidth(5);


        canvas.drawRect(0,0,this.getWidth(),this.getHeight(),primary);
        //Phasor Yaxis
        canvas.drawLine(convertDpToPx(this.getContext(),originX),
                convertDpToPx(this.getContext(),0),
                convertDpToPx(this.getContext(),originX),
                convertDpToPx(this.getContext(),290),
                black);
        //Phasor Xaxis
        canvas.drawLine(convertDpToPx(this.getContext(),0),
                convertDpToPx(this.getContext(),originY),
                convertDpToPx(this.getContext(),370),
                convertDpToPx(this.getContext(),originY),
                black);
        //Line to get an idea of how it looks
        canvas.drawLine(convertDpToPx(this.getContext(),originX),
                convertDpToPx(this.getContext(),originY),
                convertDpToPx(this.getContext(),275),
                convertDpToPx(this.getContext(),200),
                red);
        canvas.drawLine(convertDpToPx(this.getContext(),originX),
                convertDpToPx(this.getContext(),originY),
                convertDpToPx(this.getContext(),340),
                convertDpToPx(this.getContext(),100),
                blue);
    }

    public float convertDpToPx(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    private void clearRectangle(){
        canvas.drawRect(0,0,this.getWidth(),this.getHeight(),primary);
        //Phasor Yaxis
        canvas.drawLine(convertDpToPx(this.getContext(),originX),
                convertDpToPx(this.getContext(),0),
                convertDpToPx(this.getContext(),originX),
                convertDpToPx(this.getContext(),290),
                black);
        //Phasor Xaxis
        canvas.drawLine(convertDpToPx(this.getContext(),0),
                convertDpToPx(this.getContext(),originY),
                convertDpToPx(this.getContext(),370),
                convertDpToPx(this.getContext(),originY),
                black);
    }

private void drawPhasor(double amplitude,double angle,Paint paint) {
    angle *= Math.PI;
    double x;
    double y;
    //if angle b/w 0 and pi/2 (inc) the Q1
    if (angle >= 0 && angle <= Math.PI / 2) {
        x = amplitude * Math.cos(angle);
        y = amplitude * Math.sin(angle);
        canvas.drawLine(convertDpToPx(this.getContext(), originX),
                convertDpToPx(this.getContext(), originY),
                convertDpToPx(this.getContext(), originX + (float) x),
                convertDpToPx(this.getContext(), originY - (float) y),
                paint);
    }
    //if angle b/w pi/2 excluded and pi(inc) then Q2
    else if (angle > Math.PI / 2 && angle <= Math.PI) {
        angle = Math.PI - angle;
        x = amplitude * Math.cos(angle);
        y = amplitude * Math.sin(angle);
        canvas.drawLine(convertDpToPx(this.getContext(), originX),
                convertDpToPx(this.getContext(), originY),
                convertDpToPx(this.getContext(), originX - (float) x),
                convertDpToPx(this.getContext(), originY - (float) y),
                paint);
    }//If angle b/w pi (excluded) and 3pi/2(inc) then Q3
    else if (angle > Math.PI && angle <= (3 * Math.PI) / 2) {
        angle = angle - Math.PI;
        x = amplitude * Math.cos(angle);
        y = amplitude * Math.sin(angle);
        canvas.drawLine(convertDpToPx(this.getContext(), originX),
                convertDpToPx(this.getContext(), originY),
                convertDpToPx(this.getContext(), originX - (float) x),
                convertDpToPx(this.getContext(), originY + (float) y),
                paint);
    }
    //If angle b/w 3pi/2 (excluded) and 2pi(inc) then Q4
    else if ((angle > (3 * Math.PI) / 2) && angle <= 2 * Math.PI) {
        angle = (2 * Math.PI) - angle;
        x = amplitude * Math.cos(angle);
        y = amplitude * Math.sin(angle);
        canvas.drawLine(convertDpToPx(this.getContext(), originX),
                convertDpToPx(this.getContext(), originY),
                convertDpToPx(this.getContext(), originX + (float) +x),
                convertDpToPx(this.getContext(), originY - (float) +y),
                paint);
    }
}

    public void run(Phasor phasor){
        angle = phasor.getPhase();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                clearRectangle();
                drawPhasor(phasor.getAmplitude(),phasor.getPhase(),red);

                if(angle<=2){
                    angle+=0.1;
                }else {
                    angle=0.0;
                }
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(task,0,500);
    }


}

