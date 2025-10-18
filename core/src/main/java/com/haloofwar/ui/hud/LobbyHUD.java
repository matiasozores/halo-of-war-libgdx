package com.haloofwar.ui.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enumerators.GameState;
import com.haloofwar.common.enumerators.UIState;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.events.NavigationEvent;
import com.haloofwar.engine.events.SelectOptionEvent;
import com.haloofwar.engine.events.TogglePopupEvent;
import com.haloofwar.ui.HUD;
import com.haloofwar.ui.components.HUDComponent;
import com.haloofwar.ui.components.Popup;

public class LobbyHUD extends HUD{
	
	private final Popup[] popups;
	private final EventListenerManager listenerManager = new EventListenerManager();
    private final EventBus gameplayBus;
	private UIState currentUI = UIState.NONE;
	private final float NAV_COOLDOWN = 0.15f; 
    private float navigateTimer = 0f;
    
    public LobbyHUD(
		final HUDComponent[] components,
		final Popup[] popups,
        final GameStaticCamera camera,
        final SpriteBatch batch,
        final EventBus gameplayBus
    ) {
    	super(components, camera, batch, gameplayBus);
    	this.gameplayBus = gameplayBus;
    	this.popups = popups;

    	this.listenerManager.add(gameplayBus, TogglePopupEvent.class, this::onTogglePopup);
    	this.listenerManager.add(gameplayBus, NavigationEvent.class, this::onNavigate);
    	this.listenerManager.add(gameplayBus, SelectOptionEvent.class, this::onSelectOption);
    }

    @Override
    public void render(float delta) {
    	super.render(delta);
    	this.batch.begin();
    	for (Popup popup : this.popups) {
			popup.render();
		}
    	this.batch.end();
    }
    
	@Override
	public void update(float delta) {
		this.navigateTimer += delta;
	}
	
	private void onTogglePopup(TogglePopupEvent event) {
		final Popup target = this.getPopup(event.state);
		
		if(target == null) {
			return;
		}
		
		this.togglePopup(target.getState(), target);
	}
	
	private Popup getPopup(final UIState state) {
		boolean found = false;
		int i = 0;
		int index = -1;
		
		while(i < this.popups.length && !found) {
			if(this.popups[i].getState().equals(state)) {
				found = true;
				index = i;
			} else {
				i++;
			}
		}
		
		if(found) {
			return this.popups[index];
		} else {
			System.out.println("Ha ocurrido un error a buscar un Popup... | LobbyHUD");
			return null;
		}
	}
	
    private void togglePopup(UIState state, Popup popup) {
    	if(popup == null) {
    		return;
    	}
    	
    	final UIState[] blockedStates = UIState.getBlockedStates(state);
    	
    	for(UIState blocked : blockedStates) {
    		if(currentUI.equals(blocked)) {
    			return;
    		}
    	}
    	
    	boolean opening = !popup.isVisible();
    	popup.setVisible(opening);
    	
    	this.currentUI = opening ? state : UIState.NONE;
    	this.gameplayBus.publish(new GameStateEvent(opening ? GameState.WAITING : GameState.PLAYING));
    }
    
    private void onNavigate(NavigationEvent event) {
        if (this.navigateTimer < this.NAV_COOLDOWN) {
            return; 
        }
        
        // no hacer busquedas con for
        for (Popup popup : this.popups) {
            if (popup != null && popup.isVisible()) {
                popup.navigate(event.direction);
                break; 
            }
        }

        this.navigateTimer = 0f;
    }
    
    private void onSelectOption(SelectOptionEvent event) {
        if (!event.isPressed) return;

        for (Popup popup : this.popups) {
            if (popup != null && popup.isVisible()) {
                popup.onSelectOption();
                break; 
            }
        }
    }
    
	@Override
	public void reset() {
    	this.listenerManager.add(gameplayBus, TogglePopupEvent.class, this::onTogglePopup);
    	this.listenerManager.add(gameplayBus, NavigationEvent.class, this::onNavigate);
    	this.listenerManager.add(gameplayBus, SelectOptionEvent.class, this::onSelectOption);
	}

	@Override
	public void dispose() {
		super.dispose();
		this.listenerManager.clear();
	}


}
