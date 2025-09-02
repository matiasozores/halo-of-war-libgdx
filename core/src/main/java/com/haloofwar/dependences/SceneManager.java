package com.haloofwar.dependences;

import java.util.HashMap;
import java.util.Map;

import com.haloofwar.enumerators.SceneType;
import com.haloofwar.factories.SceneFactory;
import com.haloofwar.game.GameScene;

public class SceneManager {
	private Map<SceneType, GameScene> scenes = new HashMap<>();
	private SceneFactory factory;
	
	public SceneManager(SceneFactory factory) {
		this.factory = factory;
	}
	
    public void load(SceneType sceneType) {
    	if(this.scenes.containsKey(sceneType)) {
			return;
		}
    	
    	GameScene scene = this.factory.create(sceneType);
    	this.scenes.put(sceneType, scene);
    }
    
    public GameScene get(SceneType sceneType) {
    	if(!this.scenes.containsKey(sceneType)) {
			this.load(sceneType);
		}
    	
		return this.scenes.get(sceneType);
	}
}
	
