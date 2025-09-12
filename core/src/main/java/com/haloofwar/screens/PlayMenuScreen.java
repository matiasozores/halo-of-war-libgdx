package com.haloofwar.screens;

import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.components.Entity;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.components.InventoryComponent;
import com.haloofwar.components.StockComponent;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.enumerators.SoundType;
import com.haloofwar.factories.ObjectFactory;
import com.haloofwar.saves.GameSaveData;
import com.haloofwar.saves.PlayerData;
import com.haloofwar.saves.SaveManager;

public class PlayMenuScreen extends Menu {

    public PlayMenuScreen(GameContext gameContext, Menu previousMenu) {
        super(gameContext, "Jugar", new String[] {
                "Nueva Partida",
                "Cargar Partida",
                "Volver"
        }, previousMenu);
    }

    @Override
    protected void processOption(int optionIndex) {
        switch (optionIndex) {
            case 0:
                startNewGame();
                break;

            case 1:
                loadSavedGame();
                break;

            case 2:
                this.goBack();
                break;

            default:
                System.out.println("Opción inválida: " + optionIndex);
                break;
        }
    }

    private void startNewGame() {
        this.context.getGame().setScreen(new PlayerSelectionScreen(this.context, this));
    }

    private void loadSavedGame() {
        if (!SaveManager.hasSave()) {
            System.out.println("No hay partida guardada.");
            return;
        }

        // Cargamos todo el save
        GameSaveData save = SaveManager.loadGame();
        if (save == null || save.playerData == null) {
            System.out.println("Error al cargar la partida.");
            return;
        }

        PlayerData data = save.playerData;  // <- de acá sacás lo del jugador

        // Crear jugador usando la fábrica
        Entity player = this.context.getFactories()
                                    .getPLAYER_FACTORY()
                                    .create(data.type, 0, 0);

        // Aplicar HealthComponent
        HealthComponent h = player.getComponent(HealthComponent.class);
        h.setShield(data.shield);
        h.affectHealth(data.currentHealth - h.getCurrentHealth());

        // Inventario
        InventoryComponent inv = player.getComponent(InventoryComponent.class);
        for (PlayerData.ItemData itemData : data.inventory) {
            Rectangle rect = new Rectangle(0, 0, 16, 16);
            Entity item = ObjectFactory.createItem(rect, itemData.type, context.getTexture());
            item.getComponent(StockComponent.class).setStock(itemData.stock);
            inv.add(item);
        }

        // Inicializar jugador en Gameplay
        this.context.getGameplay().initializePlayer(player);

        // Sonido y cambio de pantalla
        this.context.getAudio().getSound().play(SoundType.LOAD_GAME);
        this.context.getAudio().getMusic().stop();
        this.context.getGame().setScreen(new GameManager(this.context, save.completedLevels));
    }
}
