package com.haloofwar.ui.hud;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.players.Player;
import com.haloofwar.enumerators.entities.PlayerType;

public class HUD {
    private final GameStaticCamera camera;
    private final SpriteBatch batch;
    private final ShapeRenderer shape;
    
    private final HealthBar healthBar;
    private final PlayerInfoRenderer playerInfoRenderer;
    private final InventoryRenderer inventoryRenderer;
    
    public HUD(GameContext context, Player player, PlayerType type) {
        this.camera = new GameStaticCamera();
        this.batch = context.getRender().getBatch();
        this.shape = new ShapeRenderer();
        
        this.healthBar = new HealthBar(this.shape, player, this.camera);
        this.playerInfoRenderer = new PlayerInfoRenderer(this.batch, context, player);
        this.inventoryRenderer = new InventoryRenderer(this.batch, player, context.getRender().getFont());
    }
    
    public void render() {
        this.camera.update();
        this.healthBar.render();

        this.batch.setProjectionMatrix(this.camera.getOrthographic().combined);
        this.batch.begin();
        this.playerInfoRenderer.render();
        this.inventoryRenderer.render();
        this.batch.end();
    }
    
    public GameStaticCamera getCamera() {
		return this.camera;
	}
    
    public void resize(int width, int height) {
        this.camera.resize(width, height);
    }

}
