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
    private final HaloOfWarPrincipal GAME;

    private final TextureManager TEXTURE;
    private final AudioManager AUDIO;
    private final RenderManager RENDER;

    private final InputManager INPUT;

    private final GameStaticCamera STATIC_CAMERA;
    private final GameWorldCamera WORLD_CAMERA;

    private final GameplayContext GAMEPLAY;
    private final FactoryCollection FACTORIES;
    private final SceneManager SCENE;

    private final AudioSystem AUDIO_SYSTEM;

    private final EventBus GLOBAL_BUS;
    private final EventBus GAMEPLAY_BUS;
    
    private final SaveManager SAVE;
    
    public GameContext(final HaloOfWarPrincipal GAME) {
        this.GAME = GAME;

        this.TEXTURE = new TextureManager();
        this.AUDIO = new AudioManager();
        this.RENDER = new RenderManager();

        
        
        this.GLOBAL_BUS = new EventBus();
        this.GAMEPLAY_BUS = new EventBus();
        
        this.SAVE = new SaveManager(this.GAMEPLAY_BUS);
        this.INPUT = new InputManager(this.GLOBAL_BUS, this.GAMEPLAY_BUS);
        
        this.STATIC_CAMERA = new GameStaticCamera();
        this.WORLD_CAMERA = new GameWorldCamera();

        this.GAMEPLAY = new GameplayContext(this.RENDER.getBatch(), this.TEXTURE, this.GAMEPLAY_BUS, this.WORLD_CAMERA);
        
        this.AUDIO_SYSTEM = new AudioSystem(this.AUDIO, this.GLOBAL_BUS, this.GAMEPLAY_BUS);

        this.FACTORIES = new FactoryCollection(this);
        this.SCENE = new SceneManager(this.FACTORIES.getSCENE_FACTORY());

    }

    public HaloOfWarPrincipal getGAME() {
        return this.GAME;
    }

    public TextureManager getTEXTURE() {
        return this.TEXTURE;
    }

    public AudioManager getAUDIO() {
        return this.AUDIO;
    }

    public RenderManager getRENDER() {
        return this.RENDER;
    }

    public InputManager getINPUT() {
        return this.INPUT;
    }

    public GameStaticCamera getSTATIC_CAMERA() {
        return this.STATIC_CAMERA;
    }

    public GameWorldCamera getWORLD_CAMERA() {
        return this.WORLD_CAMERA;
    }

    public GameplayContext getGAMEPLAY() {
        return this.GAMEPLAY;
    }

    public FactoryCollection getFACTORIES() {
        return this.FACTORIES;
    }

    public SceneManager getSCENE() {
        return this.SCENE;
    }

    public EventBus getGLOBAL_BUS() {
        return this.GLOBAL_BUS;
    }

    public AudioSystem getAUDIO_SYSTEM() {
        return this.AUDIO_SYSTEM;
    }
    
    public SaveManager getSAVE() {
		return this.SAVE;
	}

    public void resetGameplay() {
        if (this.GAMEPLAY != null) {
            this.GAMEPLAY.dispose(); 
            this.SCENE.clear();
            this.AUDIO_SYSTEM.subscribeEvents(this.GAMEPLAY_BUS);
        }
    }
    
    public void dispose() {
        this.resetGameplay();
        this.RENDER.dispose();
        this.TEXTURE.dispose();
        this.AUDIO.dispose();
    }
}
