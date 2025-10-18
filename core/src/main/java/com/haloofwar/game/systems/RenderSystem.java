package com.haloofwar.game.systems;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.interfaces.Renderable;
import com.haloofwar.game.components.AnimationComponent;
import com.haloofwar.game.components.RenderComponent;
import com.haloofwar.game.components.TransformComponent;

public class RenderSystem extends BaseSystem implements Renderable {

    private final SpriteBatch batch;

    public RenderSystem(EventBus bus, SpriteBatch batch) {
    	super(bus);
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
        for (Entity e : this.ENTITIES) {
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
