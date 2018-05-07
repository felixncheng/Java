package com.chengmboy.util.common.security;


import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import com.chengmboy.util.exception.CryptoException;


/**
 * @author cheng_mboy
 */
public class DESUtils {

    public static void main(String[] args) {
        process();
    }

    private static void process() {
        byte[] key = getKey();
        String s = Base64.getEncoder().encodeToString(key);
        System.out.println("key: " + s);

        String data = "我是重要的消息";
        System.out.println("发送方： " + data);

        System.out.println("-------------------");
        String s1 = Base64.getEncoder().encodeToString(encrypt(data.getBytes(), key));
        System.out.println("传输: " + s1);
        System.out.println("-------------------");


        byte[] decode = Base64.getDecoder().decode(s1);

        String s2 = new String(decrypt(decode, key));
        System.out.println("接收方: " + s2);
        System.out.println(data.equals(s2) ? "解密成功" : "失败");
    }

    private static byte[] getKey() {
        try {
            SecureRandom random = new SecureRandom();
            byte[] bytes = new byte[8];
            random.nextBytes(bytes);
            DESKeySpec keySpec = new DESKeySpec(bytes);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey key = factory.generateSecret(keySpec);
            return key.getEncoded();
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    private static byte[] encrypt(byte[] data, byte[] key) {
        try {
            DESKeySpec keySpec = new DESKeySpec(key);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey k = factory.generateSecret(keySpec);

            long s = System.nanoTime();
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, k);
            byte[] bytes = cipher.doFinal(data);
            System.out.println("对称加密花费时间： "+ (System.nanoTime()-s));
            return bytes;
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    private static byte[] decrypt(byte[] data, byte[] key) {
        try {
            DESKeySpec keySpec = new DESKeySpec(key);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            SecretKey k = factory.generateSecret(keySpec);
            long s = System.nanoTime();
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, k);
            byte[] bytes = cipher.doFinal(data);
            System.out.println("对称解密花费时间： "+ (System.nanoTime()-s));
            return bytes;
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }
}
