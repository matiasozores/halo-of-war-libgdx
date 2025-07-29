package com.haloofwar.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.utilities.text.Text;

public class HUD {
	private Player player;
    private Text hpText;
    private Text nameText;
    
    // Dependencias
    private GameStaticCamera camera;
    private SpriteBatch batch;
    
    public HUD(GameContext context, Player player) {
        this.player = player;
        this.hpText = new Text("HP: ", context.getFontManager().getTitleFont());
        this.nameText = new Text("Player", context.getFontManager().getTitleFont());
        
        this.camera = new GameStaticCamera();
        this.batch = context.getBatch();
    }

    public void render() {
        
        this.batch.setProjectionMatrix(this.camera.getCamera().combined);
        this.batch.begin();

        
        this.hpText.setText("HP: " + this.player.getHealth() + " / " + this.player.getDEFAULT_HEALTH());
        this.nameText.setText(this.player.getName());

        float viewportWidth = this.camera.getViewport().getWorldWidth();
        float viewportHeight = this.camera.getViewport().getWorldHeight();

        float padding = viewportHeight * 0.02f;

        this.hpText.draw(this.batch, padding, viewportHeight - padding);
        this.nameText.draw(this.batch, viewportWidth - nameText.getWidth() - padding, viewportHeight - padding);

        this.batch.end();
    }


    public void update() {
    	this.camera.update();
    }

    public GameStaticCamera getCamera() {
        return this.camera;
    }

    public void resize(int width, int height) {
        this.camera.resize(width, height);
    }
}
