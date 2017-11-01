package com.example.aurimas.inspektorius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class Baseinas extends AppCompatActivity {
    private LinearLayout klausimai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kontroles_langas);

        klausimai = (LinearLayout) findViewById(R.id.klausimai);

        ArrayList<Klausimas> kl = new ArrayList<>();
        kl.add(new Klausimas(R.string.k2_1, 0));
        kl.add(new Klausimas(R.string.k2_2, 0));
        kl.add(new Klausimas(R.string.k2_3, 0));
        kl.add(new Klausimas(R.string.k2_4, 0));
        kl.add(new Klausimas(R.string.k2_5, 0));
        kl.add(new Klausimas(R.string.k2_6, 0));
        kl.add(new Klausimas(R.string.k2_7, 0));
        kl.add(new Klausimas(R.string.k2_8, 0));
        kl.add(new Klausimas(R.string.k2_9, 0));
        kl.add(new Klausimas(R.string.k2_10, 0));


       for (final Klausimas klausimas: kl) {
            View view = View.inflate(this, R.layout.klausimynas, null);
            TextView textView = (TextView) view.findViewById(R.id.klausimas);
            textView.setText(klausimas.getKlausimas());
            RadioButton t = (RadioButton) view.findViewById(R.id.taip);
            t.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    klausimas.setAtsakymas(0);
                }
            });

            klausimai.addView(view);
        }

    }
}
