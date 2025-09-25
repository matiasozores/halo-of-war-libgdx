package com.haloofwar.game.cutscenes;

public enum CutSceneDataType {
	NONE(null),
	INTRO(new CutSceneType[] {CutSceneType.INTRO_1, CutSceneType.INTRO_2, CutSceneType.INTRO_3});

	private CutSceneType[] images;
	
	private CutSceneDataType(CutSceneType[] images) {
		this.images = images;
	}
	
	public CutSceneType[] getImages() {
		return this.images;
	}
}
