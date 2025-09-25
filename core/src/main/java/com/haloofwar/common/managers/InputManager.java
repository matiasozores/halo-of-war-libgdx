package com.haloofwar.common.managers;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.haloofwar.common.enums.Direction;
import com.haloofwar.common.enums.GameState;
import com.haloofwar.engine.events.AttackEvent;
import com.haloofwar.engine.events.ChangeCurrentPlayerEvent;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.events.InteractEvent;
import com.haloofwar.engine.events.MoveEvent;
import com.haloofwar.engine.events.NavigationEvent;
import com.haloofwar.engine.events.NextEvent;
import com.haloofwar.engine.events.SelectOptionEvent;
import com.haloofwar.engine.events.ToggleEquipmentEvent;
import com.haloofwar.engine.events.ToggleInventoryEvent;
import com.haloofwar.engine.events.ToggleShopEvent;

public class InputManager implements InputProcessor {

	private final EventBus globalBus;
	private final EventBus gameplayBus;

    public InputManager(EventBus globalBus, EventBus gameplayBus) {
        this.globalBus = globalBus;
        this.gameplayBus = gameplayBus;
    }
 
    private void publish(EventBus bus, Object event) {
    	if(bus != null) {
    		if (bus != null) {
                bus.publish(event);
            }
    	} else {
    		System.out.println("Se intenta publicar un evento con un EventBus nulo... | InputManager");
    	}
    }
    
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.W: this.publish(this.gameplayBus, new MoveEvent(Direction.UP, true)); break;
		    case Input.Keys.S: this.publish(this.gameplayBus, new MoveEvent(Direction.DOWN, true)); break;
		    case Input.Keys.A: this.publish(this.gameplayBus, new MoveEvent(Direction.LEFT, true)); break;
		    case Input.Keys.D: this.publish(this.gameplayBus, new MoveEvent(Direction.RIGHT, true)); break;
		    case Input.Keys.E: this.publish(this.gameplayBus, new InteractEvent(true)); break;
	        case Input.Keys.I: this.publish(this.gameplayBus,new ToggleInventoryEvent()); break;
	        case Input.Keys.U: this.publish(this.gameplayBus,new ToggleShopEvent()); break;
	        case Input.Keys.O: this.publish(this.gameplayBus,new ToggleEquipmentEvent()); break;
	        case Input.Keys.C: this.publish(this.gameplayBus, new ChangeCurrentPlayerEvent()); break;
	        case Input.Keys.ESCAPE: this.publish(this.gameplayBus, new GameStateEvent(GameState.PAUSED)); break;        
		    case Input.Keys.SPACE: this.publish(this.gameplayBus, new NextEvent(true)); break;
		
		    case Input.Keys.UP:
		    	this.publish(this.globalBus, new NavigationEvent(Direction.UP, true));
		    	this.publish(this.gameplayBus, new NavigationEvent(Direction.UP, true));
	            break;
	        case Input.Keys.DOWN:
		    	this.publish(this.globalBus, new NavigationEvent(Direction.DOWN, true));
		    	this.publish(this.gameplayBus, new NavigationEvent(Direction.DOWN, true));
	            break;
	            
	        case Input.Keys.LEFT: 
		    	this.publish(this.globalBus, new NavigationEvent(Direction.LEFT, true));
		    	this.publish(this.gameplayBus, new NavigationEvent(Direction.LEFT, true));
	        	break;
	        	
	        case Input.Keys.RIGHT: 
		    	this.publish(this.globalBus, new NavigationEvent(Direction.RIGHT, true));
		    	this.publish(this.gameplayBus, new NavigationEvent(Direction.RIGHT, true));
	        	break;
	            
	        case Input.Keys.ENTER: 
	        	this.publish(this.globalBus, new SelectOptionEvent(true));
	        	this.publish(this.gameplayBus, new SelectOptionEvent(true)); 
	        	break;
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.W: this.publish(this.gameplayBus,new MoveEvent(Direction.UP, false)); break;
		    case Input.Keys.S: this.publish(this.gameplayBus,new MoveEvent(Direction.DOWN, false)); break;
		    case Input.Keys.A: this.publish(this.gameplayBus,new MoveEvent(Direction.LEFT, false)); break;
		    case Input.Keys.D: this.publish(this.gameplayBus,new MoveEvent(Direction.RIGHT, false)); break;
		    case Input.Keys.E: this.publish(this.gameplayBus,new InteractEvent(false)); break;
		    case Input.Keys.SPACE: this.publish(this.gameplayBus,new NextEvent(false)); break;
		    
		    case Input.Keys.UP:
		    	this.publish(this.globalBus, new NavigationEvent(Direction.UP, false));
		    	this.publish(this.gameplayBus, new NavigationEvent(Direction.UP, false));
	            break;
	        case Input.Keys.DOWN:
		    	this.publish(this.globalBus, new NavigationEvent(Direction.DOWN, false));
		    	this.publish(this.gameplayBus, new NavigationEvent(Direction.DOWN, false));
	            break;
	            
	        case Input.Keys.LEFT: 
		    	this.publish(this.globalBus, new NavigationEvent(Direction.LEFT, false));
		    	this.publish(this.gameplayBus, new NavigationEvent(Direction.LEFT, false));
	        	break;
	        	
	        case Input.Keys.RIGHT: 
		    	this.publish(this.globalBus, new NavigationEvent(Direction.RIGHT, false));
		    	this.publish(this.gameplayBus, new NavigationEvent(Direction.RIGHT, false));
	        	break;
	            
	        case Input.Keys.ENTER: 
	        	this.publish(this.globalBus, new SelectOptionEvent(false));
	        	this.publish(this.gameplayBus, new SelectOptionEvent(false)); 
	        	break;
		}
		
		return true;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		 if (button == Input.Buttons.LEFT) {
			 this.publish(this.gameplayBus,new AttackEvent(true));
			 
	     }
		 
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			this.publish(this.gameplayBus, new AttackEvent(false));
		}
		
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		this.mouseMoved(screenX, screenY);
		return true;
	}
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) { return true; }
	@Override
	public boolean scrolled(float amountX, float amountY) { return false; }
	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
	@Override
	public boolean keyTyped(char character) { return false; }
}
