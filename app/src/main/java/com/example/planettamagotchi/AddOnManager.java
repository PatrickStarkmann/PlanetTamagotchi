package com.example.planettamagotchi;

import android.os.Handler;
import android.os.Looper;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * AddOnManager verwaltet die periodische Erstellung von Stern-Objekten in der MainActivity.
 * Diese Klasse sorgt dafür, dass alle 15 Sekunden ein neuer Stern erstellt wird.
 * Und auch eine Wolke nach random Zeit
 */
public class AddOnManager {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final MainActivity mainActivity;
    private final RelativeLayout layout;
    private final Runnable sternRunnable;
    private final Runnable wolkeRunnable;
    private final Random random;




    /**
     * Konstruktor
     *
     * @param mainActivity Die MainActivity, in der die AddOns erstellt werden.
     * @param layout Das RelativeLayout, in dem die AddOns angezeigt werden.
     */
    public AddOnManager(MainActivity mainActivity, RelativeLayout layout) {
        this.mainActivity = mainActivity;
        this.layout = layout;
        this.random = new Random();
        this.sternRunnable = new Runnable() {
            @Override
            public void run() {
                createNewStern();
                handler.postDelayed(this, 15000); // 15000 Millisekunden = 15 Sekunden
            }
        };
        this.wolkeRunnable = new Runnable(){
            public void run() {
                createNewWolke();
                // Generiere eine zufällige Verzögerung zwischen 10 und 30 Sekunden
                int delay = 35000 + random.nextInt(25000); // 35 Sekunden + zusätzliche bis zu 25 Sekunden
                handler.postDelayed(this, delay);
            }
        };
    }

    /**
     * Startet den Manager, sodass alle 15 Sekunden ein neuer Stern erstellt wird.
     * Der erste Stern wird nach einer Verzögerung von 5 Sekunden erstellt.
     */
    public void start() {
        handler.postDelayed(sternRunnable, 5000); // Den Runnable mit initialem Delay starten
        int delay = 15000 + random.nextInt(20000); // 10000 Millisekunden = 15 Sekunden, 20000 Millisekunden = zusätzliche bis zu 20 Sekunden
        handler.postDelayed(wolkeRunnable, delay); // Den Runnable mit initialem Delay starten
    }

    /**
     * Stoppt den Manager und entfernt alle geplanten Callbacks.
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

    /**
     * Erstellt ein neues Wolken Objekt
     */
    private void createNewWolke(){
        new Wolke(mainActivity, layout);
    }
}