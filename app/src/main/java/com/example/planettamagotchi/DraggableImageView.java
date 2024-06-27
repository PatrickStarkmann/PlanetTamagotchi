package com.example.planettamagotchi;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.ImageView;

import android.widget.RelativeLayout;
import android.widget.ProgressBar;

public class DraggableImageView extends androidx.appcompat.widget.AppCompatImageView{
    private PointF lastTouchPoint = new PointF();
    private float initialX;
    private float initialY;
    private Wolke wolke;
    private ImageView tamagotchi;
    private HealthBar healthBar;
    private Klima klima;

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
                        Log.d("DraggableImageView", "ACTION_MOVE: deltaX=" + deltaX + ", deltaY=" + deltaY);
                        setX(getX() + deltaX);
                        setY(getY() + deltaY);
                        lastTouchPoint.set(event.getRawX(), event.getRawY());


                    // Überprüfen, ob die ImageView über der Wolke gezogen wird

                        if (wolke != null && isTouchingWolke()) {
                            removeWolke();
                        }
                        return true;

                    case MotionEvent.ACTION_UP:
                        if (isTouchingTamagotchi()) {
                            handleDrop();
                        }
                        setX(initialX);
                        setY(initialY);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }



    private boolean isTouchingTamagotchi() {
        if (tamagotchi == null) {
            Log.e("DraggableImageView", "Tamagotchi is null!");
            return false;
        }
        Rect imageViewRect = new Rect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
        Rect tamagotchiRect = new Rect((int) tamagotchi.getX(), (int) tamagotchi.getY(),
                (int) (tamagotchi.getX() + tamagotchi.getWidth()),
                (int) (tamagotchi.getY() + tamagotchi.getHeight()));
        return imageViewRect.intersect(tamagotchiRect);
    }

    private void handleDrop() {
        if (healthBar == null || klima == null) {
            Log.e("DraggableImageView", "HealthBar or Klima is null!");
            return;
        }
        if (this.getId() == R.id.ColaMain) {
            klima.changeKlima(25);
        } else if (this.getId() == R.id.TeeMain) {
            klima.changeKlima(-25);
        }
    }

    public void setTamagotchi(ImageView tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    public void setKlima(Klima klima) {
        this.klima = klima;
    }

    private boolean isTouchingWolke() {
        Rect imageViewRect = new Rect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
        Rect wolkeRect = new Rect((int) wolke.getImageView().getX(), (int) wolke.getImageView().getY(),
                (int) (wolke.getImageView().getX() + wolke.getImageView().getWidth()),
                (int) (wolke.getImageView().getY() + wolke.getImageView().getHeight()));
        return imageViewRect.intersect(wolkeRect);
    }

    private void removeWolke() {
        if (wolke != null) {
            wolke.removeWolke();
            wolke = null;
        }
    }

    public void setWolke(Wolke wolke) {
        this.wolke = wolke;
    }
}
