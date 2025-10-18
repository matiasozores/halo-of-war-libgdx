package com.haloofwar.game.factories;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.MusicTrack;
import com.haloofwar.common.enumerators.SoundType;
import com.haloofwar.common.managers.TextureManager;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.game.cutscenes.CutScene;
import com.haloofwar.game.cutscenes.CutSceneData;
import com.haloofwar.game.cutscenes.CutSceneDataType;

public final class CutSceneFactory {
	private final TextureManager TEXTURE;
	private final SpriteBatch BATCH;
	private final EventBus GAMEPLAY_BUS;
	private final GameStaticCamera STATIC_CAMERA;

	public CutSceneFactory(final GameContext CONTEXT) {
		this.TEXTURE = CONTEXT.getTEXTURE();
		this.BATCH = CONTEXT.getRENDER().getBatch();
		this.GAMEPLAY_BUS = CONTEXT.getGAMEPLAY().getBus();
		this.STATIC_CAMERA = CONTEXT.getSTATIC_CAMERA();
	}
	
	public CutScene create(final CutSceneDataType TYPE, final MusicTrack previousTrack) {
		if(TYPE == null) {
			return null;
		}
		
		final Texture[] IMAGES = new Texture[TYPE.getImages().length];
		final SoundType[] SOUNDS = new SoundType[TYPE.getImages().length];
		
		for (int i = 0; i < TYPE.getImages().length; i++) {
			IMAGES[i] = this.TEXTURE.get(TYPE.getImages()[i]);
			SOUNDS[i] = TYPE.getImages()[i].getSoundType();
		}
		
		final CutSceneData DATA =  new CutSceneData(IMAGES, SOUNDS, this.BATCH, this.GAMEPLAY_BUS);
		
		return new CutScene(DATA, this.STATIC_CAMERA, previousTrack);
	}
}