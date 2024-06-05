package com.example.planettamagotchi;

import android.os.Handler;
import android.os.Looper;
import android.widget.RelativeLayout;

/**
 * SternManager verwaltet die periodische Erstellung von Stern-Objekten in der MainActivity.
 * Diese Klasse sorgt dafür, dass alle 15 Sekunden ein neuer Stern erstellt wird.
 */
public class SternManager {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final MainActivity mainActivity;
    private final RelativeLayout layout;
    private final Runnable sternRunnable;

    /**
     * Konstruktor für den SternManager.
     *
     * @param mainActivity Die MainActivity, in der die Sterne erstellt werden.
     * @param layout Das RelativeLayout, in dem die Sterne angezeigt werden.
     */
    public SternManager(MainActivity mainActivity, RelativeLayout layout) {
        this.mainActivity = mainActivity;
        this.layout = layout;
        this.sternRunnable = new Runnable() {
            @Override
            public void run() {
                createNewStern();
                handler.postDelayed(this, 15000); // 15000 Millisekunden = 15 Sekunden
            }
        };
    }

    /**
     * Startet den SternManager, sodass alle 15 Sekunden ein neuer Stern erstellt wird.
     * Der erste Stern wird nach einer Verzögerung von 5 Sekunden erstellt.
     */
    public void start() {
        handler.postDelayed(sternRunnable, 5000); // Den Runnable mit initialem Delay starten
    }

    /**
     * Stoppt den SternManager und entfernt alle geplanten Callbacks.
     */
    public void stop() {
        handler.removeCallbacks(sternRunnable);
    }

    /**
     * Erstellt ein neues Stern-Objekt und fügt es dem Layout hinzu.
     */
    private void createNewStern() {
        new Stern(mainActivity, layout);
    }
}