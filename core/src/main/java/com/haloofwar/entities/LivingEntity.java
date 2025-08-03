package com.haloofwar.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.haloofwar.components.animations.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.dependences.collision.behaviors.LivingEntityCollisionBehavior;
import com.haloofwar.entities.components.EntitySoundHandler;
import com.haloofwar.entities.components.EntityStateHandler;
import com.haloofwar.interfaces.Collidable;
import com.haloofwar.interfaces.CollisionVisitor;
import com.haloofwar.interfaces.Updatable;

public abstract class LivingEntity extends Entity implements Updatable {
	private final int DEFAULT_HEALTH = 100;
	private final int DEFAULT_VELOCITY = 150;

	protected int health;
	protected float velocity;

	protected final MovementComponent movement;
	protected final AnimationComponent animation;

	protected final EntitySoundHandler sound;
	
	public LivingEntity(
		String name,
		MovementComponent movement,
		AnimationComponent animation,
		EntitySoundHandler sound,
		EntityStateHandler state
	) {
		super(name, 32, 32, state);
		this.movement = movement;
		this.animation = animation;

		this.health = DEFAULT_HEALTH;
		this.velocity = DEFAULT_VELOCITY;

		this.sound = sound;
		
		this.collisionBehavior = new LivingEntityCollisionBehavior();
	}

	@Override
	public void update(float delta) {
		if (!this.active) {
			return;
		}

		this.movement.update(delta, this.velocity);
		this.x = this.movement.getX();
		this.y = this.movement.getY();

		this.animation.update(delta, this.movement.getDirectionX(), this.movement.getDirectionY());
	}

	@Override
	public void render(SpriteBatch batch) {
		if (!this.active) {
			return;
		}

		this.animation.render(this.movement.getX(), this.movement.getY(), this.width, this.height, batch);
	}

	public void takeDamage(int amount) {
		this.health -= amount;
		this.sound.onDamage(this, amount);

		if (this.health <= 0) {
			this.health = 0;
			this.sound.onDeath(this);
			this.state.onDeath(this);
		}
	}

	public int getHealth() {
		return this.health;
	}
	
	public int getDEFAULT_HEALTH() {
		return this.DEFAULT_HEALTH;
	}

	public int getDEFAULT_VELOCITY() {
		return this.DEFAULT_VELOCITY;
	}

	public MovementComponent getMovement() {
		return this.movement;
	}

	@Override
	public void accept(CollisionVisitor visitor, Collidable self) {
		visitor.visit(this, self);
	}

}
