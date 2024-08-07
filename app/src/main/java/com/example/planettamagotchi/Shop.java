package com.example.planettamagotchi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * Die Klasse Shop verwaltet die Logik und die Benutzeroberfläche für den Shop-Bereich der Anwendung.
 * Sie ermöglicht es den Benutzern, Gegenstände mit Sternen zu kaufen und den Fortschritt der Käufe zu speichern.
 */
public class Shop extends AppCompatActivity {
    private ImageView zuMainButton;
    private ImageView colaImageView;
    private ImageView teaImageView;
    private ImageView preiseImageView;
    private ImageView engelImageView;
    private ImageView radioImageView;
    private ImageView cakeImageView;
    private TextView colaCounterTextView;
    private TextView engelCounterTextView;
    private TextView radioCounterTextView;
    private TextView teaCounterTextView;
    private TextView cakeCounterTextView;
    private TextView sternCounterTextView;
    private static Shop instance; // Singleton-Instanz
    private int colaCounter = 0;
    private int teaCounter = 0;
    private int engelCounter = 0;
    private int radioCounter = 0;
    private int cakeCounter = 0;
    private PreferenceManager preferenceManager;
    private SharedPreferences sharedPreferences;
    public static final String PREFS_NAME = "CounterPrefs";
    public static final String TEA_COUNTER_KEY = "TeaCounter";
    public static final String COLA_COUNTER_KEY = "ColaCounter";
    public static final String ENGEL_COUNTER_KEY = "EngelCounter";
    public static final String RADIO_COUNTER_KEY = "RadioCounter";
    public static final String CAKE_COUNTER_KEY = "CakeCounter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        instance = this; // Singleton-Instanz setzen

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.zaehler), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialisieren von SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

        // Gespeicherte Zähler laden
        teaCounter = loadCounter(TEA_COUNTER_KEY);
        colaCounter = loadCounter(COLA_COUNTER_KEY);
        engelCounter = loadCounter(ENGEL_COUNTER_KEY);
        radioCounter = loadCounter(RADIO_COUNTER_KEY);
        cakeCounter = loadCounter(CAKE_COUNTER_KEY);

        // Initialisieren der Views
        sternCounterTextView = findViewById(R.id.SternCounterShop);
        zuMainButton = findViewById(R.id.ZuMainButton);
        colaImageView = findViewById(R.id.imageView7);
        colaCounterTextView = findViewById(R.id.textView2);
        engelImageView = findViewById(R.id.imageView9);
        engelCounterTextView = findViewById(R.id.textView4);
        preiseImageView = findViewById(R.id.Preise);
        radioImageView = findViewById(R.id.imageView8);
        radioCounterTextView = findViewById(R.id.textView3);
        teaImageView = findViewById(R.id.imageView2);
        teaCounterTextView = findViewById(R.id.textView);
        cakeImageView = findViewById(R.id.imageView10);
        cakeCounterTextView = findViewById(R.id.textView5);

        // Initiale Zählerwerte setzen
        colaCounterTextView.setText(String.valueOf(colaCounter));
        teaCounterTextView.setText(String.valueOf(teaCounter));
        engelCounterTextView.setText(String.valueOf(engelCounter));
        radioCounterTextView.setText(String.valueOf(radioCounter));
        cakeCounterTextView.setText(String.valueOf(cakeCounter));

        // SternCounter-Wert laden
        preferenceManager = new PreferenceManager(this);
        int sternCount = preferenceManager.loadSternCount();
        sternCounterTextView.setText(String.valueOf(sternCount));

        // Zurück zur MainActivity Button
        zuMainButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Shop.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // OnClickListener für Cola-ImageView
        colaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sternCount = Integer.parseInt(sternCounterTextView.getText().toString());
                if (sternCount >= 5) {
                    sternCount -= 5;
                    colaCounter++;
                    colaCounterTextView.setText(String.valueOf(colaCounter));
                    sternCounterTextView.setText(String.valueOf(sternCount));
                    preferenceManager.saveSternCount(sternCount); // Sternenzähler speichern
                    saveCounter(COLA_COUNTER_KEY, colaCounter); // Cola-Counter speichern
                } else {
                    showInsufficientStarsDialog();
                }
            }
        });

        // OnClickListener für Cake-ImageView
        cakeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sternCount = Integer.parseInt(sternCounterTextView.getText().toString());
                if (sternCount >= 5) {
                    sternCount -= 5;
                    cakeCounter++;
                    cakeCounterTextView.setText(String.valueOf(cakeCounter));
                    sternCounterTextView.setText(String.valueOf(sternCount));
                    preferenceManager.saveSternCount(sternCount); // Sternenzähler speichern
                    saveCounter(CAKE_COUNTER_KEY, cakeCounter); // Cake-Counter speichern
                } else {
                    showInsufficientStarsDialog();
                }
            }
        });

        // OnClickListener für Engel-ImageView
        engelImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sternCount = Integer.parseInt(sternCounterTextView.getText().toString());
                if (sternCount >= 100) {
                    sternCount -= 100;
                    engelCounter++;
                    engelCounterTextView.setText(String.valueOf(engelCounter));
                    sternCounterTextView.setText(String.valueOf(sternCount));
                    preferenceManager.saveSternCount(sternCount); // Sternenzähler speichern
                    saveCounter(ENGEL_COUNTER_KEY, engelCounter); // Engel-Counter speichern
                } else {
                    showInsufficientStarsDialog();
                }
            }
        });

        // OnClickListener für Preise-ImageView
        preiseImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreiseDialog();
            }
        });

        // OnClickListener für Radio-ImageView
        radioImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sternCount = Integer.parseInt(sternCounterTextView.getText().toString());
                if (sternCount >= 25) {
                    sternCount -= 25;
                    radioCounter++;
                    radioCounterTextView.setText(String.valueOf(radioCounter));
                    sternCounterTextView.setText(String.valueOf(sternCount));
                    preferenceManager.saveSternCount(sternCount); // Sternenzähler speichern
                    saveCounter(RADIO_COUNTER_KEY, radioCounter); // Radio-Counter speichern
                } else {
                    showInsufficientStarsDialog();
                }
            }
        });

        // OnClickListener für Tea-ImageView
        teaImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sternCount = Integer.parseInt(sternCounterTextView.getText().toString());
                if (sternCount >= 5) {
                    sternCount -= 5;
                    teaCounter++;
                    teaCounterTextView.setText(String.valueOf(teaCounter));
                    sternCounterTextView.setText(String.valueOf(sternCount));
                    preferenceManager.saveSternCount(sternCount); // Sternenzähler speichern
                    saveCounter(TEA_COUNTER_KEY, teaCounter); // Tea-Counter speichern
                } else {
                    showInsufficientStarsDialog();
                }
            }
        });
    }

    /**
     * Zeigt einen Dialog mit den Preisen der Artikel im Shop.
     */
    private void showPreiseDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ABGESPACEDE PREISE");

        String[] descriptions = {
                "Cola: Eine erfrischende Cola. 5 STERNE",
                "Tee: Ein beruhigender Tee. 5 STERNE",
                "Kuchen: Ein leckerer Kuchen. 5 STERNE",
                "Engel: Noch eine Chance. 100 STERNE",
                "Radio: Ein altes Radio mit Songs 25 STERNE"
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

    /**
     * Zeigt einen Dialog an, wenn der Benutzer nicht genügend Sterne hat, um einen Artikel zu kaufen.
     */
    private void showInsufficientStarsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nicht genügend Sterne")
                .setMessage("Du hast nicht genügend Sterne, um diesen Gegenstand zu kaufen.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    /**
     * Speichert den Wert eines Zählers in SharedPreferences.
     * @param key Der Schlüssel, unter dem der Zählerwert gespeichert wird.
     * @param value Der zu speichernde Wert des Zählers.
     */
    private void saveCounter(String key, int value) {
        SharedPreferences sharedPreferences = getSharedPreferences("ShopPreferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    // Getter-Methoden für die Zähler
    public int getColaCounter() {
        return colaCounter;
    }

    public int getEngelCounter() {
        return engelCounter;
    }

    public int getKuchenCounter() {
        return cakeCounter;
    }

    public int getTeaCounter() {
        return teaCounter;
    }

    public int getRadioCounter() {
        return radioCounter;
    }

    // Methoden zum Verringern der Zähler
    public void decrementColaCounter() {
        if (colaCounter > 0) {
            colaCounter--;
            colaCounterTextView.setText(String.valueOf(colaCounter));
            saveCounter(COLA_COUNTER_KEY, colaCounter); // Aktualisierten Cola-Counter speichern
        }
    }

    public void decrementTeeCounter() {
        if (teaCounter > 0) {
            teaCounter--;
            teaCounterTextView.setText(String.valueOf(teaCounter));
            saveCounter(TEA_COUNTER_KEY, teaCounter); // Aktualisierten TEA-Counter speichern
        }
    }

    public void decrementEngelCounter() {
        if (engelCounter > 0) {
            engelCounter--;
            engelCounterTextView.setText(String.valueOf(engelCounter));
            saveCounter(ENGEL_COUNTER_KEY, engelCounter); // Aktualisierten Cola-Counter speichern
        }
    }

    public void decrementKuchenCounter() {
        if (cakeCounter > 0) {
            cakeCounter--;
            cakeCounterTextView.setText(String.valueOf(cakeCounter));
            saveCounter(CAKE_COUNTER_KEY, cakeCounter); // Aktualisierten Cola-Counter speichern
        }
    }

    public void decrementRadioCounter() {
        if (radioCounter > 0) {
            radioCounter--;
            radioCounterTextView.setText(String.valueOf(radioCounter));
            saveCounter(RADIO_COUNTER_KEY, radioCounter); // Aktualisierten Cola-Counter speichern
        }
    }

    /**
     * Gibt die Singleton-Instanz der Shop-Klasse zurück.
     * @return Die Singleton-Instanz der Shop-Klasse.
     */
    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    /**
     * Lädt den Wert eines Zählers aus SharedPreferences.
     * @param key Der Schlüssel, unter dem der Zählerwert gespeichert ist.
     * @return Der geladene Wert des Zählers.
     */
    private int loadCounter(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences("ShopPreferences", MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }
}