package com.example.planettamagotchi;
import android.os.Handler;
import android.widget.ProgressBar;

public class HealthBar {
    private ProgressBar progressBar;
    private Handler handler;
    private int progress = 100;
    private Runnable decreaseRunnable;
    private ProgressBar climateBar;



    public HealthBar(ProgressBar progressBar, ProgressBar climateBar) {
        this.progressBar = progressBar;
        this.handler = new Handler();
        this.climateBar = climateBar;
        updateProgress();
        startDecreasing();
    }

    private void updateProgress() {
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
}
