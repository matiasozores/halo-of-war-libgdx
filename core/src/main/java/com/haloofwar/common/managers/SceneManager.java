package com.haloofwar.common.managers;

import java.util.HashMap;
import java.util.Map;

import com.haloofwar.game.factories.SceneFactory;
import com.haloofwar.game.scenes.GameScene;
import com.haloofwar.interfaces.SceneDescriptor;
import com.haloofwar.interfaces.SceneKey;

public class SceneManager {
    private final Map<SceneDescriptor, GameScene> scenes = new HashMap<>();
    private final SceneFactory factory;

    public SceneManager(final SceneFactory factory) {
        this.factory = factory;
    }

    public void load(SceneKey key) {
        SceneDescriptor descriptor = (SceneDescriptor) key;

        if (this.scenes.containsKey(descriptor)) {
            return;
        }

        GameScene scene = this.factory.create(descriptor);
        this.scenes.put(descriptor, scene);
    }

    public GameScene get(SceneKey key) {
        SceneDescriptor descriptor = (SceneDescriptor) key;

        if (!this.scenes.containsKey(descriptor)) {
            this.load(key);
        }

        return this.scenes.get(descriptor);
    }

    public void clear() {
        for (GameScene scene : scenes.values()) {
            scene.dispose();
        }
        this.scenes.clear();
    }
}
