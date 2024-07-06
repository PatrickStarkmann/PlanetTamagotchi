package com.example.planettamagotchi;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
/**
 * Die Klasse PreHomeBildschirm verwaltet die Logik und die Benutzeroberfläche für den Pre-Home-Bildschirm der Anwendung.
 * Sie ermöglicht es den Benutzern, zur Hauptansicht der Anwendung zu navigieren.
 */
public class PreHomeBildschirm extends AppCompatActivity {

    /**
     * Diese Methode wird aufgerufen, wenn die Aktivität erstellt wird.
     * Sie initialisiert die Benutzeroberfläche und setzt die erforderlichen Einstellungen für den Pre-Home-Bildschirm.
     *
     * @param savedInstanceState Wenn die Aktivität neu gestartet wird, enthält dieses Bundle die Daten, die zuletzt von onSaveInstanceState gespeichert wurden.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pre_home_bildschirm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    /**
     * Diese Methode wird aufgerufen, wenn der Play-Button geklickt wird.
     * Sie startet die Hauptansicht der Anwendung und beendet die PreHomeActivity.
     *
     * @param view Die View, die das Klickereignis ausgelöst hat.
     */
    public void onPlayButtonClick(View view) {
        // Starte die Hauptansicht
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        // Beende die PreHomeActivity
        finish();
    }
}