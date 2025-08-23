package com.haloofwar.utilities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
    private String text;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private Color color;

    public Text(String text, BitmapFont font) {
        this.text = text;
        this.font = font;
        this.layout = new GlyphLayout();
        this.color = Color.WHITE; 
    }

    public void draw(SpriteBatch batch, float x, float y) {
        Color oldColor = font.getColor();
        font.setColor(color);
        font.draw(batch, text, x, y);
        font.setColor(oldColor); 
    }

    public void setText(String text) {
        this.text = text;
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
}
