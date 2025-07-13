package com.haloofwar.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

public class Text {
    private String text;
    private BitmapFont font;

    public Text(String text, int size) {
        this.text = text;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/LEMONMILK-Regular.otf"));
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = Color.WHITE;
        this.font = generator.generateFont(parameter);
        generator.dispose();
	}
    
    public void draw(SpriteBatch batch, float x, float y) {
		font.draw(batch, this.text, x, y);
	}

    public void dispose() {
        font.dispose();
    }
}
