package com.haloofwar.factories;

import com.haloofwar.components.TransformComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.LevelType;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.game.GameScene;
import com.haloofwar.game.Level;
import com.haloofwar.game.World;
import com.haloofwar.game.dependences.MapRenderer;
import com.haloofwar.game.dependences.WorldCollisionInitializer;
import com.haloofwar.game.dependences.WorldContext;
import com.haloofwar.ui.HUD;

public final class SceneFactory {
	private GameContext context;
	
	public SceneFactory(GameContext context) {
		this.context = context;
	}
	
    public GameScene create(SceneType type) {
    	World world = this.build(type);
    	this.playerReposition(world);
    	
    	HUD hud = this.context.getFactories().getHUD_FACTORY().create();
    	return new GameScene(world, hud);
    }
    
    public GameScene create(LevelType type) {
        World world = this.build(type.getScene());
        this.playerReposition(world);

        HUD hud = this.context.getFactories().getHUD_FACTORY().create();
        return new Level(world, hud, type.getData(), this.context.getBus(), this.context.getFactories().getENEMY_FACTORY());
    }

    
	private World build(SceneType type) {
		MapRenderer map = new MapRenderer(type);
		WorldContext worldContext = new WorldContext(this.context.getGameplay().getPlayer(), map, this.context);
	
		WorldCollisionInitializer.initializeMapColliders(map, this.context);
		return new World(map, worldContext);
	}
	
	private void playerReposition(World world) {
		float x = world.getMap().getMetaData().getxSpawnPoint();
		float y = world.getMap().getMetaData().getySpawnPoint();
		
		this.context.getGameplay().getPlayer().getComponent(TransformComponent.class).setPosition(x, y);
	}
}
