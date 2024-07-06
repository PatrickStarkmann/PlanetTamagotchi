package com.example.planettamagotchi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * HealthBar verwaltet die Gesundheitsleiste und reduziert den Fortschritt periodisch.
 * Sie interagiert auch mit einer Klimaleiste und speichert den Fortschritt in SharedPreferences.
 */
public class HealthBar {
    private ProgressBar progressBar;
    private Handler handler;
    private int progress = 100;
    private Runnable decreaseRunnable;
    private ProgressBar climateBar;
    public static final String PREFS_NAME = "HealthBarPrefs";
    public static final String PROGRESS_KEY = "HealthBarProgress";
    private SharedPreferences sharedPreferences;

    /**
     * Konstruktor für HealthBar, initialisiert die Gesundheitsleiste, die Klimaleiste und SharedPreferences.
     *
     * @param progressBar Die ProgressBar für die Gesundheitsleiste.
     * @param climateBar Die ProgressBar für die Klimaleiste.
     * @param context Der Kontext, in dem die View läuft, über den sie auf das aktuelle Thema, Ressourcen usw. zugreifen kann.
     */
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

    /**
     * Aktualisiert die Gesundheitsleiste basierend auf dem aktuellen Fortschritt.
     */
    private void updateProgress() {
        Log.d("HealthBar", "Aktueller Fortschritt: " + progress);
        progressBar.setProgress(progress);
    }

    /**
     * Reduziert den Fortschritt basierend auf dem aktuellen Klimawert.
     */
    private void decreaseProgress() {
        // Nur wenn das Klima passt, verliert es wenig Leben
        if ((progress > 0) & (climateBar.getProgress() > 30 & climateBar.getProgress() < 70)) {
            progress -= 1;
            updateProgress();
        } else {
            progress -= 3;
            updateProgress();
        }
    }

    /**
     * Startet die periodische Reduzierung des Fortschritts.
     */
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
     * Startet die periodische Reduzierung des Fortschritts durch Wolkeninteraktion.
     * Führt decreaseProgress alle 5 Sekunden aus.
     */
    public void wolkenDecrease() {
        if (decreaseRunnable == null) {
            decreaseRunnable = new Runnable() {
                @Override
                public void run() {
                    decreaseProgress();
                    handler.postDelayed(this, 5000); // Alle 5 Sekunden ausführen
                }
            };
            handler.post(decreaseRunnable);
        }
    }

    /**
     * Stoppt die periodische Reduzierung des Fortschritts und setzt das Runnable auf null.
     */
    public void stopDecreasing() {
        if (decreaseRunnable != null) {
            handler.removeCallbacks(decreaseRunnable);
            decreaseRunnable = null;
        }
    }

    /**
     * Gibt den aktuellen Fortschritt zurück.
     *
     * @return Der aktuelle Fortschritt.
     */
    public int getProgress() {
        return this.progress;
    }

    /**
     * Speichert den aktuellen Fortschritt in SharedPreferences.
     */
    public void saveProgress() {
        Log.d("HealthBar", "Speichern des Fortschritts: " + progress);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PROGRESS_KEY, progress);
        boolean success = editor.commit();
        Log.d("HealthBar", "Speichern erfolgreich: " + success);
    }

    /**
     * Erhöht die Gesundheit um den angegebenen Betrag.
     *
     * @param amount Der Betrag, um den die Gesundheit erhöht werden soll.
     */
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


