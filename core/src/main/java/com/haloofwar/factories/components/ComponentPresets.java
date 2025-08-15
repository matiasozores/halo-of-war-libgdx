package com.haloofwar.factories.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.haloofwar.cameras.GameWorldCamera;
import com.haloofwar.components.movement.PlayerMovementController;
import com.haloofwar.dependences.assets.TextureManager;
import com.haloofwar.dependences.input.InputManager;
import com.haloofwar.ecs.components.collision.CollisionComponent;
import com.haloofwar.ecs.components.collision.ObstacleComponent;
import com.haloofwar.ecs.components.collision.PickupComponent;
import com.haloofwar.ecs.components.gameplay.HealthComponent;
import com.haloofwar.ecs.components.gameplay.InventoryComponent;
import com.haloofwar.ecs.components.gameplay.NameComponent;
import com.haloofwar.ecs.components.gameplay.ShooterComponent;
import com.haloofwar.ecs.components.gameplay.WeaponComponent;
import com.haloofwar.ecs.components.physics.MovementComponent;
import com.haloofwar.ecs.components.physics.TransformComponent;
import com.haloofwar.ecs.components.render.AnimationComponent;
import com.haloofwar.ecs.components.render.CrosshairComponent;
import com.haloofwar.ecs.components.render.RenderComponent;
import com.haloofwar.enumerators.entities.PlayerType;
import com.haloofwar.enumerators.entities.objects.ObjectType;
import com.haloofwar.interfaces.entities.AnimatedEntityDescriptor;
import com.haloofwar.interfaces.entities.ArmedEntityDescriptor;
import com.haloofwar.interfaces.entities.EntityDescriptor;
import com.haloofwar.interfaces.entities.MovementController;

public class ComponentPresets {

	private static final int DEFAULT_HEALTH = 100;
	private static final float WIDTH = 32, HEIGHT = 32;
	
	public static TransformComponent defaultTransform(float x, float y) {
		return new TransformComponent(x, y, WIDTH, HEIGHT);
	}
	
	public static TransformComponent defaultTransform(float x, float y, float width, float height) {
		return new TransformComponent(x, y, width, height);
	}
	
    /** Componente de animación predeterminado según el descriptor */
    public static AnimationComponent defaultAnimation(AnimatedEntityDescriptor descriptor, TextureManager textureManager) {
        return new AnimationComponent(descriptor, textureManager);
    }

    /** Componente de salud predeterminado */
    public static HealthComponent defaultHealth() {
        return new HealthComponent(DEFAULT_HEALTH);
    }

    /** Componente de colisión predeterminado basado en posición y tamaño */
    public static CollisionComponent defaultCollision(float x, float y) {
        return new CollisionComponent(new Rectangle(x, y, WIDTH, HEIGHT));
    }
    
    public static CollisionComponent defaultCollision(float x, float y, float width, float height) {
        return new CollisionComponent(new Rectangle(x, y, width, height));
    }
    
    public static MovementComponent playerMovement(InputManager input) {
    	MovementController controller = new PlayerMovementController(input);
    	return new MovementComponent(controller, 150);
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
        switch (type.getDefaultWeapon()) {
            case RIFLE_ASALTO:
                return new WeaponComponent(type.getName(), 10, 50, 60);
            case ESPADA:
                return new WeaponComponent(type.getName(), 25, 20, 60);
            default:
                return new WeaponComponent(type.getName(), 5, 10, 60);
        }
    }

    public static ShooterComponent defaultShooter() {
    	return new ShooterComponent();
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
}

