package com.haloofwar.game.dependences;

import com.haloofwar.common.enumerators.LevelSceneType;

public class LevelData {

    private LevelSceneType type;

    // --- ENEMIGOS ---
    private int enemySpawnRate;     // segundos entre spawns
    private int maxEnemies;         // cantidad máxima de enemigos simultáneos

    // --- OLEADAS ---
    private int waveCount;          // cantidad de oleadas en el nivel
    private int enemiesToDefeatPerWave;

    // --- CONDICIONES DE VICTORIA ---
    private int enemiesToDefeat;    // cantidad total de enemigos que el jugador debe eliminar
    private int enemiesDefeated = 0;
    private int wavesPassed = 1;

    public LevelData(
        LevelSceneType type,
        int enemySpawnRate,
        int maxEnemies,
        int waveCount,
        int enemiesToDefeatPerWave
    ) {
        this.type = type;
        this.enemySpawnRate = enemySpawnRate;
        this.maxEnemies = maxEnemies;
        this.waveCount = waveCount;
        this.enemiesToDefeatPerWave = enemiesToDefeatPerWave;
        this.enemiesToDefeat = this.enemiesToDefeatPerWave * this.waveCount;
    }

    public LevelData copy() {
        LevelData clone = new LevelData(
            this.type,
            this.enemySpawnRate,
            this.maxEnemies,
            this.waveCount,
            this.enemiesToDefeatPerWave
        );
        clone.enemiesDefeated = this.enemiesDefeated;
        clone.wavesPassed = this.wavesPassed;
        return clone;
    }
    
    public void incrementEnemiesDefeated() {
        enemiesDefeated++;
    }

    public void incrementWave() {
        wavesPassed++;
    }

    public boolean allEnemiesDefeated() {
        return enemiesDefeated >= enemiesToDefeat;
    }

    // --- Getters ---
    public LevelSceneType getType() { return type; }
    public int getEnemySpawnRate() { return enemySpawnRate; }
    public int getMaxEnemies() { return maxEnemies; }
    public int getWaveCount() { return waveCount; }
    public int getEnemiesToDefeat() { return enemiesToDefeat; }
    public int getEnemiesDefeated() { return enemiesDefeated; }
    public int getWavesPassed() { return wavesPassed; }
    public int getEnemiesToDefeatPerWave() { return enemiesToDefeatPerWave; }
}
