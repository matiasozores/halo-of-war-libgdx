package com.haloofwar.world;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.maps.tiled.TiledMap;
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
	private TiledMap currentMap;
	private int mapPixelWidth;
	private int mapPixelHeight;
	
	private OrthogonalTiledMapRenderer mapRenderer;
	private OrthographicCamera camera;
	
	public Level(Player player1, Zone currentZone, Map<Zone, MapMetaData> maps) {
		this.player1 = player1;
		this.currentZone = currentZone;
		this.maps = maps;
		
		this.currentMap = this.maps.get(this.currentZone).getTiledMap();
		this.shapeRenderer = Resources.getShapeRenderer();
		this.inputManager = Resources.getInputManager();
	}

	@Override
	public void show() {
		this.mapRenderer = new OrthogonalTiledMapRenderer(this.currentMap);
		this.camera = Resources.getCamera();
		this.camera.setToOrtho(false, Resources.getWINDOW_WIDTH(), Resources.getWINDOW_HEIGHT());
		this.camera.update(); 
		
		// No esta bien encapsulado, pero es un ejemplo
		this.mapPixelWidth = this.maps.get(this.currentZone).getMapPixelWidth();
		this.mapPixelHeight = this.maps.get(this.currentZone).getMapPixelHeight();
		this.player1.getControlState().setMapBounds(mapPixelWidth, mapPixelHeight);
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
		this.camera.zoom = 0.4f;

		float halfViewportWidth = this.camera.viewportWidth * this.camera.zoom / 2;
		float halfViewportHeight = this.camera.viewportHeight * this.camera.zoom / 2;

		// Posición deseada de la cámara
		float camX = this.player1.getX();
		float camY = this.player1.getY();

		// Clamp en X
		camX = Math.max(camX, halfViewportWidth);
		camX = Math.min(camX, this.mapPixelWidth - halfViewportWidth);

		// Clamp en Y
		camY = Math.max(camY, halfViewportHeight);
		camY = Math.min(camY, this.mapPixelHeight - halfViewportHeight);

		this.camera.position.set(camX, camY, 0);
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
	    this.currentMap.dispose();
	    this.mapRenderer.dispose();
	    this.shapeRenderer.dispose();
	    for (MapMetaData mapMetaData : maps.values()) {
	        mapMetaData.getTiledMap().dispose();
	    }
	}	
	
	
	
}
