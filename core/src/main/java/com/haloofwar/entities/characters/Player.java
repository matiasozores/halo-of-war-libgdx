package com.haloofwar.entities.characters;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.entities.Entity;
import com.haloofwar.enumerators.PlayerType;
import com.haloofwar.ui.Crosshair;
import com.haloofwar.weapons.Weapon;

public class Player extends Entity {
    private final Crosshair crosshair;
    private final Weapon weapon;
    private final PlayerType type;

    public Player(
        String name,
        MovementComponent movement,
        AnimationComponent animation,
        Crosshair crosshair,
        Weapon weapon,
        PlayerType type
    ) {
        super(name, movement, animation);
        this.crosshair = crosshair;
        this.weapon = weapon;
        this.type = type;
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
    
    public float getX() {
    	return this.movement.getX();
    }
    
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
}
