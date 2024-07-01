package com.example.planettamagotchi;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.media.MediaPlayer;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.planettamagotchi.PreferenceManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.media.MediaPlayer;

import java.text.BreakIterator;

import pl.droidsonroids.gif.GifImageView;

public class MainActivity extends AppCompatActivity {
    public TextView sternCounter;
    //private ProgressBar progressBar;
    private HealthBar healthBar;
    private Klima klima;
    // Oli Shop
    private ImageView shopImageView;
    private ImageView minigameImageView;
    private ProgressBar klimaProgressBar;
    private ProgressBar healthProgressBar;
    private ImageView tamagotchi;
    private DraggableImageView colaMain;
    private DraggableImageView cakeMain;
    private DraggableImageView teeMain;
    private DraggableImageView wiederbelebung;
    private Shop shop; // Referenz auf das Shop-Objekt
    private ImageView radioImageView;
    private MediaPlayer mediaPlayer;
    private int radioClickCount = 0;


    //zum speichern des Stern Wertes
    private int sternCount = 0;
    private PreferenceManager preferenceManager;
    //Stern alle 15sek spawnen
    private AddOnManager addOnManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        mediaPlayer = MediaPlayer.create(this, R.raw.trololololl);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.zaehler), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //Anthony Items
        ImageView tamagotchiImageView = findViewById(R.id.tamagotchi);
        // Initialisiere die Views
        tamagotchi = findViewById(R.id.tamagotchi);
        colaMain = findViewById(R.id.ColaMain);
        teeMain = findViewById(R.id.TeeMain);
        wiederbelebung = findViewById(R.id.Wiederbelebung);
        cakeMain = findViewById(R.id.Kuchen);

        // Initialisiere die ProgressBars
        klimaProgressBar = findViewById(R.id.progressBar2);
        healthProgressBar = findViewById(R.id.progressBar);


        if (colaMain != null) { // Überprüfe, ob colaMain gefunden wurde
            colaMain.setShop(shop); // Setze die Shop-Referenz erst jetzt
        } else {
            Log.e("MainActivity", "colaMain nicht gefunden!");
        }


        // Initialisiere die Klima-Klasse und Health-Klasse
        healthBar = new HealthBar(healthProgressBar, klimaProgressBar, this);
        klima = new Klima(klimaProgressBar, healthProgressBar, this);

        // Setze die tamagotchi- und klima-Referenzen in den DraggableImageViews
        colaMain.setTamagotchi(tamagotchi);
        colaMain.setKlima(klima);


        teeMain.setTamagotchi(tamagotchi);
        teeMain.setKlima(klima);

        wiederbelebung.setTamagotchi(tamagotchi);
        wiederbelebung.setHealthBar(healthBar);


        cakeMain.setTamagotchi(tamagotchi);
        cakeMain.setHealthBar(healthBar);


        shop = Shop.getInstance(); // Singleton-Instanz des Shops verwenden
        colaMain.setShop(shop);
        teeMain.setShop(shop);
        wiederbelebung.setShop(shop);
        cakeMain.setShop(shop);



        // Julian: Stern
        preferenceManager = new PreferenceManager(this);

        sternCounter = findViewById(R.id.SternCounter);
        sternCount = preferenceManager.loadSternCount();
        sternCounter.setText(String.valueOf(sternCount));

        // SternManager initialisieren und starten
        RelativeLayout layout = findViewById(R.id.zaehler);
        addOnManager = new AddOnManager(this, layout);
        addOnManager.start();


        // Healthbar von Anthony :
        ProgressBar progressBar = findViewById(R.id.progressBar);
        ProgressBar progressBar2 = findViewById(R.id.progressBar2);



        // OnClickListener für Radio-ImageView
        radioImageView = findViewById(R.id.RadioMain);
        radioImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioClickCount++;

                if (radioClickCount == 1) {
                    if (shop.getRadioCounter() > 0) {
                        if (!mediaPlayer.isPlaying()) {
                            mediaPlayer.start();
                            shop.decrementRadioCounter(); // Radio nur beim Starten abziehen
                            // radioClickCount nicht ändern, bleibt bei 1
                        }
                    } else {
                        showNotEnoughRadiosDialog();
                        radioClickCount = 0;
                    }
                } else if (radioClickCount == 2) { // Jetzt erst auf 2 erhöhen
                    mediaPlayer.stop();
                    mediaPlayer.prepareAsync();
                    radioClickCount = 0; // Zurücksetzen für den nächsten Start
                }
            }
        });


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

        // Navigiere zu GameHomeScreen wenn auf das minigame ImageView geklickt wird
        minigameImageView = findViewById(R.id.minigame);
        minigameImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameHomeScreen.class);
                startActivity(intent);
            }
        });

    }

    //Julian: SternCounter hochzählen und speichern nach Pause der Gameview
    protected void onPause() {
        super.onPause();
        preferenceManager.saveSternCount(sternCount);


        // Fortschritt der Klimabar speichern

        SharedPreferences sharedPreferences = getSharedPreferences(Klima.PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(Klima.PROGRESS_KEY, klima.getProgress());
        editor.apply();


        // Fortschritt der Healthbar speichern
        // erstmal auskommentiert weil Tamagotchi sonst stirbt
        sharedPreferences = getSharedPreferences(HealthBar.PREFS_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putInt(HealthBar.PROGRESS_KEY, healthBar.getProgress());
        editor.apply();


    }

    public void incrementCount() {
        sternCount++;
        sternCounter.setText(String.valueOf(sternCount));
    }

    //Julian: Schaden den die Wolke macht aufrufen
    public void startWolkenSchaden() {
        this.healthBar.wolkenDecrease();
    }

    //Julian: Schaden den die Wolke macht stoppen
    public void stopWolkenSchaden() {
        this.healthBar.stopDecreasing();
    }
    // Methode zum Setzen der Wolke für die DraggableImageView

    @Override
    protected void onResume() {
        super.onResume();
        // Überprüfen, ob Bonussterne übergeben wurden
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("BONUS_STERNE")) {
            int bonusSterne = intent.getIntExtra("BONUS_STERNE", 0);
            for (int i = 0; i < bonusSterne; i++) {
                incrementCount();
            }
            // Entferne die Bonussterne, um Mehrfacherhöhungen zu vermeiden
            intent.removeExtra("BONUS_STERNE");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }
    private void showNotEnoughRadiosDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Keine Radios mehr!")
                .setMessage("Du hast keine Radios mehr im Inventar.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }
}