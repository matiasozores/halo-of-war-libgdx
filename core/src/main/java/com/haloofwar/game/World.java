package com.haloofwar.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.collision.CollisionManager;
import com.haloofwar.dependences.BulletManager;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.EntityManager;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.entities.enemies.Enemy;
import com.haloofwar.enumerators.CharacterType;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.game.components.MapRenderer;
import com.haloofwar.game.components.WorldCollisionInitializer;

public class World {
	private MapRenderer map;
	private EntityManager entities;
	private GameWorldCamera camera;
	private BulletManager bulletManager;
	
	// Dependencias
	private SpriteBatch batch;
	private CollisionManager collisionManager;
	

	public World(SceneType scene, Player player, GameContext context) {
		this.batch = context.getBatch();
		this.bulletManager = context.getBulletManager();
		this.collisionManager = context.getCollisionManager();
		this.entities = new EntityManager(context.getCollisionManager());
		
		this.entities.addEntity(player);
		this.entities.addEntity(new Enemy(CharacterType.KRATOS, context.getInputManager(), context.getCameraGame(), context.getTextureManager(), context.getCollisionManager()));
		this.map = new MapRenderer(scene);
		WorldCollisionInitializer.initializeMapColliders(this.map, this.collisionManager);
		this.camera = context.getCameraGame();
		this.camera.configure(player, this.map.getMetaData());
		
	}

	public void update(float delta) {
		this.entities.update(delta);
		this.camera.update();
		this.bulletManager.update(delta);
		this.collisionManager.checkCollisions();
	}

	public void render() {
		this.map.render(this.camera);

		this.batch.setProjectionMatrix(this.camera.getCamera().combined);
		this.batch.begin();

		this.entities.render(this.batch);
		this.bulletManager.render(this.batch);

		this.batch.end();
	}

	public void dispose() {
	}

	public GameWorldCamera getCamera() {
		return this.camera;
	}
}
