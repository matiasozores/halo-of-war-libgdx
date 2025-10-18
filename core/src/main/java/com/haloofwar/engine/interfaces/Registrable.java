package com.haloofwar.engine.interfaces;

import com.haloofwar.engine.entity.Entity;

public interface Registrable {
    void register(Entity entity);
    void unregister(Entity entity);
    void unregisterByIdentifier(final int identifier);
}
