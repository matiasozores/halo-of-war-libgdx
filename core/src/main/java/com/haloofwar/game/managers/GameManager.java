package com.haloofwar.game.managers;

import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.engine.events.ChangeSceneEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;

public class GameManager implements Screen {

    private final GameFlowManager flow;
    private final GameEventSubscriber subscriber;

    private final EventListenerManager listenerManager = new EventListenerManager();
    
    public GameManager(
		final GameFlowManager flow,
		final GameEventSubscriber subscriber,
		final GameContext context
	) {
        this.flow = flow;
        this.subscriber = subscriber;
    	
        final EventBus gameplayBus = context.getGAMEPLAY().getBus();
        this.initializeScene(gameplayBus);
    }
    
    private void initializeScene(final EventBus gameplayBus) {
      	gameplayBus.publish(new ChangeSceneEvent(LevelSceneType.TUTORIAL));
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
    	if(this.flow.getCurrentScene()!=null) {
    		this.flow.getCurrentScene().dispose();
    	
    	}
    	this.flow.dispose();
    	this.subscriber.dispose();
    	this.listenerManager.clear();
    }
}
