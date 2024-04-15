package com.jarvis.framework.license.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;

/**
 * @author Doug Wang
 * @since 1.8, 2024-04-15 15:05:24
 */
public class Main {

    private static final String KEY = "abc";

    public static void main(String[] args) {

        String json = "{ \"status\": 1, \"enterprise\": \"天\", \"serviceCode\": \"abc\", \"registTime\": \"2024-01-24 14:04:55\", \"mac\": \"DE-53-92-68-47-FA\", \"vaildTime\": \"2024-01-24 14:04:55\" }";

        String encrypt = encrypt(json);
        System.out.println(encrypt);
        System.out.println("-------------------------");
        String encrypt1 = AESUtil.encrypt(json, KEY);
        System.out.println(encrypt1);
    }

    public static String encrypt(String content) { try { byte[] bytes = content.getBytes(StandardCharsets.UTF_8);

        SecretKeySpec key = generateKey(KEY);

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] result = cipher.doFinal(bytes);

        return parseByte2Hex(result);
    } catch (Exception e) {
        System.out.println("加密失败");
    }
        return null;
    }


    private static SecretKeySpec generateKey(String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(password.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            return new SecretKeySpec(enCodeFormat, "AES");
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private static String parseByte2Hex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString().toUpperCase();
    }

}
