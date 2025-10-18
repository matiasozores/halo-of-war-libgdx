package com.haloofwar.ui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.engine.events.ConnectEvent;
import com.haloofwar.engine.events.DisconnectEvent;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.StartGameEvent;
import com.haloofwar.game.managers.GameManager;
import com.haloofwar.launcher.GameInitializer;
import com.haloofwar.network.GameController;
import com.haloofwar.ui.Menu;

public class WaitingMenuScreen extends Menu {

	private EventListenerManager listenerManager = new EventListenerManager();
	private GameController controller;
	
    public WaitingMenuScreen(final GameContext CONTEXT, final Screen previousScreen) {
        super(CONTEXT, "Intentando conectar a un servidor...",new String[] {
        		"Desconectarse y volver al menu"
        }, previousScreen, Background.VICTORY);
        
        this.listenerManager.add(CONTEXT.getGameplay().getBus(), ConnectEvent.class, this::onConnect);
        this.listenerManager.add(CONTEXT.getGameplay().getBus(), DisconnectEvent.class, this::onDisconnect);
        this.listenerManager.add(CONTEXT.getGameplay().getBus(), StartGameEvent.class, this::onStartGameOnline);
    }
    
    public void setController(GameController controller) {
    	this.controller = controller;
    }
    
    private void onConnect(ConnectEvent event) {
    	this.updateTitle("Esperando a otro jugador para empezar la partida...");
    }
    
    private void onDisconnect(DisconnectEvent event) {
    	this.controller.dispose();
        Gdx.app.postRunnable(() -> {
            this.context.getGAME().setScreen(new DisconnectionMenuScreen(this.context));
        });
    }
    
    private void onStartGameOnline(StartGameEvent event) {
		GameManager manager = GameInitializer.initializeOnlineGameManager(event.kratosId, event.masterchiefId, this.context, this.controller, event.playerType);
		this.context.getGAME().setScreen(manager);
    }
    
    @Override
    protected void processOption(int optionIndex) {
    	switch (optionIndex) {
		case 0:
			this.controller.dispose(); 
			this.goBack();

			break;

		default:
			break;
		}
    }
    
    @Override
    public void dispose() {
    	super.dispose();
    	this.controller.dispose();
    }
}
