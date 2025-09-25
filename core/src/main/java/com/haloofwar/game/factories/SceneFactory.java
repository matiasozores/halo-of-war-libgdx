package com.haloofwar.game.factories;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.game.managers.LevelController;
import com.haloofwar.game.scenes.ExplorationScene;
import com.haloofwar.game.scenes.GameScene;
import com.haloofwar.game.world.Level;
import com.haloofwar.game.world.MapRenderer;
import com.haloofwar.game.world.NPCSpawner;
import com.haloofwar.game.world.World;
import com.haloofwar.game.world.WorldCollisionInitializer;
import com.haloofwar.game.world.WorldContext;
import com.haloofwar.interfaces.SceneDescriptor;
import com.haloofwar.ui.hud.HUD;

public final class SceneFactory {
	private final HUDFactory HUD_FACTORY;
	private final EnemyFactory ENEMY_FACTORY;
	private final NPCFactory NPC_FACTORY;
	private final CutSceneFactory CUTSCENE_FACTORY;
	private final EventBus GAMEPLAY_BUS;
	private final GameplayContext GAMEPLAY;
	private final TextureManager TEXTURE;
	private final SpriteBatch BATCH;
	private final GameWorldCamera WORLD_CAMERA;
	
	public SceneFactory(final GameContext CONTEXT, final HUDFactory HUD_FACTORY, final EnemyFactory ENEMY_FACTORY,  final NPCFactory NPC_FACTORY, final CutSceneFactory CUTSCENE_FACTORY) {
		this.HUD_FACTORY = HUD_FACTORY;
		this.ENEMY_FACTORY = ENEMY_FACTORY;
		this.NPC_FACTORY = NPC_FACTORY;
		this.CUTSCENE_FACTORY = CUTSCENE_FACTORY;
		this.GAMEPLAY_BUS = CONTEXT.getGAMEPLAY().getBus();
		this.GAMEPLAY = CONTEXT.getGAMEPLAY();
		this.TEXTURE = CONTEXT.getTEXTURE();
		this.BATCH = CONTEXT.getRENDER().getBatch();
		this.WORLD_CAMERA = CONTEXT.getWORLD_CAMERA();
	}
	
    public GameScene create(SceneDescriptor descriptor) {
        final World WORLD = this.build(descriptor);
        final HUD HUD = descriptor.isLevel()
            ? this.HUD_FACTORY.createLevelHUD(descriptor.getLevelData())
            : this.HUD_FACTORY.createLobbyHUD();

        if (descriptor.isLevel()) {
            final LevelController CONTROLLER = new LevelController(
                descriptor.getLevelData(),
                this.ENEMY_FACTORY,
                this.GAMEPLAY_BUS,
                WORLD,
                HUD,
                this.CUTSCENE_FACTORY.create(descriptor.getCutSceneType(), descriptor.getMusic())
            );
            
            final Level LEVEL = new Level(descriptor, WORLD, HUD, CONTROLLER);
            return LEVEL;
            
        } else {
        	final TiledMap MAP = WORLD.getTiled();
        	final NPCSpawner NPC_SPAWNER = new NPCSpawner(this.NPC_FACTORY, this.GAMEPLAY_BUS, MAP);
        	
            return new ExplorationScene(descriptor, WORLD, HUD, NPC_SPAWNER);
        }
    }


	private World build(final SceneDescriptor DESCRIPTOR) {
		final MapRenderer MAP = new MapRenderer(DESCRIPTOR);
		WorldContext worldContext = new WorldContext(MAP, this.GAMEPLAY, this.BATCH, this.WORLD_CAMERA);
		WorldCollisionInitializer.initializeMapColliders(MAP, this.TEXTURE, this.GAMEPLAY_BUS);
		
		return new World(MAP, worldContext);
	}
}
