package com.haloofwar.dependences;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.haloofwar.enumerators.Direction;
import com.haloofwar.enumerators.GameState;
import com.haloofwar.events.AttackEvent;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.GameStateEvent;
import com.haloofwar.events.InteractEvent;
import com.haloofwar.events.MoveEvent;
import com.haloofwar.events.NavigationEvent;
import com.haloofwar.events.NextEvent;
import com.haloofwar.events.SelectOptionEvent;

public class InputManager implements InputProcessor {

	private final EventBus globalBus;
	private EventBus gameplayBus;

    public InputManager(EventBus globalBus) {
        this.globalBus = globalBus;
    }

    public void setGameplayBus(EventBus gameplayBus) {
        this.gameplayBus = gameplayBus;
    }
	
	private boolean arrowUp, arrowDown, arrowLeft, arrowRight;
	private boolean enter, escape;
	
	private int mouseX, mouseY;
    private boolean attack, interact, openInventory;
	
    private void publishGameplay(Object event) {
        if (this.gameplayBus != null) {
            this.gameplayBus.publish(event);
        }
    }
    
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.W: publishGameplay(new MoveEvent(Direction.UP, true)); break;
		    case Input.Keys.S: publishGameplay(new MoveEvent(Direction.DOWN, true)); break;
		    case Input.Keys.A: publishGameplay(new MoveEvent(Direction.LEFT, true)); break;
		    case Input.Keys.D: publishGameplay(new MoveEvent(Direction.RIGHT, true)); break;
		    case Input.Keys.E: publishGameplay(new InteractEvent(true)); break;
	        case Input.Keys.I: this.openInventory = true; break;
	        case Input.Keys.ESCAPE: publishGameplay(new GameStateEvent(GameState.PAUSED)); this.escape = true; break;
	        case Input.Keys.UP: this.globalBus.publish(new NavigationEvent(Direction.UP, true)); break;
	        case Input.Keys.DOWN: this.globalBus.publish(new NavigationEvent(Direction.DOWN, true)); break;
	        case Input.Keys.LEFT: this.arrowLeft = true; break;
	        case Input.Keys.RIGHT: this.arrowRight = true; break;
	        case Input.Keys.ENTER: this.globalBus.publish(new SelectOptionEvent(true)); break;
		    case Input.Keys.SPACE: publishGameplay(new NextEvent(true)); break;
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.W: publishGameplay(new MoveEvent(Direction.UP, false)); break;
		    case Input.Keys.S: publishGameplay(new MoveEvent(Direction.DOWN, false)); break;
		    case Input.Keys.A: publishGameplay(new MoveEvent(Direction.LEFT, false)); break;
		    case Input.Keys.D: publishGameplay(new MoveEvent(Direction.RIGHT, false)); break;
		    case Input.Keys.E: publishGameplay(new InteractEvent(false)); break;
	        case Input.Keys.I: this.openInventory = false; break;
	        case Input.Keys.ESCAPE: this.escape = false; break;
	        case Input.Keys.UP: this.globalBus.publish(new NavigationEvent(Direction.UP, false)); break;
	        case Input.Keys.DOWN: this.globalBus.publish(new NavigationEvent(Direction.DOWN, false)); break;
	        case Input.Keys.LEFT: this.arrowLeft = false; break;
	        case Input.Keys.RIGHT: this.arrowRight = false; break;
	        case Input.Keys.ENTER: this.globalBus.publish(new SelectOptionEvent(false)); break;
		    case Input.Keys.SPACE: publishGameplay(new NextEvent(false)); break;
		}
		
		return true;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		 if (button == Input.Buttons.LEFT) {
			 this.publishGameplay(new AttackEvent(true));
	     }
		 
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			this.publishGameplay(new AttackEvent(false));
		}
		
		return true;
	}
	
	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		this.mouseMoved(screenX, screenY);
		return true;
	}
	

	// Getters
	
	public boolean isArrowUp() {
		return this.arrowUp;
	}
	
	public boolean isArrowDown() {
		return this.arrowDown;
	}
	
	public boolean isArrowLeft() {
		return this.arrowLeft;
	}
	
	public boolean isArrowRight() {
		return this.arrowRight;
	}
	
	public boolean isEnter() {
		return this.enter;
	}
	
	public boolean isEscape() {
		return this.escape;
	}
	
	public void clearEscape() {
	    this.escape = false;
	}

	
	public boolean isAttack() {
		return this.attack;
	}
	
	public boolean isInteract() {
		return this.interact;
	}
	
	public boolean isOpenInventory() {
		return this.openInventory;
	}
	
	public int getMouseX() {
		return this.mouseX;
	}
	
	public int getMouseY() {
		return this.mouseY;
	}
	
	// Metodos que no se usan
	
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
	    return true;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		return false;
	}
	
	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
		return false;
	}
}
