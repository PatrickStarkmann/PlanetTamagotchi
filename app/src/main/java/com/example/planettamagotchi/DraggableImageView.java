package com.example.planettamagotchi;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class DraggableImageView extends androidx.appcompat.widget.AppCompatImageView{
    private PointF lastTouchPoint = new PointF();
    private float initialX;
    private float initialY;
    private Wolke wolke;

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

                        // Überprüfen, ob die ImageView über der Wolke gezogen wird
                        if (wolke != null && isTouchingWolke()) {
                            removeWolke();
                        }

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

    // Methode zum Überprüfen, ob die ImageView über der Wolke gezogen wird
    private boolean isTouchingWolke() {
        Rect imageViewRect = new Rect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
        Rect wolkeRect = new Rect((int) wolke.getImageView().getX(), (int) wolke.getImageView().getY(),
                (int) (wolke.getImageView().getX() + wolke.getImageView().getWidth()),
                (int) (wolke.getImageView().getY() + wolke.getImageView().getHeight()));

        return imageViewRect.intersect(wolkeRect);
    }

    // Methode zum Entfernen der Wolke
    private void removeWolke() {
        if (wolke != null) {
            wolke.removeWolke();
            wolke = null; // Setze die Referenz auf null, um auf die entfernte Wolke nicht mehr zuzugreifen
        }
    }

    // Setter-Methode für die Wolke
    public void setWolke(Wolke wolke) {
        this.wolke = wolke;
    }
}
