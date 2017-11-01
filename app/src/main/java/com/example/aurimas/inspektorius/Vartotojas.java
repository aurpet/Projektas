package com.example.aurimas.inspektorius;

/**
 * Created by Aurimas on 2017-11-01.
 */

public class Vartotojas {
    private String vardas = "";
    private String slaptazodis = "";

    public String getVardas() {
        return vardas;
    }
    public void setVardas(String vardas) {
        this.vardas = vardas;
    }

    public String getSlaptazodis() {
        return slaptazodis;
    }

    public void setSlaptazodis(String slaptazodis) {
        this.slaptazodis = slaptazodis;
    }

    public Vartotojas(String vardas, String slaptazodis){
        this.vardas = vardas;
        this.slaptazodis = slaptazodis;
    }
}
