package com.example.planettamagotchi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

public class HealthBar {
    private ProgressBar progressBar;
    private Handler handler;
    private int progress = 100;
    private Runnable decreaseRunnable;
    private ProgressBar climateBar;
    public static final String PREFS_NAME = "HealthBarPrefs";
    public static final String PROGRESS_KEY = "HealthBarProgress";
    private SharedPreferences sharedPreferences;



    public HealthBar(ProgressBar progressBar, ProgressBar climateBar, Context context) {
        this.progressBar = progressBar;
        this.handler = new Handler();
        this.climateBar = climateBar;
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Fortschritt aus SharedPreferences laden
        this.progress = sharedPreferences.getInt(PROGRESS_KEY, 100); // Standardwert ist 100

        updateProgress();
        startDecreasing();

    }

    private void updateProgress() {
        Log.d("HealthBar", "Aktueller Fortschritt: " + progress);
        progressBar.setProgress(progress);
    }

    private void decreaseProgress() {
        // nur wenn das Klima passt verliert es wenig Leben
        if ((progress > 0) & (climateBar.getProgress() > 30 & climateBar.getProgress() < 70) ) {
            progress -= 2;
            updateProgress();
        }
        else {
            progress -= 5;
            updateProgress();
        }
    }

    private void startDecreasing() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                decreaseProgress();
                handler.postDelayed(this, 10000); // Alle 10 Sekunden ausführen
            }
        }, 5000); // Erste Ausführung nach 5 Sekunden
    }


    /**
     * Julian: decrasing durch Wolke
     * decrease alle 2 sekunden ausführen
     */
    public void wolkenDecrease(){
        if (decreaseRunnable == null) {
            decreaseRunnable = new Runnable() {
                @Override
                public void run() {
                    decreaseProgress();
                    handler.postDelayed(this,2000);//Alle 2 Sekunden ausführen
                }
            };
            handler.post(decreaseRunnable);
        }
    }

    /** Julian:
     * decrease löschen und runnable auf null setzten
     */
    public void stopDecreasing() {
        if (decreaseRunnable != null) {
            handler.removeCallbacks(decreaseRunnable);
            decreaseRunnable = null;
        }
    }

    public int getProgress() {
        return this.progress;
    }

    public void saveProgress() {
        Log.d("HealthBar", "Speichern des Fortschritts: " + progress);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PROGRESS_KEY, progress);
        boolean success = editor.commit();
        Log.d("HealthBar", "Speichern erfolgreich: " + success);
    }

    public void increaseHealth(int amount) {
        Log.d("HealthBar", "Erhöhung der Gesundheit um: " + amount);
        this.progress += amount;
        if (this.progress > 100) {
            this.progress = 100;
        } else if (this.progress < 0) {
            this.progress = 0;
        }
        updateProgress();
        saveProgress();
    }
}



