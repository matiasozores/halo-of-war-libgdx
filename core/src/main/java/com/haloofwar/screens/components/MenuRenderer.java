package com.haloofwar.screens.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.utilities.Figure;
import com.haloofwar.utilities.text.Text;

public class MenuRenderer {
    private final Figure selector;
    private final GameStaticCamera camera = new GameStaticCamera();

    private final float baseX = 100, baseY = 525;
    private final int optionSpacing = 100;
    private final int selectorOffsetX = 50;

    public MenuRenderer() {
        this.selector = new Figure(10, 10);
    }

    public void render(SpriteBatch batch, ShapeRenderer shape, Texture background, Text title, Text[] options, int selectedIndex) {
        this.camera.update();

        // Dibujar selector
        shape.setProjectionMatrix(this.camera.getOrthographic().combined);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        float selectorY = this.baseY - selectedIndex * this.optionSpacing;
        this.selector.draw(shape, this.baseX - this.selectorOffsetX, selectorY);
        shape.end();

        // Dibujar fondo, título y opciones
        batch.setProjectionMatrix(this.camera.getOrthographic().combined);
        batch.begin();

        batch.draw(background, 0, 0, this.camera.getViewportWidth(), this.camera.getViewportHeight());

        title.draw(batch, this.baseX, this.baseY + this.optionSpacing);

        for (int i = 0; i < options.length; i++) {
            options[i].setColor(i == selectedIndex ? Color.RED : Color.WHITE);
            options[i].draw(batch, this.baseX, this.baseY - i * this.optionSpacing);
        }

        batch.end();
    }
    
    public void resize(int width, int height) {
        this.camera.resize(width, height);
    }
}
