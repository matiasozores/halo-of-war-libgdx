package com.haloofwar.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.cameras.GameStaticCamera;
import com.haloofwar.events.EventBus;
import com.haloofwar.events.HideDialogueEvent;
import com.haloofwar.events.ShowDialogueEvent;
import com.haloofwar.ui.components.DialogueBox;
import com.haloofwar.ui.components.HealthBar;
import com.haloofwar.ui.components.InventoryRenderer;
import com.haloofwar.ui.components.PlayerInfoRenderer;

public class HUD {

    // Dependencias
    private final GameStaticCamera camera;
    private final SpriteBatch batch;

    // Componentes
    private final HealthBar health;
    private final PlayerInfoRenderer info;
    private final InventoryRenderer inventory;
    private final DialogueBox dialogue;

    public HUD(
            GameStaticCamera camera,
            SpriteBatch batch,
            HealthBar health,
            PlayerInfoRenderer info,
            InventoryRenderer inventory,
            DialogueBox dialogue,
            EventBus bus
    ) {
        this.camera = camera;
        this.batch = batch;
        this.health = health;
        this.info = info;
        this.inventory = inventory;
        this.dialogue = dialogue;

        // Suscribirse a eventos de diálogo
        bus.subscribe(ShowDialogueEvent.class, this::onShowDialogue);
        bus.subscribe(HideDialogueEvent.class, this::onHideDialogue);
    }

    private void onShowDialogue(ShowDialogueEvent event) {
    	if(event.getAvatar() != null) {
    		this.dialogue.show(event.getText(), event.getAvatar());
    	} else {
    		this.dialogue.show(event.getText());
    	}
    }

    private void onHideDialogue(HideDialogueEvent event) {
        this.dialogue.hide();
    }

    public void render() {
        this.camera.update();
        this.health.render();

        this.batch.setProjectionMatrix(this.camera.getOrthographic().combined);
        this.batch.begin();
        this.info.render();
        this.inventory.render();
        this.dialogue.render(); 
        this.batch.end();
    }

    public GameStaticCamera getCamera() {
        return this.camera;
    }

    public void resize(int width, int height) {
        this.camera.resize(width, height);
    }
}
