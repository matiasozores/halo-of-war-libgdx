package com.haloofwar.ui.hud;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.graphics.FontManager;
import com.haloofwar.entities.players.Player;
import com.haloofwar.entities.statics.items.Item;

public class InventoryRenderer {
    private final SpriteBatch batch;
    private final BitmapFont smallFont;
    private final Player player;
    
    private final float x = 320;
    private final float y = 690;
    
    public InventoryRenderer(SpriteBatch batch, Player player, FontManager font) {
        this.batch = batch;
        this.player = player;
        this.smallFont = font.getSmallFont();
    }

    public void render() {
        if (this.player.getItemsInventory() != null) {
            ArrayList<Item> items = this.player.getItemsInventory();
            float currentX = x;

            for (Item item : items) {
                Texture texture = item.getTexture();
                int stock = item.getStock();

                this.batch.draw(texture, currentX, y - 50, 32, 32); 

                if (stock > 1) {
                    this.smallFont.draw(this.batch, "x" + stock, currentX + 15, this.y - 40);
                }

                currentX += 40;
            }
            
            this.smallFont.draw(this.batch, "Items en inventario:", this.x, this.y + 10);
        }
    }
}
