package com.example.planettamagotchi;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Erstelle eine Instanz der GameView
        GameView gameView = new GameView(this);

        // Setze die GameView als ContentView für diese Activity
        setContentView(gameView);
    }
}
