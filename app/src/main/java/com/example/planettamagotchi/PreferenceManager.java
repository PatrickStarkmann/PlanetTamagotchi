package com.example.planettamagotchi;

import android.content.Context;
import android.content.SharedPreferences;

//Julian: Speichern des Sterncounters nach Pausieren der Gameview
public class PreferenceManager {

    private static final String PREFS_NAME = "SternPrefs";
    private static final String STERN_COUNT_KEY = "SternCount";

    private SharedPreferences sharedPreferences;

    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveSternCount(int sternCount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(STERN_COUNT_KEY, sternCount);
        editor.apply();
    }

    public int loadSternCount() {
        return sharedPreferences.getInt(STERN_COUNT_KEY, 0); // Default value is 0 if not found
    }
}