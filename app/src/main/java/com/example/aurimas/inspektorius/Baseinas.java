package com.example.aurimas.inspektorius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

public class Baseinas extends AppCompatActivity {
    private LinearLayout klausimai;

    private Date dateStarted;
    private Date dateEnded;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kontroles_langas);
        dateStarted = new Date();
        dateEnded = new Date();

        Button exit = (Button) findViewById(R.id.baigti_kontrole);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
                Log.d("BASEINAS", dateEnded.toString());
            }
        });

        klausimai = (LinearLayout) findViewById(R.id.klausimai);

        ArrayList<Klausimas> kl = new ArrayList<>();
        kl.add(new Klausimas(R.string.k2_1));
        kl.add(new Klausimas(R.string.k2_2));
        kl.add(new Klausimas(R.string.k2_3));
        kl.add(new Klausimas(R.string.k2_4));
        kl.add(new Klausimas(R.string.k2_5));
        kl.add(new Klausimas(R.string.k2_6));
        kl.add(new Klausimas(R.string.k2_7));
        kl.add(new Klausimas(R.string.k2_8));
        kl.add(new Klausimas(R.string.k2_9));
        kl.add(new Klausimas(R.string.k2_10));

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
                    Log.d("BASEINAS", dateStarted.toString());
                }
            });
            klausimai.addView(view);

            if (klausimas.getAtsakymas() == Atsakymas.NE) {
            }
        }
    }
}
