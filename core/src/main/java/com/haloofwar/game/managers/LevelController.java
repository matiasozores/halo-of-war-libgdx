package com.haloofwar.game.managers;

import java.util.ArrayList;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.NewEntityEvent;
import com.haloofwar.engine.events.RemoveEntityEvent;
import com.haloofwar.engine.events.SpawnEnemyEvent;
import com.haloofwar.game.components.EnemyComponent;
import com.haloofwar.game.components.HealthComponent;
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
        this.listenerManager.add(this.GAMEPLAY_BUS, SpawnEnemyEvent.class, this::spawnEnemy);
    }

    public void update(float delta) {
        if (this.CUTSCENE != null && !this.CUTSCENE.isFinished()) {
            this.CUTSCENE.update(delta); 
            return;
        }

        if (!this.initialSpawnDone) {
            this.initialWait(delta);
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


    private void spawnEnemy(SpawnEnemyEvent event) {
        Entity enemy = ENEMY_FACTORY.create(event.IDENTIFIER, event.type, event.x, event.y);
        activeEnemies.add(enemy.getComponent(HealthComponent.class));
        enemiesSpawnedThisWave++;
        GAMEPLAY_BUS.publish(new NewEntityEvent(enemy));
    }
    
    public void dispose() {
    	if(this.CUTSCENE != null) {
    		this.CUTSCENE.dispose();
    	}
    }

}
