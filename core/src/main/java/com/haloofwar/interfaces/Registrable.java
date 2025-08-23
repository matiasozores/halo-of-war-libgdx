package com.haloofwar.interfaces;

import com.haloofwar.components.Entity;

public interface Registrable {
    void register(Entity entity);
    void unregister(Entity entity);
}
