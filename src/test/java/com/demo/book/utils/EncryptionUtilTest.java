package com.demo.book.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;

/**
 * @author Jofo
 **/
@Slf4j
class EncryptionUtilTest {

    @Test
    void testEncrypt() throws Exception {
        // Generate an RSA key pair
        KeyPair keyPair = EncryptionUtil.generateRsaKeyPair();
        // Added Public/Private Key String Acquisition
        String publicKeyString = EncryptionUtil.getRsaPublicKeyString(keyPair.getPublic());
        log.info("RSA publicKeyString: {}", publicKeyString);
        String privateKeyString = EncryptionUtil.getRsaPrivateKeyString(keyPair.getPrivate());
        log.info("RSA privateKeyString: {}", privateKeyString);


        // Example of a hashing algorithm
        String plaintextHash = "1";
        String hashValue = EncryptionUtil.hashWithSha256(plaintextHash);
        Assertions.assertEquals("6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b", hashValue);

        // Base64 encode
        String base64Text = EncryptionUtil.encodeBase64("1");
        Assertions.assertEquals("MQ==", base64Text);
        // Base64 decode
        String decodedText = EncryptionUtil.decodeBase64(base64Text);
        Assertions.assertEquals("1", decodedText);
    }
}