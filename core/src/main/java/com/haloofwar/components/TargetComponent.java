package com.haloofwar.components;

import com.haloofwar.interfaces.Component;

public class TargetComponent implements Component {
    public TransformComponent targetTransform; // referencia al Transform del objetivo
    
    public TargetComponent(TransformComponent targetTransform) {
		this.targetTransform = targetTransform;

    }
}
