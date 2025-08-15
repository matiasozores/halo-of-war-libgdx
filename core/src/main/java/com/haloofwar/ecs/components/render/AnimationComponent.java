package com.haloofwar.ecs.components.render;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haloofwar.components.animations.AnimationSet;
import com.haloofwar.components.animations.AnimationStateController;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.ecs.components.Component;
import com.haloofwar.interfaces.entities.AnimatedEntityDescriptor;

public class AnimationComponent implements Component {
    private final AnimationSet animationSet;
    private final AnimationStateController stateController;
    private final Texture spritesheet;

    public AnimationComponent(AnimatedEntityDescriptor descriptor, TextureManager textureManager) {
        this.spritesheet = textureManager.get(descriptor);
        this.animationSet = new AnimationSet(this.spritesheet, descriptor);
        this.stateController = new AnimationStateController(this.animationSet);
    }

    // Actualiza el estado interno de la animación
    public void update(float delta, float dirX, float dirY) {
        this.stateController.update(delta, dirX, dirY);
    }

    // Solo getters: el sistema de render se encargará de dibujar
    public TextureRegion getCurrentFrame() {
        return this.stateController.getCurrentFrame();
    }

    public boolean isFacingLeft() {
        return this.stateController.isFacingLeft();
    }
}
