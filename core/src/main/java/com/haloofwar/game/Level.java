package com.haloofwar.game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.haloofwar.events.EventBus;
import com.haloofwar.events.LevelCompletedEvent;
import com.haloofwar.game.dependences.LevelData;
import com.haloofwar.ui.HUD;

public class Level extends GameScene {

    private LevelData data;
    private EventBus bus;
    private boolean levelCompleted = false;
    
    private float spawnTimer = 0f;
    private final float INITIAL_DELAY = 1f;
    private boolean initialSpawnDone = false;

    // --- Simulación de enemigos ---
    private List<String> activeEnemies = new ArrayList<>();
    private int currentWave = 0;
    private int enemiesSpawnedThisWave = 0;
    private int enemiesDefeated = 0;
   
	 
    public Level(World world, HUD hud, LevelData data, EventBus bus) {
        super(world, hud);
        this.data = data;
        this.bus = bus;
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (!this.initialSpawnDone) {
            this.initialWait(delta);
        } else if (!this.levelCompleted) {
            this.handleSpawning(delta);
            this.updateEnemies(delta);
            this.checkLevelCompletion();
        }
    }
    private void initialWait(float delta) {
        this.spawnTimer += delta;
        if (this.spawnTimer >= this.INITIAL_DELAY) {
            this.initialSpawnDone = true;
            this.spawnTimer = 0f;
            System.out.println("Spawn inicial realizado.");
        }
    }

    private void handleSpawning(float delta) {
        this.spawnTimer += delta;

        // Calculamos spawn rate actual según la oleada y aceleración
        float currentSpawnRate = Math.max(0.5f, this.data.getEnemySpawnRate() - this.currentWave * this.data.getSpawnAcceleration());

        if (this.spawnTimer >= currentSpawnRate) {
            spawnTimer = 0f;

            // Comprobar si podemos spawnear más enemigos
            if (activeEnemies.size() < data.getMaxEnemies() && enemiesSpawnedThisWave < data.getEnemiesToDefeat()) {
                spawnEnemy();
            }
            
            // Avanzar oleada si ya no quedan enemigos activos y se completó la anterior
            if (activeEnemies.isEmpty() && enemiesSpawnedThisWave > 0 && currentWave < data.getWaveCount()) {
                this.currentWave++;
                this.enemiesSpawnedThisWave = 0;
                System.out.println("Comienza la oleada " + (currentWave + 1));
            }
        }
    }

    private void spawnEnemy() {
        String enemyId = "Enemy_" + (enemiesSpawnedThisWave + 1);
        activeEnemies.add(enemyId);
        enemiesSpawnedThisWave++;
        System.out.println("Spawned: " + enemyId + " | Enemigos activos: " + activeEnemies.size());
    }
    
    private void updateEnemies(float delta) {
        Iterator<String> it = activeEnemies.iterator();
        while (it.hasNext()) {
            String enemy = it.next();

            if (Math.random() < 0.1) { // simulación de muerte
                it.remove();
                enemiesDefeated++;
                System.out.println(enemy + " ha sido derrotado! Enemigos activos: " + activeEnemies.size());
            }
        }
    }
    
    private void checkLevelCompletion() {
        if (activeEnemies.isEmpty() &&
            enemiesDefeated >= data.getEnemiesToDefeat() &&
            currentWave >= data.getWaveCount()) {

            levelCompleted = true;
            System.out.println("¡Nivel completado!");
            this.bus.publish(new LevelCompletedEvent());
            // Llamá a tu método de cambio de escena
            // changeScene(SceneType.NEXT_SCENE);
        }
    }
}
