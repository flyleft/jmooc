package me.jcala.jmooc.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class EncryptUtils {

    public static String EncoderByMd5(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            return null;
        }
    }
}
