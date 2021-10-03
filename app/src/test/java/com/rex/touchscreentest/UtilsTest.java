package com.rex.touchscreentest;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilsTest {

    @Test
    public void getGCD() {
        int a = 100;
        int b = 50;
        int expect = 50;

        int gcd = Utils.getGCD(a, b);

        assertEquals(expect, gcd);
    }
}