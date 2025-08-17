package com.haloofwar.ecs.systems.render;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.components.render.AnimationComponent;
import com.haloofwar.ecs.components.render.RenderComponent;
import com.haloofwar.ecs.systems.BaseSystem;
import com.haloofwar.interfaces.systems.Renderable;

public class RenderSystem extends BaseSystem implements Renderable {

    private final SpriteBatch batch;

    public RenderSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(TransformComponent.class) &&
            (e.hasComponent(AnimationComponent.class) || e.hasComponent(RenderComponent.class))) {
            super.register(e);
        }
    }

    @Override
    public void render() {
        for (Entity e : this.entities) {
            TransformComponent transform = e.getComponent(TransformComponent.class);
            if (transform == null) continue;

            float x = transform.x;
            float y = transform.y;
            float width = transform.width;
            float height = transform.height;

            RenderComponent render = e.getComponent(RenderComponent.class);
            if (render != null && render.texture != null) {
                this.batch.draw(render.texture,
                        transform.x, transform.y,
                        transform.width / 2f, transform.height / 2f,
                        transform.width, transform.height,
                        1f, 1f,   
                        render.angle,
                        0, 0,
                        render.texture.getWidth(),
                        render.texture.getHeight(),
                        false, false);
            }
            
            AnimationComponent anim = e.getComponent(AnimationComponent.class);
            if (anim != null) {
                TextureRegion frame = anim.getCurrentFrame();
                if (frame != null) {
                    boolean facingLeft = anim.isFacingLeft();
                    if (facingLeft) {
                        this.batch.draw(frame, x + width, y, -width, height);
                    } else {
                        this.batch.draw(frame, x, y, width, height);
                    }
                }
            }

 
        }
    }
}
