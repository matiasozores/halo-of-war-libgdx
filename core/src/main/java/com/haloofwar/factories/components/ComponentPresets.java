package com.haloofwar.factories.components;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.CollisionComponent;
import com.haloofwar.components.CrosshairComponent;
import com.haloofwar.components.DialogueComponent;
import com.haloofwar.components.HealthComponent;
import com.haloofwar.components.InventoryComponent;
import com.haloofwar.components.MovementComponent;
import com.haloofwar.components.NameComponent;
import com.haloofwar.components.ObstacleComponent;
import com.haloofwar.components.PlayerComponent;
import com.haloofwar.components.PlayerMovementController;
import com.haloofwar.components.PromptComponent;
import com.haloofwar.components.RenderComponent;
import com.haloofwar.components.StockComponent;
import com.haloofwar.components.TransformComponent;
import com.haloofwar.components.VillagerComponent;
import com.haloofwar.components.WeaponComponent;
import com.haloofwar.dependences.TextureManager;
import com.haloofwar.enumerators.PlayerType;
import com.haloofwar.events.EventBus;
import com.haloofwar.interfaces.AnimatedEntityDescriptor;
import com.haloofwar.interfaces.ArmedEntityDescriptor;
import com.haloofwar.interfaces.EntityDescriptor;
import com.haloofwar.interfaces.MovementController;
import com.haloofwar.interfaces.Talkable;

public class ComponentPresets {

	private static final int DEFAULT_HEALTH = 100;
	private static final float WIDTH = 32, HEIGHT = 32;
	private static final float DEFAULT_VELOCITY = 150;
	
	public static TransformComponent defaultTransform(float x, float y) {
		return new TransformComponent(x, y, WIDTH, HEIGHT);
	}
	
	public static TransformComponent defaultTransform(float x, float y, float width, float height) {
		return new TransformComponent(x, y, width, height);
	}
	
    public static AnimationComponent defaultAnimation(AnimatedEntityDescriptor descriptor, TextureManager textureManager) {
        return new AnimationComponent(descriptor, textureManager);
    }
    
    public static HealthComponent defaultHealth() {
        return new HealthComponent(DEFAULT_HEALTH);
    }

    public static CollisionComponent defaultCollision(float x, float y) {
        return new CollisionComponent(WIDTH, HEIGHT, x, y);
    }
    
    public static CollisionComponent defaultCollision(float x, float y, float width, float height) {
        return new CollisionComponent(width, height, x, y);
    }
    
    public static MovementComponent playerMovement(EventBus bus) {
    	MovementController controller = new PlayerMovementController(bus);
    	return new MovementComponent(controller, DEFAULT_VELOCITY);
    }
    
    public static CrosshairComponent defaultCrosshair(PlayerType type, TextureManager manager, GameWorldCamera camera) {
    	Texture texture = manager.get(type.getCrosshair());
    	return new CrosshairComponent(texture, camera);
    }
    
    public static NameComponent defaultName(EntityDescriptor type) {
    	return new NameComponent(type.getName());
    }
    
    public static InventoryComponent defaultInventory() {
    	return new InventoryComponent();
    }
    
    public static WeaponComponent defaultWeapon(ArmedEntityDescriptor type) {
    	// En algun momento cuando tengamos las armas definidas esto cambiara y se creara mediante una fabrica
        switch (type.getDefaultWeapon()) {
            case RIFLE_ASALTO:
                return new WeaponComponent(type.getName(), 10, 50, 60);
            case ESPADA:
                return new WeaponComponent(type.getName(), 25, 20, 60);
            default:
                return new WeaponComponent(type.getName(), 5, 10, 60);
        }
    }
    
    public static ObstacleComponent defaultObstacle() {
    	return new ObstacleComponent();
    }
    

    public static RenderComponent defaultRender(EntityDescriptor type, TextureManager manager) {
    	Texture texture = manager.get(type);
        return new RenderComponent(texture);
    }

    public static StockComponent defaultPickup() {
        return new StockComponent();
    }
    
    public static PlayerComponent defaultPlayer() {
    	return new PlayerComponent();
    }
    
    public static PromptComponent defaultPrompt(float offset) {
    	return new PromptComponent(offset);
    }
    
    public static VillagerComponent defaultVillager() {
    	return new VillagerComponent();
    }
    
    public static DialogueComponent defualtDialogue(Talkable type, Texture avatar) {
    	return new DialogueComponent(type.getLines(), avatar);
    }
}

