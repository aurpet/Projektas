package com.example.aurimas.inspektorius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private CheckBox rememberMeCheckBox;
    private Spinner kontrole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            public void onClick(View focusView) {


                // users data who can to login
                LoginUser aurimas = new LoginUser("aurimas", "aurimas");
                LoginUser kamile = new LoginUser("kamile", "kamile");


                String username2 = username.getText().toString();
                String password2 = password.getText().toString();
                boolean cancel = false;

                if (!authentication.isValidCredentials(username2)) {
                    username.setError("Klaidingas prisijungimo vardas");
                    focusView = username;
                    cancel = true;
                }
                if (!authentication.isValidCredentials(password2)) {
                    password.setError("Klaidingas prisijungimo slaptažodis");
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
                    } else if (kontrole.getSelectedItemPosition()==2) {
                        Intent goToBaseinas = new Intent(MainActivity.this, Baseinas.class);
                        startActivity(goToBaseinas);
                    } else if (kontrole.getSelectedItemPosition()== 3){
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
}
