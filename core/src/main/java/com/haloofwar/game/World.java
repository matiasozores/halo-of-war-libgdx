package com.haloofwar.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.input.InputManager;
import com.haloofwar.utilities.GameContext;

public class World {
    private Player player;
	private MapMetaData metaData;
    private OrthogonalTiledMapRenderer mapRenderer;
    
    private InputManager inputManager;
    private GameWorldCamera camera;
    
    public World(GameContext gameContext, SceneType scene, Player player) {
		this.metaData = new MapMetaData(scene);
		this.player = player;
		this.mapRenderer = new OrthogonalTiledMapRenderer(this.metaData.getTiledMap());
		this.inputManager = gameContext.getInputManager();
		this.camera = new GameWorldCamera(player, this.metaData);
		gameContext.setCameraGame(this.camera);
		
		// La idea es que nunca llegue a tocar los bordes pero se hace la validación por si acaso
		this.player.setMapBounds(this.metaData.getMapPixelWidth(), this.metaData.getMapPixelHeight());
	}

	public void update() {
		this.player.update(this.camera, this.inputManager);
		this.camera.update();
	}

	public void render(SpriteBatch batch) {
	    this.mapRenderer.setView(this.camera.getCamera());
	    this.mapRenderer.render(); 

		batch.setProjectionMatrix(this.camera.getCamera().combined);
		batch.begin(); 
	    this.player.render(batch);
	    batch.end(); 
	}



	public void dispose() {
		this.mapRenderer.dispose();
		this.metaData.getTiledMap().dispose();
	}
    
	public MapMetaData getMetaData() {
		return this.metaData;
	}
	
	public GameWorldCamera getCamera() {
		return this.camera;
	}
	
	public Player getPlayer() {
		return this.player;
	}
}
