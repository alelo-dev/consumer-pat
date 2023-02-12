package br.com.alelo.consumer.consumerpat.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.springframework.test.util.AssertionErrors.fail;

@SpringBootTest
public class AESUtilTest {

    @Test
    public void shouldEncrypt() {

        try {
            String input = "123";
            String encrypted = AESUtil.encryptText(input);
        } catch (InvalidKeyException | UnsupportedEncodingException |
                NoSuchAlgorithmException | NoSuchPaddingException |
                IllegalBlockSizeException | BadPaddingException
                e) {
            fail("Should not have thrown any exception. Exception: " + e.getMessage());
        }

    }

    @Test
    public void shouldDecrypt() {

        try {
            String input = "123";
            String encrypted = AESUtil.encryptText(input);
            String decrypted = AESUtil.decryptText(encrypted);
            Assertions.assertEquals(decrypted, input);
        } catch (InvalidKeyException | UnsupportedEncodingException |
                 NoSuchAlgorithmException | NoSuchPaddingException |
                 IllegalBlockSizeException | BadPaddingException
                e) {
            fail("Should not have thrown any exception. Exception: " + e.getMessage());
        }

    }




}
