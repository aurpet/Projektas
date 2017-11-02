package com.example.aurimas.inspektorius;

/**
 * Created by Aurimas on 2017-10-27.
 */

public class Klausimas {
    private int punktas;

    private int klausimas;
    private Atsakymas atsakymas;

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

    public Klausimas(int klausimas) {
        this.klausimas = klausimas;
        this.atsakymas = Atsakymas.NETIKRINTA;
    }
}
