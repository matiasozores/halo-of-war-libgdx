package com.haloofwar.game.components;

public class TargetComponent implements Component {
    public TransformComponent targetTransform;
    
    public TargetComponent(TransformComponent targetTransform) {
		this.targetTransform = targetTransform;

    }
}
