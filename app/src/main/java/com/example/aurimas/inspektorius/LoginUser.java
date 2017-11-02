package com.example.aurimas.inspektorius;

/**
 * Created by Aurimas on 2017-11-01.
 */

public class LoginUser {
    private String name = "";
    private String pass = "";

    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }

    public String getpass() {
        return pass;
    }

    public void setpass(String pass) {
        this.pass = pass;
    }

    public LoginUser(String name, String pass){
        this.name = name;
        this.pass = pass;
    }
}
