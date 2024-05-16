package com.example.planettamagotchi;

import android.view.MotionEvent;
import android.view.View;

public class TamagotchiTouchListener implements View.OnTouchListener {
    private int swipeCount = 0; // Variable zum Zählen der Swipe-Bewegungen
    private float startX; // Startposition des Berührens

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
                }
                break;
        }
        return true;
    }

    public int getSwipeCount() {
        return swipeCount;
    }
}

