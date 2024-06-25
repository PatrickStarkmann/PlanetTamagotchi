package com.example.planettamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Shop extends AppCompatActivity {
    private ImageView zuMainButton;
    private ImageView colaImageView;
    private ImageView teaImageView;

    private ImageView engelImageView;
    private ImageView radioImageView;
    private TextView colaCounterTextView;
    private TextView engelCounterTextView;
    private TextView radioCounterTextView;
    private TextView teaCounterTextView;
    private TextView sternCounterTextView;
    private int colaCounter = 0;
    private int teaCounter = 0;
    private int engelCounter = 0;
    private int radioCounter = 0;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.zaehler), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //SternCounter hinzufügen
        preferenceManager = new PreferenceManager(this);

        sternCounterTextView = findViewById(R.id.SternCounterShop);
        int sternCount = preferenceManager.loadSternCount();
        sternCounterTextView.setText(String.valueOf(sternCount));

        // Zurück zur MainActivity Button
        zuMainButton = findViewById(R.id.ZuMainButton);
        zuMainButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(Shop.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Initialize ImageView and TextView for Cola
        colaImageView = findViewById(R.id.imageView7);
        colaCounterTextView = findViewById(R.id.textView2);

        // OnClickListener für Cola-ImageView
        colaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                colaCounter++;
                colaCounterTextView.setText(String.valueOf(colaCounter));
            }
        });

        // Initialize ImageView and TextView for Engel
        engelImageView = findViewById(R.id.imageView9);
        engelCounterTextView = findViewById(R.id.textView4);

        // OnClickListener für Engel-ImageView
        engelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                engelCounter++;
                engelCounterTextView.setText(String.valueOf(engelCounter));
            }
        });
        // Initialize ImageView and TextView for Radio
        radioImageView = findViewById(R.id.imageView8);
        radioCounterTextView = findViewById(R.id.textView3);

        // OnClickListener für Engel-ImageView
        radioImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioCounter++;
                radioCounterTextView.setText(String.valueOf(radioCounter));
            }
        });

        // Initialize ImageView and TextView for Tea
        teaImageView = findViewById(R.id.imageView2);
        teaCounterTextView = findViewById(R.id.textView);

        // OnClickListener für Tea-ImageView
        teaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                teaCounter++;
                teaCounterTextView.setText(String.valueOf(teaCounter));
            }
        });
    }
}

/*Rüber Hovern Preis*/