package com.haloofwar.game.world;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.interfaces.SceneDescriptor;

public class MapRenderer {
    private final MapMetaData metaData;
    private final OrthogonalTiledMapRenderer map;

    public MapRenderer(SceneDescriptor scene) {
        this.metaData = new MapMetaData(scene);
        this.map = new OrthogonalTiledMapRenderer(this.metaData.getTiledMap());
    }

    public void render(GameWorldCamera camera) {
        this.map.setView(camera.getOrthographic());
        this.map.render();
    }

    public MapMetaData getMetaData() { return this.metaData; }

    public void dispose() {
        this.map.dispose();
        this.metaData.dispose();
    }
    
    public TiledMap getTiled() {
    	return this.metaData.getTiledMap();
    }
}
