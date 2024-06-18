package com.example.planettamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.planettamagotchi.PreferenceManager;

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

    private TextView sternCounterView;


    //zum speichern des Stern Wertes
    private int sternCount = 0;
    private PreferenceManager preferenceManager;
    //Stern alle 15sek spawnen
    private SternManager sternManager;

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

        //Anthony Stern Synchro



        // Julian: Stern
        preferenceManager = new PreferenceManager(this);

        sternCounter = findViewById(R.id.SternCounter);
        sternCount = preferenceManager.loadSternCount();
        sternCounter.setText(String.valueOf(sternCount));

        // SternManager initialisieren und starten
        RelativeLayout layout = findViewById(R.id.zaehler);
        sternManager = new SternManager(this, layout);
        sternManager.start();


        // Healthbar von Anthony :
        ProgressBar progressBar = findViewById(R.id.progressBar);
        healthBar = new HealthBar(progressBar);
        // Klimabar von Anthony:
        ProgressBar progressBar2 = findViewById(R.id.progressBar2);
        klima = new Klima(progressBar2);



        ImageView tamagotchiImageView = findViewById(R.id.tamagotchi);
        TamagotchiTouchListener touchListener = new TamagotchiTouchListener(tamagotchiImageView, R.drawable.tamagotchi_neu, R.drawable.kitzeln, progressBar, progressBar2);
        tamagotchiImageView.setOnTouchListener(touchListener);
        // Tamagotchi Verhalten von Patrick
        Tamagotchi tamagotchi = new Tamagotchi(progressBar, progressBar2, tamagotchiImageView);

        // Oli: Shop Imageview --> Das man den Shopbutton anklicken kann und zum Shop kommt
        shopImageView = findViewById(R.id.shop);
        shopImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Shop.class);
                startActivity(intent);
            }
        });

        //Wolke zum Test
        new Wolke(this,layout);
    }

    //Julian: SternCounter hochzählen und speichern nach Pause der Gameview
    protected void onPause() {
        super.onPause();
        preferenceManager.saveSternCount(sternCount);
    }
    public void incrementCount() {
        sternCount++;
        sternCounter.setText(String.valueOf(sternCount));
    }

}