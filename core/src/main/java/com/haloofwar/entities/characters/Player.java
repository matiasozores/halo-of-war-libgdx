package com.haloofwar.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.LivingEntity;
import com.haloofwar.entities.characters.components.Inventory;
import com.haloofwar.entities.statics.Item;
import com.haloofwar.enumerators.entities.PlayerType;
import com.haloofwar.enumerators.entities.behavior.CollisionType;
import com.haloofwar.interfaces.Positionable;
import com.haloofwar.ui.Crosshair;
import com.haloofwar.weapons.Weapon;

public class Player extends LivingEntity implements Positionable {
    private final Crosshair crosshair;
    private final Weapon weapon;
    private final PlayerType type;
    private Inventory inventory;
    
    public Player(
        String name,
        MovementComponent movement,
        AnimationComponent animation,
        Crosshair crosshair,
        Weapon weapon,
        PlayerType type
    ) {
        super(name, movement, animation, CollisionType.ENTITY);
        this.crosshair = crosshair;
        this.weapon = weapon;
        this.type = type;
        this.inventory = new Inventory();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        this.crosshair.update();
        this.weapon.update(delta, this.movement.getX(), this.movement.getY(), this.getMouseX(), this.getMouseY());
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        this.crosshair.render(batch);
    }
    
    public void addItemToInventory(Item item) {
    	if(item == null) {
			System.out.println("Ha ocurrido un error al intentar añadir el item a la mochila: el item es nulo.");
			return;
    	}
    	
		this.inventory.add(item);
	}
    
    @Override
    public float getX() {
    	return this.movement.getX();
    }
    
    @Override
    public float getY() {
    	return this.movement.getY();
    }

    public float getMouseX() {
        return this.crosshair.getMouseX();
    }

    public float getMouseY() {
        return this.crosshair.getMouseY();
    }
    
    public PlayerType getType() {
		return this.type;
	}
    
    public Inventory getInventory() {
		return this.inventory;
	}	
}
