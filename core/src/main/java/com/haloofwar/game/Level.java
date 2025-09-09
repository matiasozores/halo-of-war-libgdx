package com.haloofwar.game;

import java.util.ArrayList;

import com.haloofwar.components.Entity;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.enumerators.EnemyType;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.LevelCompletedEvent;
import com.haloofwar.events.NewEntityEvent;
import com.haloofwar.events.RemoveEntityEvent;
import com.haloofwar.factories.EnemyFactory;
import com.haloofwar.game.dependences.LevelData;
import com.haloofwar.ui.HUD;

public class Level extends GameScene {

    private LevelData data;
    private EventBus bus;
    private EnemyFactory enemyFactory;
    
    private boolean levelCompleted = false;
    
    private float spawnTimer = 0f;
    private final float INITIAL_DELAY = 1f;
    private boolean initialSpawnDone = false;

    private int currentWave = 0;
    private int enemiesSpawnedThisWave = 0;
    private int enemiesDefeated = 0;
    
    // Llevar cuenta propia de los enemigos activos
    private ArrayList<HealthComponent> activeEnemies = new ArrayList<>();
   
	 
    public Level(World world, HUD hud, LevelData data, EventBus bus, EnemyFactory enemyFactory) {
        super(world, hud);
        this.data = data;
        this.bus = bus;
        this.enemyFactory = enemyFactory;
        this.bus.subscribe(RemoveEntityEvent.class, this::updateEnemiesDefeated);
    }

    @Override
    public void update(float delta) {
        super.update(delta);

        if (!this.initialSpawnDone) {
            this.initialWait(delta);
        } else if (!this.levelCompleted) {
            this.handleSpawning(delta);
            this.checkLevelCompletion();
        }
    }
    private void initialWait(float delta) {
        this.spawnTimer += delta;
        if (this.spawnTimer >= this.INITIAL_DELAY) {
            this.initialSpawnDone = true;
            this.spawnTimer = 0f;
        }
    }

    private void handleSpawning(float delta) {
        this.spawnTimer += delta;

        float currentSpawnRate = Math.max(0.5f, this.data.getEnemySpawnRate() - this.currentWave * this.data.getSpawnAcceleration());

        if (this.spawnTimer >= currentSpawnRate) {
            this.spawnTimer = 0f;

            if (this.activeEnemies.size() < this.data.getMaxEnemies() && this.enemiesSpawnedThisWave < this.data.getEnemiesToDefeat()) {
                spawnEnemy();
            }
            
            // Avanzar oleada si ya no quedan enemigos activos y se completó la anterior
            if (this.activeEnemies.isEmpty() && this.enemiesSpawnedThisWave > 0 && this.currentWave < this.data.getWaveCount()) {
                this.currentWave++;
                this.enemiesSpawnedThisWave = 0;
            }
        }
    }

    private void spawnEnemy() {
        float margin = 200f; // margen en píxeles desde los bordes

        float mapWidth = this.world.getMap().getMetaData().getMapPixelWidth();
        float mapHeight = this.world.getMap().getMetaData().getMapPixelHeight();

        // Calculamos posición aleatoria dentro del margen
        float randomX = margin + (float) (Math.random() * (mapWidth - 2 * margin));
        float randomY = margin + (float) (Math.random() * (mapHeight - 2 * margin));

        Entity enemy = this.enemyFactory.create(EnemyType.ELITE, randomX, randomY);

        this.activeEnemies.add(enemy.getComponent(HealthComponent.class));
        this.enemiesSpawnedThisWave++;

        this.bus.publish(new NewEntityEvent(enemy));
    }

    
    private void updateEnemiesDefeated(RemoveEntityEvent event) {
		this.activeEnemies.remove(event.entity.getComponent(HealthComponent.class));
    	this.enemiesDefeated++;
	}
    
    private void checkLevelCompletion() {
        if (this.activeEnemies.isEmpty() &&
            this.enemiesDefeated >= this.data.getEnemiesToDefeat() &&
            this.currentWave >= this.data.getWaveCount()) {

            this.levelCompleted = true;
            this.bus.publish(new LevelCompletedEvent());
        }
    }
    
    public boolean isLevelCompleted() {
		return this.levelCompleted;
	}
}
