package com.haloofwar.common.enumerators;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    public static Direction getByName(final String NAME) {
        if (NAME == null) {
            return null;
        }

        Direction[] directions = Direction.values();
        int i = 0;
        boolean found = false;
        Direction result = null;

        while (!found && i < directions.length) {
            if (directions[i].name().equalsIgnoreCase(NAME)) {
                found = true;
                result = directions[i];
            } else {
                i++;
            }
        }

        return result;
    }
}
