package com.haloofwar.cameras;

import com.haloofwar.entities.characters.Player;
import com.haloofwar.game.MapMetaData;
import com.haloofwar.utilities.GameConfig;

public class GameWorldCamera extends GameCamera {
    private Player player;
    private MapMetaData map;

    public GameWorldCamera() {
        super(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT); 
    }
    
    public void configure(Player player, MapMetaData map) {
        this.player = player;
        this.map = map;
    }

    @Override
    public void update() {
        this.camera.zoom = 0.4f;

        float halfW = this.camera.viewportWidth * this.camera.zoom / 2f;
        float halfH = this.camera.viewportHeight * this.camera.zoom / 2f;

        float camX = Math.max(halfW, Math.min(this.player.getX(), this.map.getMapPixelWidth() - halfW));
        float camY = Math.max(halfH, Math.min(this.player.getY(), this.map.getMapPixelHeight() - halfH));

        this.camera.position.set(camX, camY, 0);
        this.camera.update();
    }
}
