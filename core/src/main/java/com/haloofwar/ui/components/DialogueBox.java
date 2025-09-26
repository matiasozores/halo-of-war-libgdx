package com.haloofwar.ui.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.ShowDialogueEvent;
import com.haloofwar.engine.events.HideDialogueEvent;
import com.haloofwar.utils.GameConfig;

public class DialogueBox implements HUDComponent {
    private final SpriteBatch batch;
    private final Texture texture;
    private final BitmapFont font;
    private final EventListenerManager listenerManager = new EventListenerManager();

    private boolean active = false;
    private String currentLine = "";

    private final float x, y, width, height;
    private final int offsetX = 20;
    private final int offsetY = 10;

    private Texture characterTexture;  
    private final int avatarSize = 100; 

    public DialogueBox(SpriteBatch batch, Texture texture, BitmapFont font, EventBus gameplayBus) {
        this.batch = batch;
        this.texture = texture;
        this.font = font;
        this.x = 0;
        this.y = 0;
        this.width = GameConfig.WINDOW_WIDTH;
        this.height = 120;

        this.listenerManager.add(gameplayBus, ShowDialogueEvent.class, this::onShowDialogue);
        this.listenerManager.add(gameplayBus, HideDialogueEvent.class, this::onHideDialogue);
    }

    private void onShowDialogue(ShowDialogueEvent event) {
        this.currentLine = event.getText();
        this.characterTexture = event.getAvatar();
        this.active = true;
    }

    private void onHideDialogue(HideDialogueEvent event) {
        this.active = false;
        this.currentLine = "";
        this.characterTexture = null;
    }

    public boolean isActive() {
        return this.active;
    }

    @Override
    public void render() {
        if (!active) return;

        this.batch.draw(this.texture, this.x, this.y, this.width, this.height);

        if (this.characterTexture != null) {
            float avatarX = this.x + this.offsetX;
            float avatarY = this.y + this.offsetY;  
            this.batch.draw(this.characterTexture, avatarX, avatarY, avatarSize, avatarSize);
        }

        this.font.setColor(0, 0, 0, 1);
        this.font.draw(this.batch, this.currentLine,
            this.x + this.avatarSize + this.offsetX * 2,
            y + height - this.offsetY
        );
    }

    @Override
    public void refresh(Entity player) {}

    @Override
    public void dispose() {
        this.listenerManager.clear();
    }
}
