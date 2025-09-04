package com.haloofwar.game.dependences;

public class LevelData {

    private int levelNumber;        // número del nivel
    private int sublevelNumber;     // número del subnivel (si aplica)

    // --- ENEMIGOS ---
    private int enemySpawnRate;     // segundos entre spawns
    private int maxEnemies;         // cantidad máxima de enemigos simultáneos

    // --- OLEADAS ---
    private int waveCount;          // cantidad de oleadas en el nivel
    private float spawnAcceleration;// reducción progresiva del spawnRate

    // --- CONDICIONES DE VICTORIA ---
    private int enemiesToDefeat;    // cantidad total de enemigos que el jugador debe eliminar

    
    public LevelData(
        int levelNumber,
        int sublevelNumber,
        int enemySpawnRate,
        int maxEnemies,
        int waveCount,
        float spawnAcceleration,
        int enemiesToDefeat
    ) {
        this.levelNumber = levelNumber;
        this.sublevelNumber = sublevelNumber;
        this.enemySpawnRate = enemySpawnRate;
        this.maxEnemies = maxEnemies;
        this.waveCount = waveCount;
        this.spawnAcceleration = spawnAcceleration;
        this.enemiesToDefeat = enemiesToDefeat;
    }

    // --- Getters ---
    public int getLevelNumber() { return levelNumber; }
    public int getSublevelNumber() { return sublevelNumber; }
    public int getEnemySpawnRate() { return enemySpawnRate; }
    public int getMaxEnemies() { return maxEnemies; }
    public int getWaveCount() { return waveCount; }
    public float getSpawnAcceleration() { return spawnAcceleration; }
    public int getEnemiesToDefeat() { return enemiesToDefeat; }
}
