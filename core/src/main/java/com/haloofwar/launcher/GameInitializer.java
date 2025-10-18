package com.haloofwar.launcher;

import java.util.Set;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.common.enumerators.SceneType;
import com.haloofwar.common.enumerators.SoundType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.StopMusicEvent;
import com.haloofwar.game.cutscenes.LevelCompletedScene;
import com.haloofwar.game.cutscenes.LevelGameOverScene;
import com.haloofwar.game.managers.GameEventSubscriber;
import com.haloofwar.game.managers.GameFlowManager;
import com.haloofwar.game.managers.GameManager;
import com.haloofwar.network.GameController;
import com.haloofwar.network.ClientGameController;
import com.haloofwar.ui.screens.MainMenuScreen;
import com.haloofwar.ui.screens.WaitingMenuScreen;

public class GameInitializer {
	/*
    public static Entity createPlayerFromSave(GameContext context, SaveGameData data, final PlayerType TYPE) {
    	final PlayerData PLAYER_DATA = data.getPlayerData(TYPE);
        if(PLAYER_DATA == null) {
        	System.out.println("No se ha podido crear a '" + TYPE + "' mediante archivo...");
        	return null;
        }
        
        Entity player = context.getFactories().getPLAYER_FACTORY().create(PLAYER_DATA.type, PLAYER_DATA.inventory, PLAYER_DATA.equipment);        
        return player;
    }*/

    public static void initializeGameplay(GameContext context, Set<LevelSceneType> completedLevels, boolean isOnline) {   	
        handleAudio(context.getGlobalBus());
        final GameManager manager;
        
        if(isOnline) {
        	final WaitingMenuScreen screen = new WaitingMenuScreen(context, new MainMenuScreen(context));
        	final ClientGameController controller = new ClientGameController(context.getGameplay().getBus(), context.getTEXTURE());
        	screen.setController(controller);
        	context.getGAME().setScreen(screen);
        	controller.connect(); 

        	
        } else {
        	manager = initializeLocalGameManager(context, completedLevels);
        	context.getGAME().setScreen(manager);
        }
        
        
    }
    
    private static GameManager initializeLocalGameManager(final GameContext context, final Set<LevelSceneType> completedLevels) {
    	final Set<LevelSceneType> lockedLevels = LevelSceneType.getAllLockedLevels();
    	
    	final GameFlowManager flow = new GameFlowManager(context.getTEXTURE(), context.getGameplay(), lockedLevels);
    	
    	final LevelCompletedScene completedScene = new LevelCompletedScene(
    			context.getStaticCamera(),
                context.getRender().getBatch(),
                context.getTEXTURE().get(Background.VICTORY),
                context.getGameplay().getBus(),
                SceneType.MAIN
        );
    	
    	final LevelGameOverScene gameOverScene = new LevelGameOverScene(
    			context.getStaticCamera(),
                context.getRender().getBatch(),
                context.getTEXTURE().get(Background.GAME_OVER),
                context.getGameplay().getBus(),
                SceneType.MAIN
        );
    	
    	final GameEventSubscriber subscriber = new GameEventSubscriber(
                flow,
                context.getGameplay().getBus(),
                context.getScene(),
                context.getGameplay().getKratos(),
                context.getGameplay().getMasterchief(),
                completedScene,
                gameOverScene,
                completedLevels,
                lockedLevels
        );
    	
    	final ClientGameController controller = new ClientGameController(context.getGameplay().getBus(), context.getTEXTURE());
    	
    	return new GameManager(flow, subscriber, completedLevels, context, controller);
    }
    
    public static GameManager initializeOnlineGameManager(final int kratosId, final int masterchiefId, final GameContext context, GameController controller, PlayerType currentType) {
    	final Set<LevelSceneType> lockedLevels = LevelSceneType.getAllLockedLevels();
    	
       	Entity player1;
       	Entity player2;
       	if(currentType.equals(PlayerType.KRATOS)) {
       		player1 = context.getFactories().getPLAYER_FACTORY().create(kratosId, currentType);
	   		player2 = context.getFactories().getPLAYER_FACTORY().create(masterchiefId, PlayerType.MASTER_CHIEF);
	   		
	   	} else if(currentType.equals(PlayerType.MASTER_CHIEF)) {
	   		player1 = context.getFactories().getPLAYER_FACTORY().create(masterchiefId, currentType);
	   		player2 = context.getFactories().getPLAYER_FACTORY().create(kratosId, PlayerType.KRATOS);
	   	} else {
	   		System.out.println("Ha ocurrido un error al crear los jugadores en el juego online");
	   		return null;
	   	}
    	
    	GameplayContext gameplay = context.getGameplay();
    	gameplay.initializePlayers(player1, player2);
    	
    	final GameFlowManager flow = new GameFlowManager(context.getTEXTURE(), context.getGameplay(), lockedLevels);
    	
    	final LevelCompletedScene completedScene = new LevelCompletedScene(
    			context.getStaticCamera(),
                context.getRender().getBatch(),
                context.getTEXTURE().get(Background.VICTORY),
                context.getGameplay().getBus(),
                SceneType.MAIN
        );
    	
    	final LevelGameOverScene gameOverScene = new LevelGameOverScene(
    			context.getStaticCamera(),
                context.getRender().getBatch(),
                context.getTEXTURE().get(Background.GAME_OVER),
                context.getGameplay().getBus(),
                SceneType.MAIN
        );
    	
    	final GameEventSubscriber subscriber = new GameEventSubscriber(
                flow,
                context.getGameplay().getBus(),
                context.getScene(),
                context.getGameplay().getKratos(),
                context.getGameplay().getMasterchief(),
                completedScene,
                gameOverScene,
                null,
                lockedLevels
        );
    	
    	return new GameManager(flow, subscriber, null, context, controller);
    }
    
    private static void handleAudio(EventBus bus) {
        bus.publish(new PlaySoundEvent(SoundType.LOAD_GAME));
        bus.publish(new StopMusicEvent());
    }
}
