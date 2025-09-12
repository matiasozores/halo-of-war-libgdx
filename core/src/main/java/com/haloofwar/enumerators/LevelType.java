package com.haloofwar.enumerators;

import com.haloofwar.game.dependences.LevelData;

public enum LevelType {

    CAVE("cave", SceneType.CAVE, 1, 2, 1, 0.2f, 5, CutSceneDataType.INTRO);

    private SceneType scene;
    private LevelData data;
    private String name;
    private CutSceneDataType cutSceneType;

    private LevelType(String name, SceneType scene,
                      int enemySpawnRate,
                      int maxEnemies,
                      int waveCount,
                      float spawnAcceleration,
                      int enemiesToDefeat,
                      CutSceneDataType cutSceneType) {
        this.name = name;
        this.scene = scene;
        this.cutSceneType = cutSceneType;
        this.data = new LevelData(this, enemySpawnRate, maxEnemies, waveCount, spawnAcceleration, enemiesToDefeat);
    }

    // Getters
    public SceneType getScene() { return scene; }
    public LevelData getData() { return data; }
    public String getName() { return name; }
    public CutSceneDataType getCutSceneType() { return cutSceneType; }

    public static LevelType getLevelByName(String name) {
        for (LevelType lt : values()) {
            if (lt.getName().equals(name)) return lt;
        }
        System.out.println("Ha ocurrido un error al cargar el nivel: " + name);
        return null;
    }
}
