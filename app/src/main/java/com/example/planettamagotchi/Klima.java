package com.example.planettamagotchi;

import android.content.SharedPreferences;
import android.os.Handler;
import android.widget.ProgressBar;
import android.content.Context;
import android.util.Log;

import java.util.Random;

/**
 * Die Klasse Klima verwaltet die Klimaleiste und aktualisiert sie periodisch basierend auf zufälligen Änderungen.
 * Sie speichert und lädt den Fortschritt der Klimaleiste in SharedPreferences.
 */
public class Klima {
    private ProgressBar progressBar2;
    private Handler handler;
    private int progress;
    private ProgressBar healthBar;
    public static final String PREFS_NAME = "KlimaPrefs";
    public static final String PROGRESS_KEY = "KlimaProgress";
    private SharedPreferences sharedPreferences;

    /**
     * Konstruktor für die Klasse Klima. Initialisiert die Klimaleiste, die Gesundheitsleiste und SharedPreferences.
     *
     * @param progressBar Die ProgressBar für die Klimaleiste.
     * @param healthBar Die ProgressBar für die Gesundheitsleiste.
     * @param context Der Kontext, in dem die View läuft, über den sie auf das aktuelle Thema, Ressourcen usw. zugreifen kann.
     */
    public Klima(ProgressBar progressBar, ProgressBar healthBar, Context context) {
        this.progressBar2 = progressBar;
        this.handler = new Handler();
        this.progress = 50; // Startet in der Mitte
        this.healthBar = healthBar;
        this.sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Fortschritt aus SharedPreferences laden
        this.progress = sharedPreferences.getInt(PROGRESS_KEY, 50); // Standardwert ist 50

        Log.d("Klima", "Initialisierter Fortschritt: " + this.progress);

        updateProgress();
        startUpdating();
    }

    /**
     * Aktualisiert die Klimaleiste basierend auf dem aktuellen Fortschritt.
     */
    private void updateProgress() {
        Log.d("Klima", "Aktualisieren des Fortschrittsbalkens: " + progress);
        progressBar2.setProgress(progress);
    }

    /**
     * Startet die periodische Aktualisierung des Klimafortschritts.
     */
    private void startUpdating() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateClimate();
                handler.postDelayed(this, 2000); // Alle 2 Sekunden ausführen
            }
        }, 2000); // Erste Ausführung nach 2 Sekunden
    }

    /**
     * Aktualisiert den Klimafortschritt basierend auf zufälligen Änderungen oder festen Änderungen,
     * falls bestimmte Grenzwerte überschritten werden.
     */
    private void updateClimate() {
        Random random = new Random();
        // wenn die Klimagrenzen erreicht werden, geht die Klimabar nicht mehr in den Normalbereich
        int change;
        if (progress > 70) {
            change = 2;
        } else if (progress < 30) {
            change = -2;
        } else {
            change = random.nextBoolean() ? 2 : -2; // Entweder um 2% erhöhen oder um 2% verringern
        }

        // wenn das Tamagotchi tot ist, bleibt die Klimabar stehen
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
    }

    /**
     * Gibt den aktuellen Klimafortschritt zurück.
     *
     * @return Der aktuelle Klimafortschritt.
     */
    public int getProgress() {
        return this.progress;
    }

    /**
     * Ändert den Klimafortschritt um den angegebenen Betrag und aktualisiert die Klimaleiste.
     *
     * @param amount Der Betrag, um den der Klimafortschritt geändert werden soll.
     */
    public void changeKlima(int amount) {
        this.progress += amount;
        if (this.progress > 100) {
            this.progress = 100;
        } else if (this.progress < 0) {
            this.progress = 0;
        }
        Log.d("Klima", "Änderung des Klimafortschritts: " + amount + ", neuer Fortschritt: " + this.progress);
        updateProgress();
        saveProgress();
    }

    /**
     * Speichert den aktuellen Klimafortschritt in SharedPreferences.
     */
    public void saveProgress() {
        Log.d("Klima", "Speichern des Fortschritts: " + progress);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(PROGRESS_KEY, progress);
        boolean success = editor.commit(); // commit() verwenden, um sofortige Speicherung sicherzustellen
        Log.d("Klima", "Speichern erfolgreich: " + success);
    }
}