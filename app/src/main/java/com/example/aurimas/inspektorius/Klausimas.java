package com.example.aurimas.inspektorius;

/**
 * Created by Aurimas on 2017-10-27.
 */

public class Klausimas {
    private int klausimas;
    private int atsakymas;

    public int getKlausimas() {
        return klausimas;
    }

    public void setKlausimas(int klausimas) {
        this.klausimas = klausimas;
    }

    public int getAtsakymas() {
        return atsakymas;
    }

    public void setAtsakymas(int atsakymas) {
        this.atsakymas = atsakymas;
    }

    public Klausimas(int klausimas, int atsakymas) {
        this.klausimas = klausimas;
        this.atsakymas = atsakymas;
    }
}
