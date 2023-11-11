package com.example.papcreativaexpress.Model;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidarContrasenia implements Serializable {
    private static final long serialVersionUID = 1L;

    private static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean validate(String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
