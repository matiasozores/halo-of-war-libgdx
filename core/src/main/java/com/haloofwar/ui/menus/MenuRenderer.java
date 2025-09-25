package com.haloofwar.ui.menus;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.utils.Text;
import com.haloofwar.utils.Figure;

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

        // Título con contorno
        drawTextWithOutline(title, batch, this.baseX, this.baseY + this.optionSpacing, Color.WHITE, Color.BLACK);

        // Opciones con contorno
        for (int i = 0; i < options.length; i++) {
            Color textColor = (i == selectedIndex ? Color.RED : Color.WHITE);
            drawTextWithOutline(options[i], batch, this.baseX, this.baseY - i * this.optionSpacing, textColor, Color.BLACK);
        }

        batch.end();
    }

    private void drawTextWithOutline(Text text, SpriteBatch batch, float x, float y, Color textColor, Color outlineColor) {
        // Guardar color original
        Color original = text.getColor();

        // Dibujar contorno en 8 direcciones
        text.setColor(outlineColor);
        float offset = 2f; // grosor del contorno
        text.draw(batch, x - offset, y);
        text.draw(batch, x + offset, y);
        text.draw(batch, x, y - offset);
        text.draw(batch, x, y + offset);
        text.draw(batch, x - offset, y - offset);
        text.draw(batch, x + offset, y + offset);
        text.draw(batch, x - offset, y + offset);
        text.draw(batch, x + offset, y - offset);

        // Dibujar texto principal encima
        text.setColor(textColor);
        text.draw(batch, x, y);

        // Restaurar color original
        text.setColor(original);
    }

    public void resize(int width, int height) {
        this.camera.resize(width, height);
    }
}
