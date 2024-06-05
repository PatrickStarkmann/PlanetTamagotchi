package com.example.planettamagotchi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.Random;
import android.os.Handler;
import android.os.Looper;

public class Stern {

    private Bitmap bitmap;
    private int sternX, sternY;
    private Random random;
    private ImageView imageView;
    private Handler handler;
    private MainActivity mainActivity;

    //Konstruktor
    public Stern(MainActivity _mainActivity, RelativeLayout _layout){
        this.mainActivity = _mainActivity;
        this.setImageView(_mainActivity);
        this.bitmap = BitmapFactory.decodeResource(_mainActivity.getResources(),R.drawable.small_star);
        this.random = new Random();
        this.randomPosition();
        this.bitmapToImageView(_layout);
        this.setOnClickListener(_layout);
        this.scheduleRemoval(_layout);
    }

    /**setImageView
     * setzt die imageView für den Konstruktor
     * @param _context = context aus konstruktor
     */
    public void setImageView(Context _context){
        this.imageView = new ImageView(_context);
    }
    /**randomPosition
     * setzt x und y auf random werte
     */
    public void randomPosition(){
        this.sternX = random.nextInt(1008);
        this.sternY = random.nextInt(1304);
    }


    /**setImageView
     * setzt die Position des Sterns und die ImageView
     * @param layout = das layout auf welches der Stern abgebildet wird
     */
    public void bitmapToImageView(RelativeLayout layout) {
        imageView.setImageBitmap(this.bitmap);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(imageView);
        imageView.setX(sternX);
        imageView.setY(sternY);
    }

    /** scheduleRemoval
     * entfernt den Stern nach 5 Sekunden
     * @param layout = das layout auf welchem der Stern abgebildet wurde
     */
    private void scheduleRemoval(final RelativeLayout layout) {
        handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.removeView(imageView);
            }
        }, 5000); // 5000 Millisekunden = 5 Sekunden
    }

    /** setOnClickListener
     * fügt einen OnClickListener hinzu, der auf Klickereignisse reagiert
     * Zählt den Sterncounter hoch
     * @param layout = das layout, in dem der Stern angezeigt wird
     */
    private void setOnClickListener(final RelativeLayout layout) {
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.incrementCount();
                layout.removeView(imageView);
                handler.removeCallbacksAndMessages(null); // Entfernt alle geplanten Callbacks
            }
        });
    }

}

