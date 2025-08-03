package com.haloofwar.dependences.input;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {

	private boolean moveUp, moveDown, moveLeft, moveRight;
	private boolean arrowUp, arrowDown, arrowLeft, arrowRight;
	private boolean enter, escape;
	
	private int mouseX, mouseY;
    private boolean attack, interact, openInventory;
	
	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
	        case Input.Keys.W: this.moveUp = true; break;
	        case Input.Keys.S: this.moveDown = true; break;
	        case Input.Keys.A: this.moveLeft = true; break;
	        case Input.Keys.D: this.moveRight = true; break;
	        case Input.Keys.E: this.interact = true; break;
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
	        case Input.Keys.W: this.moveUp = false; break;
	        case Input.Keys.S: this.moveDown = false; break;
	        case Input.Keys.A: this.moveLeft = false; break;
	        case Input.Keys.D: this.moveRight = false; break;
	        case Input.Keys.E: this.interact = false; break;
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
			 this.attack = true;
	     }
		 
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Input.Buttons.LEFT) {
			this.attack = false;
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
	
	public boolean isMoving() {
	    return (this.moveDown || this.moveUp || this.moveLeft || this.moveRight);
	}
	
	public boolean isMoveUp() {
		return this.moveUp;
	}
	
	public boolean isMoveDown() {
		return this.moveDown;
	}
	
	public boolean isMoveLeft() {
		return this.moveLeft;
	}
	
	public boolean isMoveRight() {
		return this.moveRight;
	}
	
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
