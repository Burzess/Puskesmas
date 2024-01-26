package model;

public class ModelAdmin {
    public boolean cekLogin(String username, String password) {
        return username.equals("admin") && password.equals("");
    }
}
