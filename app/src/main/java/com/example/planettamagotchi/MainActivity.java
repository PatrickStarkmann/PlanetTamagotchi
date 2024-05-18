package com.example.planettamagotchi;

import android.os.Bundle;
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
        klima = new Klima(progressBar2);

       // configureNextButton();

        ImageView tamagotchiImageView = findViewById(R.id.tamagotchi);
        TamagotchiTouchListener touchListener = new TamagotchiTouchListener(tamagotchiImageView, R.drawable.tamagotchi_neu, R.drawable.kitzeln);
        tamagotchiImageView.setOnTouchListener(touchListener);
    }
    // ShopButton, Von Mainactivity zum Shop
    /* Erstmal auskommentiert weil ein Fehler angezeigt wurde
    private void configureNextButton() {
        ImageButton shopButton = findViewById(R.id.shopButton);  // Button mit der ID buttonZuMain
        shopButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View view){
                startActivity(new Intent(MainActivity.this, Shop.class));
            }
        });
    }

     */


}