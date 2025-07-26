package com.haloofwar.utilities.text;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Text {
    private String text;
    private final BitmapFont font;
    private final GlyphLayout layout;

    public Text(String text, BitmapFont font) {
        this.text = text;
        this.font = font;
        this.layout = new GlyphLayout();
    }

    public void draw(SpriteBatch batch, float x, float y) {
        font.draw(batch, text, x, y);
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
}
