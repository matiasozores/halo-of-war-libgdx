package com.haloofwar.common.context;

import com.haloofwar.common.managers.RenderManager;
import com.haloofwar.common.managers.SceneManager;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.cameras.GameWorldCamera;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.game.config.FactoryCollection;
import com.haloofwar.launcher.HaloOfWarPrincipal;

public class GameContext {
    private final HaloOfWarPrincipal game;

    private final TextureManager texture;
    private final RenderManager render;

    private final GameStaticCamera staticCamera;
    private final GameWorldCamera worldCamera;

    private final GameplayContext gameplay;
    private final FactoryCollection factories;
    private final SceneManager sceneManager;

    private final EventBus globalBus;
    private final EventBus gameplayBus;

    public GameContext(final HaloOfWarPrincipal GAME) {
        this.game = GAME;

        this.texture = new TextureManager();
        this.render = new RenderManager();

        this.globalBus = new EventBus();
        this.gameplayBus = new EventBus();
        
        this.staticCamera = new GameStaticCamera();
        this.worldCamera = new GameWorldCamera();

        this.gameplay = new GameplayContext(this.render.getBatch(), this.texture, this.gameplayBus);
        
        this.factories = new FactoryCollection(this);
        this.sceneManager = new SceneManager(this.factories.getSCENE_FACTORY());

    }

    public HaloOfWarPrincipal getGAME() {
        return this.game;
    }

    public TextureManager getTEXTURE() {
        return this.texture;
    }
    
    public RenderManager getRENDER() {
        return this.render;
    }

    public GameStaticCamera getSTATIC_CAMERA() {
        return this.staticCamera;
    }

    public GameWorldCamera getWORLD_CAMERA() {
        return this.worldCamera;
    }

    public GameplayContext getGAMEPLAY() {
        return this.gameplay;
    }

    public FactoryCollection getFACTORIES() {
        return this.factories;
    }

    public SceneManager getSCENE() {
        return this.sceneManager;
    }

    public EventBus getGlobalBus() {
        return this.globalBus;
    }

    public void resetGameplay() {
        if (this.gameplay != null) {
            this.sceneManager.clear();
            this.gameplay.dispose(); 
        }
    }
    
    public void dispose() {
        this.resetGameplay();
        this.render.dispose();
        this.texture.dispose();
    }
}
