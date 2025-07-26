package com.haloofwar.game.components;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.game.MapMetaData;

public class MapRenderer {
	private final MapMetaData metaData;
	private final OrthogonalTiledMapRenderer mapRenderer;
	
	public MapRenderer(SceneType scene) {
		this.metaData = new MapMetaData(scene);
		this.mapRenderer = new OrthogonalTiledMapRenderer(this.metaData.getTiledMap());
	}
	
	public void render(GameWorldCamera camera) {
	    this.mapRenderer.setView(camera.getCamera());
	    this.mapRenderer.render(); 
	}

	public MapMetaData getMetaData() {
		return this.metaData;
	}

	public void dispose() {
		this.mapRenderer.dispose();
		this.metaData.dispose();
	}
}
