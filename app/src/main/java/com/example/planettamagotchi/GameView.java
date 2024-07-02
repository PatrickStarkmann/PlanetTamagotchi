package com.example.planettamagotchi;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends View {

    private int screenWidth;
    private int screenHeight;
    private int playerX;
    private int playerY;
    private int playerWidth = 150; // Größe des Tamagotchi anpassen
    private int playerHeight = 150; // Größe des Tamagotchi anpassen
    private Paint playerPaint;

    private ArrayList<Meteor> meteors;
    private int meteorSpeed = 45; // Meteor-Geschwindigkeit erhöht
    private Bitmap tamagotchiBitmap; // Bitmap für das Tamagotchi
    private Bitmap meteorBitmap; // Bitmap für den Meteor
    private Bitmap backgroundBitmap; // Bitmap für den Hintergrund

    private Handler handler;
    private Runnable runnable;
    private int score = 0;
    private Paint scorePaint;

    private Context context; // Kontext-Variable hinzufügen

    public GameView(Context context) {
        super(context);
        this.context = context; // Kontext initialisieren
        init();
    }

    private void init() {
        playerPaint = new Paint();
        playerPaint.setColor(Color.BLUE);

        scorePaint = new Paint();
        scorePaint.setColor(Color.BLACK);
        scorePaint.setTextSize(50);

        meteors = new ArrayList<>();
        handler = new Handler();

        // Laden und Skalieren der Tamagotchi-Bitmap
        Bitmap unscaledTamagotchiBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tamagotchi_neu);
        tamagotchiBitmap = Bitmap.createScaledBitmap(unscaledTamagotchiBitmap, playerWidth, playerHeight, true);

        // Laden und Skalieren der Meteor-Bitmap
        Bitmap unscaledMeteorBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.meteor);
        int meteorSize = 100; // Größe der Meteore anpassen
        meteorBitmap = Bitmap.createScaledBitmap(unscaledMeteorBitmap, meteorSize, meteorSize, true);

        // Laden des Hintergrundbildes
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.hintergrund_neu);

        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate();
            }
        };
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w;
        screenHeight = h;
        playerX = (screenWidth - playerWidth) / 2;
        playerY = screenHeight - playerHeight - 20;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Draw background
        canvas.drawBitmap(backgroundBitmap, 0, 0, null);

        // Draw player (Tamagotchi)
        canvas.drawBitmap(tamagotchiBitmap, playerX, playerY, playerPaint);

        // Draw meteors
        for (Meteor meteor : meteors) {
            canvas.drawBitmap(meteorBitmap, meteor.x, meteor.y, null); // Null-Paint für einfache Darstellung
            meteor.y += meteorSpeed;
            if (meteor.y > screenHeight) {
                meteor.y = 0;
                meteor.x = new Random().nextInt(screenWidth - meteorBitmap.getWidth());
                score++;
            }
            if (collisionDetection(meteor)) {
                handler.removeCallbacks(runnable);

                // Berechne 40 % der gesammelten Punkte und übergebe sie an GameOver
                int bonusSterne = (int) (score * 0.4);
                Intent intent = new Intent(context, GameOver.class);
                intent.putExtra("BONUS_STERNE", bonusSterne);
                context.startActivity(intent);
                return;
            }
        }

        // Draw score
        canvas.drawText("Score: " + score, 50, 50, scorePaint);

        spawnMeteor();
        handler.postDelayed(runnable, 30);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            playerX = (int) event.getX() - playerWidth / 2;
            if (playerX < 0) playerX = 0;
            if (playerX > screenWidth - playerWidth) playerX = screenWidth - playerWidth;
        }
        return true;
    }

    private void spawnMeteor() {
        if (meteors.size() < 5) {
            int x = new Random().nextInt(screenWidth - meteorBitmap.getWidth());
            int y = 0;
            meteors.add(new Meteor(x, y));
        }
    }

    private boolean collisionDetection(Meteor meteor) {
        int meteorWidth = meteorBitmap.getWidth();
        int meteorHeight = meteorBitmap.getHeight();

        return playerX < meteor.x + meteorWidth &&
                playerX + playerWidth > meteor.x &&
                playerY < meteor.y + meteorHeight &&
                playerY + playerHeight > meteor.y;
    }

    private class Meteor {
        int x, y;

        Meteor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
