package ru.netology;

import org.junit.jupiter.api.Assertions;

public class MainTest {

    @org.junit.jupiter.api.Test
    public void testSimpleHash_testEmptyString() {
        final String testStr = "";
        final int hash = 0;
        Assertions.assertEquals(hash, Main.simpleHash(testStr, false));
        Assertions.assertEquals(hash, Main.simpleHash(testStr, true));
    }

    @org.junit.jupiter.api.Test
    public void testSimpleHash_oneCharStr() {
        for (int i = 0; i < 127; i++) {
            Assertions.assertEquals(i, Main.simpleHash(String.valueOf((char) i), false));
        }
    }

    @org.junit.jupiter.api.Test
    public void testSimpleHah_oneCharPattern() {
        for (int i = 0; i < 127; i++) {
            if ((char) i == '*') {
                Assertions.assertEquals(0, Main.simpleHash(String.valueOf((char) i), true));
                continue;
            }
            Assertions.assertEquals(i, Main.simpleHash(String.valueOf((char) i), true));
        }
    }

    @org.junit.jupiter.api.Test
    public void testSimpleHash_stringHash() {
        final String testStr = "test*test";
        final int hash = 938;
        Assertions.assertEquals(hash, Main.simpleHash(testStr, false));
        Assertions.assertNotEquals(hash, Main.simpleHash(testStr, true));
    }

    @org.junit.jupiter.api.Test
    public void testSimpleHash_patternHash() {
        final String testStr = "test*test";
        final int hash = 938 - (int)'*';
        Assertions.assertEquals(hash, Main.simpleHash(testStr, true));
        Assertions.assertNotEquals(hash, Main.simpleHash(testStr, false));
    }
}

