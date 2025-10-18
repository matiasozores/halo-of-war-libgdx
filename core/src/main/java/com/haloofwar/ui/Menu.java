package com.haloofwar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.StartGameEvent;
import com.haloofwar.engine.events.online.StartGameEventOnline;
import com.haloofwar.engine.utils.RandomUtils;
import com.haloofwar.engine.utils.Text;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.launcher.GameInitializer;
import com.haloofwar.ui.menus.MenuRenderer;

public abstract class Menu implements Screen {
    protected final GameContext context;
    
    private final MenuRenderer renderer;

    private final Text title;
    private final Texture background;
   
    private final EventBus globalBus;
	private final EventListenerManager listenerManager = new EventListenerManager();
    
    public Menu(
		final GameContext context, 
		final String title, 
		final Background background
    ) {
        this.context = context;
        this.renderer = new MenuRenderer();
        this.title = new Text(title, context.getRENDER().getFont().getTitleFont());
        this.background = context.getTEXTURE().get(background);
        this.globalBus = context.getGlobalBus();
    }
    
    private void subscribeEvents() {
        this.listenerManager.add(this.globalBus, StartGameEvent.class, this::onStartGameEvent);
    }
    
    private void onStartGameEvent(StartGameEvent event) {
    	Gdx.app.postRunnable(() -> {
	    	Entity kratos = this.context.getFACTORIES().getPLAYER_FACTORY().create(RandomUtils.generateUniqueId(), PlayerType.KRATOS);
	    	Entity masterchief = this.context.getFACTORIES().getPLAYER_FACTORY().create(RandomUtils.generateUniqueId(), PlayerType.MASTER_CHIEF);
			GameInitializer.initializeGameplay(context, kratos, masterchief);
			
			final int kratosId = kratos.getComponent(TransformComponent.class).identifier;
			final int masterchiefId = masterchief.getComponent(TransformComponent.class).identifier;
			
			this.context.getGAMEPLAY().getBus().publish(new StartGameEventOnline(kratosId, masterchiefId));
    	});
	}

    @Override
    public void render(float delta) {
        this.renderer.render(
    	    this.context.getRENDER().getBatch(),
    	    this.context.getRENDER().getShape(),
    	    this.background,
    	    this.title
    	);

    }

    @Override
    public void resize(int width, int height) {
        this.renderer.resize(width, height);
    }

    @Override public void show() {
        this.subscribeEvents();
    }
   
    @Override public void hide() {
    	this.listenerManager.clear();
    }
    @Override public void dispose() {
    	this.listenerManager.clear();
    }
    
    @Override public void pause() {}
    @Override public void resume() {}
}
