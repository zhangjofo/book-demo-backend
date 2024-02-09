package com.demo.book.config;

import com.demo.book.utils.CaesarCipher;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author Jofo
 * description: desencrypt yaml
 **/
@Configuration
@Component("desencrypt")
public class DefaultEncryptor implements StringEncryptor {

    @Value("${jasypt.encryptor.password}")
    private int password;

    /**
     * Encryption methods
     */
    @Override
    public String encrypt(String s) {
        return s;
    }

    /**
     * Decryption method
     */
    @Override
    public String decrypt(String s) {
        return CaesarCipher.decrypt(s, password);
    }
}

