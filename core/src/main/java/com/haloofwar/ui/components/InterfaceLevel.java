package com.haloofwar.ui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.game.dependences.LevelData;

public class InterfaceLevel {
    private final LevelData DATA;
    private final SpriteBatch BATCH;
    private final BitmapFont FONT;

    public InterfaceLevel(final LevelData DATA, final SpriteBatch BATCH, final BitmapFont FONT) {
        this.DATA = DATA;
        this.BATCH = BATCH;

        this.FONT = FONT;
        FONT.setColor(Color.WHITE);
    }

    public void render() {
        // Texto a mostrar
        String waveText = "Oleada: " + DATA.getWavesPassed() + "/" + DATA.getWaveCount();
        String enemiesText = "Enemigos: " + DATA.getEnemiesDefeated() + "/" + DATA.getEnemiesToDefeat();

        FONT.draw(BATCH, waveText, 1080, 680);  
        FONT.draw(BATCH, enemiesText, 1080, 640);
    }
}
