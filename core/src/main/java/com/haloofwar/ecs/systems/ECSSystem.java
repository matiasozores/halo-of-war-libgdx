package com.haloofwar.ecs.systems;

import com.haloofwar.ecs.Entity;

public interface ECSSystem {
    default void update(float delta) {}
    default void render() {}
    default void dispose() {}
    
    default void register(Entity entity) {}
    default void unregister(Entity entity) {}
}
