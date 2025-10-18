package com.haloofwar.game.systems.weapons;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.events.EventBus;
import com.haloofwar.game.components.Component;

public interface WeaponBehavior extends Component {
	void update(Entity owner, Entity weapon, float delta, EventBus bus);
}
