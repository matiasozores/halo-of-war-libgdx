package com.haloofwar.launcher;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.common.enumerators.SceneType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.cutscenes.LevelCompletedScene;
import com.haloofwar.game.cutscenes.LevelGameOverScene;
import com.haloofwar.game.managers.GameEventSubscriber;
import com.haloofwar.game.managers.GameFlowManager;
import com.haloofwar.game.managers.GameManager;

public class GameInitializer {
	
	public static void initializeGameplay(GameContext context, Entity player1, Entity player2) {
    	GameplayContext gameplay = context.getGAMEPLAY();
    	gameplay.initializePlayers(player1, player2);

    	final GameManager manager = initializeLocalGameManager(context);
    	context.getGAME().setScreen(manager);
    }
    
    private static GameManager initializeLocalGameManager(final GameContext context) {
    	final GameFlowManager flow = new GameFlowManager(context.getTEXTURE(), context.getGAMEPLAY());
    	
    	final LevelCompletedScene completedScene = new LevelCompletedScene(
    			context.getSTATIC_CAMERA(),
                context.getRENDER().getBatch(),
                context.getTEXTURE().get(Background.VICTORY),
                context.getGAMEPLAY().getBus(),
                SceneType.MAIN
        );
    	
    	final LevelGameOverScene gameOverScene = new LevelGameOverScene(
    			context.getSTATIC_CAMERA(),
                context.getRENDER().getBatch(),
                context.getTEXTURE().get(Background.GAME_OVER),
                context.getGAMEPLAY().getBus(),
                SceneType.MAIN
        );
    	
    	final GameEventSubscriber subscriber = new GameEventSubscriber(
                flow,
                context.getGAMEPLAY().getBus(),
                context.getSCENE(),
                context.getGAMEPLAY().getKratos(),
                context.getGAMEPLAY().getMasterchief(),
                completedScene,
                gameOverScene,
                context.getTEXTURE()
        );
    	
    	return new GameManager(flow, subscriber, context);
    }
}
