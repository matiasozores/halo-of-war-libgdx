package com.haloofwar.events;

import com.haloofwar.components.Entity;

public class PlayerDiedEvent {
    public final Entity player;

    public PlayerDiedEvent(Entity player) {
        this.player = player;
    }
}
