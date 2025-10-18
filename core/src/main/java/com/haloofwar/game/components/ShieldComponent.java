package com.haloofwar.game.components;

public class ShieldComponent implements Component {
	public int shield;
	
	public ShieldComponent() {
		this.shield = 100;
	}

	public ShieldComponent(int shield) {
		this.shield = shield;
	}
}
