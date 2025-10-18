package com.haloofwar.ui.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.utils.Text;

public class MenuRenderer {
	
    private final GameStaticCamera camera = new GameStaticCamera();

    private final float baseX = 100, baseY = 525;
    private final int optionSpacing = 100;

    public void render(SpriteBatch batch, ShapeRenderer shape, Texture background, Text title) {
        this.camera.update();

        batch.setProjectionMatrix(this.camera.getOrthographic().combined);
        batch.begin();
        
        batch.draw(background, 0, 0, this.camera.getViewportWidth(), this.camera.getViewportHeight());
        this.drawTextWithOutline(title, batch, this.baseX, this.baseY + this.optionSpacing, Color.WHITE, Color.BLACK);

        batch.end();
    }

    private void drawTextWithOutline(Text text, SpriteBatch batch, float x, float y, Color textColor, Color outlineColor) {
        Color original = text.getColor();

        text.setColor(outlineColor);
        float offset = 2f; 
        text.draw(batch, x - offset, y);
        text.draw(batch, x + offset, y);
        text.draw(batch, x, y - offset);
        text.draw(batch, x, y + offset);
        text.draw(batch, x - offset, y - offset);
        text.draw(batch, x + offset, y + offset);
        text.draw(batch, x - offset, y + offset);
        text.draw(batch, x + offset, y - offset);

        text.setColor(textColor);
        text.draw(batch, x, y);

        text.setColor(original);
    }

    public void resize(int width, int height) {
        this.camera.resize(width, height);
    }
}
