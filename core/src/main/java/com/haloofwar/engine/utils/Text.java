package com.haloofwar.engine.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
    private String text;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private Color color;

    // Posición del texto
    private float x;
    private float y;

    public Text(String text, BitmapFont font) {
        this.text = text;
        this.font = font;
        this.layout = new GlyphLayout();
        this.color = Color.WHITE; 
        this.x = 0;
        this.y = 0;
    }

    // Dibuja usando la posición guardada
    public void draw(SpriteBatch batch) {
        draw(batch, this.x, this.y);
    }

    // Dibuja en coordenadas específicas
    public void draw(SpriteBatch batch, float x, float y) {
        Color oldColor = font.getColor();
        font.setColor(color);
        font.draw(batch, text, x, y);
        font.setColor(oldColor); 
    }

    public void setText(String text) {
        this.text = text;
    }
    
    public String getText() {
		return text;
	}

    public float getWidth() {
        layout.setText(font, text);
        return layout.width;
    }

    public void setScale(float scale) {
        font.getData().setScale(scale);
    }

    public void resetScale() {
        font.getData().setScale(1f);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    // Nueva función para posicionar el texto
    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() { return x; }
    public float getY() { return y; }
}
