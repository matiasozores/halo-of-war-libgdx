package com.haloofwar.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Text {
    private String text;
    private BitmapFont font;
    private GlyphLayout layout = new GlyphLayout(); // en tu clase
    private final int DEFAULT_SIZE = 24;
    
    public Text(String text) {
        this.text = text;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/LEMONMILK-Regular.otf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = this.DEFAULT_SIZE;
        parameter.color = Color.WHITE;
        this.font = generator.generateFont(parameter);
        generator.dispose();
	}
    
    public void draw(SpriteBatch batch, float x, float y) {
		font.draw(batch, this.text, x, y);
	}

    public void setText(String text) {
		this.text = text;
	}
    

    public float getWidth() {
        layout.setText(font, text);
        return layout.width;
    }

    
    public void dispose() {
        font.dispose();
    }
    
}
