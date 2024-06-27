package com.example.planettamagotchi;

import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.ProgressBar;
import android.content.Context;

import java.util.Random;

public class Klima {
    private ProgressBar progressBar2;
    private Handler handler;
    private int progress;
    private ProgressBar healthBar;
    public static final String PREFS_NAME = "KlimaPrefs";
    public static final String PROGRESS_KEY = "KlimaProgress";
    private SharedPreferences sharedPreferences;



    public Klima(ProgressBar progressBar, ProgressBar healthBar, Context context) {
        this.progressBar2 = progressBar;
        this.handler = new Handler();
        this.progress = 50; // Startet in der Mitte
        this.healthBar = healthBar;
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Fortschritt aus SharedPreferences laden
        this.progress = sharedPreferences.getInt(PROGRESS_KEY, 50); // Standardwert ist 50


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

        // wenn das Tamagotchi tot ist bleibt die Klimabar stehen
        if (healthBar.getProgress() != 0) {
            progress += change;
        }

        // Sicherstellen, dass der Fortschritt innerhalb des gültigen Bereichs (0-100) bleibt
        if (progress > 100) {
            progress = 100;
        } else if (progress < 0) {
            progress = 0;
        }
        updateProgress();

        // Fortschritt in SharedPreferences speichern
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PROGRESS_KEY, progress);
        editor.apply();
    }

    public int getProgress() {
        return this.progress;
    }

    public void changeKlima(int amount) {
        this.progress += amount;
        if (this.progress > 100) {
            this.progress = 100;
        } else if (this.progress < 0) {
            this.progress = 0;
        }
        updateProgress();
        saveProgress();
    }

    private void saveProgress() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PROGRESS_KEY, progress);
        editor.apply();
    }


}
