package com.example.planettamagotchi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {

    private TextView sternCounterTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        int bonusSterne = getIntent().getIntExtra("BONUS_STERNE", 0);

        

        ImageView tamagotchiButton = findViewById(R.id.tamagotchiButton);
        tamagotchiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Wechsel zu MainActivity und übergeben der Bonussterne
                Intent intent = new Intent(GameOver.this, MainActivity.class);
                intent.putExtra("BONUS_STERNE", bonusSterne);
                startActivity(intent);
                finish(); // Beendet die aktuelle Activity
            }
        });

        //anzeigen wie viele Sterne gewonnen wurden
        sternCounterTextView = findViewById(R.id.SternCounterGameOver);
        sternCounterTextView.setText(String.valueOf(bonusSterne));
    }
}
