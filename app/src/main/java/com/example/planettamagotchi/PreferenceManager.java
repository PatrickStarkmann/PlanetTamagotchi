package com.example.planettamagotchi;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * PreferenceManager verwaltet das Speichern und Laden des Sternzählers in den SharedPreferences.
 * Diese Klasse speichert den aktuellen Wert des Sternzählers und stellt sicher, dass der Wert
 * nach einer Unterbrechung der App (z.B. beim Pausieren oder Schließen der App) erhalten bleibt.
 */
public class PreferenceManager {

    private static final String PREFS_NAME = "SternPrefs";
    private static final String STERN_COUNT_KEY = "SternCount";
    private SharedPreferences sharedPreferences;


    /**
     * Konstruktor für den PreferenceManager.
     *
     * @param context Der Kontext, der zum Zugriff auf die SharedPreferences verwendet wird.
     */
    public PreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Speichert den aktuellen Wert des Sternzählers in den SharedPreferences.
     *
     * @param sternCount Der zu speichernde Wert des Sternzählers.
     */
    public void saveSternCount(int sternCount) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(STERN_COUNT_KEY, sternCount);
        editor.apply();
    }

    /**
     * Lädt den gespeicherten Wert des Sternzählers aus den SharedPreferences.
     *
     * @return Der gespeicherte Wert des Sternzählers. Wenn kein Wert gespeichert ist, wird 0 zurückgegeben.
     */
    public int loadSternCount() {
        return sharedPreferences.getInt(STERN_COUNT_KEY, 0); // Standardwert ist 0, falls nichts gefunden wird
    }
}