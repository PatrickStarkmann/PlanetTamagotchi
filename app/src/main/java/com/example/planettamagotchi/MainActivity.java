package com.example.planettamagotchi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    // private ProgressBar progressBar;
    private HealthBar healthBar;

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

       // progressBar = findViewById(R.id.progressBar);
       // healthBar = new HealthBar(progressBar);

       // configureNextButton();
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