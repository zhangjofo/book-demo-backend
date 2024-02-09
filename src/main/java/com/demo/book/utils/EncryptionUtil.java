package com.demo.book.utils;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 *  @author Jofo
 */
@Slf4j
public class EncryptionUtil {

    private static final String KEY_RSA_ALGORITHM = "RSA";

    private EncryptionUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Generate an RSA key pair
     */
    public static KeyPair generateRsaKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * Obtain the Base 64-encoded string of the RSA public key
     */
    public static String getRsaPublicKeyString(PublicKey publicKey) {
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKey.getEncoded());
            return Base64.getEncoder().encodeToString(keyFactory.generatePublic(publicKeySpec).getEncoded());
        } catch (Exception e) {
            log.error("getRSAPublicKeyString error", e);
            return null;
        }
    }

    /**
     * Obtain the Base 64-encoded string of the RSA private key
     */
    public static String getRsaPrivateKeyString(PrivateKey privateKey) {
        KeyFactory keyFactory;
        try {
            keyFactory = KeyFactory.getInstance(KEY_RSA_ALGORITHM);
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
            return Base64.getEncoder().encodeToString(keyFactory.generatePrivate(privateKeySpec).getEncoded());
        } catch (Exception e) {
            log.error("getRSAPrivateKeyString error", e);
            return null;
        }
    }

    /**
     * Hashing algorithm SHA-256
     */
    public static String hashWithSha256(String plaintext) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hashBytes = digest.digest(plaintext.getBytes(StandardCharsets.UTF_8));
        return bytesToHex(hashBytes);
    }

    /**
     * Convert byte arrays to hexadecimal strings
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    /**
     * Base64 encode
     */
    public static String encodeBase64(String plainText) {
        byte[] plainBytes = plainText.getBytes(StandardCharsets.UTF_8);
        return Base64.getEncoder().encodeToString(plainBytes);
    }

    /**
     * Base64 decode
     */
    public static String decodeBase64(String base64Text) {
        byte[] base64Bytes = Base64.getDecoder().decode(base64Text);
        return new String(base64Bytes, StandardCharsets.UTF_8);
    }
}
