package com.example.planettamagotchi;

import android.os.Handler;
import android.os.Looper;
import android.widget.RelativeLayout;
public class SternManager {
    private final Handler handler = new Handler(Looper.getMainLooper());
    private final MainActivity mainActivity;
    private final RelativeLayout layout;
    private final Runnable sternRunnable;

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

    public void start() {
        handler.postDelayed(sternRunnable, 5000); // den runnable mit delay starten
    }

    public void stop() {
        handler.removeCallbacks(sternRunnable);
    }

    private void createNewStern() {
        new Stern(mainActivity, layout);
    }
}
