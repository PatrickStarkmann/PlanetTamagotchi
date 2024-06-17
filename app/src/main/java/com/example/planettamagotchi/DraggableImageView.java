package com.example.planettamagotchi;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
public class DraggableImageView extends androidx.appcompat.widget.AppCompatImageView{
    private PointF lastTouchPoint = new PointF();
    private float initialX;
    private float initialY;

    public DraggableImageView(Context context) {
        super(context);
        init();
    }

    public DraggableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DraggableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastTouchPoint.set(event.getRawX(), event.getRawY());
                        initialX = getX();
                        initialY = getY();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getRawX() - lastTouchPoint.x;
                        float deltaY = event.getRawY() - lastTouchPoint.y;
                        setX(getX() + deltaX);
                        setY(getY() + deltaY);
                        lastTouchPoint.set(event.getRawX(), event.getRawY());
                        return true;

                    case MotionEvent.ACTION_UP:
                        setX(initialX);
                        setY(initialY);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }
}
