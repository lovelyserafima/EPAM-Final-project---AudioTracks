package com.epam.audiomanager.util;

public class ConfirmCodeGenerator {
    public static String generateConfirmCode(){
        return java.util.UUID.randomUUID().toString();
    }
}
