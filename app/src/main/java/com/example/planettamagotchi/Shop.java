package com.example.planettamagotchi;// Shop.java
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.planettamagotchi.MainActivity;

public class Shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // MainButon, Von Shop zur Mainactivity
        ImageButton zuMainButton = findViewById(R.id.ZuMainButton);

        zuMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Starte die MainActivity
                Intent intent = new Intent(Shop.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
