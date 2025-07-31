package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.SoundType;
import com.haloofwar.utilities.Figure;
import com.haloofwar.utilities.text.Text;

public abstract class Menu implements Screen {
    protected final int SELECTOR_COOLDOWN = 35;
    protected final int ACTION_COOLDOWN = 30;
    private final int OPTION_SPACING = 100;
    private final int SELECTOR_OFFSET_X = 50;

    protected final GameContext context;
    private final GameStaticCamera camera = new GameStaticCamera();

    private final Figure selector = new Figure(10, 10);

    private final Text[] OPTIONS;
    private final Text TITLE;
    
    protected int selectedIndex = 0;
    protected int actionCooldown = ACTION_COOLDOWN;
    protected int selectorCooldown = SELECTOR_COOLDOWN;

    private float baseX = 100, baseY = 525;

    private final Screen previousScreen;

    public Menu(GameContext context, String title, String[] optionTexts, Screen previousScreen) {
        this.context = context;
        this.previousScreen = previousScreen;

        this.OPTIONS = new Text[optionTexts.length];
        for (int i = 0; i < optionTexts.length; i++) {
            this.OPTIONS[i] = new Text(optionTexts[i], context.getRender().getFont().getDefaultFont());
        }
        
        this.TITLE = new Text(title, context.getRender().getFont().getTitleFont());
    }
    
    public Menu(GameContext gameContext, String title, String[] options) {
    	this(gameContext, title, options, null); 
    }


    @Override
    public void render(float delta) {
        this.update();

        this.camera.update();
        this.context.getRender().getShape().setProjectionMatrix(this.camera.getOrthographic().combined);
        this.context.getRender().getShape().begin(ShapeRenderer.ShapeType.Filled);

        float selectorY = this.baseY - this.selectedIndex * this.OPTION_SPACING;
        float targetY = baseY - selectedIndex * OPTION_SPACING;
        selectorY += (targetY - selectorY) * 0.15f; // suaviza

        this.selector.draw(this.context.getRender().getShape(), this.baseX - this.SELECTOR_OFFSET_X, selectorY);

        this.context.getRender().getShape().end();

        this.context.getRender().getBatch().setProjectionMatrix(this.camera.getOrthographic().combined);
        this.context.getRender().getBatch().begin();
        
        // Título
        this.TITLE.draw(this.context.getRender().getBatch(), this.baseX, this.baseY + this.OPTION_SPACING);

        // Opciones
        for (int i = 0; i < this.OPTIONS.length; i++) {
            if (i == this.selectedIndex) {
                this.OPTIONS[i].setColor(Color.RED);
            } else {
                this.OPTIONS[i].setColor(Color.WHITE);
            }
            this.OPTIONS[i].draw(this.context.getRender().getBatch(), this.baseX, this.baseY - i * this.OPTION_SPACING);
        }

        this.context.getRender().getBatch().end();

    }

    protected void update() {
    	this.handleNavigation();
    	this.handleSelection();
    	this.handleBack();
    }

    private void handleNavigation() {
        if (this.selectorCooldown > 0) {
        	this.selectorCooldown--;
            return;
        }

        boolean moved = false;

        if (this.context.getInput().isArrowDown()) {
        	this.selectedIndex = (this.selectedIndex + 1) % this.OPTIONS.length;
            moved = true;
        } else if (this.context.getInput().isArrowUp()) {
        	this.selectedIndex = (this.selectedIndex - 1 + this.OPTIONS.length) % this.OPTIONS.length;
            moved = true;
        }

        if (moved) {
        	this.context.getAudio().getSound().play(SoundType.CLICK);
        	this.selectorCooldown = this.SELECTOR_COOLDOWN;
            float newY = this.baseY - this.selectedIndex * this.OPTION_SPACING;
            this.selector.setPosition(this.baseX - this.SELECTOR_OFFSET_X, newY);
        }
    }

    private void handleSelection() {
        if (this.actionCooldown > 0) {
        	this.actionCooldown--;
            return;
        }

        if (this.context.getInput().isEnter()) {
        	this.context.getAudio().getSound().play(SoundType.ENTER);
        	this.actionCooldown = this.ACTION_COOLDOWN;
            processOption(this.selectedIndex);
        }
    }

    private void handleBack() {
        if (this.context.getInput().isEscape()) {
            goBack();
        }
    }

    protected void goBack() {
        if (this.previousScreen != null) {
        	this.context.getGame().setScreen(this.previousScreen);
        }
    }

    protected void updateText(int index, String newText) {
        if (index >= 0 && index < this.OPTIONS.length) {
        	this.OPTIONS[index].setText(newText);
        }
    }

    protected abstract void processOption(int optionIndex);

    @Override public void show() {}
    @Override public void resize(int width, int height) { this.camera.resize(width, height); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
