package com.haloofwar.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.PlayerType;

public class HUD {
    private Player player;

    private GameStaticCamera camera;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;

    private BitmapFont smallFont;
    private BitmapFont titleFont;

    private Texture portrait;
    private Texture weapon;

    public HUD(GameContext context, Player player, PlayerType type) {
        this.player = player;

        this.camera = new GameStaticCamera();
        this.batch = context.getRender().getBatch();
        this.shapeRenderer = new ShapeRenderer();

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
        shapeRenderer.setProjectionMatrix(camera.getOrthographic().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x + 80, y, barWidth, barHeight);

        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x + 80, y, barWidth * hpPercentage, barHeight);
        shapeRenderer.end();

        batch.setProjectionMatrix(camera.getOrthographic().combined);
        batch.begin();

        //vida numérica
        String hpText = (int) currentHp + "/" + (int) maxHp;
        smallFont.draw(batch, hpText, x + 80 + barWidth / 2 - 20, y + barHeight / 2 + 6);

        //nombre del personaje
        titleFont.draw(batch, player.getName(), x + 80, y + barHeight + 45);
        
        //arma del personaje
        batch.draw(this.weapon, 20, 575, 80, 65);
        
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
        shapeRenderer.dispose();
    }
}