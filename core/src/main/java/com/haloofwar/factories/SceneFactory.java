package com.haloofwar.factories;

import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.SceneType;
import com.haloofwar.game.GameScene;
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
	
	// Luego se mejorara para no utilizar switch
    public GameScene create(SceneType type) {
    	World world = this.build(type);
    	HUD hud = this.context.getFactories().getHUD_FACTORY().create();
    	
    	return new GameScene(world, hud, this.context.getGameplay().getPlayer());
    }
    
	private World build(SceneType type) {
		MapRenderer map = new MapRenderer(type);
		WorldContext worldContext = new WorldContext(this.context.getGameplay().getPlayer(), map, this.context);
	
		// Se encarga de agregar las collisiones del mapa al collision manager
		WorldCollisionInitializer.initializeMapColliders(map, this.context);
		return new World(map, worldContext);
	}
}
