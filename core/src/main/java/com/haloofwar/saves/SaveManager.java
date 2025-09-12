package com.haloofwar.saves;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import com.haloofwar.components.Entity;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.components.InventoryComponent;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.components.StockComponent;
import com.haloofwar.enumerators.LevelType;
import com.haloofwar.enumerators.ObjectType;

public class SaveManager {

    private static final String FILE_PATH = "game_save.dat";

    public static void saveGame(Entity player, Set<LevelType> completedLevels) {
        GameSaveData saveData = new GameSaveData();
        
        PlayerData playerData = new PlayerData();
        playerData.type = player.getComponent(PlayerComponent.class).type;

        HealthComponent health = player.getComponent(HealthComponent.class);
        playerData.currentHealth = health.getCurrentHealth();
        playerData.maxHealth = health.getMaxHealth();
        playerData.shield = health.getShield();

        InventoryComponent inv = player.getComponent(InventoryComponent.class);
        if (inv != null) {
            for (Entity item : inv.getObjects()) {
                StockComponent stockComp = item.getComponent(StockComponent.class);

                if (stockComp != null) {
                    ObjectType type = stockComp.getType();
                    int stock = stockComp.getStock();
                    playerData.inventory.add(new PlayerData.ItemData(type, stock));
                }
            }
        }

        saveData.playerData = playerData;
        saveData.completedLevels = completedLevels;

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(saveData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GameSaveData loadGame() {
        if (!hasSave()) {
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_PATH))) {
            return (GameSaveData) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean hasSave() {
        File f = new File(FILE_PATH);
        return f.exists();
    }
}
