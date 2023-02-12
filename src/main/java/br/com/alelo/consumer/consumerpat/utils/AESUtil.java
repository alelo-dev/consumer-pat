package br.com.alelo.consumer.consumerpat.utils;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;

/**
 * Advanced Encryption Standard
 */
public class AESUtil {

    private static final String secret = "ConsumerAlelo";
    private static final String ALGORITHM = "AES";

    public static SecretKeySpec prepareSecreteKey(String myKey) throws NoSuchAlgorithmException {
        byte[] key = myKey.getBytes(StandardCharsets.UTF_8);
        MessageDigest sha = MessageDigest.getInstance("SHA-1");
        key = sha.digest(key);
        key = Arrays.copyOf(key, 16);
        SecretKeySpec secretKey = new SecretKeySpec(key, ALGORITHM);
        return secretKey;
    }

    public static String encryptText(String strToEncrypt) throws InvalidKeyException,
            UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException {
        SecretKeySpec secretKey = prepareSecreteKey(secret);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
    }

    public static String decryptText(String strToDecrypt) throws NoSuchAlgorithmException, NoSuchPaddingException,
            IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        SecretKeySpec secretKey = prepareSecreteKey(secret);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }

}