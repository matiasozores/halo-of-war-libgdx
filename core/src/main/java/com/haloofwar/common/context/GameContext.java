package com.haloofwar.common.context;

import com.haloofwar.common.managers.AudioManager;
import com.haloofwar.common.managers.InputManager;
import com.haloofwar.common.managers.RenderManager;
import com.haloofwar.common.managers.SaveManager;
import com.haloofwar.common.managers.SceneManager;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.game.config.FactoryCollection;
import com.haloofwar.game.systems.AudioSystem;
import com.haloofwar.launcher.HaloOfWarPrincipal;

public class GameContext {
    private final HaloOfWarPrincipal game;

    private final TextureManager texture;
    private final AudioManager audio;
    private final RenderManager render;

    private final InputManager input;

    private final GameStaticCamera staticCamera;
    private final GameWorldCamera worldCamera;

    private final GameplayContext gameplay;
    private final FactoryCollection factories;
    private final SceneManager scene;

    private final AudioSystem audioSystem;

    private final EventBus globalBus;
    private final EventBus gameplayBus;
    
    private final SaveManager saveManager;
    
    public GameContext(final HaloOfWarPrincipal GAME) {
        this.game = GAME;

        this.texture = new TextureManager();
        this.audio = new AudioManager();
        this.render = new RenderManager();
        
        this.globalBus = new EventBus();
        this.gameplayBus = new EventBus();
        
        this.saveManager = new SaveManager(this.gameplayBus);
        this.input = new InputManager(this.globalBus, this.gameplayBus);
        
        this.staticCamera = new GameStaticCamera();
        this.worldCamera = new GameWorldCamera();

        this.gameplay = new GameplayContext(this.render.getBatch(), this.texture, this.gameplayBus, this.worldCamera);
        
        this.audioSystem = new AudioSystem(this.audio, this.globalBus, this.gameplayBus);

        this.factories = new FactoryCollection(this);
        this.scene = new SceneManager(this.factories.getSCENE_FACTORY());

    }

    public HaloOfWarPrincipal getGAME() {
        return this.game;
    }

    public TextureManager getTEXTURE() {
        return this.texture;
    }

    public AudioManager getAudio() {
        return this.audio;
    }

    public RenderManager getRender() {
        return this.render;
    }

    public InputManager getInput() {
        return this.input;
    }

    public GameStaticCamera getStaticCamera() {
        return this.staticCamera;
    }

    public GameWorldCamera getWorldCamera() {
        return this.worldCamera;
    }

    public GameplayContext getGameplay() {
        return this.gameplay;
    }

    public FactoryCollection getFactories() {
        return this.factories;
    }

    public SceneManager getScene() {
        return this.scene;
    }

    public EventBus getGlobalBus() {
        return this.globalBus;
    }

    public AudioSystem getAudioSystem() {
        return this.audioSystem;
    }
    
    public SaveManager getSaveManager() {
		return this.saveManager;
	}

    public void resetGameplay() {
        if (this.gameplay != null) {
            this.saveManager.dispose();
            this.scene.clear();
            this.gameplay.dispose(); 
            this.audioSystem.dispose();
            this.audioSystem.subscribeEvents(this.globalBus, this.gameplayBus);
        }
    }
    
    public void dispose() {
        this.resetGameplay();
        this.render.dispose();
        this.texture.dispose();
        this.audio.dispose();
        this.audioSystem.dispose();
    }
}
