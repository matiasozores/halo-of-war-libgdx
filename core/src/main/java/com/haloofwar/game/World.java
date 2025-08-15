package com.haloofwar.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.game.components.MapRenderer;
import com.haloofwar.game.components.WorldContext;

public class World {
    private final MapRenderer map;
    private final WorldContext context;
    private final SpriteBatch batch;

    public World(MapRenderer map, WorldContext context) {
        this.map = map;
        this.context = context;
        this.batch = context.getBatch();
    }

    public void update(float delta) {
    	this.context.getGameplay().update(delta);
        this.context.getCamera().update();
    }

    public void render() {
        // Renderizar mapa
        this.map.render(this.context.getCamera());

        // Renderizar entidades a través de los sistemas
        this.batch.setProjectionMatrix(this.context.getCamera().getOrthographic().combined);
        this.batch.begin();

        this.context.getGameplay().render();

        this.batch.end();
    }

    public void dispose() {
        this.map.dispose();
        this.batch.dispose();
    }

    public GameWorldCamera getCamera() {
        return this.context.getCamera();
    }
}
