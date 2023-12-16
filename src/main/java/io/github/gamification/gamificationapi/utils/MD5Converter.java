package io.github.gamification.gamificationapi.utils;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Converter {

    public static String convertToMd5(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(message.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        BigInteger no = new BigInteger(1, digest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
