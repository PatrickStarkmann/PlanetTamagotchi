package com.example.planettamagotchi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.Random;

public class Stern {

    private Bitmap stern;
    private int sternX, sternY;
    Random random;

    //Konstruktor
    public Stern(Context context){
        stern = BitmapFactory.decodeResource(context.getResources(),R.drawable.star);
        random = new Random();
        resetPosition();
    }

    public void resetPosition(){
        sternX = random.nextInt(1008);
        sternY = random.nextInt(1604);
    }


}

