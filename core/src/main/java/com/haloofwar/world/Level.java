package com.haloofwar.world;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.Zone;
import com.haloofwar.input.InputManager;
import com.haloofwar.utilities.Resources;
 
public abstract class Level implements Screen{
	private ShapeRenderer shapeRenderer;
	private InputManager inputManager;
	private Player player1;
	private Map<Zone, MapMetaData> maps = new HashMap<Zone, MapMetaData>();
	private Zone currentZone;
	
	private TiledMap tiledMap;
	private OrthogonalTiledMapRenderer mapRenderer;
	private OrthographicCamera camera;
	
	private int mapPixelWidth;
	private int mapPixelHeight;
	
	public Level(Player player1, Zone currentZone, Map<Zone, MapMetaData> maps) {
		this.player1 = player1;
		this.currentZone = currentZone;
		this.shapeRenderer = Resources.getShapeRenderer();
		this.inputManager = Resources.getInputManager();
	}

	
	
	@Override
	public void show() {
		this.tiledMap = new TmxMapLoader().load(currentZone.getPath());
		this.mapRenderer = new OrthogonalTiledMapRenderer(this.tiledMap);
		this.camera = new OrthographicCamera();
		this.camera.setToOrtho(false, Resources.getWINDOW_WIDTH(), Resources.getWINDOW_HEIGHT());
		this.camera.update();
		
		int tileWidth = this.tiledMap.getProperties().get("tilewidth", Integer.class);
		int tileHeight = this.tiledMap.getProperties().get("tileheight", Integer.class);
		int mapWidth = this.tiledMap.getProperties().get("width", Integer.class);
		int mapHeight = this.tiledMap.getProperties().get("height", Integer.class);

		this.mapPixelWidth = mapWidth * tileWidth;
		this.mapPixelHeight = mapHeight * tileHeight;

		// Limitar el movimiento del jugador a ese tamaño
		player1.getControlState().setMapBounds(mapPixelWidth, mapPixelHeight);

	}


	@Override
	public void render(float delta) {
		 this.update();

		 this.mapRenderer.setView(this.camera);
		 this.mapRenderer.render();
		 
		 this.shapeRenderer.setProjectionMatrix(this.camera.combined);  
		 this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		 this.player1.render(shapeRenderer, this.inputManager);
		 this.shapeRenderer.end();
	}
	
	public void update() {
		this.player1.update(this.inputManager);
	    this.camera.zoom = 0.5f;
	    this.camera.position.set(player1.getX(), player1.getY(), 0);
	    this.camera.update();
	}
	
	public void changeZone(Zone newZone) {
		int i = 0;
		boolean found = false;
		Zone[] keys = maps.keySet().toArray(new Zone[0]);
		
		while(i < this.maps.size() && !found) {
			Zone currentKey = keys[i];
			
			if(newZone.equals(currentKey)) {
				found = true;
				this.currentZone = currentKey;
			} else {
				i++;
			}
		}
		
		if(!found) {
			System.out.println(newZone + " no encontrado en el mapa de zonas.");
		}
	}

	@Override
	public void resize(int width, int height) {
		this.camera.viewportWidth = width;
		this.camera.viewportHeight = height;
		this.camera.update();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
	    this.tiledMap.dispose();
	    this.mapRenderer.dispose();
	}	
	
	
	
}
