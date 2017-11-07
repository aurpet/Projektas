package com.example.aurimas.inspektorius.Kontrole;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.aurimas.inspektorius.R;

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


        klausimai = (LinearLayout) findViewById(R.id.klausimai);

        final ArrayList<Klausimas> kl = new ArrayList<>();
        kl.add(new Klausimas(R.string.k2_1, R.string.b1));
        kl.add(new Klausimas(R.string.k2_2, R.string.b2));
        kl.add(new Klausimas(R.string.k2_3, R.string.b3));
        kl.add(new Klausimas(R.string.k2_4, R.string.b4));
        kl.add(new Klausimas(R.string.k2_5, R.string.b5));
        kl.add(new Klausimas(R.string.k2_6, R.string.b6));
        kl.add(new Klausimas(R.string.k2_7, R.string.b7));
        kl.add(new Klausimas(R.string.k2_8, R.string.b8));
        kl.add(new Klausimas(R.string.k2_9, R.string.b9));
        kl.add(new Klausimas(R.string.k2_10, R.string.b10));

        Button exit = (Button) findViewById(R.id.baigti_kontrole);
        exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText pavadinimas = (EditText) findViewById(R.id.pavadinimas);
                EditText kodas = (EditText) findViewById(R.id.kodas);
                EditText adresas = (EditText) findViewById(R.id.adresas);
                EditText tel = (EditText) findViewById(R.id.tel_nr);
                EditText mail = (EditText) findViewById(R.id.el_pastas);
                EditText pareigos = (EditText) findViewById(R.id.atsakingo_pareigos);
                EditText vp = (EditText) findViewById(R.id.atsakingo_vp);
                EditText pastabos = (EditText) findViewById(R.id.pastabos);

                Log.v("EditText", "Juridinio asmens pavadinimas: " + pavadinimas.getText().toString());
                Log.v("EditText", "kodas: " + kodas.getText().toString());
                Log.v("EditText", "Adresas: " + adresas.getText().toString());
                Log.v("EditText", "Telefonas: " + tel.getText().toString());
                Log.v("EditText", "El. paštas: " + mail.getText().toString());

                for (Klausimas klausimas : kl) {
                    if (Atsakymas.NE == klausimas.getAtsakymas()) {
                        Log.d("Baseinas", "HN 109:2016 " + getString(klausimas.getPunktas()) + " p.");
                    }
                }

                Log.v("EditText", "Pastabos: " + pastabos.getText().toString());
                Log.v("Baseinas", "Patikrinimas baigtas: " + dateEnded.toString());
                Log.v("EditText", "Atsakingo asmens pareigos: " + pareigos.getText().toString());
                Log.v("EditText", "Vardas Pavardė: " + vp.getText().toString());
            }
        });

        Log.v("", "\nKLAUSYMYNAS: ");

        for (final Klausimas klausimas : kl) {
            View view = View.inflate(this, R.layout.klausimynas, null);
            TextView textView = (TextView) view.findViewById(R.id.klausimas);
            textView.setText(klausimas.getKlausimas());
            RadioGroup pasirinkimas = (RadioGroup) view.findViewById(R.id.pasirinkimas);
            pasirinkimas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    klausimas.setAtsakymas(Atsakymas.fromId(checkedId));
                    Log.d("Baseinas", "Klausimas: " + getString(klausimas.getKlausimas()));
                    Log.d("Baseinas", "Įvertinimas (Taip, ne, netikrinta): " + klausimas.getAtsakymas().name());
                }
            });
            klausimai.addView(view);
        }
    }
}
