package com.haloofwar.enumerators;

public enum SoundType {
    CLICK("audio/sounds/click.wav"),
    ENTER("audio/sounds/enter.wav"),
    LOAD_GAME("audio/sounds/load_game.wav"),
    WALK("audio/sounds/walk.wav");
    // agrega más sonidos

    private final String path;

    SoundType(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
