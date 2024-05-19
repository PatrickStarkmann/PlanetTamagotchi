package com.example.planettamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Shop extends AppCompatActivity {
    private ImageView zuMainButton;
    private TextView colaCounterTextView;
    private TextView teaCounterTextView;
    private int colaCounter = 0;
    private int teaCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shop);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.zaehler), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // ZuMainButton ImageView
        zuMainButton = findViewById(R.id.ZuMainButton);
        zuMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Shop.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Counters
        colaCounterTextView = findViewById(R.id.textView);
        teaCounterTextView = findViewById(R.id.textView2);

        // Cola kaufen
        Button colaButton = findViewById(R.id.button);
        colaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colaCounter++;
                colaCounterTextView.setText(String.valueOf(colaCounter));
            }
        });

        // Tee kaufen
        Button teaButton = findViewById(R.id.button2);
        teaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teaCounter++;
                teaCounterTextView.setText(String.valueOf(teaCounter));
            }
        });
    }
}
