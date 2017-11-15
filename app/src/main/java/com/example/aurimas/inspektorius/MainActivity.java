package com.example.aurimas.inspektorius;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aurimas.inspektorius.Kontrole.Baseinas;
import com.example.aurimas.inspektorius.Kontrole.Kirpykla;
import com.example.aurimas.inspektorius.Kontrole.SportoKlubas;
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
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText username, vardas, pavarde;
    private EditText password;
    private CheckBox rememberMeCheckBox;
    private Spinner kontrole;
    private Date dateStarted;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dateStarted = new Date();

        Button pradeti = (Button) findViewById(R.id.pradeti_kontrole);
        pradeti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                vardas = (EditText) findViewById(R.id.login_name);
                pavarde = (EditText) findViewById(R.id.login_password);

            }
        });

        kontrole = (Spinner) findViewById(R.id.spinner1);
        List<String> listKontroles = new ArrayList<>();
        listKontroles.add(" - ");
        listKontroles.add("Sporto klubas");
        listKontroles.add("Baseinas");
        listKontroles.add("Kirpykla");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, listKontroles);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kontrole.setAdapter(dataAdapter);


        login();

    }

    private void login() {


        this.username = (EditText) findViewById(R.id.login_name);
        this.password = (EditText) findViewById(R.id.login_password);

        Button submit = (Button) findViewById(R.id.pradeti_kontrole);


        rememberMeCheckBox = (CheckBox) findViewById(R.id.login_remember_me);
        final User user = new User(getApplicationContext());
        rememberMeCheckBox.setChecked(user.isRemembered());

        if (user.isRemembered()) {
            username.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
            password.setText(user.getPasswordForLogin(), TextView.BufferType.EDITABLE);
        } else {
            username.setText("", TextView.BufferType.EDITABLE);
            password.setText("", TextView.BufferType.EDITABLE);
        }

        submit.setOnClickListener(new View.OnClickListener() {
            Authentication authentication = new Authentication();

            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View focusView) {

                SimpleDateFormat dateFormat = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                }

                EditText vardas = (EditText) findViewById(R.id.login_name);
                EditText pavarde = (EditText) findViewById(R.id.login_password);
                Log.v("MainActivity", "Patikrinimą atliko: \nVisuomenės sveikatos saugos kontrolės skyriaus " +
                        "vyr. specialistas " + vardas.getText().toString() + " " +
                        pavarde.getText().toString() + "\nPatikrinimas pradėtas: " + dateFormat.format(dateStarted).toString());


                generatePDF();


                String username2 = username.getText().toString();
                String password2 = password.getText().toString();
                boolean cancel = false;

                if (!authentication.isValidCredentials(username2)) {
                    username.setError("Klaidingas vardas");
                    focusView = username;
                    cancel = true;
                }
                if (!authentication.isValidCredentials(password2)) {
                    password.setError("Klaidinga pavardė");
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                } else {
                    Toast.makeText(MainActivity.this, "Prisijungėte sėkmingai",
                            Toast.LENGTH_LONG).show();

                    if (rememberMeCheckBox.isChecked()) {
                        user.setUsernameLogin(username2);
                        user.setPasswordForLogin(password2);
                        user.setRemembered(false);
                    }

                    if (kontrole.getSelectedItemPosition() == 1) {
                        Intent goToSportoKlubas = new Intent(MainActivity.this, SportoKlubas.class);
                        startActivity(goToSportoKlubas);
                    } else if (kontrole.getSelectedItemPosition() == 2) {
                        Intent goToBaseinas = new Intent(MainActivity.this, Baseinas.class);
                        startActivity(goToBaseinas);
                    } else if (kontrole.getSelectedItemPosition() == 3) {
                        Intent goToKirpykla = new Intent(MainActivity.this, Kirpykla.class);
                        startActivity(goToKirpykla);
                    } else {
                        Toast.makeText(MainActivity.this, "Nepasirinkta kontroliuojama veikla",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.N)
    private void generatePDF() {

        vardas = (EditText) findViewById(R.id.login_name);
        pavarde = (EditText) findViewById(R.id.login_password);

        SimpleDateFormat dateFormat = null;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String fullPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath()
                + "/kontrole-"+System.currentTimeMillis()+".pdf";

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
                            .add(new Text("Nacionalinio visuomenes sveikatos centro \nKauno departamentas")));
            document.add(
                    new Paragraph()
                            .setFontSize(16)
                            .setBold()
                            .setTextAlignment(TextAlignment.CENTER)
                            .add("Patikrinimo aktas"));

            document.add(new Paragraph("Kontrole atliko:\n Visuomenes sveikatos saugos kontroles skyriaus vyr. specialistas "
                    + vardas.getText().toString() + " " + pavarde.getText().toString()));

            document.add(
                    new Paragraph()
                            .setItalic()
                            .add(new Text("Patikrinimas pradetas: " + dateFormat.format(dateStarted).toString())));

            document.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
