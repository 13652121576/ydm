package com.ydm.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * EncryptionUtil 编码/解码 工具类
 * 
 * @author niuguodong
 *
 */
public class EncryptionUtils {
    /**
     * MD5
     * 
     * @param data
     * @return
     */
    public static String md5(String data) {
        return DigestUtils.md5Hex(data).toUpperCase();
    }

    /**
     * SHA1
     * 
     * @param data
     * @return
     */
    public static String sha1(String data) {
        return DigestUtils.sha1Hex(data).toUpperCase();
    }

    /**
     * base64 encode
     * 
     * @param data
     * @return
     */
    public static String base64Encode(String data) {
        return Base64.encodeBase64String(data.getBytes());
    }

    public static String des3EncodeECB(String data, String key) {
        try {
            DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());
            SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("DESede");
            Key deskey = keyfactory.generateSecret(spec);
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, deskey);
            byte[] bOut = cipher.doFinal(data.getBytes("UTF-8"));
            return byte2hex(bOut);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int i = 0; i < b.length; i++) {
            stmp = Integer.toHexString(b[i] & 0XFF);
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            } else {
                hs = hs + stmp;
            }
        }
        return hs.toUpperCase();
    }

    public static void main(String[] args) {
        String s = des3EncodeECB("123", "duS548shn17pVBsXHqH7CgED");
        System.out.println(s);
    }

}
