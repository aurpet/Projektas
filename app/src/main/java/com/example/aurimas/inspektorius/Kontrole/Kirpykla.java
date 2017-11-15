package com.example.aurimas.inspektorius.Kontrole;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.aurimas.inspektorius.R;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;

public class Kirpykla extends AppCompatActivity {
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
        kl.add(new Klausimas(R.string.k3_1, R.string.k1));
        kl.add(new Klausimas(R.string.k3_2, R.string.k2));
        kl.add(new Klausimas(R.string.k3_3, R.string.k3));
        kl.add(new Klausimas(R.string.k3_4, R.string.k4));
        kl.add(new Klausimas(R.string.k3_5, R.string.k5));
        kl.add(new Klausimas(R.string.k3_6, R.string.k6));
        kl.add(new Klausimas(R.string.k3_7, R.string.k7));
        kl.add(new Klausimas(R.string.k3_8, R.string.k8));
        kl.add(new Klausimas(R.string.k3_9, R.string.k9));
        kl.add(new Klausimas(R.string.k3_10, R.string.k10));

        Button exit = (Button) findViewById(R.id.baigti_kontrole);
        exit.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
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

                generatePDF(kl);



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
                    Log.d("kirpykla", "Klausimas: " + getString(klausimas.getKlausimas()));
                    Log.d("kirpykla", "Įvertinimas (Taip, ne, netikrinta): " + klausimas.getAtsakymas().name());
                }
            });
            klausimai.addView(view);
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void generatePDF(ArrayList<Klausimas> kl) {

        EditText imone = (EditText)findViewById(R.id.pavadinimas);
        EditText kodas = (EditText)findViewById(R.id.kodas);
        EditText adresas = (EditText) findViewById(R.id.adresas);
        EditText tel = (EditText)findViewById(R.id.tel_nr);
        EditText mail = (EditText)findViewById(R.id.el_pastas);
        EditText pareigos = (EditText) findViewById(R.id.atsakingo_pareigos);
        EditText vardaspav = (EditText)findViewById(R.id.atsakingo_vp);
        EditText pastabos = (EditText)findViewById(R.id.pastabos);
        generatePDF(kl, imone, kodas, adresas, tel, mail, pareigos, vardaspav, pastabos);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void generatePDF(ArrayList<Klausimas> kl, EditText imone, EditText kodas, EditText adresas, EditText tel, EditText mail, EditText pareigos, EditText vardaspav, EditText pastabos) {
        String fullPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()
                + "/kirpykla-"+System.currentTimeMillis()+".pdf";

        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");


        File file = new File(fullPath);
        try {
            PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(file));
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            Document document = new Document(pdfDocument);
            document.add(
                    new Paragraph()
                            .setFontSize(20)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER)
                            .add(new Text("Kirpyklos patikrinimo aktas")));
            document.add(new Paragraph("Juridinio asmens pavadinimas: " +imone.getText().toString()));
            document.add(new Paragraph("Imones kodas: "+ kodas.getText().toString()));
            document.add(new Paragraph("Veiklos vykdymo adresas: " + adresas.getText().toString()));
            document.add(new Paragraph("Telefono numeris: "+ tel.getText().toString()));
            document.add(new Paragraph("El. pastas: " + mail.getText().toString()));
            document.add(new Paragraph("Atsakingo asmens pareigos: " + pareigos.getText().toString()));
            document.add(new Paragraph("Vardas pavarde: " + vardaspav.getText().toString()));
            document.add(new Paragraph("Pastabos " + pastabos.getText().toString()));
            document.add(new Paragraph("Patikrinimas baigtas: " + dateFormat.format(dateStarted).toString()));


            for (Klausimas klausimas : kl) {
                document.add(new Paragraph("Klausimas: " + getString(klausimas.getKlausimas())));
                document.add(new Paragraph("Atsakymas: " + klausimas.getAtsakymas().name()));
                if (Atsakymas.NE == klausimas.getAtsakymas()) {
                    document.add(new Paragraph("Pazeidimas: " + getString(klausimas.getPunktas()) + " p."));
                }
            }

            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
