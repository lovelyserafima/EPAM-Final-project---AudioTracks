package com.epam.audiomanager.util.valid;

public class Validation {
    private static final String CHECK_EMAIL = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
            "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
    private static final String CHECK_PASSWORD = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";
    private static final int MAX_LOGIN_LENGTH = 20;

    public static boolean isCorrectEmail(String email){
        return email.matches(CHECK_EMAIL);
    }
    public static boolean isCorrectPassword(String password){
        return password.matches(CHECK_PASSWORD);
    }
    public static boolean arePasswordsEqual(String firstPassword, String secondPassword){
        return firstPassword.equals(secondPassword);
    }
    public static boolean isCorrectLogin(String login){
        return login.length() <= MAX_LOGIN_LENGTH;
    }
}
