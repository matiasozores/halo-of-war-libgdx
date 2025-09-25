package com.haloofwar.launcher;

import java.util.Set;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.context.GameplayContext;
import com.haloofwar.common.enums.HeadType;
import com.haloofwar.common.enums.LevelSceneType;
import com.haloofwar.common.enums.PlayerType;
import com.haloofwar.common.enums.SceneType;
import com.haloofwar.common.enums.SoundType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.StopMusicEvent;
import com.haloofwar.game.cutscenes.LevelCompletedScene;
import com.haloofwar.game.data.PlayerData;
import com.haloofwar.game.data.SaveGameData;
import com.haloofwar.game.managers.GameEventSubscriber;
import com.haloofwar.game.managers.GameFlowManager;
import com.haloofwar.game.managers.GameManager;

public class GameInitializer {
	
    public static Entity createPlayerFromSave(GameContext context, SaveGameData data, final PlayerType TYPE) {
        final PlayerData PLAYER_DATA = data.getPlayerData(TYPE);
        if(PLAYER_DATA == null) {
        	System.out.println("No se ha podido crear a '" + TYPE + "' mediante archivo...");
        	return null;
        }
        
        Entity player = context.getFACTORIES().getPLAYER_FACTORY().create(PLAYER_DATA.type, PLAYER_DATA.inventory, PLAYER_DATA.equipment);        
        return player;
    }

    public static void initializeGameplay(GameContext context, Entity player1, Entity player2, Set<LevelSceneType> completedLevels) {
    	GameplayContext gameplay = context.getGAMEPLAY();
    	gameplay.initializePlayers(player1, player2);
        
        handleAudio(context.getGLOBAL_BUS());
        
        final GameManager manager = initializeGameManager(context, completedLevels);
        
        context.getGAME().setScreen(manager);
    }
    
    private static GameManager initializeGameManager(final GameContext context, final Set<LevelSceneType> completedLevels) {
    	final GameFlowManager flow = new GameFlowManager(context.getGAMEPLAY());
    	
    	final LevelCompletedScene completedScene = new LevelCompletedScene(
                context.getRENDER().getBatch(),
                context.getTEXTURE().get(HeadType.KRATOS),
                context.getGAMEPLAY().getBus(),
                SceneType.MAIN
        );
    	
    	final GameEventSubscriber subscriber = new GameEventSubscriber(
                flow,
                context.getGAMEPLAY().getBus(),
                context.getSCENE(),
                context.getGAMEPLAY().getCurrentPlayer(),
                completedScene,
                completedLevels
        );
    	
    	return new GameManager(flow, completedScene, subscriber, completedLevels, context);
    }
    
    private static void handleAudio(EventBus bus) {
        bus.publish(new PlaySoundEvent(SoundType.LOAD_GAME));
        bus.publish(new StopMusicEvent());
    }
}
