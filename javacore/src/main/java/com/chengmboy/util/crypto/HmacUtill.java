package com.chengmboy.util.crypto;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import com.chengmboy.util.crypto.exception.CryptoException;

/**
 * 该类提供了“消息验证码”（MAC）算法的功能。
 * MAC提供了一种基于秘密密钥来检查在不可靠介质中传输或存储在信息中的信息的完整性的方法。
 * 通常，在共享密钥的两方之间使用消息认证码，以验证在这些方之间传输的信息。
 * <p>
 * 基于加密散列函数的MAC机制被称为HMAC。 HMAC可以与任何加密散列函数一起使用，
 * 例如MD5或SHA-1与秘密共享密钥的组合。 HMAC在RFC 2104中规定。
 * <p>
 * Java平台的每个实现都需要支持以下标准Mac算法：
 * <p>
 * <ul>
 * <li>{@code HmacMD5}</li>
 * <li>{@code HmacSHA1}</li>
 * <li>{@code HmacSHA256}</li>
 * </ul>
 *
 * @author cheng_mboy
 */
public class HmacUtill {

    public static byte[] encrypt(byte[] data, byte[] key, String algorithm) {
        try {
            SecretKeySpec secret = new SecretKeySpec(key, algorithm);
            Mac mac = Mac.getInstance(algorithm);
            mac.init(secret);
            return mac.doFinal(data);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            throw new CryptoException(e);
        }
    }
}
