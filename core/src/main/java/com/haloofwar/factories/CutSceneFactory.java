package com.haloofwar.factories;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.CutSceneDataType;
import com.haloofwar.game.cutscenes.CutSceneData;
import com.haloofwar.interfaces.Scene;

public final class CutSceneFactory {
	private final GameContext context;
	
	public CutSceneFactory(GameContext context) {
		this.context = context;
	}
	
	public CutSceneData create(CutSceneDataType type, Scene nextScene) {
		Texture[] images = new Texture[type.getImages().length];
		for (int i = 0; i < type.getImages().length; i++) {
			images[i] = this.context.getTexture().get(type.getImages()[i]);
		}
		
		return new CutSceneData(images, this.context.getRender().getBatch(), this.context.getBus(), nextScene);
	}
}
