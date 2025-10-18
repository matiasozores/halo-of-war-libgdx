package com.haloofwar.game.components;

import com.haloofwar.common.enumerators.EnemyType;

public class EnemyComponent implements Component {
	public final EnemyType type;

	public EnemyComponent(EnemyType type) {
		this.type = type;
	}
}
