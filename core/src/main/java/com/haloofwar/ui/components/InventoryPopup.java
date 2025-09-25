package com.haloofwar.ui.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.haloofwar.common.enums.Background;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.components.NameComponent;
import com.haloofwar.game.components.StockComponent;

public class InventoryPopup extends Popup {
    private final TextureManager TEXTURE;

    public InventoryPopup(final EventBus GAMEPLAY_BUS, final TextureManager TEXTURE, final BitmapFont FONT, final InventoryComponent INVENTORY) {
        super(GAMEPLAY_BUS, FONT, TEXTURE.get(Background.INVENTORY), INVENTORY.getObjects());	
        this.TEXTURE = TEXTURE;
    }

    @Override
    protected void drawEntity(final SpriteBatch BATCH, final Entity ITEM, final float X, final float Y, final boolean IS_SELECTED) {
        Entity entity = (Entity) ITEM;

        String name = entity.getComponent(NameComponent.class).name;
        StockComponent stockComponent = entity.getComponent(StockComponent.class);
        int stock = stockComponent.getStock();
        Texture icon = this.TEXTURE.get(stockComponent.getType());
        String description = stockComponent.getType().getDescription();
        
        BATCH.draw(icon, X, Y, 64, 64);

        float nameX = X + 108;
        float nameY = Y + 80;

        GlyphLayout nameLayout = new GlyphLayout(this.FONT, name);
        
        if(IS_SELECTED) {
        	this.FONT.setColor(1f, 0.72f, 0.12f, 1f);
        } else {
        	this.FONT.setColor(0.15f, 0.15f, 0.15f, 1f);
        }

        this.FONT.draw(BATCH, name, nameX + 1, nameY + 1);
        this.FONT.draw(BATCH, name, nameX, nameY);

        this.FONT.setColor(0.92f, 0.8f, 0.16f, 1f);
        this.FONT.draw(BATCH, " x" + stock, nameX + nameLayout.width + 10, nameY);

        this.FONT.getData().setScale(0.8f);
        this.FONT.setColor(0.23f, 0.23f, 0.23f, 1f);
        GlyphLayout descLayout = new GlyphLayout(this.FONT, description, this.FONT.getColor(), 800, Align.left, true);
        this.FONT.draw(BATCH, descLayout, nameX, nameY - 30);
        this.FONT.getData().setScale(1f);
    }
}
