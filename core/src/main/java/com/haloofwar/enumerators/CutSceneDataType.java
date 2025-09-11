package com.haloofwar.enumerators;

public enum CutSceneDataType {
	INTRO(new CutSceneType[] {CutSceneType.PLAYA_PERDIDA, CutSceneType.PLAYA_PERDIDA});

	private CutSceneType[] images;
	
	private CutSceneDataType(CutSceneType[] images) {
		this.images = images;
	}
	
	public CutSceneType[] getImages() {
		return this.images;
	}
}
