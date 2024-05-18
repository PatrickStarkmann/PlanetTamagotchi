package com.example.planettamagotchi;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class HealthBar {
    private ProgressBar progressBar;
    private Handler handler;
    private int progress = 100;

    private final ImageView tamagotchiImageView;


    public HealthBar(ProgressBar progressBar, ImageView tamagotchiImageView) {
        this.progressBar = progressBar;
        this.handler = new Handler();
        this.tamagotchiImageView = tamagotchiImageView;
        updateProgress();
        startDecreasing();
    }

    private void updateProgress() {
        progressBar.setProgress(progress);
    }

    private void decreaseProgress() {
        if (progress > 0) {
            progress -= 5;
            updateProgress();
            if (progress < 30) {
                tamagotchiImageView.setImageResource(R.drawable.krank);
            }
        }
    }

    private void startDecreasing() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                decreaseProgress();
                handler.postDelayed(this, 10000); // Alle 5 Sekunden ausführen
            }
        }, 5000); // Erste Ausführung nach 5 Sekunden
    }

}
