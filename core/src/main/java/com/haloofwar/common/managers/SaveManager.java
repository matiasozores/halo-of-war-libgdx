package com.haloofwar.common.managers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.haloofwar.common.enumerators.LevelSceneType;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.EventListenerManager;
import com.haloofwar.engine.events.LevelCompletedEvent;
import com.haloofwar.game.data.SaveGameData;

public class SaveManager {
	private final SaveGameData DATA;
	private final EventListenerManager listenerManager = new EventListenerManager();
	
	public SaveManager(final EventBus GAMEPLAY_BUS) {
		this.DATA = new SaveGameData();
		this.listenerManager.add(GAMEPLAY_BUS, LevelCompletedEvent.class, this::onLevelCompleted);
	}
	
	private void onLevelCompleted(LevelCompletedEvent event) {
	    final LevelSceneType completedLevel = event.getLevelType();
	    if (this.DATA.levelsCompleted.add(completedLevel)) {
	        this.saveToFile();
	    } 
	}
	
    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("savegame.dat"))) {
            out.writeObject(this.DATA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SaveGameData loadFromFile() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("savegame.dat"))) {
            return (SaveGameData) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No hay archivo de guardado, creando nuevo...");
            return new SaveGameData();
        }
    }
    
    public SaveGameData getDATA() {
		return this.DATA;
	}
    
    public void dispose() {
    	this.listenerManager.clear();
    }
}
