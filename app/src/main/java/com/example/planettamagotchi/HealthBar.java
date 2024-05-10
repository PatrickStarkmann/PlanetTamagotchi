package com.example.planettamagotchi;
import android.os.Handler;
import android.widget.ProgressBar;

public class HealthBar {
    private ProgressBar progressBar;
    private int progressStatus = 100;
    private Handler handler = new Handler();
    private Runnable runnable;

    public HealthBar(ProgressBar progressBar) {
        this.progressBar = progressBar;
        initialize();
    }

    private void initialize() {
        // Starte den Timer, der alle 30 Sekunden die Lebensleiste aktualisiert
        startTimer();
    }

    private void startTimer() {
        runnable = new Runnable() {
            @Override
            public void run() {
                // Verringere den Fortschritt um 1%
                progressStatus -= 30;

                // Aktualisiere die Lebensleiste
                progressBar.setProgress(progressStatus);

                // Überprüfe, ob die Lebensleiste leer ist
                if (progressStatus <= 0) {
                    System.out.println("U DEAD");
                } else {
                    // Wenn die Lebensleiste noch nicht leer ist, führe den Timer erneut aus
                    handler.postDelayed(this, 10000); // 30 Sekunden in Millisekunden
                }
            }
        };

        // Starte den Timer zum ersten Mal
        handler.postDelayed(runnable, 30000); // 30 Sekunden in Millisekunden
    }
}
