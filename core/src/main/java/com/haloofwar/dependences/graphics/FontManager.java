package com.haloofwar.dependences.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.haloofwar.utilities.GraphicsUtils;

public class FontManager {
    private BitmapFont defaultFont;
    private BitmapFont titleFont;
    private BitmapFont smallFont;

    public FontManager() {
    	this.load();
    }
    
    private void load() {
        float scaleFactor = Gdx.graphics.getHeight() / 720f;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(GraphicsUtils.MAIN_FONT_PATH));
        
        FreeTypeFontParameter defaultParam = new FreeTypeFontParameter();
        defaultParam.size = (int)(24 * scaleFactor);
        defaultParam.color = Color.WHITE;
        defaultParam.magFilter = Texture.TextureFilter.Linear;
        defaultParam.minFilter = Texture.TextureFilter.Linear;
        this.defaultFont = generator.generateFont(defaultParam);

        // Title font (títulos grandes)
        FreeTypeFontParameter titleParam = new FreeTypeFontParameter();
        titleParam.size = (int)(48 * scaleFactor);
        titleParam.color = Color.WHITE;
        titleParam.magFilter = Texture.TextureFilter.Linear;
        titleParam.minFilter = Texture.TextureFilter.Linear;
        this.titleFont = generator.generateFont(titleParam);

        // Small font (texto chico, por ejemplo subtítulos o botones)
        FreeTypeFontParameter smallParam = new FreeTypeFontParameter();
        smallParam.size = (int)(16 * scaleFactor);
        smallParam.color = Color.WHITE;
        smallParam.magFilter = Texture.TextureFilter.Linear;
        smallParam.minFilter = Texture.TextureFilter.Linear;
        this.smallFont = generator.generateFont(smallParam);

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
        }
        
        if (this.titleFont != null) { 
        	this.titleFont.dispose();
        }
        
        if (this.smallFont != null) {
        	this.smallFont.dispose();
        }
    }
}
