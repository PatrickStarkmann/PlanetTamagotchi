package com.example.planettamagotchi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

import java.util.Random;

public class Stern {

    public Bitmap bitmap;
    private int sternX, sternY;
    Random random;

    //Konstruktor
    public Stern(Context context, ImageView imageView){
        this.bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.star);
        this.random = new Random();
        this.randomPosition();
        this.setImageView(imageView);
    }

    public void randomPosition(){
        this.sternX = random.nextInt(1008);
        this.sternY = random.nextInt(1604);
    }

    public void setImageView(ImageView imageView) {
        imageView.setImageBitmap(this.bitmap);
    }

}

