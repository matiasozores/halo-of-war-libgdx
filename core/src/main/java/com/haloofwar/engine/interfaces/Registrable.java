package com.haloofwar.engine.interfaces;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.components.Component;

public interface Registrable {
    void register(Entity entity);
    void unregister(Entity entity);
    void unregisterByComponent(Class<? extends Component> component);
}
