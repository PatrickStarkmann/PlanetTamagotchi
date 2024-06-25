package com.example.planettamagotchi;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
    private ImageView preiseImageView;
    private ImageView engelImageView;
    private ImageView radioImageView;
    private TextView colaCounterTextView;
    private TextView engelCounterTextView;
    private TextView radioCounterTextView;
    private TextView teaCounterTextView;
    private int colaCounter = 0;
    private int teaCounter = 0;
    private int engelCounter = 0;
    private int radioCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.zaehler), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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
        // Initialize ImageView for Preise and set OnClickListener
        preiseImageView = findViewById(R.id.Preise);
        preiseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreiseDialog();
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
    private void showPreiseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ABGESPACEDE PREISE");

        String[] descriptions = {
                "Cola: Eine erfrischende Cola.                                                    5 STERNE",
                "Tee: Ein beruhigender Tee.                                                        5 STERNE",
                "Engel: Noch eine Chance.                                                      100 STERNE",
                "Radio: Ein altes Radio mit Songs                                            25 STERNE"
        };

        builder.setItems(descriptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Handle the click event if needed
            }
        });


        builder.setNegativeButton("LETS GO BACK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

/*Rüber Hovern Preis*/