package com.example.aurimas.inspektorius.Kontrole;

/**
 * Created by Aurimas on 2017-10-27.
 */

public class Klausimas {

    private int punktas;
    private int klausimas;
    private Atsakymas atsakymas;

    public Klausimas(int klausimas, int punktas) {
        this.klausimas = klausimas;
        this.punktas = punktas;
        this.atsakymas = Atsakymas.NETIKRINTA;
    }

    public int getPunktas() {
        return punktas;
    }

    public void setPunktas(int punktas) {
        this.punktas = punktas;
    }

    public int getKlausimas() {
        return klausimas;
    }

    public void setKlausimas(int klausimas) {
        this.klausimas = klausimas;
    }

    public Atsakymas getAtsakymas() {
        return atsakymas;
    }

    public void setAtsakymas(Atsakymas atsakymas) {
        this.atsakymas = atsakymas;
    }
}
