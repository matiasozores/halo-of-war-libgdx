package com.haloofwar.game.components;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.gameplay.GameplayContext;
import com.haloofwar.entities.Entity;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.entities.EnemyType;
import com.haloofwar.factories.EnemyFactory;

public class WorldContext {
	private final GameplayContext gameplay;
	
	// Dependencias
	private final SpriteBatch batch;
	private final GameWorldCamera camera;
	
	public WorldContext(Player player, MapRenderer map, GameContext context) {
		this.gameplay = context.getGameplay();
		
		// El agregado de entidades se debe hacer a partir de SceneType pero por ahora se hace de esta manera
		this.addEntity(player);
		
		EnemyFactory enemyFactory = new EnemyFactory(context);
		this.addEntity(enemyFactory.create(EnemyType.ELITE));
		
		this.camera = context.getWorldCamera();
		this.camera.configure(player, map.getMetaData());
		
		this.batch = context.getRender().getBatch();
	}
	
	private void addEntity(Entity entity) {
		this.gameplay.getEntities().add(entity);
		this.gameplay.getCollisions().add(entity);
	}
	
	public GameplayContext getGameplay() {
		return this.gameplay;
	}
	
	public SpriteBatch getBatch() {
		return this.batch;
	}
	
	public GameWorldCamera getCamera() {
		return this.camera;
	}
}
