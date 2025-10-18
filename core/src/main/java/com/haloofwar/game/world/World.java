package com.haloofwar.game.world;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.haloofwar.engine.cameras.GameWorldCamera;

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
        this.map.render(this.context.getCamera());

        this.batch.setProjectionMatrix(this.context.getCamera().getOrthographic().combined);
        this.batch.begin();

        this.context.getGameplay().render();

        this.batch.end();
    }    
    
    public void reconfigureCamera() {
		this.context.reconfigureCamera();
	}

    public GameWorldCamera getCamera() {
        return this.context.getCamera();
    }
    
    public MapRenderer getMap() {
		return this.map;
	}
    
    public TiledMap getTiled() {
    	return this.map.getTiled();
    }
}
