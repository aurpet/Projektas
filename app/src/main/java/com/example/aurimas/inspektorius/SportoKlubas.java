package com.example.aurimas.inspektorius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;


public class SportoKlubas extends AppCompatActivity {
    private LinearLayout klausimai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kontroles_langas);

        klausimai = (LinearLayout) findViewById(R.id.klausimai);

        ArrayList<Klausimas> kl = new ArrayList<>();
        kl.add(new Klausimas(R.string.kl_1));
        kl.add(new Klausimas(R.string.kl_2));
        kl.add(new Klausimas(R.string.kl_3));
        kl.add(new Klausimas(R.string.kl_4));
        kl.add(new Klausimas(R.string.kl_5));
        kl.add(new Klausimas(R.string.kl_6));
        kl.add(new Klausimas(R.string.kl_7));
        kl.add(new Klausimas(R.string.kl_8));
        kl.add(new Klausimas(R.string.kl_9));
        kl.add(new Klausimas(R.string.kl_10));
        kl.add(new Klausimas(R.string.kl_1));
        kl.add(new Klausimas(R.string.kl_1));
        kl.add(new Klausimas(R.string.kl_1));
        kl.add(new Klausimas(R.string.kl_1));
        kl.add(new Klausimas(R.string.kl_1));
        kl.add(new Klausimas(R.string.kl_1));
        kl.add(new Klausimas(R.string.kl_18));

        for (final Klausimas klausimas : kl) {
            View view = View.inflate(this, R.layout.klausimynas, null);
            TextView textView = (TextView) view.findViewById(R.id.klausimas);
            textView.setText(klausimas.getKlausimas());
            RadioGroup pasirinkimas = (RadioGroup) view.findViewById(R.id.pasirinkimas);
            pasirinkimas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    klausimas.setAtsakymas(Atsakymas.fromId(checkedId));
                    Log.d("BASEINAS", "Klausimas: " + getString(klausimas.getKlausimas()));
                    Log.d("BASEINAS", "Pasirinktas atsakymas: " + klausimas.getAtsakymas().name());
                    Log.d("BASEINAS", new Date().toString());
                }
            });
            klausimai.addView(view);

            if (klausimas.getAtsakymas() == Atsakymas.NE) {

            }
        }

    }
}
