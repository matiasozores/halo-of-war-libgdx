package com.haloofwar.game.cutscenes;

import com.haloofwar.common.enums.SoundType;
import com.haloofwar.engine.entity.EntityDescriptor;

public enum CutSceneType implements EntityDescriptor {
	INTRO_1("Intro 1", "images/background/cutscenes/intro_1.png", SoundType.INTRO_VOICE_1),
	INTRO_2("Intro 2", "images/background/cutscenes/intro_2.png", SoundType.INTRO_VOICE_2),
	INTRO_3("Intro 2", "images/background/cutscenes/intro_3.png", SoundType.INTRO_VOICE_3),;

	private final String name;
	private final String path;
	private final SoundType soundType;
	
	private CutSceneType(String name, String path, SoundType type) {
		this.name = name;
		this.path = path;
		this.soundType = type;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getPath() {
		return this.path;
	}
	
	public SoundType getSoundType() {
		return this.soundType;
	}
	
}
