package com.haloofwar.common.enums;

import com.haloofwar.game.cutscenes.CutSceneDataType;
import com.haloofwar.game.dependences.LevelData;
import com.haloofwar.interfaces.SceneDescriptor;
import com.haloofwar.interfaces.SceneKey;

public enum LevelSceneType implements SceneDescriptor, SceneKey {

	INTRO("intro", SceneType.CAVE, 0, 0, 1, 0, CutSceneDataType.INTRO),
    TUTORIAL("cave", SceneType.CAVE, 1, 1, 1, 1, CutSceneDataType.INTRO),
    INFINITY("infinity", SceneType.CAVE, 1, 2, Integer.MAX_VALUE, Integer.MAX_VALUE);

    private final String name;
    private final SceneType baseScene;
    private final LevelData data;
    private final CutSceneDataType cutSceneType;

    private LevelSceneType(
    	String name, 
    	SceneType scene,
    	int enemySpawnRate,
    	int maxEnemies,
    	int waveCount,
    	int enemiesToDefeatPerWave,
    	CutSceneDataType cutSceneType
    ) {
        this.name = name;
        this.baseScene = scene;
        this.cutSceneType = cutSceneType;
        this.data = new LevelData(this, enemySpawnRate, maxEnemies, waveCount, enemiesToDefeatPerWave);
    }
    
    private LevelSceneType(
		String name, 
		SceneType scene,
        int enemySpawnRate,
        int maxEnemies,
        int waveCount,
        int enemiesToDefeatPerWave
    ) {
		this(name, scene, enemySpawnRate, maxEnemies, waveCount, enemiesToDefeatPerWave, null);
    }
    
    @Override
    public String getName() { return this.name; }
    @Override
	public String getPath() { return this.baseScene.getPath(); }
	@Override
	public MusicTrack getMusic() { return this.baseScene.getMusic(); }
    @Override
    public LevelData getLevelData() { return this.data.copy(); }
    @Override
    public CutSceneDataType getCutSceneType() { return this.cutSceneType; }
	@Override
	public boolean isLevel() { return true; }
	@Override
	public SceneType getScene() { return this.baseScene; }
	
    public static LevelSceneType getLevelByName(String name) {
        for (LevelSceneType lt : values()) {
            if (lt.getName().equals(name)) return lt;
        }
        System.out.println("Ha ocurrido un error al cargar el nivel: " + name);
        return null;
    }
}
