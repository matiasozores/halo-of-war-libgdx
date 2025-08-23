package com.haloofwar.factories.components;

import com.haloofwar.components.Entity;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.interfaces.Component;
import com.haloofwar.interfaces.EntityDescriptor;

public class EntityBuilder<T extends EntityDescriptor> {

    private final Entity entity;

    public EntityBuilder(GameContext context) {
        this.entity = new Entity();
    }
    
    public EntityBuilder() {
        this.entity = new Entity();
    }

    public <C extends Component> EntityBuilder<T> withComponent(C component) {
        this.entity.addComponent(component);
        return this;
    }

    public Entity build() {
        return this.entity;
    }
}
