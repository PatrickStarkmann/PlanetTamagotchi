package com.example.planettamagotchi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import java.util.Random;

public class Stern {

    public Bitmap bitmap;
    private int sternX, sternY;
    private Random random;
    private ImageView imageView;

    //Konstruktor
    public Stern(Context context, RelativeLayout layout){
        this.imageView = new ImageView(context);
        this.bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.small_star);
        this.random = new Random();
        this.randomPosition();
        this.setImageView(layout);
    }

    /**
     * setzt x und y auf random werte
     */
    public void randomPosition(){
        this.sternX = random.nextInt(1008);
        this.sternY = random.nextInt(1604);
    }


    /**
     * setzt die Position des Sterns und die ImageView
     * @param layout = das layout auf welches der Stern abgebildet wird
     */
    public void setImageView(RelativeLayout layout) {
        imageView.setImageBitmap(this.bitmap);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
        layout.addView(imageView);
        imageView.setX(sternX);
        imageView.setY(sternY);
    }



}

