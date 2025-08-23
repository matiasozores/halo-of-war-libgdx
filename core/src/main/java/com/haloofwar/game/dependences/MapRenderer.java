package com.haloofwar.game.dependences;

import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.game.MapMetaData;

public class MapRenderer {
	private final MapMetaData metaData;
	private final OrthogonalTiledMapRenderer map;
	
	public MapRenderer(SceneType scene) {
		this.metaData = new MapMetaData(scene);
		this.map = new OrthogonalTiledMapRenderer(this.metaData.getTiledMap());
	}
	
	public void render(GameWorldCamera camera) {
	    this.map.setView(camera.getOrthographic());
	    this.map.render(); 
	}

	public MapMetaData getMetaData() {
		return this.metaData;
	}

	public void dispose() {
		this.map.dispose();
		this.metaData.dispose();
	}
}
