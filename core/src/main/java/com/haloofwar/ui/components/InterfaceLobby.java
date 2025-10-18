package com.haloofwar.ui.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.game.components.InventoryComponent;

public class InterfaceLobby implements HUDComponent {

    private final SpriteBatch batch;

    private final Texture image1;
    private final Texture image2;
    private final Texture image3;

    private final InventoryComponent inventory;
    private final Texture textureCoin;
    private final BitmapFont font;
    
    // Posiciones y tamaños por defecto
    private float x2 = 980, y2 = 640, width2 = 64, height2 = 64;
    private float x1 = 1080, y1 = 640, width1 = 64, height1 = 64;
    private float x3 = 1180, y3 = 640, width3 = 64, height3 = 64;
    
    private float coinX = 50, coinY = 650, coinSize = 48;
    
    private boolean visible = true;
    
    public InterfaceLobby(SpriteBatch batch, TextureManager texture, BitmapFont font, InventoryComponent inventory) {
        this.batch = batch;
        this.image1 = texture.get(Background.INVENTORY_ICON);
        this.image2 = texture.get(Background.SHOP_ICON);
        this.image3 = texture.get(Background.EQUIPMENT_ICON);
        this.inventory = inventory;
        this.textureCoin = texture.get(ObjectType.MONEDA_DE_ORO);
        this.font = font;
    }

    @Override
    public void render() {
    	if(!this.visible) {
    		return;
    	}
    	
    	
        if (image1 != null) batch.draw(image1, x1, y1, width1, height1);
        if (image2 != null) batch.draw(image2, x2, y2, width2, height2);
        if (image3 != null) batch.draw(image3, x3, y3, width3, height3);
    
        // Dibujar moneda
        if (textureCoin != null) batch.draw(textureCoin, coinX, coinY, coinSize, coinSize);

        // Dibujar cantidad de monedas con borde
        if (font != null && inventory != null) {
            int coinCount = inventory.getItemCount(ObjectType.MONEDA_DE_ORO);
            String text = "x " + coinCount;
            
            // Borde negro (4 direcciones)
            font.setColor(0f, 0f, 0f, 1f);
            font.draw(batch, text, coinX + coinSize + 10 - 1, coinY + coinSize * 0.8f - 1);
            font.draw(batch, text, coinX + coinSize + 10 + 1, coinY + coinSize * 0.8f - 1);
            font.draw(batch, text, coinX + coinSize + 10 - 1, coinY + coinSize * 0.8f + 1);
            font.draw(batch, text, coinX + coinSize + 10 + 1, coinY + coinSize * 0.8f + 1);

            // Texto amarillo encima
            font.setColor(1f, 1f, 0f, 1f);
            font.draw(batch, text, coinX + coinSize + 10, coinY + coinSize * 0.8f);
        }
    }
    
    public void setVisible(final boolean VISIBLE) {
		this.visible = VISIBLE;
	}

	@Override
	public void refresh(Entity player) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}
