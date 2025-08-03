package com.haloofwar.ui.hud;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.players.Player;

public class PlayerInfoRenderer {
    private final SpriteBatch batch;
    private final BitmapFont smallFont;
    private final BitmapFont titleFont;
    private final Player player;
    private final Texture portrait;
    private final Texture weapon;

    private final float x = 20;
    private final float y = 640;
    
    public PlayerInfoRenderer(SpriteBatch batch, GameContext context, Player player) {
        this.batch = batch;
        this.player = player;
        this.smallFont = context.getRender().getFont().getSmallFont();
        this.titleFont = context.getRender().getFont().getTitleFont();
        this.portrait = context.getTexture().get(player.getType().getHead());
        this.weapon = context.getTexture().get(player.getType().getDefaultWeapon());
    }
    
    public void render() {
        String hpText = (int) player.getHealth() + "/100";
        this.smallFont.draw(this.batch, hpText, x + 80 + 100, y + 20);

        this.titleFont.draw(this.batch, this.player.getName(), x + 80, y + 70);
        
        this.batch.draw(this.weapon, 20, 575, 80, 65);
        

        if (this.portrait != null) {
            this.batch.draw(this.portrait, this.x, this.y, 66, 70);
        }
    }
    
    public void dispose() {
        if (portrait != null) {
        	portrait.dispose();
        }
        if (weapon != null) {
        	weapon.dispose();
        }
    }
}

