package com.haloofwar.interfaces.systems;

import com.haloofwar.ecs.Entity;

public interface Registrable {
    void register(Entity entity);
    void unregister(Entity entity);
}
