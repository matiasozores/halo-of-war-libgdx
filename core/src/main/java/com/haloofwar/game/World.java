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
		this.context.getEntities().update(delta);
		this.context.getCamera().update();
		this.context.getBullets().update(delta);
		this.context.getCollisions().checkCollisions();
	}

	public void render() {
		this.map.render(this.context.getCamera());
		
		this.batch.setProjectionMatrix(this.context.getCamera().getOrthographic().combined);
		this.batch.begin();
		this.context.getEntities().render(this.batch);
		this.context.getBullets().render(this.batch);
		this.batch.end();
	}

	public void dispose() {
	}
	
	public GameWorldCamera getCamera() {
		return this.context.getCamera();
	}
}
