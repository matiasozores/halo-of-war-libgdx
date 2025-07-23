package com.haloofwar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameHudCamera;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.utilities.GameContext;
import com.haloofwar.utilities.Text;

public class HUD {
    private GameHudCamera hudCamera;
    private Player player;
    
    private Text hpText;
    private Text nameText;

    private static final int PADDING = 20;

    public HUD(GameContext gameContext, Player player) {
        this.hudCamera = new GameHudCamera();
        this.player = player;
        this.hpText = new Text("HP: ");
        this.nameText = new Text("Player");
    }

    public void render(SpriteBatch batch) {
        this.hudCamera.update();
        batch.setProjectionMatrix(this.hudCamera.getCamera().combined);
        batch.begin();

        hpText.setText("HP: " + this.player.getHealth() + " / " + this.player.getDEFAULT_HEALTH());
        nameText.setText(this.player.getName());

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        hpText.draw(batch, PADDING, screenHeight - PADDING);
        nameText.draw(batch, screenWidth - nameText.getWidth() - PADDING, screenHeight - PADDING);
        batch.end();
    }
    
    public void update() {
	}
}
