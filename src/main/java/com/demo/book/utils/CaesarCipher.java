package com.demo.book.utils;

/**
 * description: Caesar's cipher for yaml
 *  @author Jofo
 */
public class CaesarCipher {

    private CaesarCipher() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * encrypt
     */
    public static String encrypt(String input, int key) {

        char[] chars = input.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            int ascii = chars[i];
            ascii = (ascii - '!' + key) % 94 + '!';
            char newChar = (char) ascii;
            chars[i] = newChar;
        }
        return new String(chars);
    }

    /**
     * decrypt
     */
    public static String decrypt(String input, int key) {
        //Get every character in the string
        char[] array = input.toCharArray();

        for (int i = 0; i < array.length; ++i) {
            //Characters are converted to ASCII code values
            int ascii = array[i];
            //Restore character offsets, such as b->a
            ascii = (ascii - '~' - key) % 94 + '~';
            //ASCII Code value to char
            char newChar = (char) ascii;
            //Replace the original characters
            array[i] = newChar;
        }
        //Convert an array of characters to a String
        return new String(array);
    }
}
