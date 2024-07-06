package com.example.planettamagotchi;

import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Tamagotchi {

    private ProgressBar healthBar;
    private ProgressBar climateBar;
    private ImageView tamagotchiImageView;
    private Handler handler;

    /**
     * Konstruktor
     *
     * @param healthBar Die Fortschrittsanzeige für das Leben
     * @param climateBar Die Fortschrittsanzeige für das Klima
     * @param tamagotchiImageView Das Bild des Tamagotchis
     */
    public Tamagotchi(ProgressBar healthBar, ProgressBar climateBar, ImageView tamagotchiImageView) {
        this.healthBar = healthBar;
        this.climateBar = climateBar;
        this.tamagotchiImageView = tamagotchiImageView;

        this.handler = new Handler();

        startTamagotchi();
    }

    /**
     * Aktualisiert den Zustand des Tamagotchi basierend auf der Gesundheit und dem Klima
     * Ändert das Bild des Tamagotchi abhängig vom aktuellen Zustand
     */
    private void updateTamagotchiState() {
        if (healthBar.getProgress() == 0) {
            tamagotchiImageView.setImageResource(R.drawable.tot);
        } else if (healthBar.getProgress() < 30) {
            tamagotchiImageView.setImageResource(R.drawable.krank);
        } else if (climateBar.getProgress() < 30) {
            tamagotchiImageView.setImageResource(R.drawable.zuheiss);
        } else if (climateBar.getProgress() > 70) {
            tamagotchiImageView.setImageResource(R.drawable.frieren);
        } else {
            tamagotchiImageView.setImageResource(R.drawable.tamagotchi_neu);
        }
    }

    /**
     * Startet das Tamagotchi
     * Die Methode wird alle 2 Sekunden aufgerufen
     */
    private void startTamagotchi() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    updateTamagotchiState();
                }
                catch(Exception e) {
                    Log.e("Tamagotchi", "Error updating Tamagotchi state", e);
                }
                handler.postDelayed(this, 2000); // Alle 2 Sekunden ausführen
            }
        }, 2000); // Erste Ausführung nach 2 Sekunden
    }
}
