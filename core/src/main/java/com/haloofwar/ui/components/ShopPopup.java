package com.haloofwar.ui.components;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.common.enumerators.ObjectType;
import com.haloofwar.common.enumerators.SoundType;
import com.haloofwar.common.enumerators.UIState;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.FireArmComponent;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.components.MeleeWeaponComponent;
import com.haloofwar.game.components.NameComponent;
import com.haloofwar.game.factories.WeaponFactory;
import com.haloofwar.interfaces.Weapon;

public class ShopPopup extends Popup {

	private EquipmentComponent EQUIPMENT;
    private InventoryComponent INVENTORY;
    private final Texture BUY_BUTTON;
    private final Texture GOLD_COIN;
    private final TextureManager TEXTURE;
    	
    public ShopPopup(
		final EventBus GAMEPLAY_BUS, 
		TextureManager textureManager, 
		BitmapFont font,
		ArrayList<Entity> shopItems, 
		EquipmentComponent equipment,
		InventoryComponent inventory,
		final SpriteBatch batch
	) {
        super(GAMEPLAY_BUS, font, textureManager.get(Background.SHOP), shopItems, UIState.SHOP, batch);

        this.BUY_BUTTON = textureManager.get(Background.BUY);
        this.GOLD_COIN = textureManager.get(ObjectType.MONEDA_DE_ORO);
        this.EQUIPMENT = equipment;
        this.INVENTORY = inventory;
        this.TEXTURE = textureManager;
    }

    @Override
    protected void drawEntity(SpriteBatch batch, Entity entity, float x, float y, boolean isSelected) {
        if (entity == null) return;

        NameComponent nameComp = entity.getComponent(NameComponent.class);
        String name = nameComp != null ? nameComp.name : "Sin nombre";

        Weapon weapon = null;
        Texture icon = null;
        String description = "";
        int price = 0;

        if (entity.hasComponent(FireArmComponent.class)) {
            FireArmComponent fa = entity.getComponent(FireArmComponent.class);
            weapon = fa.type;
            icon = TEXTURE.get(fa.type);
        } else if (entity.hasComponent(MeleeWeaponComponent.class)) {
            MeleeWeaponComponent mw = entity.getComponent(MeleeWeaponComponent.class);
            weapon = mw.type;
            icon = TEXTURE.get(mw.type);
        }

        if (weapon != null) {
            description = weapon.getDescription();
            price = weapon.getPrice();
        }

        boolean owned = EQUIPMENT.isInInventory(name);

        // --- Icono del arma ---
        if (icon != null) {
            batch.draw(icon, x, y, 64, 64);
        }

        float nameX = x + 108;
        float nameY = y + 80;

        // Nombre
        this.FONT.setColor(isSelected ? new Color(245/255f, 73/255f, 39/255f, 1f) :
                                       new Color(0.15f, 0.15f, 0.15f, 1f));
        GlyphLayout nameLayout = new GlyphLayout(FONT, name);
        FONT.draw(batch, name, nameX + 1, nameY + 1);
        FONT.draw(batch, name, nameX, nameY);

        // Precio u Obtenido
        float textX = nameX + nameLayout.width + 10;
        if (owned) {
        	FONT.setColor(0.5f, 0.5f, 0.5f, 1f);
        	FONT.draw(batch, "Obtenido", textX, nameY);
        } else {
            if (GOLD_COIN != null) {batch.draw(GOLD_COIN, textX, nameY - 25, 24, 24);
            FONT.setColor(1f, 1f, 0f, 1f);
            FONT.draw(batch, String.valueOf(price), textX + 30, nameY);}
        }

        // Descripción
        FONT.getData().setScale(0.8f);
        FONT.setColor(0.23f, 0.23f, 0.23f, 1f);
        GlyphLayout descLayout = new GlyphLayout(FONT, description, FONT.getColor(), 800, Align.left, true);
        FONT.draw(batch, descLayout, nameX, nameY - 30);
        FONT.getData().setScale(1f);

        // Botón comprar
        if (isSelected && !owned) {
        	batch.draw(BUY_BUTTON, nameX + 700, y - 100, 128, 128);
        }
    }

    private void buyCurrent() {
        Entity selected = getSelectedEntity();
        if (selected == null) return;

        NameComponent nameComp = selected.getComponent(NameComponent.class);
        String name = nameComp != null ? nameComp.name : null;
        if (name == null) return;

        if (EQUIPMENT.isInInventory(name)) {
            System.out.println("Ya tienes este item: " + name);
            return;
        }

        Weapon weapon = getWeapon(selected);
        if (weapon == null) return;

        final int price = weapon.getPrice();
        final int playerGold = INVENTORY.getItemCount(ObjectType.MONEDA_DE_ORO);

        if (playerGold < price) {
            System.out.println("No tienes suficiente oro para comprar " + name + ". Necesitas " + price + ", tienes " + playerGold);
            return;
        }

        boolean removed = INVENTORY.remove(ObjectType.MONEDA_DE_ORO, price);
        if (!removed) {
            return;
        }

        Entity newWeapon = WeaponFactory.createWeapon(weapon);
        this.EQUIPMENT.weaponInventory.add(newWeapon);
        this.GAMEPLAY_BUS.publish(new PlaySoundEvent(SoundType.PURCHASE));
    }

    @Override
    public void onSelectOption() {
        this.buyCurrent();
    }
    
    private Weapon getWeapon(Entity entity) {
    	if(entity.hasComponent(FireArmComponent.class)) {
    		return entity.getComponent(FireArmComponent.class).getWeapon();
    	} else {
    		if(entity.hasComponent(MeleeWeaponComponent.class)) {
        		return entity.getComponent(MeleeWeaponComponent.class).getWeapon();
        	} else {
        		return null;
        	}
    	}
    }

    @Override
    public void refresh(Entity player) {
        if (player == null) {
        	return;
        }

        // agregar logica de cambio de tienda dependiendo del jugador
    }


}
