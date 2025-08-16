package com.haloofwar.dependences.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.ecs.events.types.input.AttackEvent;
import com.haloofwar.ecs.events.types.input.InteractEvent;
import com.haloofwar.ecs.events.types.input.MoveEvent;
import com.haloofwar.enumerators.events.Direction;

public class InputManager implements InputProcessor {

	private final EventBus bus;
	
	public InputManager(EventBus bus) {
		this.bus = bus;
	}
	
	private boolean arrowUp, arrowDown, arrowLeft, arrowRight;
	private boolean enter, escape;
	
	private int mouseX, mouseY;
    private boolean attack, interact, openInventory;
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
			case Input.Keys.W: bus.publish(new MoveEvent(Direction.UP, true)); break;
			case Input.Keys.S: bus.publish(new MoveEvent(Direction.DOWN, true)); break;
			case Input.Keys.A: bus.publish(new MoveEvent(Direction.LEFT, true)); break;
			case Input.Keys.D: bus.publish(new MoveEvent(Direction.RIGHT, true)); break;
	        case Input.Keys.E: bus.publish(new InteractEvent(true)); break;
	        case Input.Keys.I: this.openInventory = true; break;
	        case Input.Keys.ESCAPE: this.escape = true; break;
	        case Input.Keys.UP: this.arrowUp = true; break;
	        case Input.Keys.DOWN: this.arrowDown = true; break;
	        case Input.Keys.LEFT: this.arrowLeft = true; break;
	        case Input.Keys.RIGHT: this.arrowRight = true; break;
	        case Input.Keys.ENTER: this.enter = true; break;
		}
		
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
			case Input.Keys.W: bus.publish(new MoveEvent(Direction.UP, false)); break;
			case Input.Keys.S: bus.publish(new MoveEvent(Direction.DOWN, false)); break;
			case Input.Keys.A: bus.publish(new MoveEvent(Direction.LEFT, false)); break;
			case Input.Keys.D: bus.publish(new MoveEvent(Direction.RIGHT, false)); break;
	        case Input.Keys.E: bus.publish(new InteractEvent(false)); break;
	        case Input.Keys.I: this.openInventory = false; break;
	        case Input.Keys.ESCAPE: this.escape = false; break;
	        case Input.Keys.UP: this.arrowUp = false; break;
	        case Input.Keys.DOWN: this.arrowDown = false; break;
	        case Input.Keys.LEFT: this.arrowLeft = false; break;
	        case Input.Keys.RIGHT: this.arrowRight = false; break;
	        case Input.Keys.ENTER: this.enter = false; break;
		}
		
		return true;
	}
	
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		 if (button == Input.Buttons.LEFT) {
			 this.bus.publish(new AttackEvent(true));
	     }
		 
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			this.bus.publish(new AttackEvent(false));
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
