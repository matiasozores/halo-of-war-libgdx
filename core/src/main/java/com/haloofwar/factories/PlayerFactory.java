package com.haloofwar.factories;

import com.haloofwar.components.AnimationComponent;
import com.haloofwar.components.movement.MovementComponent;
import com.haloofwar.components.movement.PlayerMovementController;
import com.haloofwar.dependences.GameContext;
import com.haloofwar.entities.characters.Kratos;
import com.haloofwar.entities.characters.MasterChief;
import com.haloofwar.entities.characters.Player;
import com.haloofwar.enumerators.PlayerType;
import com.haloofwar.ui.Crosshair;
import com.haloofwar.weapons.Weapon;

public class PlayerFactory {
	private final GameContext context;
	
	public PlayerFactory(GameContext context) {
		this.context = context;
	}
	
	public Player create(PlayerType type) {
		MovementComponent movement = new MovementComponent(new PlayerMovementController(context.getInput()));
		AnimationComponent animation = new AnimationComponent(type, this.context.getTexture());
		
		final UICrosshairFactory CROSSHAIR_FACTORY = new UICrosshairFactory(this.context);
		Crosshair crosshair = CROSSHAIR_FACTORY.create(type);
		
		final WeaponFactory WEAPON_FACTORY = new WeaponFactory(this.context);
		Weapon weapon = WEAPON_FACTORY.create(type.getDefaultWeapon());	
		
		switch (type) {
		case KRATOS:
			return new Kratos(movement, animation, crosshair, weapon);
			
		case MASTER_CHIEF:
			return new MasterChief(movement, animation, crosshair, weapon);

		default:
			System.out.println("Ha ocurrido un error inesperado al seleccionar un personaje. ERROR 01");
			return new Kratos(movement, animation, crosshair, weapon);
		}
	}
}
