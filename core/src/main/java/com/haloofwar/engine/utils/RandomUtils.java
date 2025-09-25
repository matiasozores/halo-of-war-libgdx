package com.haloofwar.engine.utils;

import java.util.Random;

public final class RandomUtils {
	private RandomUtils() {}
	
	private static Random random = new Random();
	
    public static int generateInt(final int MIN, final int MAX) {
        return random.nextInt(MAX - MIN + 1) + MIN;
    }
    
    public static int generateInt(final int N) {
        return random.nextInt(N);
    }
	
    public static boolean checkChance(int percentage) {
        int randomNumber = generateInt(1, 100);
        return randomNumber <= percentage;
    }
}
