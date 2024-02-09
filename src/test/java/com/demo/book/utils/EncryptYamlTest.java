package com.demo.book.utils;

import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *  @author Jofo
 */
public class EncryptYamlTest {

    @Test
    public void encryptPwd(){

        String account = "book";
        String password = "123456";

        String newAccount = CaesarCipher.encrypt(account, 123);
        String newPassword = CaesarCipher.encrypt(password, 123);

        assertEquals("!..*", newAccount);
        assertEquals("NOPQRS", newPassword);
    }
}
