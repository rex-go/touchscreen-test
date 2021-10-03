package com.rex.touchscreentest;

public class Utils {
    public static int getGCD(int a, int b) {
        while (b != 0) {
            int t = a;
            a = b;
            b = t % b;
        }
        return a;
    }
}
