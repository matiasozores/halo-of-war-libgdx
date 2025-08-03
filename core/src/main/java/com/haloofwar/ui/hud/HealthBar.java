package com.haloofwar.ui.hud;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.entities.players.Player;

public class HealthBar {
    private final ShapeRenderer shape;
    private final Player player;
    
    private final float x = 100;
    private final float y = 640;
    private final float width = 200;
    private final float height = 25;
    private final GameStaticCamera camera;
    
    public HealthBar(ShapeRenderer shape, Player player, GameStaticCamera camera) {
        this.shape = shape;
        this.player = player;
        this.camera = camera;
    }
    
    public void render() {
        float currentHp = player.getHealth();
        float maxHp = 100f;
        float hpPercentage = Math.max(0, currentHp / maxHp);
        
        this.shape.setProjectionMatrix(this.camera.getOrthographic().combined);
        this.shape.begin(ShapeRenderer.ShapeType.Filled);
        this.shape.setColor(Color.DARK_GRAY);
        this.shape.rect(x, y, width, height);
        this.shape.setColor(Color.GREEN);
        this.shape.rect(x, y, width * hpPercentage, height);
        this.shape.end();
    }
}
