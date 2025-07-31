package com.haloofwar.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public class Image {
    private Texture texture;
    private Vector2 position;
    private Vector2 scale;
    private float rotation;
    private float alpha; // Opacidad
    private boolean visible;

    public Image(Texture texture, float x, float y) {
        this.texture = texture;
        this.position = new Vector2(x, y);
        this.scale = new Vector2(1f, 1f);
        this.rotation = 0f;
        this.alpha = 1f;
        this.visible = true;
    }

    public void draw(Batch batch) {
        if (!visible) return;

        Color oldColor = batch.getColor();
        batch.setColor(1f, 1f, 1f, alpha);

        batch.draw(
            texture,
            position.x, position.y,
            texture.getWidth() / 2f, texture.getHeight() / 2f, // Origen (para rotar)
            texture.getWidth(), texture.getHeight(),
            scale.x, scale.y,
            rotation,
            0, 0,
            texture.getWidth(), texture.getHeight(),
            false, false
        );

        batch.setColor(oldColor); // Restaurar color original
    }

    // -------- Setters --------
    public void setPosition(float x, float y) {
        this.position.set(x, y);
    }

    public void setScale(float scaleX, float scaleY) {
        this.scale.set(scaleX, scaleY);
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setAlpha(float alpha) {
        this.alpha = Math.max(0f, Math.min(1f, alpha)); // Clamp
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public void centerAndFitToScreen() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        float texWidth = texture.getWidth();
        float texHeight = texture.getHeight();

        float scaleX = screenWidth / texWidth;
        float scaleY = screenHeight / texHeight;

        // Para mantener proporción sin deformar:
        float scale = Math.max(scaleX, scaleY); // Usa `min` si querés que no se recorte

        setScale(scale, scale);

        float newWidth = texWidth * scale;
        float newHeight = texHeight * scale;

        float x = (screenWidth - newWidth) / 2f;
        float y = (screenHeight - newHeight) / 2f;

        setPosition(x, y);
    }


    // -------- Getters --------
    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

    public float getAlpha() {
        return alpha;
    }

    public boolean isVisible() {
        return visible;
    }

    public Texture getTexture() {
        return texture;
    }
    
    
}
