package com.haloofwar.factories.components;

import java.util.function.Consumer;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.Component;
import com.haloofwar.interfaces.entities.EntityDescriptor;

public class EntityBuilder<T extends EntityDescriptor> {

    private final Entity entity;
    private final T descriptor;

    public EntityBuilder(GameContext context, T descriptor) {
        this.entity = new Entity();
        this.descriptor = descriptor;
    }
    
    public EntityBuilder() {
        this.entity = new Entity();
        this.descriptor = null;
    }

    /** Devuelve el descriptor (PlayerType, EnemyType, ItemType, etc.) */
    public T getDescriptor() {
        return descriptor;
    }

    /** Agrega cualquier componente genérico */
    public <C extends Component> EntityBuilder<T> withComponent(C component) {
        entity.addComponent(component);
        return this;
    }

    /** Permite modificar la entidad directamente antes de construirla */
    public EntityBuilder<T> modify(Consumer<Entity> consumer) {
        consumer.accept(entity);
        return this;
    }

    /** Construye la entidad final */
    public Entity build() {
        return entity;
    }
}
