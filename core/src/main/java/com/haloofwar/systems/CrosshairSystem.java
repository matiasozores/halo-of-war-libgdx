package com.haloofwar.systems;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.haloofwar.components.CrosshairComponent;
import com.haloofwar.components.Entity;
import com.haloofwar.interfaces.Renderable;
import com.haloofwar.interfaces.Updatable;

public class CrosshairSystem extends BaseSystem implements Updatable, Renderable {

    private final SpriteBatch batch;

    public CrosshairSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(CrosshairComponent.class)) {
            super.register(e);
        }
    }

    @Override
    public void update(float delta) {
        for (Entity entity : this.entities) {
            CrosshairComponent crosshair = entity.getComponent(CrosshairComponent.class);
            if (crosshair == null) {
            	continue;
            }

            Vector3 mouseWorldPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
            crosshair.camera.getOrthographic().unproject(mouseWorldPos);

            crosshair.mouseX = (int) mouseWorldPos.x;
            crosshair.mouseY = (int) mouseWorldPos.y;
        }
    }

    @Override
    public void render() {
        for (Entity entity : this.entities) {
            CrosshairComponent crosshair = entity.getComponent(CrosshairComponent.class);
            if (crosshair == null) continue;

            this.batch.draw(
                crosshair.texture,
                crosshair.mouseX,
                crosshair.mouseY,
                crosshair.width,
                crosshair.height
            );
        }
    }
}
