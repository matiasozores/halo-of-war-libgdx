package com.haloofwar.dependences.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.haloofwar.utilities.GraphicsUtils;

public class FontManager {
    private BitmapFont defaultFont;
    private BitmapFont titleFont;
    private BitmapFont smallFont;

    public FontManager() {
        this.loadFonts();
    }

    private BitmapFont generateFont(FreeTypeFontGenerator generator, int size, Color color) {
        FreeTypeFontParameter param = new FreeTypeFontParameter();
        param.size = size;
        param.color = color;
        param.magFilter = TextureFilter.Linear;
        param.minFilter = TextureFilter.Linear;
        return generator.generateFont(param);
    }

    private void loadFonts() {
        float scaleFactor = Gdx.graphics.getHeight() / 720f;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GraphicsUtils.MAIN_FONT_PATH));

        this.defaultFont = generateFont(generator, (int)(24 * scaleFactor), Color.WHITE);
        this.titleFont = generateFont(generator, (int)(48 * scaleFactor), Color.WHITE);
        this.smallFont = generateFont(generator, (int)(16 * scaleFactor), Color.WHITE);

        generator.dispose();
    }

    public BitmapFont getDefaultFont() {
        return this.defaultFont;
    }

    public BitmapFont getTitleFont() {
        return this.titleFont;
    }

    public BitmapFont getSmallFont() {
        return this.smallFont;
    }

    public void dispose() {
        if (this.defaultFont != null) {
        	this.defaultFont.dispose();
        	this.defaultFont = null;
        }
        if (this.titleFont != null) {
        	this.titleFont.dispose();
        	this.titleFont = null;
        }
        if (this.smallFont != null) {
        	this.smallFont.dispose();
        	this.smallFont = null;
        }
    }
}
