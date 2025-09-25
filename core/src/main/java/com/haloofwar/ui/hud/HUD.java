package com.haloofwar.ui.hud;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.common.enums.Direction;
import com.haloofwar.common.enums.GameState;
import com.haloofwar.common.enums.UIState;
import com.haloofwar.engine.cameras.GameStaticCamera;
import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.engine.events.GameStateEvent;
import com.haloofwar.engine.events.HideDialogueEvent;
import com.haloofwar.engine.events.NavigationEvent;
import com.haloofwar.engine.events.NewPlayerEvent;
import com.haloofwar.engine.events.SelectOptionEvent;
import com.haloofwar.engine.events.ShowDialogueEvent;
import com.haloofwar.engine.events.ToggleEquipmentEvent;
import com.haloofwar.engine.events.ToggleInventoryEvent;
import com.haloofwar.engine.events.ToggleShopEvent;
import com.haloofwar.game.components.EquipmentComponent;
import com.haloofwar.game.components.HealthComponent;
import com.haloofwar.game.components.InventoryComponent;
import com.haloofwar.game.components.NameComponent;
import com.haloofwar.game.components.PlayerComponent;
import com.haloofwar.ui.components.DialogueBox;
import com.haloofwar.ui.components.EquipmentPopup;
import com.haloofwar.ui.components.HealthBar;
import com.haloofwar.ui.components.InterfaceLevel;
import com.haloofwar.ui.components.InterfaceLobby;
import com.haloofwar.ui.components.InventoryPopup;
import com.haloofwar.ui.components.PlayerInfoRenderer;
import com.haloofwar.ui.components.ShopPopup;

public class HUD {
    private final GameStaticCamera camera;
    private final SpriteBatch batch;

    // Componentes opcionales
    private final HealthBar health;
    private final PlayerInfoRenderer info;
    private final DialogueBox dialogue;
    private final InventoryPopup inventoryPopup;
    private final ShopPopup shopPopup;
    private final EquipmentPopup equipmentPopup;
    private final InterfaceLobby lobby;
    private final InterfaceLevel level;
    private final EventBus gameplayBus;
    private UIState currentUI = UIState.NONE;

    private final Set<Class<?>> subscribedEvents = new HashSet<>();
    private final Consumer<ToggleInventoryEvent> toggleInventoryListener;
    private final Consumer<ToggleShopEvent> toggleShopListener;
    private final Consumer<ToggleEquipmentEvent> toggleEquipmentListener;
    private final Consumer<NavigationEvent> navigationListener;
    private final Consumer<SelectOptionEvent> selectOptionListener;
    
    private final float NAV_COOLDOWN = 0.15f; 
    private float navigateTimer = 0f;

    public HUD(
            GameStaticCamera camera,
            SpriteBatch batch,
            HealthBar health,
            PlayerInfoRenderer info,
            DialogueBox dialogue,
            InventoryPopup inventoryPopup,
            ShopPopup shopPopUp,
            EquipmentPopup equipmentPopup,
            InterfaceLobby lobby,
            InterfaceLevel level,
            EventBus bus
    ) {
        this.camera = camera;
        this.batch = batch;
        this.health = health;
        this.info = info;
        this.dialogue = dialogue;
        this.inventoryPopup = inventoryPopup;
        this.shopPopup = shopPopUp;
        this.equipmentPopup = equipmentPopup;
        this.gameplayBus = bus;
        this.lobby = lobby;
        this.level = level;
        // Suscribirse a eventos de diálogo
        if (bus != null && dialogue != null) {
            bus.subscribe(ShowDialogueEvent.class, this::onShowDialogue);
            bus.subscribe(HideDialogueEvent.class, this::onHideDialogue);
        }

        this.toggleInventoryListener = this::toggleInventory;
        this.toggleShopListener = this::toggleShop;
        this.toggleEquipmentListener = this::toggleEquipment;
        this.navigationListener = this::onNavigate;
        this.selectOptionListener = this::onSelectOption;
        
        if(this.shopPopup != null && this.inventoryPopup != null) {
        	this.subscribeEvents();
        }   
        
        this.gameplayBus.subscribe(NewPlayerEvent.class, this::onNewPlayer);
    }

