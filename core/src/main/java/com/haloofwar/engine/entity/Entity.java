package com.haloofwar.engine.entity;

import java.util.HashMap;
import java.util.Map;

import com.haloofwar.engine.components.Component;

public class Entity {
    private final Map<Class<? extends Component>, Component> COMPONENTS = new HashMap<>();

    public <T extends Component> void addComponent(T component) {
    	if(component != null) {
    		this.COMPONENTS.put(component.getClass(), component);
    	}
    }

    public <T extends Component> T getComponent(Class<T> componentType) {
    	Component component = this.COMPONENTS.get(componentType);
    	
    	if(component == null || !componentType.isInstance(component)) {
    		return null;
    	}

        return componentType.cast(component);
    }

    public <T extends Component> boolean hasComponent(Class<T> componentType) {
        return this.COMPONENTS.containsKey(componentType);
    }
}
