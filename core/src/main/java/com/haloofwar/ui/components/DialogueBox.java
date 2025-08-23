package com.haloofwar.ui.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.utilities.GameConfig;

public class DialogueBox {
    private final SpriteBatch batch;
    private final Texture texture;
    private final BitmapFont font;

    private boolean active = false;
    private String currentLine = "";

    private final float x, y, width, height;
    
    private final int offsetX = 25;
    private final int offsetY = 10;
    
    private Texture characterTexture;  
    private final int avatarSize = 100; 
    
    public DialogueBox(SpriteBatch batch, Texture texture, BitmapFont font) {
        this.batch = batch;
        this.texture = texture;
        this.font = font;
        this.x = 0;
        this.y = 0;
        this.width = GameConfig.WINDOW_WIDTH;
        this.height = 120;
    }

    public void show(String text) {
        this.currentLine = text;
        this.active = true;
    }
    
    public void show(String text, Texture characterTexture) {
        this.currentLine = text;
        this.characterTexture = characterTexture;
        this.active = true;
    }

    public void hide() {
        this.active = false;
        this.currentLine = "";
        this.characterTexture = null;
    }

    public boolean isActive() {
        return this.active;
    }

    public void render() {
        if (!active) {
            return;
        }

        this.batch.draw(this.texture, this.x, this.y, this.width, this.height);

        if (this.characterTexture != null) {
            float avatarX = this.x + this.offsetX;
            float avatarY = this.y + this.offsetY;  
            this.batch.draw(this.characterTexture, avatarX, avatarY, avatarSize, avatarSize);
        }

        this.font.setColor(0, 0, 0, 1);
        this.font.draw(this.batch, this.currentLine, this.x + this.avatarSize + this.offsetX * 2, y + height - this.offsetY);
    }
}
