package com.haloofwar.ecs.systems.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.ecs.Entity;
import com.haloofwar.ecs.components.debug.ShapeComponent;
import com.haloofwar.ecs.systems.BaseSystem;

public class ShapeSystem extends BaseSystem {

    private final SpriteBatch batch;
    private final ShapeRenderer shapeRenderer;

    public ShapeSystem(SpriteBatch batch) {
        this.batch = batch;
        this.shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void register(Entity e) {
        if (e.hasComponent(ShapeComponent.class)) {
            super.register(e);

        }
    }

    public void render() {
    	this.batch.end();
    	this.batch.begin();
        shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line); // solo contorno

        for (Entity entity : this.entities) {
            ShapeComponent shape = entity.getComponent(ShapeComponent.class);
            if (shape != null) {
                shapeRenderer.setColor(shape.color != null ? shape.color : Color.RED);
                shapeRenderer.rect(shape.x, shape.y, shape.width, shape.height);
            }
        }

        shapeRenderer.end();
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
