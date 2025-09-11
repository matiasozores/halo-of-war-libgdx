package com.haloofwar.enumerators;

import com.haloofwar.game.dependences.LevelData;

public enum LevelType {
    CAVE("cave", SceneType.CAVE, new LevelData(1, 0, 1, 2, 1, 0.2f, 5), CutSceneDataType.INTRO),;

    private SceneType scene;
    private LevelData data;
    private String name;
    private CutSceneDataType cutSceneType;

    private LevelType(String name, SceneType scene, LevelData data) {
        this.scene = scene;
        this.data = data;
        this.name = name;
    }
    
    private LevelType(String name, SceneType scene, LevelData data, CutSceneDataType cutSceneType) {
		this(name, scene, data);
		this.cutSceneType = cutSceneType;
	}

    public SceneType getScene() { return scene; }
    public LevelData getData() { return data; }
    public String getName() { return this.name; }
    public CutSceneDataType getCutSceneType() { return this.cutSceneType; }
    
    
    public static LevelType getLevelByName(String name) {
		int i = 0;
    	boolean found = false;
    	int index = -1;
    	
    	while(i < LevelType.values().length && !found) {
			if(LevelType.values()[i].getName().equals(name)) {
				found = true;
				index = i;
			}
			i++;
		}
    	
    	if(found) {
			return LevelType.values()[index];
		} else {
			System.out.println("Ha ocurrido un error al cargar el nivel: " + name);
		}
		
		return null;
	}
}
