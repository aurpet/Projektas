package com.example.aurimas.inspektorius;

import java.util.regex.Matcher;

/**
 * Created by Aurimas on 2017-10-30.
 */

public class Authentication { Authentication() {
}

    public boolean isValidCredentials(String credentials) {
        final String CREDENTIALS_PATTERN = "^([0-9a-zA-Z]{3,25})+$";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }
}

