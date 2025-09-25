package com.haloofwar.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;
import com.haloofwar.common.enums.Background;
import com.haloofwar.common.enums.SoundType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.PlaySoundEvent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.FireArmComponent;
import com.haloofwar.game.components.MeleeWeaponComponent;
import com.haloofwar.game.components.NameComponent;
import com.haloofwar.interfaces.Weapon;

public class EquipmentPopup extends Popup {

    private final EquipmentComponent EQUIPMENT;
    private final Texture SELECT_BUTTON; // resalta arma seleccionada
    private final TextureManager TEXTURE;
    
    public EquipmentPopup(final EventBus GAMEPLAY_BUS, final TextureManager TEXTURE, final BitmapFont FONT, final EquipmentComponent EQUIPMENT) {
        super(GAMEPLAY_BUS, FONT, TEXTURE.get(Background.EQUIPMENT), EQUIPMENT.weaponInventory);
        this.EQUIPMENT = EQUIPMENT;
        this.SELECT_BUTTON = TEXTURE.get(Background.SELECT); 
        this.TEXTURE = TEXTURE;
        
    }

    @Override
    protected void drawEntity(final SpriteBatch batch, final Entity entity, final float x, final float y, final boolean isSelected) {
        if (entity == null) return;

        NameComponent nameComp = entity.getComponent(NameComponent.class);
        String name = nameComp != null ? nameComp.name : "Sin nombre";

        Weapon weapon = null;
        Texture icon = null;
        String description = "";

        // Obtener referencia al arma y textura
        if (entity.hasComponent(FireArmComponent.class)) {
            FireArmComponent fa = entity.getComponent(FireArmComponent.class);
            weapon = fa.type;
            icon = this.TEXTURE.get(fa.type);
        } else if (entity.hasComponent(MeleeWeaponComponent.class)) {
            MeleeWeaponComponent mw = entity.getComponent(MeleeWeaponComponent.class);
            weapon = mw.type;
            icon = TEXTURE.get(mw.type);
        }

        if (weapon != null) {
            description = weapon.getDescription();
        }

        boolean isCurrent = (entity == EQUIPMENT.currentWeapon);

        // --- Icono del arma ---
        if (icon != null) {
            batch.draw(icon, x, y, 64, 64);
        }

        float nameX = x + 108;
        float nameY = y + 80;

        // Nombre del arma
        this.FONT.setColor(isSelected ? new Color(1f, 0.6f, 0f, 1f) : new Color(0.15f, 0.15f, 0.15f, 1f));
        GlyphLayout layout = new GlyphLayout(this.FONT, name);
        this.FONT.draw(batch, name, nameX + 1, nameY + 1);
        this.FONT.draw(batch, name, nameX, nameY);

        // Descripción
        FONT.getData().setScale(0.8f);
        FONT.setColor(0.23f, 0.23f, 0.23f, 1f);
        GlyphLayout descLayout = new GlyphLayout(FONT, description, FONT.getColor(), 800, Align.left, true);
        FONT.draw(batch, descLayout, nameX, nameY - 30);
        FONT.getData().setScale(1f);

        // Indicar arma actualmente equipada
        if (isCurrent) {
            this.FONT.setColor(0f, 1f, 0f, 1f);
            this.FONT.draw(batch, "Equipada", nameX + layout.width + 10, nameY);
        }

        boolean current = this.getSelectedEntity().equals(this.EQUIPMENT.currentWeapon);
        
        // Fondo para arma seleccionada
        if (isSelected && !current) {
            batch.draw(SELECT_BUTTON, nameX + 700, y - 100, 128, 128);
        }
    }


    /** Equipar el arma actualmente seleccionada */
    public void equipSelected() {
        if (this.EQUIPMENT.weaponInventory.isEmpty()) {
        	return;
        }
        
        if(this.getSelectedEntity().equals(this.EQUIPMENT.currentWeapon)) {
        	return;
        }
        
        this.EQUIPMENT.currentWeapon = this.getSelectedEntity();
        this.GAMEPLAY_BUS.publish(new PlaySoundEvent(SoundType.SELECT_WEAPON));
        System.out.println("Equipaste: " + EQUIPMENT.currentWeapon.getComponent(NameComponent.class).name);
    }
}
