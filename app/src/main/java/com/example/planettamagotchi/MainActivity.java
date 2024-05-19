package com.example.planettamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // private ProgressBar progressBar;
    private HealthBar healthBar;
    private Klima klima;
    // Oli Shop
    private ImageView shopImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.zaehler), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        // Stern im Hintergrund erstellen, VORLÄUFIG. RandomTimer zum einfügen folgt.
        Stern stern = new Stern(this, findViewById(R.id.zaehler));
        // Erstelle eine Instanz der Stern-Klasse,
        // layout als parameter wird erst noch gesucht

        // Healthbar von Anthony :
        ProgressBar progressBar = findViewById(R.id.progressBar);
        healthBar = new HealthBar(progressBar, findViewById(R.id.tamagotchi));
        // Klimabar von Anthony:
        ProgressBar progressBar2 = findViewById(R.id.progressBar2);
        klima = new Klima(progressBar2, findViewById(R.id.tamagotchi));



        ImageView tamagotchiImageView = findViewById(R.id.tamagotchi);
        TamagotchiTouchListener touchListener = new TamagotchiTouchListener(tamagotchiImageView, R.drawable.tamagotchi_neu, R.drawable.kitzeln);
        tamagotchiImageView.setOnTouchListener(touchListener);

        // Oli: Shop Imageview --> Das man den Shopbutton anklicken kann und zum Shop kommt
        shopImageView = findViewById(R.id.shop);
        shopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Shop.class);
                startActivity(intent);
            }
        });
    }
}