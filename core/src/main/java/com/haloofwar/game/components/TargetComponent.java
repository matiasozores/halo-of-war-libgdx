package com.haloofwar.game.components;

import com.haloofwar.engine.components.Component;
import com.haloofwar.engine.components.TransformComponent;

public class TargetComponent implements Component {
    public TransformComponent targetTransform;
    
    public TargetComponent(TransformComponent targetTransform) {
		this.targetTransform = targetTransform;

    }
}
