package com.haloofwar.game.managers;

import java.util.Set;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.GameState;
import com.haloofwar.common.enums.LevelSceneType;
import com.haloofwar.common.enums.SceneType;
import com.haloofwar.engine.events.ChangeSceneEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.game.cutscenes.LevelCompletedScene;
import com.haloofwar.ui.screens.GameOverScreen;
import com.haloofwar.ui.screens.PauseMenuScreen;

public class GameManager implements Screen {

    private final GameFlowManager flow;
    private final GameEventSubscriber subscriber;
    private final PauseMenuScreen pauseMenu;
    private final GameOverScreen gameOver;

    public GameManager(
		final GameFlowManager flow,
		final LevelCompletedScene completedScene,
		final GameEventSubscriber subscriber,
		final Set<LevelSceneType> completedLevels,
		final GameContext context
	) {
        this.flow = flow;
        this.subscriber = subscriber;
        this.pauseMenu = new PauseMenuScreen(context, this);
        this.gameOver = new GameOverScreen(context, this);
    	
        final EventBus gameplayBus = context.getGAMEPLAY().getBus();
        this.initializeScene(gameplayBus, completedLevels);
    }
    
    private void initializeScene(final EventBus gameplayBus, final Set<LevelSceneType> completedLevels) {
    	if(completedLevels == null || completedLevels.isEmpty()) {
        	gameplayBus.publish(new ChangeSceneEvent(LevelSceneType.TUTORIAL));
        } else {
        	gameplayBus.publish(new ChangeSceneEvent(SceneType.MAIN));
        }
    }

    @Override
    public void show() { 
    	if(this.flow.getCurrentScene()!=null) {
    		this.flow.getCurrentScene().show(); 
    	}
    }

    @Override
    public void render(float delta) {
        this.flow.update(delta);
        this.flow.render(delta);

        if (this.flow.getCurrentState() == GameState.PAUSED) {
            this.pauseMenu.render(delta);
        } else if (this.flow.getCurrentState() == GameState.GAME_OVER) {
            this.gameOver.render(delta);
        }
    }


    @Override
    public void resize(int width, int height) { if(flow.getCurrentScene()!=null) flow.getCurrentScene().resize(width, height); }
    @Override
    public void pause() { if(flow.getCurrentScene()!=null) flow.getCurrentScene().pause(); }
    @Override
    public void resume() { if(flow.getCurrentScene()!=null) flow.getCurrentScene().resume(); }
    @Override
    public void hide() { if(flow.getCurrentScene()!=null) flow.getCurrentScene().hide(); }
    @Override
    public void dispose() { 
    	if(flow.getCurrentScene()!=null) {
    		flow.getCurrentScene().dispose();
    	}
    }
}
