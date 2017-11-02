package com.example.aurimas.inspektorius;

/**
 * Created by Aurimas on 2017-11-02.
 */

public enum Atsakymas {
    TAIP, NE, NETIKRINTA;

    public static Atsakymas fromId(int id) {
        if (id == R.id.taip) {
            return TAIP;
        } else if (id == R.id.ne) {
            return NE;
        } else {
            return NETIKRINTA;
        }
    }
}
