package com.chengmboy.algorithm.crypto.hash;

import java.security.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author cheng_mboy
 */
public class HashMessage {

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] args) {
        String data = "abc";
        byte[] digest = digest(data.getBytes());
        String x = toHexString(digest);
        System.out.println(x);
    }

    public static byte[] encrypt(byte[] data, byte[] key) {
        try {
            SecretKeySpec secret = new SecretKeySpec(key, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secret);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] digest(byte[] data) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            return messageDigest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHexString(byte[] bytes) {
        StringBuilder r = new StringBuilder();
        for (byte b : bytes) {
            r.append(HEX_DIGITS[(b & 0xf0) >> 4])
                    .append(HEX_DIGITS[b & 0xf]);
        }
        return r.toString();
    }

}
