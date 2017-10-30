package com.example.aurimas.inspektorius;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    Toast.makeText(MainActivity.this, username2 + "\n" + password2,
                            Toast.LENGTH_LONG).show();

                    if (rememberMeCheckBox.isChecked()) {
                        user.setUsernameLogin(username2);
                        user.setPasswordForLogin(password2);
                        user.setRemembered(false);
                    }
                    Intent goToSportoKlubas = new Intent(MainActivity.this, SportoKlubas.class);
                    startActivity(goToSportoKlubas);

                }
            }
        });
    }
}
