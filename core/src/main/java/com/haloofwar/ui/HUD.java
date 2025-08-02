package com.haloofwar.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.entities.PlayerType;

public class HUD {
    private Player player;

    private GameStaticCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shape;

    private BitmapFont smallFont;
    private BitmapFont titleFont;

    private Texture portrait;
    private Texture weapon;

    public HUD(GameContext context, Player player, PlayerType type) {
        this.player = player;

        this.camera = new GameStaticCamera();
        this.batch = context.getRender().getBatch();
        this.shape = new ShapeRenderer();

        this.smallFont = context.getRender().getFont().getSmallFont();
        this.titleFont = context.getRender().getFont().getTitleFont();
        
        this.portrait = context.getTexture().get(type.getHead());
        this.weapon = context.getTexture().get(type.getDefaultWeapon());
    }

    public void render() {
        float x = 20;
        float y = 640;

        float barWidth = 200;
        float barHeight = 25;

        float currentHp = player.getHealth();
        float maxHp = 100f;
        float hpPercentage = Math.max(0, currentHp / maxHp);

        camera.update();

        // Dibujar barra de vida
        shape.setProjectionMatrix(camera.getOrthographic().combined);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.DARK_GRAY);
        shape.rect(x + 80, y, barWidth, barHeight);

        shape.setColor(Color.GREEN);
        shape.rect(x + 80, y, barWidth * hpPercentage, barHeight);
        shape.end();

        batch.setProjectionMatrix(camera.getOrthographic().combined);
        batch.begin();

        //vida numérica
        String hpText = (int) currentHp + "/" + (int) maxHp;
        smallFont.draw(batch, hpText, x + 80 + barWidth / 2 - 20, y + barHeight / 2 + 6);

        //nombre del personaje
        titleFont.draw(batch, player.getName(), x + 80, y + barHeight + 45);
        
        //arma del personaje
        batch.draw(this.weapon, 20, 575, 80, 65);
        
        //inventario del personaje
        if (player.getInventory() != null) {
			String inventoryText = "Inventario: " + player.getInventory().getItemsCount();
			smallFont.draw(batch, inventoryText, x + 300, y + barHeight);
        }
        
        //imagen del personaje
        if (portrait != null) {
            batch.draw(portrait, x, y, 66, 70);
        }

        batch.end();
    }
    
    public void update() {
        this.camera.update();
    }

    public GameStaticCamera getCamera() {
        return this.camera;
    }

    
    public void dispose() {
        if (portrait != null) {
            portrait.dispose();
        }
        shape.dispose();
    }
}