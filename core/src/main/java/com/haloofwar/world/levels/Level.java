package com.haloofwar.world.levels;

import java.util.Map;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.Zone;
import com.haloofwar.input.InputManager;
import com.haloofwar.screens.PauseMenuScreen;
import com.haloofwar.ui.CameraGame;
import com.haloofwar.ui.HUD;
import com.haloofwar.utilities.Resources;
import com.haloofwar.world.MapMetaData;

public abstract class Level implements Screen {
	
	private InputManager inputManager = Resources.getInputManager();
	private Player player1;
	private CameraGame cameraGame;
	private HUD hud;
	private OrthogonalTiledMapRenderer mapRenderer;
	
	private Map<Zone, MapMetaData> maps;
	private Zone currentZone;
	private TiledMap currentMap;

	public Level(Player player1, Zone currentZone, Map<Zone, MapMetaData> maps) {
		this.player1 = player1;
		this.currentZone = currentZone;
		this.maps = maps;
		this.currentMap = this.maps.get(this.currentZone).getTiledMap();
	}

	@Override
	public void show() {
		this.mapRenderer = new OrthogonalTiledMapRenderer(this.currentMap);
		this.player1.getMovement().setMapBounds(this.maps.get(this.currentZone).getMapPixelWidth(),
				this.maps.get(this.currentZone).getMapPixelHeight());
		
		this.hud = new HUD();
		this.cameraGame = Resources.getCameraGame();
	}

	@Override
	public void render(float delta) {
		this.update();
		this.mapRenderer.setView(this.cameraGame.getCamera());
		this.mapRenderer.render();
		this.hud.render(this.player1);
		
		this.player1.render(this.cameraGame.getCamera());
	}


	public void update() {
		this.cameraGame.update(this.player1, this.maps.get(this.currentZone));
		this.player1.update(this.inputManager);
		
		if (this.inputManager.isEscape()) {
			Resources.getGame().setScreen(new PauseMenuScreen());
		}
	}
	
	// Funciones en relacion a los mapas y zonas

	public void changeZone(Zone newZone) {
		int i = 0;
		boolean found = false;
		Zone[] keys = maps.keySet().toArray(new Zone[0]);

		while (i < this.maps.size() && !found) {
			Zone currentKey = keys[i];

			if (newZone.equals(currentKey)) {
				found = true;
				this.currentZone = currentKey;
			} else {
				i++;
			}
		}

		if (!found) {
			System.out.println(newZone + " no encontrado en el mapa de zonas.");
		}
	}
	
	@Override
	public void resize(int width, int height) {
		this.cameraGame.resize(width, height);
	}
	
	@Override
	public void dispose() {
		this.currentMap.dispose();
		this.mapRenderer.dispose();
		for (MapMetaData mapMetaData : maps.values()) {
			mapMetaData.getTiledMap().dispose();
		}
	}
	
	// Funciones que no se usan, pero que son necesarias para implementar la interfaz Screen

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
}
