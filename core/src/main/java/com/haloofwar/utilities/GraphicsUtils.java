package com.haloofwar.utilities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public final class GraphicsUtils {
	private GraphicsUtils() {}
	
	public static final String MAIN_FONT_PATH = "fonts/GODOFWAR.TTF";
	
	public static void cleanWindow() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}
}
