package com.haloofwar.game.scenes;

import com.haloofwar.game.world.NPCSpawner;
import com.haloofwar.game.world.World;
import com.haloofwar.interfaces.SceneDescriptor;
import com.haloofwar.ui.HUD;

public class ExplorationScene extends GameScene {
    public ExplorationScene(final SceneDescriptor DESCRIPTOR, final World WORLD, final HUD HUD, final NPCSpawner NPC_SPAWNER) {
        super(DESCRIPTOR, WORLD, HUD);
        NPC_SPAWNER.spawn();
    }

    @Override
    public boolean isLevel() {
        return false;
    }
}
