package com.example.planettamagotchi;


import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class TamagotchiTouchListener implements View.OnTouchListener {
    private int swipeCount = 0; // Variable zum Zählen der Swipe-Bewegungen
    private float startX; // Startposition des Berührens
    private final ImageView imageView; // Die ImageView, die das GIF anzeigt
    private final int originalGif; // Originales GIF
    private final int alternateGif; // Alternatives GIF
    private final Handler handler = new Handler(); // Handler für die Verzögerung

    public TamagotchiTouchListener(ImageView imageView, int originalGif, int alternateGif) {
        this.imageView = imageView;
        this.originalGif = originalGif;
        this.alternateGif = alternateGif;
    }

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
                if (Math.abs(deltaX) > 200) { // Hier kannst du die Schwelle für eine Swipe-Bewegung anpassen
                    swipeCount++;
                    // Führe hier weitere Aktionen aus, die du im Zusammenhang mit dem Swipe durchführen möchtest
                    System.out.println("Es wurde geswiped yuhu" + "SwipeCount:" + swipeCount);
                    changeGifTemporarily(); // ändert Tamagotchi
                }
                break;
        }
        return true;
    }

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

    public int getSwipeCount() {
        return swipeCount;
    }
}

