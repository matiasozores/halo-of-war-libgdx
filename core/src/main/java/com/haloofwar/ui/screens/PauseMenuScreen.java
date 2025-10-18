package com.haloofwar.ui.screens;

import com.haloofwar.common.context.GameContext;
import com.haloofwar.common.enumerators.Background;
import com.haloofwar.common.enumerators.GameState;
import com.haloofwar.common.enumerators.PlayerType;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.game.data.PlayerData;
import com.haloofwar.game.data.SaveGameData;
import com.haloofwar.game.managers.GameManager;
import com.haloofwar.ui.Menu;

public class PauseMenuScreen extends Menu {

    private final GameManager manager;
    private final EventBus gameplayBus;
    private final SaveGameData save;
    
    public PauseMenuScreen(final GameContext context, final GameManager manager) {
        super(context, "Menu Pausa", new String[] {
                "Reanudar", "Configuracion", "Guardar y volver al menu principal"
        }, null, Background.MAIN_MENU);
        this.gameplayBus = context.getGameplay().getBus();
        this.manager = manager;
        this.save = context.getSaveManager().getDATA();
    }

    @Override
    protected void processOption(int optionIndex) {
        switch(optionIndex) {
            case 0: 
                this.goBack();
                break;
            case 1: 
                this.context.getGAME().setScreen(new SettingsScreen(this.context, this));
                break;
            case 2: 
                this.saveData(this.context.getGameplay().getKratos());
                this.saveData(this.context.getGameplay().getMasterchief());
                this.context.getAudio().getMusic().stop();
                this.context.getAudio().getSound().stopAll();
                this.manager.dispose();
                this.context.resetGameplay();
                this.context.getGAME().setScreen(new MainMenuScreen(this.context));
                break;
        }
    }

    @Override
    protected void goBack() {
        this.gameplayBus.publish(new GameStateEvent(GameState.PLAYING));
    }
    
    private void saveData(Entity player) {
        if (player.hasComponent(PlayerComponent.class)) {
            final InventoryComponent inventory = player.getComponent(InventoryComponent.class);
            final EquipmentComponent equipment = player.getComponent(EquipmentComponent.class);
            final PlayerType type = player.getComponent(PlayerComponent.class).type;

            final PlayerData data = new PlayerData();
            
            data.inventory = inventory.toData();
            data.equipment = equipment.toData();
            data.type = type;
            
            this.save.saveData(data);
            this.context.getSaveManager().saveToFile();

        } else {
            System.out.println("No se puede realizar el guardado porque la entidad no es un Jugador... | PauseMenuScreen");
        }
    }
}