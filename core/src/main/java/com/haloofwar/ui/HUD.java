package com.haloofwar.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.utilities.Resources;
import com.haloofwar.utilities.Text;

public class HUD {
    private SpriteBatch batch;
    private OrthographicCamera hudCam;

    private Text hpText;
    private Text nameText;

    private static final int PADDING = 20;
    private static final int FONT_SIZE = 24;
    private static final Color FONT_COLOR = Color.WHITE;

    public HUD() {
        this.batch = Resources.getBatchHUD();
        this.hudCam = new OrthographicCamera();
        this.hudCam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        this.hpText = new Text("HP: ", FONT_SIZE);
        this.nameText = new Text("Player", FONT_SIZE);
    }

    public void render(Player player) {
        this.hudCam.update();
        this.batch.setProjectionMatrix(this.hudCam.combined);
        this.batch.begin();

        hpText.setText("HP: " + player.getHealth() + " / " + player.getDEFAULT_HEALTH());
        nameText.setText(player.getName());

        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        hpText.draw(batch, PADDING, screenHeight - PADDING);
        nameText.draw(batch, screenWidth - nameText.getWidth() - PADDING, screenHeight - PADDING);

        this.batch.end();
    }
}
