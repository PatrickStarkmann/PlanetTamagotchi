package com.example.planettamagotchi;

import android.content.Context;
import android.os.Handler;

import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class Wolke {

    private Bitmap bitmap;
    private MainActivity mainActivity;
    private ImageView imageView;
    private Handler handler;

    //Konstruktor
    public Wolke(MainActivity _mainActivity, RelativeLayout _layout){
        this.mainActivity = _mainActivity;
        this.setImageView(_mainActivity);
        this.bitmap = BitmapFactory.decodeResource(_mainActivity.getResources(),R.drawable.small_wolken);
        this.bitmapToImageView(_layout);
        handler = new Handler();
        startHealthDecrease();

    }

    /**setImageView
     * setzt die imageView für den Konstruktor
     * @param _context = context aus konstruktor
     */
    public void setImageView(Context _context){
        this.imageView = new ImageView(_context);
    }

    public void bitmapToImageView(RelativeLayout layout) {
        imageView.setImageBitmap(this.bitmap);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(imageView);
        imageView.setX(380);
        imageView.setY(200);
    }

    /**
     * Methode in Main aufrufen um leben zu verringern und nach 10 sekunden stoppen
     */
    private void startHealthDecrease() {
        mainActivity.startWolkenSchaden();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mainActivity.stopWolkenSchaden();
                // Entferne Wolke nach einer bestimmten Zeit (z.B. 10 Sekunden)
                removeWolke();
            }
        }, 20000); // Wolke bleibt 10 Sekunden
    }



    /**
     * Wolke entfernen
     */
    private void removeWolke() {
        ((RelativeLayout) imageView.getParent()).removeView(imageView);
    }

}

