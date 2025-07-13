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
	private final int DISTANCE_BETWEEN_OPTIONS = 50; 
	
	private InputManager inputManager;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
	
	private int optionIndex = 0;
	private int couldown = this.COULDOWN_TIME;

	private final Figure CURRENT_OPTION = new Figure(10, 10);
	private final Text[] TEXTS;
	
	private float posX, posY;
	
	public Menu(Text[] texts, float posX, float posY) {
		this.TEXTS = texts;
		this.posX = posX;
		this.posY = posY;
	}
	
	@Override
	public void show() {
		this.batch = Resources.getBatch();
		this.inputManager = Resources.getInputManager();
		this.shapeRenderer = Resources.getShapeRenderer();
	}

	@Override
	public void render(float delta) {
		this.update();
		
	    this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
	    this.CURRENT_OPTION.draw(shapeRenderer, this.posX - this.DISTANCE_BETWEEN_OPTIONS, this.posY - this.optionIndex * this.DISTANCE_BETWEEN_OPTIONS);
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
		// Opciones del menu y desplazamiento de seleccion
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
		
		// Acciones de las opciones del menu
		if(this.couldown == 0 && this.inputManager.isEnter()) {
			this.couldown = this.COULDOWN_TIME;
			this.processOption(this.optionIndex);
		}
		
		if(this.couldown == 0 && this.inputManager.isEscape() ) {
			this.couldown = this.COULDOWN_TIME;
			Resources.getGame().setScreen(new MainMenuScreen());
		}
		
		if(this.couldown > 0) {
			this.couldown--;
		}
	}
	
	protected abstract void processOption(int optionIndex);

	@Override
	public void resize(int width, int height) {
	
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
