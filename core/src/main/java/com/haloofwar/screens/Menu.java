package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.input.InputManager;
import com.haloofwar.utilities.Figure;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public abstract class Menu implements Screen{
	private final int COULDOWN_TIME = 30; 
	private final int DISTANCE_BETWEEN_OPTIONS = 100;
	private final int DISTANCE_OPTION_SELECTION = 50;
	
	private InputManager inputManager;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	private int optionIndex = 0;
	private int couldown = this.COULDOWN_TIME;

	private final Figure CURRENT_OPTION = new Figure(10, 10);
	private final Text[] TEXTS;
	
	private float posX = 100, posY = 600;
	
	public Menu(Text[] texts) {
		this.TEXTS = texts;
	}
	
	@Override
	public void show() {
		this.batch = Resources.getBatchUI();
		this.inputManager = Resources.getInputManager();
		this.shapeRenderer = Resources.getShapeRenderer();
	}

	@Override
	public void render(float delta) {
		this.update();
		
	    this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
	    this.CURRENT_OPTION.draw(shapeRenderer, this.posX - this.DISTANCE_OPTION_SELECTION, this.posY - this.optionIndex * this.DISTANCE_BETWEEN_OPTIONS);
	    this.shapeRenderer.end();
	    
	    this.batch.begin();
		int distance = 0;
		for (Text text : this.TEXTS) {
			text.draw(batch, this.posX, this.posY - distance * this.DISTANCE_BETWEEN_OPTIONS);
			distance++;
		}
		this.batch.end();		
	}
	
	public void update() {
		if ((this.inputManager.isArrowDown() || this.inputManager.isArrowUp()) && this.couldown == 0) {
			this.couldown = this.COULDOWN_TIME;
			
			if (this.inputManager.isArrowDown()) {
				if(this.optionIndex + 1 > this.TEXTS.length - 1) {
					this.optionIndex = 0;
				} else {
					this.optionIndex += 1;
				}
			} else if (this.inputManager.isArrowUp()) {
				
				if(this.optionIndex - 1 < 0) {
					this.optionIndex = this.TEXTS.length - 1;
				} else {
					this.optionIndex -= 1;
				}
				
			}
			
			this.CURRENT_OPTION.setPosition(this.posX - this.DISTANCE_BETWEEN_OPTIONS, this.posY - this.optionIndex * this.DISTANCE_BETWEEN_OPTIONS);
		}
		
		if(this.couldown == 0) {
			if(this.inputManager.isEnter()) {
				this.couldown = this.COULDOWN_TIME;
				this.processOption(this.optionIndex);
			}
			
			if(this.inputManager.isEscape() ) {
				this.couldown = this.COULDOWN_TIME;
				Resources.getGame().setScreen(new MainMenuScreen());
			}
		}
	
		
		if(this.couldown > 0) {
			this.couldown--;
		}
	}
	
	protected abstract void processOption(int optionIndex);

	@Override
	public void resize(int width, int height) {
		System.out.println("" + this.getClass().getSimpleName() + " resized to " + width + "x" + height);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