    private void onNewPlayer(NewPlayerEvent event) {
    	if(event.player.hasComponent(PlayerComponent.class)) {
    		Entity player = event.player;
    		
    		if(this.health != null) {
    			this.health.health = player.getComponent(HealthComponent.class);
    		}
    		
    		if(this.info != null) {
    			this.info.health = player.getComponent(HealthComponent.class);
    			this.info.nameComponent = player.getComponent(NameComponent.class);
    			this.info.setPortrait(player.getComponent(PlayerComponent.class));
    			this.info.setWeapon(player.getComponent(EquipmentComponent.class));
    		}
    		
    		if(this.inventoryPopup != null) {
    			this.inventoryPopup.ENTITIES = player.getComponent(InventoryComponent.class).getObjects();
    		}
    	
    		if(this.equipmentPopup != null) {
    			this.equipmentPopup.ENTITIES = player.getComponent(EquipmentComponent.class).weaponInventory;
    		}
    	}
    }
    
    // --- EVENTOS DE DIÁLOGO ---
    private void onShowDialogue(ShowDialogueEvent event) {
        if (dialogue == null) return;
        if (event.getAvatar() != null) {
            dialogue.show(event.getText(), event.getAvatar());
        } else {
            dialogue.show(event.getText());
        }
    }

    private void onHideDialogue(HideDialogueEvent event) {
        if (dialogue != null) dialogue.hide();
    }

    // --- RENDER PRINCIPAL ---
    public void render(float delta) {
        this.update(delta);
        this.camera.update();

        if (this.health != null) health.render();

        if (this.batch != null) {
            this.batch.setProjectionMatrix(this.camera.getOrthographic().combined);
            this.batch.begin();
            if (info != null) info.render();
            if (dialogue != null) dialogue.render();
            if (inventoryPopup != null) inventoryPopup.render(batch, camera);
            if (shopPopup != null) shopPopup.render(batch, camera);
            if (lobby != null) lobby.render();
            if (level != null) level.render();
            if (equipmentPopup != null) equipmentPopup.render(batch, camera);
            this.batch.end();
        }
    }



    public void update(float delta) {   	
        this.navigateTimer += delta;
    }


    // --- NAVEGACIÓN ---
    private void onNavigate(NavigationEvent event) {
        if (navigateTimer < NAV_COOLDOWN) {
        	return; 
        }
        Direction dir = event.direction;
        if (this.inventoryPopup != null && this.inventoryPopup.isVisible()) {
            this.inventoryPopup.navigate(dir);
        } else if (this.shopPopup != null && this.shopPopup.isVisible()) {
            this.shopPopup.navigate(dir);
        } else if (this.equipmentPopup != null && this.equipmentPopup.isVisible()) {
        	this.equipmentPopup.navigate(dir);
        }

        navigateTimer = 0f; // reset timer
    }


    private void onSelectOption(SelectOptionEvent event) {
        if (event.isPressed) {
            if (this.shopPopup != null && this.shopPopup.isVisible()) {
                this.shopPopup.buyCurrent();
            }
            
            if(this.equipmentPopup != null && this.equipmentPopup.isVisible()) {
            	this.equipmentPopup.equipSelected();
            }
        }
    }

    // --- INVENTARIO ---
    private void toggleInventory(ToggleInventoryEvent event) {
    	if(this.inventoryPopup != null) {
            if (currentUI == UIState.SHOP || currentUI == UIState.EQUIPMENT) {
                return; // no abrir inventario si está el shop
            }

            boolean opening = !inventoryPopup.isVisible();
            this.inventoryPopup.setVisible(opening);
            this.currentUI = opening ? UIState.INVENTORY : UIState.NONE;
            this.gameplayBus.publish(new GameStateEvent(opening ? GameState.WAITING : GameState.PLAYING));
            
            if(opening) {
            	this.lobby.setVisible(false);
            } else {
            	this.lobby.setVisible(true);
            }
    	}
    }
    
