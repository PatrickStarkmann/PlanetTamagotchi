package com.example.planettamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {
    public TextView sternCounter;
    // private ProgressBar progressBar;
    private HealthBar healthBar;
    private Klima klima;
    // Oli Shop
    private ImageView shopImageView;

    //zum speichern des Stern Wertes
    private int sternCount = 0;

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


        // Julian: Stern im Hintergrund erstellen, VORLÄUFIG. RandomTimer zum einfügen folgt.
        Stern stern = new Stern(this, findViewById(R.id.zaehler));
        sternCounter = findViewById(R.id.SternCounter);


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

    //Julian: SternCounter hochzählen
    public void incrementCount() {
        sternCount++;
        sternCounter.setText(String.valueOf(sternCount));
    }

}