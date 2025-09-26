package com.haloofwar.ui.components;

import com.haloofwar.engine.entity.Entity;
import com.haloofwar.engine.interfaces.Disposable;
import com.haloofwar.engine.interfaces.Renderable;

public interface HUDComponent extends Renderable, Disposable {
	void refresh(final Entity player);
}
