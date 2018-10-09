package com.epam.audiomanager.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encryption {
    private static final Logger LOGGER = LogManager.getLogger(Encryption.class);

    public static String encryptPassword(String password){
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try{
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("NoSuchAlgorithmException", e);
        }

        BigInteger bigInteger = new BigInteger(1, digest);
        StringBuilder encryptedPassword = new StringBuilder(bigInteger.toString(16));

        while (encryptedPassword.length() < 32){
            encryptedPassword.insert(0, "0");
        }

        return encryptedPassword.toString();
    }
}
