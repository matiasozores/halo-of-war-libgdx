package com.haloofwar.ui.menus;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enums.Background;
import com.haloofwar.common.enums.Direction;
import com.haloofwar.common.enums.SoundType;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.NavigationEvent;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.engine.events.SelectOptionEvent;
import com.haloofwar.engine.utils.Text;

public abstract class Menu implements Screen {
    private final float DEFAULT_SELECTOR_COOLDOWN = 0.15f;
    private final float DEFAULT_ACTION_COOLDOWN = 0.3f;
	
    protected final GameContext context;
    private final Screen previousScreen;
    
    private final MenuRenderer renderer;
    protected final MenuNavigator navigator;

    private final Text[] options;
    private final Text title;
    private final Texture background;
    
    private float actionCooldown = DEFAULT_ACTION_COOLDOWN;

    private boolean upFlag = false;
    private boolean downFlag = false;
    private boolean enterFlag = false;
    
    private final EventBus globalBus;
    
    public Menu(
		final GameContext context, 
		final String title, 
		final String[] options, 
		final Screen previousScreen, 
		final Background background
    ) {
        this.context = context;
        this.previousScreen = previousScreen;

        this.renderer = new MenuRenderer();
        this.navigator = new MenuNavigator(options.length, this.DEFAULT_SELECTOR_COOLDOWN);
        
        this.options = new Text[options.length];
        for (int i = 0; i < options.length; i++) {
            this.options[i] = new Text(options[i], context.getRENDER().getFont().getDefaultFont());
        }
        
        this.title = new Text(title, context.getRENDER().getFont().getTitleFont());
        
        this.background = context.getTEXTURE().get(background);
        
        this.globalBus = context.getGLOBAL_BUS();
        this.subscribeEvents();
    }
    
    public Menu(GameContext gameContext, String title, String[] options, final Background BACKGROUND) {
    	this(gameContext, title, options, null, BACKGROUND); 
    }
    
    private void subscribeEvents() {
    	 this.globalBus.subscribe(NavigationEvent.class, this::onNavigationEvent);
         this.globalBus.subscribe(SelectOptionEvent.class, this::onSelectOptionEvent);
    }
    
    protected void onNavigationEvent(NavigationEvent event) {
    	if(event.direction.equals(Direction.UP)) {
    		this.upFlag = event.isPressed;
    	} else if(event.direction.equals(Direction.DOWN)) {
    		this.downFlag = event.isPressed;
    	}	
    }
    
    private void onSelectOptionEvent(SelectOptionEvent event) {
    	this.enterFlag = event.isPressed;
    }

    @Override
    public void render(float delta) {
        this.update(delta);
        this.renderer.render(
    	    this.context.getRENDER().getBatch(),
    	    this.context.getRENDER().getShape(),
    	    this.background,
    	    this.title,
    	    this.options,
    	    this.navigator.getSelectedIndex()
    	);

    }

    protected void update(float delta) {
    	this.handleNavigation(delta);
    	this.handleSelection(delta);
    }

    private void handleNavigation(float delta) {
        this.navigator.updateCooldown(delta);
        boolean moved = false;

        if (this.navigator.canMove()) {
            if (this.downFlag) {
                this.navigator.moveDown();
                moved = true;
            } else if (this.upFlag) {
                this.navigator.moveUp();
                moved = true;
            }
        }

        if (moved) {
            this.globalBus.publish(new PlaySoundEvent(SoundType.NAVIGATE_MENU));
        }
    }


    private void handleSelection(float delta) {
        if (this.actionCooldown > 0) {
            this.actionCooldown -= delta;
            return;
        }

        if (this.enterFlag) {
            this.globalBus.publish(new PlaySoundEvent(SoundType.ENTER_MENU));
            this.actionCooldown = this.DEFAULT_ACTION_COOLDOWN;
            processOption(this.navigator.getSelectedIndex());
        }
    }

    protected void goBack() {
        if (this.previousScreen != null) {
        	this.context.getGAME().setScreen(this.previousScreen);
        }
    }

    protected void updateText(int index, String newText) {
        if (index >= 0 && index < this.options.length) {
        	this.options[index].setText(newText);
        }
    }

    protected abstract void processOption(int optionIndex);

    @Override
    public void resize(int width, int height) {
        this.renderer.resize(width, height);
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
