package com.example.planettamagotchi;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Eine benutzerdefinierte ImageView, die auf dem Bildschirm gezogen werden kann.
 * Behandelt Interaktionen mit einem "Tamagotchi"-Charakter und anderen UI-Elementen.
 */
public class DraggableImageView extends AppCompatImageView {
    private PointF lastTouchPoint = new PointF();
    private float initialX;
    private float initialY;
    private Wolke wolke;
    private ImageView tamagotchi;
    private HealthBar healthBar;
    private Klima klima;
    private Shop shop;

    /**
     * Konstruktor, der beim Inflating aus XML aufgerufen wird.
     *
     * @param context Der Kontext, in dem die View läuft, über den sie auf das aktuelle Thema, Ressourcen usw. zugreifen kann.
     */
    public DraggableImageView(Context context) {
        super(context);
        init();
    }

    /**
     * Konstruktor, der beim Inflating aus XML mit angegebenen Attributen aufgerufen wird.
     *
     * @param context Der Kontext, in dem die View läuft, über den sie auf das aktuelle Thema, Ressourcen usw. zugreifen kann.
     * @param attrs Die Attribute des XML-Tags, das die View aufbläst.
     */
    public DraggableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Führt das Aufblasen aus XML durch und wendet einen klassenspezifischen Basisstil an.
     *
     * @param context Der Kontext, in dem die View läuft, über den sie auf das aktuelle Thema, Ressourcen usw. zugreifen kann.
     * @param attrs Die Attribute des XML-Tags, das die View aufbläst.
     * @param defStyleAttr Ein Attribut im aktuellen Thema, das eine Referenz auf eine Stilressource enthält, die Standardwerte für die View liefert. Kann 0 sein, um nicht nach Standards zu suchen.
     */
    public DraggableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * Initialisiert die View und richtet den Touch-Listener für die Drag-Funktionalität ein.
     */
    private void init() {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        lastTouchPoint.set(event.getRawX(), event.getRawY());
                        initialX = getX();
                        initialY = getY();
                        return true;

                    case MotionEvent.ACTION_MOVE:
                        float deltaX = event.getRawX() - lastTouchPoint.x;
                        float deltaY = event.getRawY() - lastTouchPoint.y;
                        Log.d("DraggableImageView", "ACTION_MOVE: deltaX=" + deltaX + ", deltaY=" + deltaY);
                        setX(getX() + deltaX);
                        setY(getY() + deltaY);
                        lastTouchPoint.set(event.getRawX(), event.getRawY());

                        if (wolke != null && isTouchingWolke()) {
                            removeWolke();
                        }
                        return true;

                    case MotionEvent.ACTION_UP:
                        if (isTouchingTamagotchi()) {
                            handleDrop();
                            handleDrop2();
                        }
                        setX(initialX);
                        setY(initialY);
                        return true;

                    default:
                        return false;
                }
            }
        });
    }

    /**
     * Überprüft, ob diese View die Tamagotchi-View berührt.
     *
     * @return true, wenn berührt, false sonst.
     */
    private boolean isTouchingTamagotchi() {
        if (tamagotchi == null) {
            Log.e("DraggableImageView", "Tamagotchi ist null!");
            return false;
        }
        Rect imageViewRect = new Rect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
        Rect tamagotchiRect = new Rect((int) tamagotchi.getX(), (int) tamagotchi.getY(),
                (int) (tamagotchi.getX() + tamagotchi.getWidth()),
                (int) (tamagotchi.getY() + tamagotchi.getHeight()));
        return imageViewRect.intersect(tamagotchiRect);
    }

    /**
     * Behandelt die Drop-Aktion für spezifische Elemente, wenn die Tamagotchi-View berührt wird.
     */
    private void handleDrop() {
        if (this.getId() == R.id.ColaMain) {
            int colaCounter = Shop.getInstance().getColaCounter();
            if (colaCounter > 0) {
                if (klima != null) {
                    klima.changeKlima(25);
                    Shop.getInstance().decrementColaCounter();
                }
            }
        }
        if (this.getId() == R.id.TeeMain) {
            int teaCounter = Shop.getInstance().getTeaCounter();
            if (teaCounter > 0) {
                if (klima != null) {
                    klima.changeKlima(-25);
                    Shop.getInstance().decrementTeeCounter();
                }
            }
        }
    }

    /**
     * Behandelt die Drop-Aktion für spezifische Elemente, wenn die Tamagotchi-View berührt wird (zweite Aktion).
     */
    private void handleDrop2() {
        if (this.getId() == R.id.Wiederbelebung) {
            int engelCounter = Shop.getInstance().getEngelCounter();
            if (engelCounter > 0) {
                healthBar.increaseHealth(100);
                Shop.getInstance().decrementEngelCounter();
            }
        }
        if (this.getId() == R.id.Kuchen) {
            int kuchenCounter = Shop.getInstance().getKuchenCounter();
            if (kuchenCounter > 0) {
                if (healthBar != null && healthBar.getProgress() > 0) {
                    healthBar.increaseHealth(25);
                    Shop.getInstance().decrementKuchenCounter();
                }
            }
        }
    }

    /**
     * Setzt die Tamagotchi-ImageView.
     *
     * @param tamagotchi Die Tamagotchi-ImageView.
     */
    public void setTamagotchi(ImageView tamagotchi) {
        this.tamagotchi = tamagotchi;
    }

    /**
     * Setzt die HealthBar-View.
     *
     * @param healthBar Die HealthBar-View.
     */
    public void setHealthBar(HealthBar healthBar) {
        this.healthBar = healthBar;
    }

    /**
     * Setzt die Klima-Instanz.
     *
     * @param klima Die Klima-Instanz.
     */
    public void setKlima(Klima klima) {
        this.klima = klima;
    }

    /**
     * Setzt die Shop-Instanz.
     *
     * @param shop Die Shop-Instanz.
     */
    public void setShop(Shop shop) {
        this.shop = shop;
    }

    /**
     * Überprüft, ob diese View die Wolke berührt.
     *
     * @return true, wenn berührt, false sonst.
     */
    private boolean isTouchingWolke() {
        Rect imageViewRect = new Rect((int) getX(), (int) getY(), (int) (getX() + getWidth()), (int) (getY() + getHeight()));
        Rect wolkeRect = new Rect((int) wolke.getImageView().getX(), (int) wolke.getImageView().getY(),
                (int) (wolke.getImageView().getX() + wolke.getImageView().getWidth()),
                (int) (wolke.getImageView().getY() + wolke.getImageView().getHeight()));
        return imageViewRect.intersect(wolkeRect);
    }

    /**
     * Entfernt die Wolke, wenn sie berührt wird.
     */
    private void removeWolke() {
        if (wolke != null) {
            wolke.removeWolke();
            wolke = null;
        }
    }

    /**
     * Setzt die Wolke-Instanz.
     *
     * @param wolke Die Wolke-Instanz.
     */
    public void setWolke(Wolke wolke) {
        this.wolke = wolke;
    }
}