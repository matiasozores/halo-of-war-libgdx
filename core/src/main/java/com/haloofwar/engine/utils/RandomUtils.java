package com.haloofwar.engine.utils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public final class RandomUtils {
	private RandomUtils() {}
	
    private static final int MIN_ID = 1;
    private static final int MAX_ID = 999999;
	
	private static Random random = new Random();
	private static final Set<Integer> usedIds = new HashSet<>();
	
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
    
    public static int generateUniqueId() {
        if (usedIds.size() >= (MAX_ID - MIN_ID + 1)) {
            throw new IllegalStateException("Se han generado todos los IDs posibles en el rango.");
        }

        int id;
        do {
            id = generateInt(MIN_ID, MAX_ID);
        } while (usedIds.contains(id));

        usedIds.add(id);
        return id;
    }
}
