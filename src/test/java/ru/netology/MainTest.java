package ru.netology;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class MainTest {

    @org.junit.jupiter.api.Test
    public void testSimpleHash_testEmptyString() {
        final String testStr = "";
        final int hash = 0;
        assertThat(hash, equalTo(Main.simpleHash(testStr, false)));
        assertThat(hash, equalTo(Main.simpleHash(testStr, true)));
    }

    @org.junit.jupiter.api.Test
    public void testSimpleHash_oneCharStr() {
        for (int i = 0; i < 127; i++) {
            assertThat(i, equalTo(Main.simpleHash(String.valueOf((char) i), false)));
        }
    }

    @org.junit.jupiter.api.Test
    public void testSimpleHah_oneCharPattern() {
        for (int i = 0; i < 127; i++) {
            if ((char) i == '*') {
                assertThat(0, equalTo(Main.simpleHash(String.valueOf((char) i), true)));
                continue;
            }
            assertThat(i, equalTo(Main.simpleHash(String.valueOf((char) i), true)));
        }
    }

    @org.junit.jupiter.api.Test
    public void testSimpleHash_stringHash() {
        final String testStr = "test*test";
        final int hash = 938;
        assertThat(hash, equalTo(Main.simpleHash(testStr, false)));
        assertThat(hash, not(equalTo(Main.simpleHash(testStr, true))));
    }

    @org.junit.jupiter.api.Test
    public void testSimpleHash_patternHash() {
        final String testStr = "test*test";
        final int hash = 938 - (int)'*';
        assertThat(hash, equalTo(Main.simpleHash(testStr, true)));
        assertThat(hash, not(equalTo(Main.simpleHash(testStr, false))));
    }
}

