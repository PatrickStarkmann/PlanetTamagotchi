package com.example.planettamagotchi;


import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class TamagotchiTouchListener implements View.OnTouchListener {
    private int swipeCount = 0; // Variable zum Zählen der Swipe-Bewegungen
    private float startX; // Startposition des Berührens
    private final ImageView imageView; // Die ImageView, die das GIF anzeigt
    private final int originalGif; // Originales GIF
    private final int alternateGif; // Alternatives GIF
    private final Handler handler = new Handler(); // Handler für die Verzögerung
    private ProgressBar healthBar;
    private ProgressBar climateBar;


    /**
     * Konstruktor
     *
     * @param imageView    Die ImageView, die das GIF anzeigt
     * @param originalGif  Das originale Gif
     * @param alternateGif Das alternative Gif
     * @param healthBar    Die Fortschrittsanzeige für die Healthbar
     * @param climateBar   Die Fortschrittsanzeige für die Klimabar
     */
    public TamagotchiTouchListener(ImageView imageView, int originalGif, int alternateGif, ProgressBar healthBar, ProgressBar climateBar) {
        this.imageView = imageView;
        this.originalGif = originalGif;
        this.alternateGif = alternateGif;
        this.healthBar = healthBar;
        this.climateBar = climateBar;
    }

    /**
     * Methode wird aufgerufen, wenn eine Berührung auf der View erkannt wird
     *
     * @param v     Die View, die gestreichelt wird
     * @param event Das MotionEvent für die Berührung
     * @return true, wenn geswiped wurde
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Speichere die Startposition des Berührens
                startX = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                // Berechne die Distanz zwischen Start- und Endposition
                float endX = event.getX();
                float deltaX = endX - startX;

                // Wenn die Distanz groß genug ist, erhöhe die Swipe-Zähler-Variable
                if (Math.abs(deltaX) > 200) {
                    swipeCount++;
                    // Printen zum testen
                    System.out.println("Es wurde geswiped yuhu" + "SwipeCount:" + swipeCount);
                    if ((healthBar.getProgress() >= 30) & (climateBar.getProgress() > 30 & climateBar.getProgress() < 70))
                        changeGifTemporarily(); // ändert Tamagotchi nur wenn es dem Tamagotchi gut geht
                }
                break;
        }
        return true;
    }

    /**
     * Ändert das GIF temporär und setzt es nach einer Verzögerung wieder auf das originale Gif zurück
     */
    private void changeGifTemporarily() {
        // Ändern des GIFs
        imageView.setImageResource(alternateGif);

        // Setzen des GIFs nach einer Verzögerung wieder auf das originale GIF
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                imageView.setImageResource(originalGif);
            }
        }, 1000); // 1000 Millisekunden = 1 Sekunde Verzögerung
    }

}

