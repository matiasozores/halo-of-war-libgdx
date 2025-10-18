package com.haloofwar.game.managers;

import java.util.ArrayList;

import com.haloofwar.common.enumerators.EnemyType;
import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.GameOverEvent;
import com.haloofwar.engine.events.LevelCompletedEvent;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.events.UnlockLevelEvent;
import com.haloofwar.engine.events.online.SpawnEnemyEventOnline;
import com.haloofwar.engine.utils.RandomUtils;
import com.haloofwar.game.components.EnemyComponent;
import com.haloofwar.game.components.HealthComponent;
import com.haloofwar.game.components.TransformComponent;
import com.haloofwar.game.cutscenes.CutScene;
import com.haloofwar.game.dependences.LevelData;
import com.haloofwar.game.factories.EnemyFactory;
import com.haloofwar.game.world.World;
import com.haloofwar.ui.HUD;

public class LevelController {
    private final LevelData DATA;
    private final EnemyFactory ENEMY_FACTORY;
    private final EventBus GAMEPLAY_BUS;
    private final EventListenerManager listenerManager = new EventListenerManager();
    private final World WORLD;
    private final HUD HUD;
    private final CutScene CUTSCENE; 

    private boolean levelCompleted = false;
    private boolean isLocked = true;
    
    private float spawnTimer = 0f;
    private final float INITIAL_DELAY = 1f;
    private boolean initialSpawnDone = false;

    private int enemiesSpawnedThisWave = 0;
    
    private final ArrayList<HealthComponent> activeEnemies = new ArrayList<>();
    
    public LevelController(LevelData DATA, EnemyFactory ENEMY_FACTORY, EventBus GAMEPLAY_BUS, World WORLD, HUD HUD, CutScene CUTSCENE) {
        this.DATA = DATA;
        this.ENEMY_FACTORY = ENEMY_FACTORY;
        this.GAMEPLAY_BUS = GAMEPLAY_BUS;
        this.WORLD = WORLD;
        this.HUD = HUD;
        this.CUTSCENE = CUTSCENE;

        this.subscribeEvents();
    }
    
    private void subscribeEvents() {
        this.listenerManager.add(this.GAMEPLAY_BUS, RemoveEntityEvent.class, this::updateEnemiesDefeated);
        this.listenerManager.add(this.GAMEPLAY_BUS, GameOverEvent.class, this::onGameOver);
    }
    
    private void onGameOver(GameOverEvent event) {
    	this.listenerManager.clear();
    }

    public void update(float delta) {
        if (this.CUTSCENE != null && !this.CUTSCENE.isFinished()) {
            this.CUTSCENE.update(delta); 
            return;
        }

        if (!this.initialSpawnDone) {
            this.initialWait(delta);
        } else if (!this.levelCompleted) {
            this.handleSpawning(delta);
            this.checkLevelCompletion();
        }
    }

    public void render(float delta) {
        this.WORLD.render();
        this.HUD.render(delta);
        
        if (this.CUTSCENE != null && !this.CUTSCENE.isFinished()) {
            this.CUTSCENE.render(delta);
        }
    }

 

    private void initialWait(float delta) {
        spawnTimer += delta;
        if (spawnTimer >= INITIAL_DELAY) {
            initialSpawnDone = true;
            spawnTimer = 0f;
        }
    }

    private void updateEnemiesDefeated(RemoveEntityEvent event) {
        if (event.entity.hasComponent(EnemyComponent.class)) {
            activeEnemies.remove(event.entity.getComponent(HealthComponent.class));
            DATA.incrementEnemiesDefeated(); 
            if (activeEnemies.isEmpty() && enemiesSpawnedThisWave >= DATA.getEnemiesToDefeatPerWave()) {
                enemiesSpawnedThisWave = 0;
                DATA.incrementWave();
            }
        }
    }


    private void spawnEnemy() {
        float margin = 200f;
        float mapWidth = WORLD.getMap().getMetaData().getMapPixelWidth();
        float mapHeight = WORLD.getMap().getMetaData().getMapPixelHeight();

        float randomX = margin + (float) (Math.random() * (mapWidth - 2 * margin));
        float randomY = margin + (float) (Math.random() * (mapHeight - 2 * margin));

        Entity enemy = ENEMY_FACTORY.create(RandomUtils.generateUniqueId(), EnemyType.getEnemyByCollection(this.DATA.getType().getEnemies()), randomX, randomY);
        activeEnemies.add(enemy.getComponent(HealthComponent.class));
        enemiesSpawnedThisWave++;

        this.GAMEPLAY_BUS.publish(new NewEntityEvent(enemy));
        
        final EnemyComponent ec = enemy.getComponent(EnemyComponent.class);
        final TransformComponent tc = enemy.getComponent(TransformComponent.class);
        
        this.GAMEPLAY_BUS.publish(new SpawnEnemyEventOnline(tc.identifier, ec.type, tc.x, tc.y));
    }

    private void checkLevelCompletion() {
        if (DATA.getType() == LevelSceneType.INFINITY) {
        	return;
        } else {
            if (activeEnemies.isEmpty() && DATA.allEnemiesDefeated()) {
                levelCompleted = true;
                this.listenerManager.clear();
                GAMEPLAY_BUS.publish(new LevelCompletedEvent(DATA.getType()));
            	this.GAMEPLAY_BUS.publish(new UnlockLevelEvent(this.DATA.getType().getNextLevel()));
            }
        }   
    }

    private void handleSpawning(float delta) {
        spawnTimer += delta;

        if (spawnTimer >= DATA.getEnemySpawnRate()) {
            spawnTimer = 0f;

            if (activeEnemies.size() < DATA.getMaxEnemies() &&
                enemiesSpawnedThisWave < DATA.getEnemiesToDefeatPerWave()) {
                spawnEnemy();
            }
        }
    }
    
    public void dispose() {
    	if(this.CUTSCENE != null) {
    		this.CUTSCENE.dispose();
    	}
    }
    
    public boolean isLocked() {
    	return this.isLocked;
    }
    
    public void reset() {
        // 1️⃣ Reiniciar el estado interno del LevelController
        this.levelCompleted = false;
        this.spawnTimer = 0f;
        this.initialSpawnDone = false;
        this.enemiesSpawnedThisWave = 0;
        this.activeEnemies.clear();
        
        // 2️⃣ Reiniciar los datos del nivel (enemigos derrotados, oleadas, etc.)
        this.DATA.reset();

        // 3️⃣ Volver a suscribirse a los eventos (por si se habían limpiado)
        this.listenerManager.clear();
        this.subscribeEvents();

        // 5️⃣ Si hay una cinemática, reiniciarla
        if (this.CUTSCENE != null) {
            this.CUTSCENE.reset();
        }

        // 6️⃣ Reiniciar HUD si aplica
        if (this.HUD != null) {
            this.HUD.reset();
        }

        System.out.println("[LevelController] Nivel reiniciado correctamente.");
    }


}
