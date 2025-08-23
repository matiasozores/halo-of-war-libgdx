package com.haloofwar.components;

import java.util.HashMap;
import java.util.Map;

import com.haloofwar.interfaces.Component;

public class Entity {
    private final Map<Class<? extends Component>, Component> components = new HashMap<>();

    public <T extends Component> void addComponent(T component) {
    	if(component != null) {
    		this.components.put(component.getClass(), component);
    	}
    }

    public <T extends Component> T getComponent(Class<T> componentType) {
    	Component component = this.components.get(componentType);
    	
    	if(component == null || !componentType.isInstance(component)) {
    		return null;
    	}
    	
        return componentType.cast(component);
    }

    public <T extends Component> boolean hasComponent(Class<T> componentType) {
        return this.components.containsKey(componentType);
    }

    public <T extends Component> void removeComponent(Class<T> componentType) {
        this.components.remove(componentType);
    }
}
