package com.haloofwar.ecs.events.types.gameplay;

import com.haloofwar.ecs.Entity;

public class PlayerDiedEvent {
    public final Entity player;

    public PlayerDiedEvent(Entity player) {
        this.player = player;
    }
}