    private void toggleEquipment(ToggleEquipmentEvent event) {
    	if(this.equipmentPopup != null) {
            if (currentUI == UIState.INVENTORY || currentUI == UIState.SHOP) {
                return; // no abrir inventario si está el shop
            }

            boolean opening = !this.equipmentPopup.isVisible();
            this.equipmentPopup.setVisible(opening);
            this.currentUI = opening ? UIState.EQUIPMENT : UIState.NONE;
            this.gameplayBus.publish(new GameStateEvent(opening ? GameState.WAITING : GameState.PLAYING));
    	
            if(opening) {
            	this.lobby.setVisible(false);
            } else {
            	this.lobby.setVisible(true);
            }
    	}
    }

    // --- SHOP ---
    private void toggleShop(ToggleShopEvent event) {
    	if(this.shopPopup != null) {
			  if (currentUI == UIState.EQUIPMENT || currentUI == UIState.INVENTORY) {
		            return; // no abrir shop si está el inventario
		        }
	
		        boolean opening = !shopPopup.isVisible();
		        this.shopPopup.setVisible(opening);
		        this.currentUI = opening ? UIState.SHOP : UIState.NONE;
		        this.gameplayBus.publish(new GameStateEvent(opening ? GameState.WAITING : GameState.PLAYING));
		
		        if(opening) {
	            	this.lobby.setVisible(false);
	            } else {
	            	this.lobby.setVisible(true);
	            }
    	}
    }

    // --- GETTERS ---
    public GameStaticCamera getCamera() {
        return this.camera;
    }

    public void resize(int width, int height) {
        if (camera != null) camera.resize(width, height);
    }

    public void dispose() {
        this.unsuscribeEvents();
    }

    // --- EVENTOS: SUBSCRIBE / UNSUBSCRIBE ---
    private void subscribeEvents() {
        if (!subscribedEvents.contains(ToggleInventoryEvent.class)) {
            this.gameplayBus.subscribe(ToggleInventoryEvent.class, toggleInventoryListener);
            subscribedEvents.add(ToggleInventoryEvent.class);
        }

        if (!subscribedEvents.contains(ToggleShopEvent.class)) {
            this.gameplayBus.subscribe(ToggleShopEvent.class, toggleShopListener);
            subscribedEvents.add(ToggleShopEvent.class);
        }
        
        if (!subscribedEvents.contains(ToggleEquipmentEvent.class)) {
            this.gameplayBus.subscribe(ToggleEquipmentEvent.class, this.toggleEquipmentListener);
            subscribedEvents.add(ToggleEquipmentEvent.class);
        }

        if (!subscribedEvents.contains(NavigationEvent.class)) {
            this.gameplayBus.subscribe(NavigationEvent.class, navigationListener);
            subscribedEvents.add(NavigationEvent.class);
        }

        if (!subscribedEvents.contains(SelectOptionEvent.class)) {
            this.gameplayBus.subscribe(SelectOptionEvent.class, selectOptionListener);
            subscribedEvents.add(SelectOptionEvent.class);
        }
    }

    private void unsuscribeEvents() {
        if (subscribedEvents.contains(ToggleInventoryEvent.class)) {
            this.gameplayBus.unsubscribe(ToggleInventoryEvent.class, toggleInventoryListener);
            subscribedEvents.remove(ToggleInventoryEvent.class);
        }

        if (subscribedEvents.contains(ToggleShopEvent.class)) {
            this.gameplayBus.unsubscribe(ToggleShopEvent.class, toggleShopListener);
            subscribedEvents.remove(ToggleShopEvent.class);
        }

        if (subscribedEvents.contains(NavigationEvent.class)) {
            this.gameplayBus.unsubscribe(NavigationEvent.class, navigationListener);
            subscribedEvents.remove(NavigationEvent.class);
        }

        if (subscribedEvents.contains(SelectOptionEvent.class)) {
            this.gameplayBus.unsubscribe(SelectOptionEvent.class, selectOptionListener);
            subscribedEvents.remove(SelectOptionEvent.class);
        }
    }
    
    public void reset() {
        if(this.shopPopup != null && this.inventoryPopup != null) {
        	this.subscribeEvents();
        }   
    }
}
