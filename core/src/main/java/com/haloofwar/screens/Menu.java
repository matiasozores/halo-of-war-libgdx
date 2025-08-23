package com.haloofwar.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.Background;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.NavigationEvent;
import com.haloofwar.events.SelectOptionEvent;
import com.haloofwar.screens.dependences.MenuNavigator;
import com.haloofwar.screens.dependences.MenuRenderer;
import com.haloofwar.utilities.Text;

public abstract class Menu implements Screen {
    protected final int SELECTOR_COOLDOWN = 35;
    protected final int ACTION_COOLDOWN = 30;
	private final int ESCAPE_COOLDOWN = 30;
	
    protected final GameContext context;
    private final Screen previousScreen;
    
    private final MenuRenderer renderer;
    protected final MenuNavigator navigator;

    private final Text[] OPTIONS;
    private final Text TITLE;
    private final Texture BACKGROUND;
    
    protected int actionCooldown = ACTION_COOLDOWN;
    private int escapeCooldown = this.ESCAPE_COOLDOWN;
    
    private final EventBus BUS;

    public Menu(GameContext context, String title, String[] optionTexts, Screen previousScreen) {
        this.context = context;
        this.previousScreen = previousScreen;

        this.renderer = new MenuRenderer();
        this.navigator = new MenuNavigator(optionTexts.length, SELECTOR_COOLDOWN);
        
        this.OPTIONS = new Text[optionTexts.length];
        for (int i = 0; i < optionTexts.length; i++) {
            this.OPTIONS[i] = new Text(optionTexts[i], context.getRender().getFont().getDefaultFont());
        }
        
        this.TITLE = new Text(title, context.getRender().getFont().getTitleFont());
        
        // A futuro podriamos hacer que algunos menu tengan un fondo distinto
        this.BACKGROUND = context.getTexture().get(Background.MAIN_MENU);
        
        this.BUS = context.getBus();
    }
    
    public Menu(GameContext gameContext, String title, String[] options) {
    	this(gameContext, title, options, null); 
    }

    @Override
    public void render(float delta) {
        this.update(delta);
        this.renderer.render(
    	    this.context.getRender().getBatch(),
    	    this.context.getRender().getShape(),
    	    this.BACKGROUND,
    	    this.TITLE,
    	    this.OPTIONS,
    	    this.navigator.getSelectedIndex()
    	);

    }

    protected void update(float delta) {
    	this.handleNavigation();
    	this.handleSelection();
    	this.handleBack();
    }

    private void handleNavigation() {
        this.navigator.updateCooldown();

        boolean moved = false;

        if (this.navigator.canMove()) {
            if (this.context.getInput().isArrowDown()) {
                this.navigator.moveDown();
                moved = true;
            } else if (this.context.getInput().isArrowUp()) {
                this.navigator.moveUp();
                moved = true;
            }
        }

        if (moved) {
        	this.BUS.publish(new NavigationEvent());
        }
    }

    private void handleSelection() {
        if (this.actionCooldown > 0) {
        	this.actionCooldown--;
            return;
        }

        if (this.context.getInput().isEnter()) {
        	this.BUS.publish(new SelectOptionEvent());
        	this.actionCooldown = this.ACTION_COOLDOWN;
        	processOption(this.navigator.getSelectedIndex());
        }
    }

    private void handleBack() {
        if (this.escapeCooldown > 0) {
            this.escapeCooldown--;
            return;
        }

        if (this.context.getInput().isEscape()) {
            this.goBack();
            this.escapeCooldown = this.ESCAPE_COOLDOWN;
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
    
    @Override
    public void resize(int width, int height) {
        this.renderer.resize(width, height);
    }

    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
