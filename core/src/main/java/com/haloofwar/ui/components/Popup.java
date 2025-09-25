package com.haloofwar.ui.components;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enums.Direction;
import com.haloofwar.common.enums.SoundType;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PlaySoundEvent;

public abstract class Popup {

    protected boolean visible;
    protected final BitmapFont FONT;
    protected final Texture BACKGROUND;
    protected final EventBus GAMEPLAY_BUS;
    
    protected int maxVisibleItems = 3;  // cuántos items mostrar a la vez
    protected int scrollIndex = 0;      // primer item visible
    protected int selectedIndex = 0;    // índice absoluto del item seleccionado

    public ArrayList<Entity> ENTITIES;

    public Popup(final EventBus GAMEPLAY_BUS, final BitmapFont FONT, final Texture BACKGROUND, ArrayList<Entity> ENTITIES) {
        this.visible = false;
        this.FONT = FONT;
        this.GAMEPLAY_BUS = GAMEPLAY_BUS;
        this.BACKGROUND = BACKGROUND;
        this.ENTITIES = ENTITIES;
    }

    public void render(final SpriteBatch BATCH, GameStaticCamera camera) {
        if (!this.visible) {
        	return;
        }

        BATCH.draw(this.BACKGROUND, 0, 0, 1280, 720);

        int startX = 185;
        int startY = 500;
        int spacingY = 195;

        int endIndex = Math.min(this.scrollIndex + this.maxVisibleItems, this.ENTITIES.size());

        for (int i = 0, idx = this.scrollIndex; idx < endIndex; i++, idx++) {
            Entity item = this.ENTITIES.get(idx);
            boolean isSelected = (idx == this.selectedIndex);
            this.drawEntity(BATCH, item, startX, startY - i * spacingY, isSelected);
        }
    }

    protected abstract void drawEntity(final SpriteBatch BATCH, final Entity ENTITY, final float X, final float Y, final boolean IS_SELECTED);

    public void navigate(final Direction DIRECTION) {
        if (!this.visible || this.ENTITIES.isEmpty()) {
        	return;
        }

        switch (DIRECTION) {
            case UP -> this.moveUp();
            case DOWN -> this.moveDown();
            case LEFT -> this.moveUp();
            case RIGHT -> this.moveDown();
        }
        
        this.GAMEPLAY_BUS.publish(new PlaySoundEvent(SoundType.NAVIGATE_GAME));
    }

    private void moveUp() {
        if (this.selectedIndex > 0) {
            this.selectedIndex--;
            
            if (this.selectedIndex < this.scrollIndex) {
                this.scrollIndex = this.selectedIndex;
            }
        }
    }

    private void moveDown() {
        if (this.selectedIndex < this.ENTITIES.size() - 1) {
            this.selectedIndex++;
            if (this.selectedIndex >= this.scrollIndex + this.maxVisibleItems) {
                this.scrollIndex = this.selectedIndex - this.maxVisibleItems + 1;
            }
        }
    }

    public void setVisible(boolean visible) { this.visible = visible; }
    public boolean isVisible() { return this.visible; }
    public Entity getSelectedEntity() { return ENTITIES.isEmpty() ? null : this.ENTITIES.get(this.selectedIndex); }}
