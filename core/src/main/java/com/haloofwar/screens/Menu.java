package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.dependences.InputManager;
import com.haloofwar.enumerators.SoundType;
import com.haloofwar.utilities.Figure;
import com.haloofwar.utilities.text.Text;

public abstract class Menu implements Screen {
    protected final int SELECTOR_COOLDOWN = 35;
    protected final int ACTION_COOLDOWN = 30;
    private final int OPTION_SPACING = 100;
    private final int SELECTOR_OFFSET_X = 50;
    
    protected final Text[] options;
    private final Figure selector = new Figure(10, 10);
    
    protected int selectedIndex = 0;
    protected int actionCooldown = this.ACTION_COOLDOWN;
    protected int selectorCooldown = this.SELECTOR_COOLDOWN;
    
    private float baseX = 100, baseY = 600;

    // Dependencias
    protected final GameContext gameContext;
    protected final InputManager inputManager;
    protected final SpriteBatch batch;
    protected final ShapeRenderer shapeRenderer;
    private final GameStaticCamera camera;
    
    public Menu(GameContext gameContext, String[] optionTexts) {
        this.gameContext = gameContext;
        this.inputManager = gameContext.getInputManager();
        this.batch = gameContext.getBatch();
        this.shapeRenderer = gameContext.getShapeRenderer();
        this.camera = new GameStaticCamera();
        
        this.options = new Text[optionTexts.length];
        for (int i = 0; i < optionTexts.length; i++) {
			this.options[i] = new Text(optionTexts[i], this.gameContext.getFontManager().getDefaultFont());
		}
    }

    @Override
    public void render(float delta) {
        this.update();

        this.camera.update();
        this.shapeRenderer.setProjectionMatrix(this.camera.getCamera().combined);
        this.batch.setProjectionMatrix(this.camera.getCamera().combined);

        this.shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float selectorY = this.baseY - this.selectedIndex * this.OPTION_SPACING;
        this.selector.draw(this.shapeRenderer, this.baseX - this.SELECTOR_OFFSET_X, selectorY);
        this.shapeRenderer.end();

        this.batch.begin();
        for (int i = 0; i < this.options.length; i++) {
            this.options[i].draw(this.batch, this.baseX, this.baseY - i * this.OPTION_SPACING);
        }
        
        this.batch.end();
    }

    public void update() {
        if (this.selectorCooldown > 0) {
            this.selectorCooldown--;
        } else {
            boolean changed = false;

            if (this.inputManager.isArrowDown()) {
                this.selectedIndex = (this.selectedIndex + 1) % this.options.length;
                changed = true;
            } else if (this.inputManager.isArrowUp()) {
                this.selectedIndex = (this.selectedIndex - 1 + this.options.length) % this.options.length;
                changed = true;
            }

            if (changed) {
            	this.gameContext.getSoundManager().play(SoundType.CLICK);
                this.selectorCooldown = this.SELECTOR_COOLDOWN; 
                float newY = this.baseY - this.selectedIndex * this.OPTION_SPACING;
                this.selector.setPosition(this.baseX - this.SELECTOR_OFFSET_X, newY);
            }
        }
        
        if (this.actionCooldown > 0) {
            this.actionCooldown--;
        } else if (this.inputManager.isEnter()) {
        	this.gameContext.getSoundManager().play(SoundType.ENTER);
            this.actionCooldown = this.ACTION_COOLDOWN;
            this.processOption(this.selectedIndex);
        }
    }


    protected void updateText(int index, String newText) {
        if (index >= 0 && index < this.options.length) {
            this.options[index].setText(newText);
        }
    }

    protected abstract void processOption(int optionIndex);

    @Override public void show() {}
    @Override public void resize(int width, int height) { camera.resize(width, height); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
