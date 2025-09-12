package com.haloofwar.game.dependences;

import com.haloofwar.enumerators.LevelType;

public class LevelData {

	private LevelType type;

    // --- ENEMIGOS ---
    private int enemySpawnRate;     // segundos entre spawns
    private int maxEnemies;         // cantidad máxima de enemigos simultáneos

    // --- OLEADAS ---
    private int waveCount;          // cantidad de oleadas en el nivel
    private float spawnAcceleration;// reducción progresiva del spawnRate

    // --- CONDICIONES DE VICTORIA ---
    private int enemiesToDefeat;    // cantidad total de enemigos que el jugador debe eliminar

    
    public LevelData(
        LevelType type,
        int enemySpawnRate,
        int maxEnemies,
        int waveCount,
        float spawnAcceleration,
        int enemiesToDefeat
    ) {
        this.type = type;
        this.enemySpawnRate = enemySpawnRate;
        this.maxEnemies = maxEnemies;
        this.waveCount = waveCount;
        this.spawnAcceleration = spawnAcceleration;
        this.enemiesToDefeat = enemiesToDefeat;
    }

    // --- Getters ---
    public LevelType getType() { return type; }
    public int getEnemySpawnRate() { return enemySpawnRate; }
    public int getMaxEnemies() { return maxEnemies; }
    public int getWaveCount() { return waveCount; }
    public float getSpawnAcceleration() { return spawnAcceleration; }
    public int getEnemiesToDefeat() { return enemiesToDefeat; }
}
