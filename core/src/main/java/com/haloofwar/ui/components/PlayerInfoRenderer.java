package com.haloofwar.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.HealthComponent;
import com.haloofwar.game.components.NameComponent;
import com.haloofwar.game.components.PlayerComponent;

public class PlayerInfoRenderer {
    
    // Dependencias
    private final SpriteBatch batch;
    private final BitmapFont smallFont;
    private final BitmapFont titleFont;
    private final TextureManager texture;
    private Texture portrait;
    private Texture weapon;
    private final Texture background; // Placeholder del arma
    
    // Componentes
    public HealthComponent health;
    public NameComponent nameComponent;
    
    // Posiciones base
    private final float x = 20;
    private final float y = 620;
    
    // Tamaños
    private final float portraitWidth = 64;
    private final float portraitHeight = 64;
    private final float weaponWidth = 64;
    private final float weaponHeight = 64;
    private final float bgWidth = 112;
    private final float bgHeight = 112;
    private final float spacing = 10; // margen entre nombre y arma
    
    public PlayerInfoRenderer(
        SpriteBatch batch,
        BitmapFont smallFont,
        BitmapFont titleFont,
        TextureManager texture,
        Texture portrait,
        Texture weapon,
        Texture background,
        NameComponent nameComponent,
        HealthComponent health
    ) {
        this.batch = batch;
        this.smallFont = smallFont;
        this.titleFont = titleFont;
        this.texture = texture;
        
        this.portrait = portrait;
        this.nameComponent = nameComponent;
        this.health = health;
        this.background = background;
        this.weapon = weapon;
    }
    
    public void render() {
        // Dibuja retrato del jugador
        if (portrait != null) {
            batch.draw(portrait, x, y, portraitWidth, portraitHeight);
        }

        // Dibuja nombre del jugador con contorno
        float nameX = x + portraitWidth + 10;
        float nameY = y + portraitHeight - 10;

        Color outlineColor = Color.BLACK;
        titleFont.setColor(outlineColor);
        float offset = 1f;
        titleFont.draw(batch, nameComponent.name, nameX - offset, nameY + offset);
        titleFont.draw(batch, nameComponent.name, nameX + offset, nameY + offset);
        titleFont.draw(batch, nameComponent.name, nameX - offset, nameY - offset);
        titleFont.draw(batch, nameComponent.name, nameX + offset, nameY - offset);

        titleFont.setColor(Color.WHITE);
        titleFont.draw(batch, nameComponent.name, nameX, nameY);

        // Dibuja barra de HP debajo del nombre
        String hpText = (int) health.getCurrentHealth() + "/" + health.getMaxHealth();
        smallFont.setColor(Color.WHITE);
        smallFont.draw(batch, hpText, nameX, nameY - 75);

        // Medir ancho del nombre para posicionar arma a la derecha
        GlyphLayout layout = new GlyphLayout(titleFont, nameComponent.name);
        float weaponX = nameX + layout.width + spacing;
        float weaponY = (nameY - bgHeight / 2f) - 30; // centrar verticalmente con el nombre

        // Dibuja placeholder del arma
        if (background != null) {
            batch.draw(background, weaponX, weaponY, bgWidth, bgHeight);
        }

        // Dibuja el arma centrada sobre el placeholder
        if (weapon != null) {
            float weaponCenteredX = weaponX + (bgWidth - weaponWidth) / 2f;
            float weaponCenteredY = weaponY + (bgHeight - weaponHeight) / 2f;
            batch.draw(weapon, weaponCenteredX, weaponCenteredY, weaponWidth, weaponHeight);
        }
    }
    
    public void setPortrait(PlayerComponent playerComp) {
    	this.portrait = this.texture.get(playerComp.type.getHead());
    }
    
    public void setWeapon(EquipmentComponent equipmentComp) {
    	this.weapon = this.texture.get(equipmentComp.getCurrentWeapon());
    }
    
    public void dispose() {
        if (portrait != null) portrait.dispose();
        if (weapon != null) weapon.dispose();
        if (background != null) background.dispose();
    }
}
