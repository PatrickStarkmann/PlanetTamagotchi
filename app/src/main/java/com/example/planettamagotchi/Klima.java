package com.example.planettamagotchi;

import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.util.Random;

public class Klima {
    private ProgressBar progressBar2;
    private Handler handler;
    private int progress;



    public Klima(ProgressBar progressBar) {
        this.progressBar2 = progressBar;
        this.handler = new Handler();
        this.progress = 50; // Startet in der Mitte

        updateProgress();
        startUpdating();
    }

    private void updateProgress() {
        progressBar2.setProgress(progress);
    }

    private void startUpdating() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateClimate();
                handler.postDelayed(this, 2000); // Alle 5 Sekunden ausführen
            }
        }, 2000); // Erste Ausführung nach 5 Sekunden
    }

    private void updateClimate() {
        Random random = new Random();
        int change = random.nextBoolean() ? 5 : -5; // Entweder um 5% erhöhen oder um 5% verringern

        progress += change;

        // Sicherstellen, dass der Fortschritt innerhalb des gültigen Bereichs (0-100) bleibt
        if (progress > 100) {
            progress = 100;
        } else if (progress < 0) {
            progress = 0;
        }

        updateProgress();
    }


}
