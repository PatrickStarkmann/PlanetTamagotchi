package com.example.planettamagotchi;

import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Tamagotchi {

    private ProgressBar healthBar;
    private ProgressBar climateBar;
    private ImageView tamagotchiImageView;
    private Handler handler;

    public Tamagotchi(ProgressBar healthBar, ProgressBar climateBar, ImageView tamagotchiImageView) {
        this.healthBar = healthBar;
        this.climateBar = climateBar;
        this.tamagotchiImageView = tamagotchiImageView;

        this.handler = new Handler();

        startTamagotchi();
    }

    //hallo
    private void updateTamagotchiState() {
        if (healthBar.getProgress() < 30) {
            tamagotchiImageView.setImageResource(R.drawable.krank);
        } else if (climateBar.getProgress() < 30) {
            tamagotchiImageView.setImageResource(R.drawable.zuheiss);
        } else if (climateBar.getProgress() > 70) {
            tamagotchiImageView.setImageResource(R.drawable.frieren);
        } else {
            tamagotchiImageView.setImageResource(R.drawable.tamagotchi_neu);
        }
    }

    private void startTamagotchi() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    updateTamagotchiState();
                }
                catch(Exception e) {
                    Log.e("Tamagotchi", "Error updating Tamagotchi state", e);
                }
                handler.postDelayed(this, 2000); // Alle 2 Sekunden ausführen
            }
        }, 2000); // Erste Ausführung nach 2 Sekunden
    }
}
