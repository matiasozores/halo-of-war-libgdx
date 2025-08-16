package com.haloofwar.factories.components;

import com.badlogic.gdx.graphics.Texture;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.ecs.components.collision.CollisionComponent;
import com.haloofwar.ecs.components.collision.ObstacleComponent;
import com.haloofwar.ecs.components.collision.PickupComponent;
import com.haloofwar.ecs.components.collision.PlayerComponent;
import com.haloofwar.ecs.components.debug.ShapeComponent;
import com.haloofwar.ecs.components.gameplay.HealthComponent;
import com.haloofwar.ecs.components.gameplay.InventoryComponent;
import com.haloofwar.ecs.components.gameplay.NameComponent;
import com.haloofwar.ecs.components.gameplay.WeaponComponent;
import com.haloofwar.ecs.components.physics.MovementComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.components.physics.movement.PlayerMovementController;
import com.haloofwar.ecs.components.render.AnimationComponent;
import com.haloofwar.ecs.components.render.CrosshairComponent;
import com.haloofwar.ecs.components.render.RenderComponent;
import com.haloofwar.ecs.events.EventBus;
import com.haloofwar.enumerators.entities.PlayerType;
import com.haloofwar.enumerators.entities.objects.ObjectType;
import com.haloofwar.interfaces.entities.AnimatedEntityDescriptor;
import com.haloofwar.interfaces.entities.ArmedEntityDescriptor;
import com.haloofwar.interfaces.entities.EntityDescriptor;
import com.haloofwar.interfaces.entities.MovementController;

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
        return new CollisionComponent(WIDTH, HEIGHT);
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
    

    public static RenderComponent defaultRender(ObjectType type, TextureManager manager) {
    	Texture texture = manager.get(type);
        return new RenderComponent(texture);
    }

    public static PickupComponent defaultPickup() {
        return new PickupComponent();
    }
    
    public static PlayerComponent defaultPlayer() {
    	return new PlayerComponent();
    }
    
    public static ShapeComponent defaultShape(float x, float y, float width, float height) {
    	return new ShapeComponent(x, y, width, height);
    }
}

