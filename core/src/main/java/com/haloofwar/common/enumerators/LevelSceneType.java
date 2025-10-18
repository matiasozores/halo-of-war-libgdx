package com.haloofwar.common.enumerators;

import java.util.HashSet;
import java.util.Set;

import com.haloofwar.game.cutscenes.CutSceneDataType;
import com.haloofwar.game.dependences.LevelData;
import com.haloofwar.interfaces.SceneDescriptor;
import com.haloofwar.interfaces.SceneKey;

public enum LevelSceneType implements SceneDescriptor, SceneKey {

    TUTORIAL("cave", SceneType.CAVE, 1, 5, 1, 5, CutSceneDataType.INTRO),
    LEVEL_ONE_ONE("Level 1_1", SceneType.THE_LOAST_COAST, 1, 1, 1, 1),
    LEVEL_ONE_TWO("Level 1_2", SceneType.ENTRANCE_TO_TARTARUS, 1, 1, 1, 1),
    LEVEL_ONE_THREE("Level 1_3", SceneType.SUBMERGED_TEMPLE, 1, 1, 1, 1),
    INFINITY("infinity", SceneType.CAVE, 1, 2, Integer.MAX_VALUE, Integer.MAX_VALUE);

	static {
		TUTORIAL.nextLevel = LEVEL_ONE_ONE;
		LEVEL_ONE_ONE.nextLevel = LEVEL_ONE_TWO;
		LEVEL_ONE_TWO.nextLevel = LEVEL_ONE_THREE;
	}
	
    private final String name;
    private final SceneType baseScene;
    private final LevelData data;
    private final CutSceneDataType cutSceneType;
    private LevelSceneType nextLevel;
    
    private LevelSceneType(
    	final String name, 
    	final SceneType scene,
    	final int enemySpawnRate,
    	final int maxEnemies,
    	final int waveCount,
    	final int enemiesToDefeatPerWave,
    	final CutSceneDataType cutSceneType
    ) {
        this.name = name;
        this.baseScene = scene;
        this.cutSceneType = cutSceneType;
        this.data = new LevelData(this, enemySpawnRate, maxEnemies, waveCount, enemiesToDefeatPerWave);
    }
    
    private LevelSceneType(
    	final String name, 
    	final SceneType scene,
    	final int enemySpawnRate,
    	final int maxEnemies,
    	final int waveCount,
    	final int enemiesToDefeatPerWave
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
    
    public LevelSceneType getNextLevel() {
		return this.nextLevel;
	}
    
    public static Set<LevelSceneType> getAllLockedLevels(){
    	Set<LevelSceneType> lockedLevels = new HashSet<LevelSceneType>();
    	
    	for (int i = 0; i < LevelSceneType.values().length; i++) {
			lockedLevels.add(LevelSceneType.values()[i]);
		}
    	
    	return lockedLevels;
    }	
}
