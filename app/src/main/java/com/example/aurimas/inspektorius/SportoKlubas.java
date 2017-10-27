package com.example.aurimas.inspektorius;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class SportoKlubas extends AppCompatActivity {
    private LinearLayout klausimai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sporto_klubas);

        klausimai = (LinearLayout) findViewById(R.id.klausimai);

        ArrayList<Klausimas> kl = new ArrayList<>();
        kl.add(new Klausimas(R.string.kl_1, 0));
        kl.add(new Klausimas(R.string.kl_2, 0));
        kl.add(new Klausimas(R.string.kl_3, 0));
        kl.add(new Klausimas(R.string.kl_4, 0));
        kl.add(new Klausimas(R.string.kl_5, 0));
        kl.add(new Klausimas(R.string.kl_6, 0));
        kl.add(new Klausimas(R.string.kl_7, 0));
        kl.add(new Klausimas(R.string.kl_8, 0));
        kl.add(new Klausimas(R.string.kl_9, 0));
        kl.add(new Klausimas(R.string.kl_10, 0));
        kl.add(new Klausimas(R.string.kl_11, 0));
        kl.add(new Klausimas(R.string.kl_12, 0));
        kl.add(new Klausimas(R.string.kl_14, 0));
        kl.add(new Klausimas(R.string.kl_15, 0));
        kl.add(new Klausimas(R.string.kl_16, 0));
        kl.add(new Klausimas(R.string.kl_17, 0));
        kl.add(new Klausimas(R.string.kl_18, 0));

        for (final Klausimas klausimas: kl) {
            View view = View.inflate(this, R.layout.klausimynas, null);
            TextView textView = (TextView) view.findViewById(R.id.klausimas);
            textView.setText(klausimas.getKlausimas());
            RadioButton radioButton = (RadioButton) view.findViewById(R.id.taip);
            radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    klausimas.setAtsakymas(0);
                    Toast.makeText(SportoKlubas.this, klausimas.getKlausimas(), Toast.LENGTH_SHORT).show();
                }
            });
            klausimai.addView(view);


        }

    }
}
